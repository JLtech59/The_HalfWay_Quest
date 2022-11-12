package Java_Game.Characters;

import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public class Trader extends Pnj {

    private Weapon firstWeapon;
    private Weapon secondWeapon;

    public Trader(String name, String adjective, Integer hp, Weapon weapon) { // Création du constructeur de la classe PnjTrader
        super(name, adjective, hp, weapon);
        setTrustFactor(randomTrustFactor());
    }
    /**Le facteur de confiance est situé entre 3 et 10*/
    @Override
    public Integer randomTrustFactor() {
        return (int) ((Math.random() * 7) + 3);
    }
    /**@return la première offre du PNJ*/
    public Weapon getFirstWeapon() { // Récupération de l'arme
        return firstWeapon;
    }
    /**@return la seconde offre du PNJ*/
    public Weapon getSecondWeapon() { // Récupération de l'arme
        return secondWeapon;
    }

    public void setOffer(Weapon w1, Weapon w2) {
        this.firstWeapon = w1;
        this.secondWeapon = w2;
    }

    @Override
    public void information() { // Affichage des information du PNJ marchant
        System.out.println("\n\nName : " + getName() + "\nAdjective : " + getAdjective() + "\nRace : " + getRace() + "\nFun fact : " + funFact + "\nTrust Factor : " + getTrustFactor() + "/10");
    }

    @Override
    public void introduce() {
        System.out.println(getName() + " : Hello adventurer, my name is " + getName() + " " + getAdjective() + " and fun fact on me :  " + funFact);
    }
    /**Dialogue du PNJ pour présenter l'offre*/
    public void offer() {
        System.out.println("\n\n\t" + getName() + " : Today I have a :");
        firstWeapon.getInfo();
        System.out.println("\tand a : ");
        secondWeapon.getInfo();
        System.out.println("or a 20hp potion for 20 golds");
    }
    /**Fonction qui permet le paiement*/
    public void takePlayerMoney(Player player, Integer price) {
        player.setGold(player.getGold() - price);

    }
}
