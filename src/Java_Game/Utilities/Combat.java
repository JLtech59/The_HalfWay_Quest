package Java_Game.Utilities;


import Java_Game.Characters.CanFight;
import Java_Game.Characters.Enemy;
import Java_Game.Characters.Player;

import java.util.Scanner;


/**
 * @author Jean-Louis & Corentin
 */
public class Combat implements GetDataFromJSON {

    private Boolean win;
    //permet de connaître l'état du combat
    private void setFightState(Boolean state) {
        win = state;
    }

    public Boolean getFightState() {
        return win;
    }
/** Cette fonction permet de générer un combat entre un player et un ennemi aléatoire avec des caractéristiques basées sur le rang du joueur */
    public void createCombat(Player player) {


        Enemy enemy = new Enemy(GetDataFromJSON.createName(),
                GetDataFromJSON.createAdjective(), CanFight.randomHp(player.getRank()),
                GetDataFromJSON.createWeapon(player.getRank()));
        System.out.println("\n\t\t/ / / / FIGHT / / / / \n");
        System.out.println("You encounter an enemy : ");
        enemy.introduce();
        player.introduce();
        System.out.println("You have to fight ");
        win = false;
        while (!getFightState()) {

            fightActions(player, enemy);
        }
        if (player.getHp() <= 0) {
            System.out.println("OUCH you lost the fight");
        } else {
            System.out.println("You (" + player.getName() + ") : Good bye " + enemy.getName());
        }


    }
//Créer les possibilités pendant les combats avec des probabilités de réussite
    private void fightActions(Player player, Enemy enemy) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\tSelect an option : ");
        System.out.print("Enter (1) to attack or (2) to flee or (3) to get infos on your enemy : ");
        int select;
        try {
            select = input.nextInt();
            switch (select) {
                case 1 -> {
                    if (Math.random() > 0.4) {
                        enemy.appliedDamageOnEnemy(player);
                    } else {
                        enemy.appliedDamageOnPlayer(player);
                    }
                }

                case 2 -> {
                    if (Math.random() > 0.7) {
                        System.out.println("You didn't achieve to flee " + enemy.getName());
                        enemy.appliedDamageOnPlayer(player);
                    } else {
                        System.out.println("You fled the fight");
                        setFightState(true);
                    }
                }
                case 3 -> enemy.information();
                default -> System.out.println(enemy.getName() + " : WHat ArE YuO DoiNg ?!?");
            }
            if (enemy.getHp() <= 0) {
                setFightState(true);
                CanFight.deathReward(player, enemy.getWeapon().getPrice());

            }
            if (player.getHp() <= 0) {
                setFightState(true);
                player.setDeathState(true);
            }

        } catch (Exception e) {
            System.out.println("Error during fight action : "+e);
        }

    }


}
