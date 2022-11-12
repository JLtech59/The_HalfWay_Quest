package Java_Game.Utilities;

import Java_Game.Characters.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameGenerator {
/**Créé une partie qui ne se finit que si le joueur atteint le rang 11 ou s'il meurt
 * Le score sera enregistré*/
    public void Play() {
        String name = "Default";
        String adj = "";
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n\t\t||| Welcome on halfway earth ! |||");
        System.out.print("What's your character's name : ");
        try {
            name = input.next();
        } catch (Exception e) {
            System.out.println("Please Mr.Morelle, stop");
        }
        System.out.print("Describe your character in 1 word : ");
        Scanner input2 = new Scanner(System.in);
        try {
            adj = input2.next();
        } catch (Exception e) {
            System.out.println("Please Mr.Morelle, stop");
        }

        Weapon playerWeapon = new Weapon("Wood Stick", 2, 0);
        Player player = new Player(name, adj, playerWeapon);
        player.introduce();
        ActionManager am = new ActionManager();
        int maxRank = 10;
        while (player.getRank() <= maxRank && !player.getDeathState()) {
            selectAction(player, am);
            if (player.getRank() < 1) {
                player.setDeathState(true);
            }
            if (player.getHp() <= 0) {
                player.setDeathState(true);
            }
        }
        ArrayList<String> score = new ArrayList<>();
        score.add("PlayerName : " + player.getName());
        score.add("PlayerAdjective : " + player.getAdjective());
        score.add("PlayerRank : " + player.getRank());
        score.add("PlayerMoney : " + player.getGold());
        score.add("PlayerHP : " + player.getHp());
        score.add("PlayerQuestsDone : " + am.getTotalActions() + "\n");

        if (player.getDeathState()) {
            System.out.println("\t\t/ / / / THE END / / / / \nYou are a big loser !");
            System.out.println("You survived " + am.getTotalActions() + " quests");

        } else {
            System.out.println("\t\t! ! ! ! ! ! ! / / / / THE END / / / / ! ! ! ! ! ! !");
            System.out.println("You won in " + am.getTotalActions() + " quests !");
        }
        scoreSaving(score);
    }
    /**Fonction qui sauvegarde les données de la partie
     * Elle n'écrase pas les données existantes
     * Création d'un nouveau fichier .txt si besoin*/
    private void scoreSaving(ArrayList<String> arrayList) {
        try {
            File f = new File("scores.txt");
            if (!f.exists()) { //création d'un fichier s'il n'existe pas
                f.createNewFile();
            }
            //Cela permet de ne pas écraser le contenu juste d'ajouter les données
            PrintWriter pw = new PrintWriter(new FileWriter(f.getAbsoluteFile(), true));
            BufferedWriter bw = new BufferedWriter(pw);

            for (String s : arrayList) {
                bw.write(s + System.getProperty("line.separator"));
            }
            //Fermeture à ne pas oublier
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
/**Fonction qui propose propose toutes les actions possibles au joueur
 * Si le rang est trop faible il avance forcément dans les quêtes
 * Sinon il peut aller voir un marchand ou avoir des informations
 * S'il continue la quête il peut tomber sur un énnemi*/
    private void selectAction(Player player, ActionManager am) {
        if (player.getRank() <= 3) {
            am.getQuests(player.getRank(), player);
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n\t\t | | NEW PROPOSITIONS | |\nSelect an action : ");
        System.out.print("Enter (1) to continue your quest or (2) to trade with a merchant or (3) to get infos : ");
        int select;
        try {
            select = input.nextInt();
            switch (select) {
                case 1 -> {
                    if (Math.random() > 0.4) {
                        am.getQuests(player.getRank(), player);
                    } else {
                        Combat fight = new Combat();
                        fight.createCombat(player);
                    }

                }
                case 2 -> {
                    Trade trade = new Trade();
                    trade.createTrade(player);
                }
                case 3 -> player.information();

            }
        } catch (Exception e) {
            System.out.print("Error " + e);
        }


    }
}
