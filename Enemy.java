public class Enemy {
	private int hp;
	private int dmg;
	// 2 attributes: 4 points
	public Enemy(int hp, int dmg) {
		this.hp = hp;
		this.dmg = dmg;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getHp() {
		return hp;
	}

	public int getDmg() {
		return dmg;
	}
	// 2 pairs of getters and setters: 8 points
}
// TOTAL: 12 Points