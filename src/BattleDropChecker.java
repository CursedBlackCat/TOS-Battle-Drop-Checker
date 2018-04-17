import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BattleDropChecker {

	private static String getCardNameFromID(int id) {
		JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(new FileInputStream("cards.json"));
		} catch (FileNotFoundException e) {
			System.out.println("\\nBattleDropChecker encountered an error when trying to load card name for card ID" + padZeroes(id) + ". Using card ID instead.");
			return null;
		}
		JSONObject cards = new JSONObject(tokener);
		return cards.getString(padZeroes(id));
	}
	
	private static String padZeroes(int id) {
		if (id < 10) {
			return "00" + id;
		} else if (id >= 10 && id < 100) {
			return "0" + id;
		} else {
			return Integer.toString(id);
		}
	}

	public static void main(String[] args) {
		/*Initialize the document*/
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse("com.madhead.tos.en.v2.playerprefs.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.out.println("\\nBattleDropChecker encountered an error when trying to load the XML file.");
			System.exit(0);
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("\\nBattleDropChecker encountered an error when trying to load the XML file.");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nBattleDropChecker encountered an error when trying to load the XML file.");
			System.exit(0);
		}

		/*Get the battle data and store it in a string*/
		String battleData = "";
		NodeList nList = doc.getElementsByTagName("string");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			NamedNodeMap attributes = nNode.getAttributes();
			Node attr = attributes.getNamedItem("name");
			if (attr != null) {
				if (attr.getTextContent().equals("MH_CACHE_RUNTIME_DATA_CURRENT_FLOOR_WAVES")) {
					battleData = nNode.getTextContent();
					//System.out.println("Battle data obtained.");
				}
			}
		}

		/*Check that the battle data exists. If not, exit the program.*/
		if (battleData.isEmpty()) {
			System.out.println("Couldn't load the battle data from the XML file.");
			System.exit(0);
		}

		/*Decode the URL encoding*/
		try {
			battleData = java.net.URLDecoder.decode(battleData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("\nBattleDropChecker encountered an error when trying to decode the battle data.");
		}

		/*Truncate the hash at the start of the string*/
		battleData = battleData.substring(32);

		/*Convert string to JSON*/
		JSONArray jsonBattleArray;
		JSONObject[] battleWaves = null;

		try {
			jsonBattleArray = new JSONArray(battleData);
			battleWaves = new JSONObject[jsonBattleArray.length()];
			for (int i = 0; i < jsonBattleArray.length(); i++) {
				battleWaves[i] = jsonBattleArray.getJSONObject(i);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("\nBattleDropChecker encountered an error when trying to parse the battle data JSON.");
		}

		/*Iterate over each battle wave, finding the loot that drops and storing it in an array.*/
		String[] battleDrops = new String[battleWaves.length];

		for (int i = 0; i < battleWaves.length; i++) {
			String[] waveDrops = null;
			try {
				JSONArray enemies = battleWaves[i].getJSONArray("enemies");
				waveDrops = new String[enemies.length()]; //stores what each enemy drops in the current wave
				for (int j = 0; j < enemies.length(); j++) {
					JSONObject enemy = enemies.getJSONObject(j);
					if (enemy.get("lootItem").equals(JSONObject.NULL)) {
						waveDrops[j] = null;
					} else {
						JSONObject loot = enemy.getJSONObject("lootItem");
						if (loot.getString("type").equals("monster")) {
							String cardName = getCardNameFromID(loot.getJSONObject("card").getInt("monsterId"));
							if (cardName == null) {
								waveDrops[j] = "Card ID " + (loot.getJSONObject("card").getInt("monsterId"));
							} else {
								waveDrops[j] = cardName;
							}
						} //TODO implement drops for dragonary craft, souls, treasure chest, etc.
					}
				}

				//Store the drop for the wave in the battleDrops array. This is assuming a maximum of only one drop per wave.
				for (int j = 0; j < waveDrops.length; j++) {
					if (waveDrops[j] != null) {
						battleDrops[i] = waveDrops[j];
						break; //break out of the loop, since we already found the drop for that wave
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nBattleDropChecker encountered an error when trying to check the loot for battle wave " + Integer.toString(i + 1) + ".");
			}
		}

		for (int i = 0; i < battleDrops.length; i++) {
			if (battleDrops[i] != null) {
				System.out.println("Battle wave " + Integer.toString(i + 1) + ": " + battleDrops[i]);
			} else {
				System.out.println("Battle wave " + Integer.toString(i + 1) + ": no drops");
			}
		}
	}
}