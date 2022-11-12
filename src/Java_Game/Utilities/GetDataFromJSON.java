package Java_Game.Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * @author Jean-Louis & Corentin
 */

public interface GetDataFromJSON {
    /**@return un nom du json si le fichier existe */
    static String createName() {
        String name = "";
        try {
            String file = GetDataFromJSON.getJSONFromFile("PNJData.json");
            name = getJSONContent(file, "Names");
        } catch (Exception e) {
            System.out.println("Can't get a character name " + e);

        }
        if (name == null) name = "Anonymous";
        return name;
    }
/**@return un adjectif du json si le fichier existe */
    static String createAdjective() {
        String adj = "NoAdjective";
        try {
            String file = GetDataFromJSON.getJSONFromFile("PNJData.json");
            adj = getJSONContent(file, "Adjectives");
        } catch (Exception e) {
            System.out.println("Can't get an adjective " + e);

        }
        if (adj == null) adj = "NoAdjective";
        return adj;
    }
 /**@Return une arme aléatoire avec des caractéristiques basées sur le rang du joueur */
    static Weapon createWeapon(Integer playerRank) {
        String name = "";
        int price;
        int dmg;
        Random random = new Random();
        double gauss = random.nextGaussian();
        dmg = Math.abs((int) Math.round(playerRank * gauss * 5)) + 1;
        price = Math.abs((int) Math.round(gauss * dmg * 1.2)) + 1;
        try {
            String file = GetDataFromJSON.getJSONFromFile("PNJData.json");
            name = getJSONContent(file, "WeaponNames");
        } catch (Exception e) {
            System.out.println("Can't get a weapon name " + e);

        }
        if (name == null) name = "DefaultWeapon";
        return new Weapon(name, dmg, price);
    }
/** @return ( une chaine de caractère ) un fichier json qu'il faudra parser par la suite*/
    static String getJSONFromFile(String filename) {
        StringBuilder jsonText = new StringBuilder();
        try {
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line).append("\n");
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Error of Json Input : " + e);
        }

        return jsonText.toString();
    }
/**Parser
 * @return  une chaîne de caractère provenant du json
 * @param le json et la clé */
    static String getJSONContent(String file, String info) {
        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(file);
            try {
                JSONObject mainJsonObject = (JSONObject) object;
                JSONArray content = (JSONArray) mainJsonObject.get(info);
                int random = (int) (Math.random() * content.size());
                return (String) content.get(random);

            } catch (NumberFormatException ex) {
                System.out.println("Error of number reading : " + ex);
            }
        } catch (ParseException e) {
            System.out.println("Error of JSON reading : " + e);
        }

        return null;
    }
}
