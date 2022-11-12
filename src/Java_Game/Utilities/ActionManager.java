package Java_Game.Utilities;

import java.util.Scanner;

import Java_Game.Characters.Player;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Jean-Louis
 */
public class ActionManager implements GetDataFromJSON {

    private int totalActions = -1;

    public int getTotalActions() {
        return totalActions;
    }


    public void getQuests(int rank, Player player) {
        System.out.println();
        System.out.println("\t\t|||||||| NEW QUEST ! |||||||| \n");

        String file = jsonSelector();
        JSONArray content = questContent(rank, file);
        System.out.println(getContext(content));
        Quest action1 = getQuest(content, 1);//action 1 selector 1
        Quest action2 = getQuest(content, 2);
        Scanner input = new Scanner(System.in);
        System.out.print("Enter (1) for action 1 or (2) for action 2 : ");
        int select;
        try {
            select = input.nextInt();
            if (select == 1) {
                action1.appliedPoints(player);
                totalActions += 1;
            }
            if (select == 2) {
                action2.appliedPoints(player);
                totalActions += 1;
            }
        } catch (Exception e) {
            System.out.println("Error selection actions : "+e);
        }
    }
    //renvoie soit le contenu du fichier de quete 1 ou 2
    private String jsonSelector() {
        int tmp = (int) (Math.random() * 2 + 1); // will return either 1 or 2
        String path = "quest" + tmp + ".json";
        return GetDataFromJSON.getJSONFromFile(path);
    }
    //Renvoie la chaine de caractère correspondant au contexte de la quête
    private String getContext(JSONArray content) {
        String display = "";
        try {
            JSONObject context = (JSONObject) content.get(0);
            display = (String) context.get("display");
        } catch (Exception e) {
            System.out.println("error of quest context  : " +e);
        }
        return display;
    }
    //Renvoie un tableau Json des informations de l'action de la quête
    private JSONArray questContent(int rank, String file) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(file);
            JSONObject main = (JSONObject) object;
            try {

                return (JSONArray) main.get(String.valueOf(rank));


            } catch (Exception ex) {
                System.out.println("Error of content reading : " + ex);
            }
        } catch (ParseException e) {
            System.out.println("Error of JSON reading : " + e);
        }
        return null;
    }
    //Renvoie l'objet Quest en chargeant tous les attributs dans le Json
    private Quest getQuest(JSONArray content, int selection) {

        String actionName = "No Quest Found";
        int gain = 0;
        int loss = 0;
        String finalDisp = "";

        try {
            JSONObject action = (JSONObject) content.get(selection);
            actionName = (String) action.get("display");
            gain = Integer.parseInt((String) action.get("gain"));
            loss = Integer.parseInt((String) action.get("loss"));
            finalDisp = (String) action.get("finalDisplay");
        } catch (Exception e) {
            System.out.println("No quest found in the good format : " + e);
        }

        Quest action = new Quest(actionName, gain, loss, finalDisp);
        System.out.println(action);
        return action;
    }


}