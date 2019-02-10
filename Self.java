import java.util.List;
import java.util.ArrayList;

public class Self {

	private int hp; // Can be 1-10
	private List<Item> inventory;

	// 2 attributes: 4 points

	public Self() {
		hp = 10;
		inventory = new ArrayList<Item>();
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public List<String> getNames() {
		List<String> result = new ArrayList<String>();
		for (Item item : inventory) {
			result.add(item.getName());
		}
		return result;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void removeItem(Item item) {
		inventory.remove(item);
	}
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	// 6 methods, but i'll just devide them by two since that's what i've been mostly doing: 12 points
}
// TOTAL: 16 points