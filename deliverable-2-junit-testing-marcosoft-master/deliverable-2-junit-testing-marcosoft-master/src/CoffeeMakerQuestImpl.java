import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	// TODO: Add more member variables and methods as needed.
	private Player player;
	private boolean flag;
	private ArrayList<Room> roomList;
	private Room currentIn;

	CoffeeMakerQuestImpl() {
		// TODO
		flag = false;
		roomList = new ArrayList<Room>();
		currentIn = null;
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean isGameOver() {
		// TODO
		return flag;
	}

	/**
	 * Set the player to p.
	 * 
	 * @param p the player
	 */
	public void setPlayer(Player p) {
		// TODO
		player = p;
	}
	
	/**
	 * Add the first room in the game. If room is null or if this not the first room
	 * (there are pre-exiting rooms), the room is not added and false is returned.
	 * 
	 * @param room the room to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFirstRoom(Room room) {
		// TODO
		if (room == null || roomList.size() != 0) {
			return false;
		}
		roomList.add(room);
		return true;
	}

	/**
	 * Attach room to the northern-most room. If either room, northDoor, or
	 * southDoor are null, the room is not added. If there are no pre-exiting rooms,
	 * the room is not added. If room is not a unique room (a pre-exiting room has
	 * the same adjective or furnishing), the room is not added. If all these tests
	 * pass, the room is added. Also, the north door of the northern-most room is
	 * labeled northDoor and the south door of the added room is labeled southDoor.
	 * Of course, the north door of the new room is still null because there is
	 * no room to the north of the new room.
	 * 
	 * @param room      the room to add
	 * @param northDoor string to label the north door of the current northern-most room
	 * @param southDoor string to label the south door of the newly added room
	 * @return true if successful, false otherwise
	 */
	public boolean addRoomAtNorth(Room room, String northDoor, String southDoor) {
		// TODO
		if (room == null || northDoor == null || southDoor == null) {
			return false;
		}
		for(Room i:roomList) {
			if (i.getFurnishing().equals(room.getFurnishing()) || i.getAdjective().equals(room.getAdjective())) {
				return false;
			}
		}
		roomList.get(roomList.size() - 1).setNorthDoor(northDoor);
		roomList.add(room);
		roomList.get(roomList.size() - 1).setSouthDoor(southDoor);
		return true;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */ 
	public Room getCurrentRoom() {
		// TODO
		return currentIn;
	}
	
	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO
		if (roomList.contains(room)){
			currentIn = room;
			return true;
		}
		return false;
	}
	
	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		// TODO
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
	}
	
	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
     * sure you use Player.getInventoryString() whenever you need to display
     * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO
		String result = "";
		cmd = cmd.toLowerCase();
		switch (cmd){
			case "n":
				if (roomList.indexOf(currentIn) < roomList.size() - 1) {
					currentIn = roomList.get(roomList.indexOf(currentIn) + 1);
//					return getInstructionsString() + currentIn.getDescription();
				} else {
					result += "A door in that direction does not exist.\n";
				}
				break;
			case "s":
				if (roomList.indexOf(currentIn) > 0) {
					currentIn = roomList.get(roomList.indexOf(currentIn) - 1);
//					return getInstructionsString() + currentIn.getDescription();
				} else {
					result += "A door in that direction does not exist.\n";
				}
				break;
			case "l":
				if (currentIn.getItem() != Item.NONE) {
					String temp = "";
					player.addItem(currentIn.getItem());
					switch (currentIn.getItem()) {
						case CREAM:
							temp = "creamy cream";
							break;
						case COFFEE:
							temp = "caffeinated coffee";
							break;
						case SUGAR:
							temp = "sweet sugar";
							break;
					}
					result += "There might be something here...\n" +
							"You found some " + temp + "!\n";
				} else {
					result += "You don't see anything out of the ordinary.\n";
				}
				break;
			case "i":
				result += player.getInventoryString();
				break;
			case "d":
				flag = true;
				result += player.getInventoryString();
				result += "\n";
				result += endingHelper(player.checkCoffee(), player.checkCream(), player.checkSugar());
				break;
			case "h":
				result += "N - Go north\n" +
						"S - Go south\n" +
						"L - Look and collect any items in the room\n" +
						"I - Show inventory of items collected\n" +
						"D - Drink coffee made from items in inventory\n";
				break;
		}

		return result;
	}

	private String endingHelper(boolean isCoffee, boolean isCream, boolean isSugar) {
		String result = "";
		if (isCoffee && isCream && isSugar) {
			result += "You drink the beverage and are ready to study!\n" +
					"You win!\n";
		} else if (!isCoffee && isCream && isSugar) {
			result += "You drink the sweetened cream, but without caffeine you cannot study.\n" +
					"You lose!\n";
		} else if (isCoffee && !isCream && isSugar) {
			result += "Without cream, you get an ulcer and cannot study.\n" +
					"You lose!\n";
		} else if (isCoffee && isCream && !isSugar) {
			result += "Without sugar, the coffee is too bitter. You cannot study.\n" +
					"You lose!\n";
		} else if (!isCoffee && !isCream && isSugar) {
			result += "You eat the sugar, but without caffeine, you cannot study.\n" +
					"You lose!\n";
		} else if (!isCoffee && isCream && !isSugar) {
			result += "You drink the cream, but without caffeine, you cannot study.\n" +
					"You lose!\n";
		} else if (isCoffee && !isCream && !isSugar) {
			result += "Without cream, you get an ulcer and cannot study.\n" +
					"You lose!\n";
		} else if (!isCoffee && !isCream && !isSugar) {
			result += "You drink the air, as you have no coffee, sugar, or cream.\n" +
					"The air is invigorating, but not invigorating enough. You cannot study.\n" +
					"You lose!\n";
		}
		return result;
	}
	
}
