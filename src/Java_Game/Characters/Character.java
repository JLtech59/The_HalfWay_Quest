
package Java_Game.Characters;


import Java_Game.Utilities.Races;
import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public abstract class Character {

    protected String name; // Nom du personnage
    protected String adjective; // Adjectif lié au personnage
    protected Races race; // Race du personnage
    protected Integer hp; // Nombre de point de vie du personnage

    protected Weapon weapon;

    public Character(String name, String adjective, Integer hp, Weapon weapon) {
        this.name = name;
        this.adjective = adjective;
        this.race = Races.randomRace();//Race forcément aléatoire lors de la création du personnage
        this.hp = hp;
        this.weapon = weapon;
    }

    public String getName() { // Récupération du nom
        return name;
    }

    public String getAdjective() { // Récupération de l'adjectif
        return adjective;
    }

    public Races getRace() { // Récupération de la race
        return race;
    }

    public Integer getHp() { // Récupération du nombre de point de vie
        return hp;
    }

    public void setHp(Integer hp) { // Modification du nombre de point de vie
        this.hp = hp;
    }

    public Weapon getWeapon() { // Récupération de l'arme
        return weapon;
    }

    /** Affichage des information du personnage*/
    protected abstract void information();
    /** Présentation du personnage sous forme de dialogue*/
    protected abstract void introduce();

}
