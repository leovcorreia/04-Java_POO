package entities;

public class Champion {
	
	private String name;
	private int life, attack, armor;
	
	public Champion(String name, int life, int attack, int armor) {
		this.name = name;
		this.life = life;
		this.attack = attack;
		this.armor = armor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLife() { // Não temos setLife
		return life;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public void takeDamage(Champion otherChamp) {
		if (this.armor >= otherChamp.attack) {
			this.life -= 1;
		} else {
			this.life -= otherChamp.attack + this.armor;
		}
	}
	
	public String status() {
		if (this.life == 0) {
			return "teste2";
			//return this.name + ": " + this.life + " de vida (morreu)";
		} else {
			return "teste1";
			// return this.name + ": " + this.life + " de vida";
		}
	}

}
