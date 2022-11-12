
package Java_Game.Characters;


import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public class Charlatan extends Trader implements CanFight {

    public Charlatan(String name, String adjective, Integer hp, Weapon weapon) { // Création du constructeur de la classe PNJ
        super(name, adjective, hp, weapon);
        setTrustFactor(randomTrustFactor());
    }
/**@return une note entre 0 et 7 */
    @Override
    public Integer randomTrustFactor() {
        return (int) (Math.random() * 7);
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

