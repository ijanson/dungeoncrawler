import java.util.List;
import java.util.ArrayList;

public class Room {
	private boolean active;
	private boolean conquered;
	private boolean boss;
	private List<Item> items;
	private Enemy enemy;

	// 5 attributes: 10 points

	public Room() {
		items = new ArrayList<Item>();
		active = false;
		conquered = false;
		boss = false;
	}
	public void addEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public void killEnemy() {
		enemy = null;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void clrItems() {
		items.removeAll(items);
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setActive(boolean b) {
		active = b;
	}

	public boolean getActive() {
		return active;
	}

	public boolean getConquered() {
		return conquered;
	}

	public boolean getBoss() {
		return boss;
	}

	public void setConquered(boolean conquered) {
		this.conquered = conquered;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public String toString(boolean cheat) { // true = cheats false = player
		if (conquered || cheat) {
			if (active) {
				return "P";
			}
			else if (boss) {
				return "O";
			} else {
				return "#";
			}
		} else {
			return "-";
		}
	}
	// 6 pairs of getters and setters + tostring method: 28 points
}
// TOTAL: 38 points