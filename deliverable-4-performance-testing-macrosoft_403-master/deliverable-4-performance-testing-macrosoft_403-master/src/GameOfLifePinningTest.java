import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameOfLifePinningTest {
	/*
	 * READ ME: You may need to write pinning tests for methods from multiple
	 * classes, if you decide to refactor methods from multiple classes.
	 * 
	 * In general, a pinning test doesn't necessarily have to be a unit test; it can
	 * be an end-to-end test that spans multiple classes that you slap on quickly
	 * for the purposes of refactoring. The end-to-end pinning test is gradually
	 * refined into more high quality unit tests. Sometimes this is necessary
	 * because writing unit tests itself requires refactoring to make the code more
	 * testable (e.g. dependency injection), and you need a temporary end-to-end
	 * pinning test to protect the code base meanwhile.
	 * 
	 * For this deliverable, there is no reason you cannot write unit tests for
	 * pinning tests as the dependency injection(s) has already been done for you.
	 * You are required to localize each pinning unit test within the tested class
	 * as we did for Deliverable 2 (meaning it should not exercise any code from
	 * external classes). You will have to use Mockito mock objects to achieve this.
	 * 
	 * Also, you may have to use behavior verification instead of state verification
	 * to test some methods because the state change happens within a mocked
	 * external object. Remember that you can use behavior verification only on
	 * mocked objects (technically, you can use Mockito.verify on real objects too
	 * using something called a Spy, but you wouldn't need to go to that length for
	 * this deliverable).
	 */

	/* TODO: Declare all variables required for the test fixture. */
	Cell cell;
	MainPanel mainPanel;
	int size;
	Cell[][] cells;

	@Before
	public void setUp() {
		/*
		 * TODO: initialize the text fixture. For the initial pattern, use the "blinker"
		 * pattern shown in:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns
		 * The actual pattern GIF is at:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_blinker.gif
		 * Start from the vertical bar on a 5X5 matrix as shown in the GIF.
		 */
		size = 5;
		cells = new Cell[size][size];
		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				cells[j][k] = Mockito.mock(Cell.class);
				Mockito.when(cells[j][k].getAlive()).thenReturn(false);
			}
		}
		mainPanel = new MainPanel(cells);
		Mockito.when(cells[2][1].getAlive()).thenReturn(true);
		Mockito.when(cells[2][2].getAlive()).thenReturn(true);
		Mockito.when(cells[2][3].getAlive()).thenReturn(true);
	}

	@After
	public void teardown() {
		cell = null;
		mainPanel = null;
		cells = null;
	}

	/**
	 * Test case for String cell.toString().
	 * Preconditions: create an alive cell.
	 * Execution steps: Call cell.toString().
	 * Postconditions: Return value is "X".
	 */
	@Test
	public void testAliveCellToString() {
		cell = new Cell();
		cell.setAlive(true);
		assertEquals("X", cell.toString());
	}

	/**
	 * Test case for String cell.toString().
	 * Preconditions: create a dead cell.
	 * Execution steps: Call cell.toString().
	 * Postconditions: Return value is ".".
	 */
	@Test
	public void testDeadCellToString() {
		cell = new Cell();
		cell.setAlive(false);
		assertEquals(".", cell.toString());
	}

	/**
	 * Test case for Boolean iterateCell().
	 * Preconditions: mock 25 cells then put them in an 5x5 array. The cell [2][1] [2][2] [2][3] are alive.
	 * 				other cells are all dead.
	 * 				create a mainpanel with the cells array.
	 * Execution steps: Call mainPanel.iterateCell(2, 2).
	 * 				the cells [1][1] [1][2] [1][3] [2][1] [2][2] [2][3] [3][1] [3][2] [3][3] `s getAlive() are called.
	 * Postconditions: Return value is true.
	 */
	@Test
	public void testMainPanelIterateCell() {
		Boolean result = mainPanel.iterateCell(2, 2);
		for (int j = 1; j < size - 1; j++) {
			for (int k = 1; k < size - 1; k++) {
				Mockito.verify(cells[j][k]).getAlive();
			}
		}
		assertEquals(true, result);
	}

	/**
	 * Test case for void calculateNextIteration().
	 * Preconditions: mock 25 cells then put them in an 5x5 array. The cell [2][1] [2][2] [2][3] are alive.
	 * 				other cells are all dead.
	 * 				create a mainpanel with the cells array.
	 * Execution steps: Call mainPanel.calculateNextIteration().
	 * 				the cells [1][2] [2][2] [3][2] `s setAlive(true) are called.
	 * 				other cells`s setAlive(false) are all dead.
	 * Postconditions: None.
	 */
	@Test
	public void testMainPanelCalculateNextIteration() {
		mainPanel.calculateNextIteration();
		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				Mockito.verify(cells[j][k]).setAlive((j == 1 && k == 2) || (j == 2 && k == 2) || (j == 3 && k == 2));
			}
		}
	}
	/* TODO: Write the three pinning unit tests for the three optimized methods */

}
