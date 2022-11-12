package Java_Game.Characters;

import Java_Game.Utilities.Weapon;

/**
 * @author Jean-Louis & Corentin
 */

public abstract class Pnj extends Character {

    protected String funFact = "Mr.Morelle is the best Java professor, he should give us +1 on the project mark !";
    /**Notation des PNJ de 0 à 10. Meilleure note :10*/
    protected Integer trustFactor = 0;

    public Pnj(String name, String adjective, Integer hp, Weapon weapon) { // Création du constructeur de la classe PnjBad

        super(name, adjective, hp, weapon);

    }
    //Les fonctions suivantes sont utiles pour déceler le charlatan du vrai marchand
    public Integer getTrustFactor() { // Récupération de l'arme
        return trustFactor;
    }

    public void setTrustFactor(Integer number) {
        this.trustFactor = number;
    }
    /**Fonction permettant de mettre une note aléatoire sur le PNJ marchand avec une pondérence en fonction du statut du PNJ*/
    public abstract Integer randomTrustFactor();

}
