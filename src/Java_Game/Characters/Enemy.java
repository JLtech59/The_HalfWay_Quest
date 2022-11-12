package Java_Game.Characters;



import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public class Enemy extends Pnj implements CanFight {


    public Enemy(String name, String adjective, Integer hp, Weapon weapon) { // Création du constructeur de la classe PnjBad
        super(name, adjective, hp, weapon);
    }

    @Override
    public Integer randomTrustFactor() {
        return 0;
    }


    @Override
    public void information() { // Affichage des information du méchant PNJ
        System.out.println("\n\nName : " + name + "\nAdjective : " + adjective + "\nRace : " + race + "\nHP : " + hp + "\nWeapon : ");
        weapon.getInfo();
    }

    @Override
    public void introduce() {
        System.out.println(name + " : Don't move adventurer ! My name is " + name + " " + adjective + " and I'm going to kill you with my " + weapon.getName());
    }


    @Override
    public void appliedDamageOnPlayer(Player player) {
        System.out.println("You missed... You take a hit from " + getName());
        player.setHp(player.getHp() - getWeapon().getDmg());
        System.out.println("You have " + player.getHp() + " Hp");
    }

    @Override
    public void appliedDamageOnEnemy(Player player) {
        System.out.println("You hit " + getName());
        setHp(getHp() - player.getWeapon().getDmg());
        System.out.println(getName() + " has " + getHp() + " HP");
    }


}