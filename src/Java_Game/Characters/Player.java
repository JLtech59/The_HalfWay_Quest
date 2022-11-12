package Java_Game.Characters;

import Java_Game.Utilities.Races;
import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public class Player extends Character {
    private Integer rank = 1;
    private Boolean isDead = false;
    private Integer gold = 100;

    public Player(String name, String adjective, Weapon playerWeapon) {
        super(name, adjective, 100, playerWeapon);
        this.race = Races.Hobbit; //Race par défaut

    }


    public void setWeapon(Weapon weapon) { // Récupération de l'arme
        this.weapon = weapon;
    }

    public void setRank(Integer number) {
        this.rank = number;
    }

    public Boolean getDeathState() {
        return this.isDead;
    }

    public void setDeathState(Boolean state) {
        this.isDead = state;
    }

    public int getRank() {
        return this.rank;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(Integer number) {
        this.gold = number;
    }


    @Override
    public void information() {
        System.out.println("You are " + getName() + " the " + getAdjective() + ", your rank is incredibly high : " + getRank() + ", " + "your hps are " + getHp() + " hp.\nYou have : " + getGold() + " golds. You are a " + getRace() + "\nYour weapon is : ");
        weapon.getInfo();
    }

    @Override
    public void introduce() {
        System.out.println("(You) " + getName() + " the " + getAdjective() + " : Hello I'm a " + getRace() + " I have " + getHp() + " hp. I have " + getGold() + " Golds. My weapon is a " + getWeapon().getName());

    }
}

