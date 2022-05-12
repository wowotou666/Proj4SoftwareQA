import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import org.mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	Room room1;	// Small room
	Room room2;	// Funny room
	Room room3;	// Refinanced room
	Room room4;	// Dumb room
	Room room5;	// Bloodthirsty room
	Room room6;	// Rough room

	@Before
	public void setup() {
		// 0. Turn on bug injection for Player and Room.
		Config.setBuggyPlayer(true);
		Config.setBuggyRoom(true);
		
		// 1. Create the Coffee Maker Quest object and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance();

		// TODO: 2. Create a mock Player and assign to player and call cmq.setPlayer(player). 
		// Player should not have any items (no coffee, no cream, no sugar)
		player = Mockito.mock(Player.class);
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		cmq.setPlayer(player);
		// TODO: 3. Create mock Rooms and assign to room1, room2, ..., room6.
		// Mimic the furnishings / adjectives / items of the rooms in the original Coffee Maker Quest.
		room1 = Mockito.mock(Room.class);	// Small room
		Mockito.when(room1.getFurnishing()).thenReturn("Quaint sofa");
		Mockito.when(room1.getAdjective()).thenReturn("Small");
		Mockito.when(room1.getItem()).thenReturn(Item.CREAM);
		Mockito.when(room1.getDescription()).thenReturn("You see a Small room.\nIt has a Quaint sofa.\n" +
				"A Magenta door leads North.\n");

		room2 = Mockito.mock(Room.class);	// Funny room
		Mockito.when(room2.getFurnishing()).thenReturn("Sad record player");
		Mockito.when(room2.getAdjective()).thenReturn("Funny");
		Mockito.when(room2.getItem()).thenReturn(Item.NONE);
		Mockito.when(room2.getDescription()).thenReturn("You see a Funny room.\nIt has a Sad record player.\n" +
				"A Beige door leads North.\nA Massive door leads South.\n");

		room3 = Mockito.mock(Room.class);	// Refinanced room
		Mockito.when(room3.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(room3.getAdjective()).thenReturn("Refinanced");
		Mockito.when(room3.getItem()).thenReturn(Item.COFFEE);
		Mockito.when(room3.getDescription()).thenReturn("You see a Refinanced room.\nIt has a Tight pizza.\n" +
				"A Dead door leads North.\nA Smart door leads South.\n");

		room4 = Mockito.mock(Room.class);	// Dumb room
		Mockito.when(room4.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(room4.getAdjective()).thenReturn("Dumb");
		Mockito.when(room4.getItem()).thenReturn(Item.NONE);
		Mockito.when(room4.getDescription()).thenReturn("You see a Dumb room.\nIt has a Flat energy drink.\n" +
				"A Vivacious door leads North.\nA Slim door leads South.");

		room5 = Mockito.mock(Room.class);	// Bloodthirsty room
		Mockito.when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
		Mockito.when(room5.getAdjective()).thenReturn("Bloodthirsty");
		Mockito.when(room5.getItem()).thenReturn(Item.NONE);
		Mockito.when(room5.getDescription()).thenReturn("You see a Bloodthirsty room.\nIt has a Beautiful bag of money.\n" +
				"A Purple door leads North.\nA Sandy door leads South.");

		room6 = Mockito.mock(Room.class);	// Rough room
		Mockito.when(room6.getFurnishing()).thenReturn("Perfect air hockey table");
		Mockito.when(room6.getAdjective()).thenReturn("Rough");
		Mockito.when(room6.getItem()).thenReturn(Item.SUGAR);
		Mockito.when(room6.getDescription()).thenReturn("You see a Small room.\nIt has a Perfect air hockey table.\n" +
				"A Minimalist door leads South.\n");

		// TODO: 4. Add the rooms created above to mimic the layout of the original Coffee Maker Quest.
		cmq.addFirstRoom(room1);
		cmq.addRoomAtNorth(room2, "Magenta", "Massive");
		cmq.addRoomAtNorth(room3, "Beige", "Smart");
		cmq.addRoomAtNorth(room4, "Dead", "Slim");
		cmq.addRoomAtNorth(room5, "Vivacious", "Sandy");
		cmq.addRoomAtNorth(room6, "Purple", "Minimalist");
	}

	@After
	public void tearDown() {
		cmq = null;
		player = null;
		room1 = null;
		room2 = null;
		room3 = null;
		room4 = null;
		room5 = null;
		room6 = null;
	}
	
	/**
	 * Test case for String getInstructionsString().
	 * Preconditions: None.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 */
	@Test
	public void testGetInstructionsString() {
		// TODO
		assertEquals(" INSTRUCTIONS (N,S,L,I,D,H) > ", cmq.getInstructionsString());
	}
	
	/**
	 * Test case for boolean addFirstRoom(Room room).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock room and assign to myRoom.
	 * Execution steps: Call cmq.addFirstRoom(myRoom).
	 * Postconditions: Return value is false.
	 */
	@Test
	public void testAddFirstRoom() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		assertFalse(cmq.addFirstRoom(myRoom));
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Fake bed" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is true.
	 *                 room6.setNorthDoor("North") is called.
	 *                 myRoom.setSouthDoor("South") is called.
	 */
	@Test
	public void testAddRoomAtNorthUnique() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Fake bed");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		Mockito.when(myRoom.getDescription()).thenReturn("You see a Fake room.\nIt has a Fake bed.\n" +
				"A South door leads South.\n");

		assertTrue(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6, Mockito.times(1)).setNorthDoor("North");
		Mockito.verify(myRoom, Mockito.times(1)).setSouthDoor("South");

	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Flat energy drink" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is false.
	 *                 room6.setNorthDoor("North") is not called.
	 *                 myRoom.setSouthDoor("South") is not called.
	 */
	@Test
	public void testAddRoomAtNorthDuplicate() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		Mockito.when(myRoom.getDescription()).thenReturn("You see a Fake room.\nIt has a Flat energy drink.\n" +
				"A South door leads South.\n");
		cmq.addRoomAtNorth(myRoom, "North", "South");

		assertFalse(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6, Mockito.never()).setNorthDoor("North");
		Mockito.verify(myRoom, Mockito.never()).setSouthDoor("South");
	}
	
	/**
	 * Test case for Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room) has not yet been called.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is null.
	 */
	@Test
	public void testGetCurrentRoom() {
		// TODO
		assertNull(cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room room) has not yet been called.
	 * Execution steps: Call cmq.setCurrentRoom(room3).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(room3) is true. 
	 *                 Return value of cmq.getCurrentRoom() is room3.
	 */
	@Test
	public void testSetCurrentRoom() {
		// TODO
		assertTrue(cmq.setCurrentRoom(room3));
		assertEquals(room3, cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for String processCommand("I").
	 * Preconditions: Player does not have any items.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 */
	@Test
	public void testProcessCommandI() {
		// TODO
		assertEquals("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n", cmq.processCommand("I"));
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 player.addItem(Item.CREAM) is called.
	 */
	@Test
	public void testProcessCommandLCream() {
		// TODO
		cmq.setCurrentRoom(room1);
		assertEquals("There might be something here...\nYou found some creamy cream!\n", cmq.processCommand("l"));
		Mockito.verify(player, Mockito.times(1)).addItem(Item.CREAM);
	}
	
	/**
	 * Test case for String processCommand("n").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room4) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is room5.
	 */
	@Test
	public void testProcessCommandN() {
		// TODO
		cmq.setCurrentRoom(room4);
		assertEquals("", cmq.processCommand("n"));
		assertEquals(room5, cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for String processCommand("s").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is room1.
	 */
	@Test
	public void testProcessCommandS() {
		// TODO
		cmq.setCurrentRoom(room1);
		assertEquals("A door in that direction does not exist.\n", cmq.processCommand("s"));
		assertEquals(room1, cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has no items.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDLose() {
		// TODO
		assertEquals("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n", cmq.processCommand("D"));
		assertTrue(cmq.isGameOver());
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDWin() {
		// TODO
		Mockito.when(player.checkCoffee()).thenReturn(true);
		Mockito.when(player.checkCream()).thenReturn(true);
		Mockito.when(player.checkSugar()).thenReturn(true);
		Mockito.when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");

		assertEquals("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n", cmq.processCommand("D"));
		assertTrue(cmq.isGameOver());
	}
	
	// TODO: Put in more unit tests of your own making to improve coverage!
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room3) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some caffeinated coffee!\n".
	 *                 player.addItem(Item.COFFEE) is called.
	 */
	@Test
	public void testProcessCommandLCoffee() {
		// TODO
		cmq.setCurrentRoom(room3);
		assertEquals("There might be something here...\nYou found some caffeinated coffee!\n", cmq.processCommand("l"));
		Mockito.verify(player, Mockito.times(1)).addItem(Item.COFFEE);
	}

	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room6) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some sweet sugar!\n".
	 *                 player.addItem(Item.CREAM) is called.
	 */
	@Test
	public void testProcessCommandLSugar() {
		// TODO
		cmq.setCurrentRoom(room6);
		assertEquals("There might be something here...\nYou found some sweet sugar!\n", cmq.processCommand("l"));
		Mockito.verify(player, Mockito.times(1)).addItem(Item.SUGAR);
	}

	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room2) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "You don't see anything out of the ordinary.\n".
	 *                 player.addItem(Item.NONE) is not called.
	 */
	@Test
	public void testProcessCommandLNothing() {
		// TODO
		cmq.setCurrentRoom(room2);
		assertEquals("You don't see anything out of the ordinary.\n", cmq.processCommand("l"));
		Mockito.verify(player, Mockito.never()).addItem(Item.NONE);
	}

	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 * 				  Create a mock "Fake" room with "Fake bed" furnishing with no item, and assign to myRoom.
	 *                cmq.setCurrentRoom(Room room) has not yet been called.
	 * Execution steps: Call cmq.setCurrentRoom(myRoom).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(room3) is false.
	 *                 Return value of cmq.getCurrentRoom() is null.
	 */
	@Test
	public void testSetCurrentRoomDoesNotExist() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		Mockito.when(myRoom.getFurnishing()).thenReturn("Fake bed");
		Mockito.when(myRoom.getAdjective()).thenReturn("Fake");
		Mockito.when(myRoom.getItem()).thenReturn(Item.NONE);
		Mockito.when(myRoom.getDescription()).thenReturn("You see a Fake room.\nIt has a Fake bed.\n" +
				"A South door leads South.\n");
		assertFalse(cmq.setCurrentRoom(myRoom));
		assertNull(cmq.getCurrentRoom());
	}

	/**
	 * Test case for String processCommand("H").
	 * Preconditions: NULL
	 * Execution steps: Call cmq.processCommand("H").
	 * Postconditions: Return value is "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n".
	 */
	@Test
	public void testProcessCommandH() {
		// TODO
		assertEquals("N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n", cmq.processCommand("H"));
	}

	/**
	 * Test case for String endingHelper(true, false, true).
	 * Preconditions: Player only has coffee and sugar.
	 * Execution steps: Call endingHelper(true, false, true).
	 * Postconditions: Return value of endingHelper(true, false, true) is "Without cream, you get an ulcer and cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperCoffeeSugar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), true, false, true);
		assertEquals(ret, "Without cream, you get an ulcer and cannot study.\nYou lose!\n");
	}

	/**
	 * Test case for String endingHelper(false, true, true).
	 * Preconditions: Player only has cream and sugar.
	 * Execution steps: Call endingHelper(false, true, true).
	 * Postconditions: Return value of endingHelper(false, true, true) is "You drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperCreamSugar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), false, true, true);
		assertEquals(ret, "You drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n");
	}

	/**
	 * Test case for String endingHelper(true, true, false).
	 * Preconditions: Player only has coffee and cream.
	 * Execution steps: Call endingHelper(true, true, false).
	 * Postconditions: Return value of endingHelper(true, true, false) is "Without sugar, the coffee is too bitter. You cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperCoffeeCream() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), true, true, false);
		assertEquals(ret, "Without sugar, the coffee is too bitter. You cannot study.\nYou lose!\n");
	}

	/**
	 * Test case for String endingHelper(false, false, true).
	 * Preconditions: Player only has sugar.
	 * Execution steps: Call endingHelper(false, false, true).
	 * Postconditions: Return value of endingHelper(false, false, true) is "You eat the sugar, but without caffeine, you cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperSugar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), false, false, true);
		assertEquals(ret, "You eat the sugar, but without caffeine, you cannot study.\nYou lose!\n");
	}

	/**
	 * Test case for String endingHelper(false, true, false).
	 * Preconditions: Player only has cream.
	 * Execution steps: Call endingHelper(false, true, false).
	 * Postconditions: Return value of endingHelper(false, true, false) is "You drink the cream, but without caffeine, you cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperCream() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), false, true, false);
		assertEquals(ret, "You drink the cream, but without caffeine, you cannot study.\nYou lose!\n");
	}

	/**
	 * Test case for String endingHelper(true, false, false).
	 * Preconditions: Player only has coffee.
	 * Execution steps: Call endingHelper(true, false, false).
	 * Postconditions: Return value of endingHelper(true, false, false) is "Without cream, you get an ulcer and cannot study.\nYou lose!\n".
	 */
	@Test
	public void testPrivateEndinghelperCoffee() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO
		Method m = CoffeeMakerQuestImpl.class.getDeclaredMethod("endingHelper", boolean.class, boolean.class, boolean.class);
		m.setAccessible(true);
		Object ret = m.invoke(new CoffeeMakerQuestImpl(), true, false, false);
		assertEquals(ret, "Without cream, you get an ulcer and cannot study.\nYou lose!\n");
	}
}
