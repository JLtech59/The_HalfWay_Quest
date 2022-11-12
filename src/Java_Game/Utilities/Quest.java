package Java_Game.Utilities;

import Java_Game.Characters.Player;

/**
 * @author Jean-Louis
 */
public class Quest {
    private final String text;
    private final Integer gain;
    private final Integer loss;
    private final String finalText;

    public Quest(String name, Integer gain, Integer loss, String finalText) {
        this.text = name;
        this.gain = gain;
        this.loss = loss;
        this.finalText = finalText;
    }
/**Fonction qui applique sur le joueur les dégats ou le gain de rank */
    public void appliedPoints(Player player) {
        player.setRank(player.getRank() + gain);
        player.setHp(player.getHp() + loss);
        System.out.println("\tYou earned " + gain + " of rank");
        System.out.println("\tYou lost " + loss + " hp");
        System.out.println(finalText);

    }

    @Override
    public String toString() {
        return "Would you like to " + this.text;
    }
}
