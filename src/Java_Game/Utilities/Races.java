package Java_Game.Utilities;

import java.util.Random;

/**
 * @author Jean-Louis & Corentin
 */

public enum Races {
    Elf,
    Hobbit,
    Wizzard,
    Dwarf,
    Human,
    Gobelin,
    Troll,
    Orc;

    private static final Random PRNG = new Random();
/**@return  une race parmi celles proposées*/
    public static Races randomRace() {
        Races[] races = values();
        return races[PRNG.nextInt(races.length)];
    }
}