package Java_Game.Characters;



import java.util.Random;

/**
 * @author Jean-Louis & Corentin
 */
/**Cette interface est destinée pour les PNJs effectuant des dégats sur le joueur*/
public interface CanFight {
    //Fonction pour renvoyer les points de vie du PNJ avec une gaussienne en fonction du rang du joueur
    static Integer randomHp(Integer playerRank) {

        Random random = new Random();
        double gauss = random.nextGaussian();
        int hp;
        hp = Math.abs((int) Math.round(playerRank * gauss * 9)) + 5;

        return hp;
    }
    /**Fonction qui convertit le prix de l'arme du PNJ en monnaie pour le joueur*/
    static void deathReward(Player player, Integer price) {
        player.setGold(player.getGold() + price);
        System.out.println("You earn " + price + " gold");
    }
    /**Fonction qui applique les dégats de l'arme du PNJ sur les HP du joueur*/
    void appliedDamageOnPlayer(Player player);
    /**Fonction qui applique les dégats de l'arme du joueur sur les HP du PNJ*/
    void appliedDamageOnEnemy(Player player);


}
