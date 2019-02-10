public class Item {
	private String name;
	private int health; // heal for food, attack for weapons
	private int durability; 
	private int type; //1 for weapon 2 for food 3 is unbreakable (like fists) 4 is armor upgrades

	// 4 attributes: 8 points

	public Item(String name, int health, int durability, int type) {
		this.health = health;
		this.name = name;
		this.durability = durability;
		this.type = type;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	// 4 pairs of getters and setters: 16 points
}
// TOTAL: 24 points