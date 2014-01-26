package fuzzification;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains all rules to choose a column, extract from a file.
 * 
 */
public class Rules {
	private final String FILE = "ressources/Rules.txt";
	private ArrayList<String> elementsSet;
	private HashMap<String, String> rules;

	public Rules() {
		rules = new HashMap<>();
		elementsSet = new ArrayList<>();
		lecture(FILE);
	}

	private void lecture(String file) {
		Scanner sc;
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				if (s.charAt(0) == '!') {
					analyseElementsSetFile(s);
				} else {
					analyseRulesFile(s);
				}
			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void analyseElementsSetFile(String s) {
		s = s.substring(1);
		String[] str = s.split(" ");
		for (int i = 0; i < str.length; i++) {
			elementsSet.add(str[i]);
		}
	}

	private void analyseRulesFile(String s) {
		String[] str = s.split(":");
		rules.put(str[0], str[1]);
	}

	/**
	 * Return the set of terms after applying each rules for one column (entries
	 * fuzzificated)
	 * 
	 * @param entriesFuzzificated
	 * @return
	 */
	public HashMap<String, Float> applyRules(float[][] entriesFuzzificated) {
		HashMap<String, Float> rulesMin = new HashMap<>();
		for (int i = 0; i < entriesFuzzificated[0].length; i++) {
			String typeCount = type(entriesFuzzificated[0][i], i);
			if (typeCount != null) {
				for (int j = 0; j < entriesFuzzificated[1].length; j++) {
					String typeSum = type(entriesFuzzificated[1][j], j);
					if (typeSum != null) {
						rulesMin.put(
								typeCount + " " + typeSum,
								min(entriesFuzzificated[0][i],
										entriesFuzzificated[1][j]));
					}
				}
			}
		}
		return analyseRules(rulesMin);
	}

	/**
	 * To <HIGH LOW, 0.2> the response will be <RulesResult of "HIGH LOW", 0.2>
	 * This function find the results'rules corresponding to the data
	 * 
	 * @param rulesMin
	 * @return
	 */
	public HashMap<String, Float> analyseRules(HashMap<String, Float> rulesMin) {
		HashMap<String, Float> result = new HashMap<>();
		for (String r : rulesMin.keySet()) {
			if (rules.containsKey(r)) {
				if (!result.containsKey(rules.get(r)))
					result.put(rules.get(r), rulesMin.get(r));
				else {
					// If the key exist, find the max between the high of the 2
					// area, but we can put a sum, min, middle..
					float max = max(rulesMin.get(r), result.get(rules.get(r)));
					result.put(rules.get(r), max);
				}
			}
		}
		return result;
	}

	public String type(float a, int position) {
		if (a != 0) {
			if (position < elementsSet.size())
				return elementsSet.get(position);
		}
		return null;
	}

	private float min(float a, float b) {
		return (a > b) ? b : a;
	}

	private float max(float a, float b) {
		return (a > b) ? a : b;
	}

	public void affiche() {
		System.out.println(rules);

	}
}
