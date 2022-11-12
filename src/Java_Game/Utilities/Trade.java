package Java_Game.Utilities;

import Java_Game.Characters.CanFight;
import Java_Game.Characters.Player;
import Java_Game.Characters.Charlatan;
import Java_Game.Characters.Trader;

import java.util.Scanner;

/**
 * @author Jean-Louis
 */
public class Trade implements GetDataFromJSON {

/**Bool permettant de connaitre l'etat de la vente*/
    private Boolean end;

    private void setTradeState(Boolean state) {
        end = state;
    }

    public Boolean getTradeState() {
        return end;
    }
/**Création d'un charlatan*/
    private Charlatan fakeTrader(Integer playerRank) {
        Charlatan badTrader = new Charlatan(GetDataFromJSON.createName(),
                GetDataFromJSON.createAdjective(), 100, GetDataFromJSON.createWeapon(playerRank));

        return badTrader;
    }
    /**Création d'un vendeur*/
    private Trader trueTrader(Integer playerRank) {
        Trader trueTrader = new Trader(GetDataFromJSON.createName(),
                GetDataFromJSON.createAdjective(), CanFight.randomHp(playerRank), GetDataFromJSON.createWeapon(playerRank));
        return trueTrader;
    }
/**Générateur d'un système de vente avec une probabilité de tomber sur un charlatan*/
    public void createTrade(Player player) {
        System.out.println("\n\n\t\t/ / / / TRADE / / / / \n");
        System.out.println("You arrive at a stand : ");
        Weapon firstOffer = GetDataFromJSON.createWeapon(player.getRank());
        Weapon secondOffer = GetDataFromJSON.createWeapon(player.getRank());
        if (Math.random() > 0.3) {
            Trader merchant = trueTrader(player.getRank());
            merchant.setOffer(firstOffer, secondOffer);
            realOffer(player, merchant);
            System.out.println(merchant.getName() + " : Thank you bye !");

        } else {
            Charlatan charlatan = fakeTrader(player.getRank());
            charlatan.setOffer(firstOffer, secondOffer);
            fakeOffer(player, charlatan);
        }
        System.out.println("You leave this place");
    }
/**Lorsqu'on tombe sur un charlatan de nouvelles propositions sont annoncées au joueur
 * Le joueur n'a pas d'informations sur les HP et les dégats de l'arme du charlatan
 * Si le joueur décide de se battre il peut récuperer son argent + celui de l'arme du PNJ s'il tue le charlatan
 * */
    private void negociation(Player player, Charlatan charlatan) {
        System.out.println("Oh " + charlatan.getName() + " is a charlatan, he stole your money");
        Scanner input = new Scanner(System.in);
        while(!getTradeState()) {


            System.out.println("\n\n\tSelect an option : ");
            System.out.print("(1) to fight the charlatan and take back your money\n(2) to leave\n(3) to get infos about the charlatan\n");
            int select;
            try {
                select = input.nextInt();
                switch (select) {
                    case 1 -> {
                        if (Math.random() > 0.4) {
                            charlatan.appliedDamageOnEnemy(player);

                            if (charlatan.getHp() <= 0) {
                                setTradeState(true);
                                CanFight.deathReward(player, charlatan.getWeapon().getPrice() +
                                        charlatan.getFirstWeapon().getPrice() +
                                        charlatan.getSecondWeapon().getPrice());

                            } else {
                                System.out.println("The charlatan flees the fight, you didn't get back your money");
                            }

                        } else {
                            charlatan.appliedDamageOnPlayer(player);
                            if (player.getHp() <= 0) {
                                setTradeState(true);
                                System.out.println(charlatan.getName() + " : Hahaha loser !");
                                player.setDeathState(true);
                            }
                        }
                        setTradeState(true);
                    }
                    case 2 -> {
                        System.out.println(charlatan.getName() + " : Hahaha loser, go away !");
                        setTradeState(true);

                    }
                    case 3 -> {
                        charlatan.information();
                    }
                    default -> System.out.println(charlatan.getName() + " : WHat ArE YuO DoiNg ?!?");
                }
            } catch (Exception e) {
                System.out.print("Error negociation : e");
            }
        }
    }
/**
 * Création d'une offre frauduleuse
 * Le joueur s'il ne se méfie pas perdra son argent et une nouvelle étape lui sera proposé*/
    private void fakeOffer(Player player, Charlatan charlatan) {
        charlatan.introduce();
        player.introduce();
        charlatan.offer();
        setTradeState(false);
        Scanner input = new Scanner(System.in);
        while (!getTradeState()) {
            System.out.println("\n\n\tSelect an option : ");
            System.out.print("(1) to buy first weapon\n(2) to buy second weapon \n(3) to buy the life " +
                    "potion\n(4) to get infos on the trader \n(5) to leave this place\n");
            int select;
            try {
                select = input.nextInt();
                switch (select) {
                    case 1 -> {
                        if (player.getGold() >= charlatan.getFirstWeapon().getPrice()) {
                            System.out.println(charlatan.getName() + " : I have nothing hahahah");
                            charlatan.takePlayerMoney(player, charlatan.getFirstWeapon().getPrice());
                            negociation(player, charlatan);

                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 2 -> {
                        if (player.getGold() >= charlatan.getSecondWeapon().getPrice()) {
                            System.out.println(charlatan.getName() + " : I have nothing hahahah");
                            charlatan.takePlayerMoney(player, charlatan.getSecondWeapon().getPrice());
                            negociation(player, charlatan);

                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 3 -> {
                        if (player.getGold() >= 20) {
                            System.out.println(charlatan.getName() + " : I have nothing hahahah");
                            charlatan.takePlayerMoney(player, 20);
                            negociation(player, charlatan);

                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 4 -> {
                        charlatan.information();
                        charlatan.offer();
                    }
                    case 5 -> {
                        System.out.println(player.getName() + " : Bye bye");
                        setTradeState(true);
                    }


                    default -> System.out.println(charlatan.getName() + " : WHat ArE YuO DoiNg ?!?");
                }


            } catch (Exception e) {
                System.out.println("Error fake trading : "+e);
            }
        }

    }
/**Fonction créant l'interface entre le joueur et le vendeur */
    private void realOffer(Player player, Trader merchant) {
        merchant.introduce();
        player.introduce();
        merchant.offer();
        setTradeState(false);
        Scanner input = new Scanner(System.in);
        while (!getTradeState()) {
            System.out.println("\n\n\tSelect an option : ");
            System.out.print("(1) to buy first weapon\n(2) to buy second weapon\n(3) to buy the life " +
                    "potion\n(4) to get infos on the trader\n(5) to leave this place\n");
            int select;
            try {
                select = input.nextInt();
                switch (select) {
                    case 1 -> {
                        if (player.getGold() >= merchant.getFirstWeapon().getPrice()) {
                            System.out.println("You buy the " + merchant.getFirstWeapon().getName());
                            player.setWeapon(merchant.getFirstWeapon());
                            merchant.takePlayerMoney(player, merchant.getFirstWeapon().getPrice());
                            setTradeState(true);
                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 2 -> {
                        if (player.getGold() >= merchant.getSecondWeapon().getPrice()) {
                            System.out.println("You buy the " + merchant.getSecondWeapon().getName());
                            player.setWeapon(merchant.getSecondWeapon());
                            merchant.takePlayerMoney(player, merchant.getSecondWeapon().getPrice());
                            setTradeState(true);

                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 3 -> {
                        if (player.getGold() >= 20) {
                            System.out.println("You buy the life potion");
                            player.setHp(player.getHp() + 20);
                            merchant.takePlayerMoney(player, 20);
                            setTradeState(true);

                        } else {
                            System.out.println("You are too poor");
                        }
                    }
                    case 4 -> {
                        merchant.information();
                        merchant.offer();
                    }
                    case 5 -> {
                        System.out.println(player.getName() + " : Bye bye");
                        setTradeState(true);
                    }


                    default -> System.out.println(merchant.getName() + " : WHat ArE YuO DoiNg ?!?");
                }


            } catch (Exception e) {
                System.out.println("Error trading : "+ e);
            }
        }


    }

}
