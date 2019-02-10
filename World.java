import java.util.Random;
import java.awt.Point;

public class World {
	private Room map[][];
	private Random random;
	private Point active;
	// 3 attributes: 6 points

	public World() {
		map = new Room[8][9];
		random = new Random();
		active = new Point(4, 7);
	}

	public void generate() {
		map[0][4] = new Room(); // final boss
		map[0][4].addEnemy(new Enemy(50, 5));
		map[1][4] = new Room();
		map[1][5] = new Room();
		map[1][6] = new Room();
		map[2][0] = new Room(); // reg boss
		map[2][0].addEnemy(new Enemy(10, 2));
		map[2][0].addItem(new Item("Cooked Turkey", 10, 10, 2));
		map[2][1] = new Room();
		map[2][2] = new Room();
		map[2][3] = new Room();
		map[2][4] = new Room();
		map[2][6] = new Room();
		map[2][7] = new Room();
		map[2][8] = new Room(); // reg boss
		map[2][8].addEnemy(new Enemy(10, 2));
		map[2][8].addItem(new Item("Armor Upgrade", 20, -1, 4));
		map[3][4] = new Room();
		map[4][0] = new Room(); // reg boss
		map[4][0].addEnemy(new Enemy(10, 2));
		map[4][0].addItem(new Item("Armor Upgrade", 10, -1, 4));
		map[4][1] = new Room();
		map[4][4] = new Room();
		map[4][5] = new Room();
		map[4][6] = new Room();
		map[5][1] = new Room();
		map[5][2] = new Room();
		map[5][4] = new Room();
		map[5][6] = new Room();
		map[6][2] = new Room();
		map[6][3] = new Room();
		map[6][4] = new Room();
		map[6][6] = new Room();
		map[6][7] = new Room();
		map[6][8] = new Room(); // reg boss
		map[6][8].addEnemy(new Enemy(10, 2));
		map[6][8].addItem(new Item("Demon Sword", 5, 1000, 1));
		map[7][4] = new Room();
		map[7][4].setActive(true);
		map[7][4].setConquered(true);
		map[7][4].addItem(new Item("Rusty Sword", 2, 50, 1));

		// 6 unique room objects: 50 points

		for (int row = 0; row < 7; row++) { //randomize excluding room 1
        	for (int col = 0; col < 9; col++) {
        	    if ((row == 0 || col == 0 || col == 8) && map[row][col] != null) {
        	    	map[row][col].setBoss(true);
        	    	// add bosses
        	    } else if (map[row][col] != null && random.nextInt(10) > 3) {
        	    	// 60 percent chance for monster
        	    	map[row][col].addEnemy(new Enemy(random.nextInt(5) + 1, random.nextInt(2)+ 1)); // monsters can be 1-5 health and 1-2 damage
        	    } else if (map[row][col] != null) {
        	    	// food if monster not present
        	    	map[row][col].addItem(new Item("Rotten Food", 4, 1, 2));
        	    } // randomness: 5 points
        	}
    	}
	}

	public String toString(boolean cheat) {
		String result = "";
		for (Room[] row : map) {
			for (Room column : row) {
				if (column == null) {
					result += "-";
				} else {
					result += column.toString(cheat);
				}
			}
			result += "\n";
		}
		return result;
	}

	public int[] getAdjacentRooms() { //rooms around active in format [north, east, south, west]
		int[] result = new int[4];
		if (active.x !=  8 && map[active.y][active.x + 1] != null) {
			result[1] = 1;
		}

		if (active.y != 7 && map[active.y + 1][active.x] != null) {
			result[2] = 1;
		}

		if (active.x != 0 && map[active.y][active.x - 1] != null) {
			result[3] = 1;
		}

		if (active.y != 0 && map[active.y - 1][active.x] != null) {
			result[0] = 1;
		}
		return result;
	}

	public boolean north() {
		if (getAdjacentRooms()[0] == 1) {
			getActiveRoom().setActive(false);
			active.translate(0, -1);
			getActiveRoom().setActive(true);
			getActiveRoom().setConquered(true);
			return true;
		}
		return false;
	}

	public boolean east() {
		if (getAdjacentRooms()[1] == 1) {
			getActiveRoom().setActive(false);
			active.translate(1, 0);
			getActiveRoom().setActive(true);
			getActiveRoom().setConquered(true);
			return true;
		}
		return false;
	}

	public boolean south() {
		if (getAdjacentRooms()[2] == 1) {
			getActiveRoom().setActive(false);
			active.translate(0, 1);
			getActiveRoom().setActive(true);
			getActiveRoom().setConquered(true);
			return true;
		}
		return false;
	}

	public boolean west() {
		if (getAdjacentRooms()[3] == 1) {
			getActiveRoom().setActive(false);
			active.translate(-1, 0);
			getActiveRoom().setActive(true);
			getActiveRoom().setConquered(true);
			return true;
		}
		return false;
	}

	public int getX() {
		return active.x;
	}

	public int getY() {
		return active.y;
	}

	public boolean bossesConquered() {
		if (map[4][0].getConquered() && map[2][0].getConquered() && map[2][8].getConquered() && map[6][8].getConquered()) {
			return true;
		}
		return false;
	}

	public Room getActiveRoom() {
		return map[active.y][active.x];
	}
	// 11 methods: 44 points
}
// TOTAL: 99 points