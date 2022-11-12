package Java_Game.Utilities;

/**
 * @author Jean-Louis & Corentin
 */

public class Weapon {
    private final String name; // Nom de l'arme
    private final int dmg; // Dégats de l'arme
    private final int price; // Prix de l'arme
    /**constructeur*/
    public Weapon(String name, int dmg, int price) {
        this.name = name;
        this.dmg = dmg;
        this.price = price;
    }

    public String getName() { // Récupération du nom
        return name;
    }

    public Integer getDmg() { // Récupération des dégats
        return dmg;
    }

    public Integer getPrice() { // Récupération du prix
        return price;
    }
    /** Affichage des informations*/
    public void getInfo() {
        System.out.println("Name : " + name + "\nDamage : " + dmg + "\nPrice : " + price + " gold");
    }


}
