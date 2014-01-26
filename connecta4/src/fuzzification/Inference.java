package fuzzification;

import java.util.ArrayList;
import java.util.HashMap;

import polygon.Point;
import polygon.Polygon;

/**
 * The Inference class load the rules and apply them for each column that is
 * each set of fuzzificated terms. Then apply the agregation process.
 * 
 */
public class Inference {
	int a, b, c, d;
	private Rules rules;
	private ArrayList<HashMap<String, Float>> resultRules;
	private ArrayList<Polygon> resultAgregation;

	public Inference(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		// Load rules
		this.rules = new Rules();
		this.resultRules = new ArrayList<>();

		// Agregation
		this.resultAgregation = new ArrayList<>();

	}

	/**
	 * Apply the inference's process
	 * 
	 * @param entriesFuzzyficated
	 */
	public void apply(ArrayList<float[][]> entriesFuzzyficated) {
		// For every set of entries of each column, call and apply rules
		for (int i = 0; i < entriesFuzzyficated.size(); i++)
			this.resultRules.add(rules.applyRules(entriesFuzzyficated.get(i)));
		this.agregation();
	}

	/**
	 * Agregation process
	 * 
	 * @param response
	 * @return
	 */
	public void agregation() {
		// For each resultRules, create a polygon
		for (int i = 0; i < resultRules.size(); i++) {
			this.resultAgregation.add(analysePoints(resultRules.get(i)));
		}
	}

	/**
	 * Find all points of the final polygon for the n column from the list of
	 * the rules'result
	 * 
	 * @param liste
	 * @return
	 */
	public Polygon analysePoints(HashMap<String, Float> liste) {
		float gammaL, gammaM, gammaH;
		float intersection = 0.5f;
		ArrayList<Point> setPoints = new ArrayList<>();
		if (liste.containsKey("LOW")) {
			gammaL = liste.get("LOW");
			setPoints.add(new Point(0, 0));
			setPoints.add(new Point(0, gammaL));
		} else {
			gammaL = 0;
		}

		gammaM = liste.containsKey("MEDIUM") ? liste.get("MEDIUM") : 0;
		if (gammaL > intersection && gammaM > intersection) {
			setPoints.add(new Point(anteL(gammaL), gammaL));
			setPoints.add(new Point(anteL(intersection), intersection));
			setPoints.add(new Point(anteM(gammaM)[0], gammaM));
		} else if (gammaL < intersection || gammaM < intersection
				|| (gammaL == intersection ^ gammaM == intersection)) {
			if (gammaL < gammaM) {
				setPoints.add(new Point(anteM(gammaL)[0], gammaL));
				setPoints.add(new Point(anteM(gammaM)[0], gammaM));
			} else if (gammaL > gammaM) {
				setPoints.add(new Point(anteL(gammaL), gammaL));
				setPoints.add(new Point(anteL(gammaM), gammaM));
			}
		}

		gammaH = liste.containsKey("HIGH") ? liste.get("HIGH") : 0;
		if (gammaM > intersection && gammaH > intersection) {
			setPoints.add(new Point(anteM(gammaM)[1], gammaM));
			setPoints.add(new Point(anteH(intersection), intersection));
			setPoints.add(new Point(anteH(gammaH), gammaH));
		} else if (gammaM < intersection || gammaH < intersection
				|| (gammaM == intersection ^ gammaH == intersection)) {
			if (gammaM < gammaH) {
				setPoints.add(new Point(anteH(gammaM), gammaM));
				setPoints.add(new Point(anteH(gammaH), gammaH));
			} else if (gammaM > gammaH) {
				setPoints.add(new Point(anteM(gammaM)[1], gammaM));
				setPoints.add(new Point(anteM(gammaH)[1], gammaH));
			}
		}
		if (gammaH != 0) {
			setPoints.add(new Point(15, gammaH));
			setPoints.add(new Point(15, 0));
		}
		Polygon p = new Polygon(setPoints);
		return p;
	}

	// Antecedant function
	public float anteL(float y) {
		float rep;
		if (y == 1) {
			rep = a;
		} else if (y == 0) {
			rep = b;
		} else {
			rep = (a - b) * y + b;
		}
		return rep;
	}

	public float[] anteM(float y) {
		float[] rep = new float[2];
		if (y == 0) {
			rep[0] = a;
			rep[1] = d;
		} else if (y == 1) {
			rep[0] = b;
			rep[1] = c;
		} else {
			rep[0] = (b - a) * y + a;
			rep[1] = (c - d) * y + d;
		}
		return rep;
	}

	public float anteH(float y) {
		float rep;
		if (y == 1) {
			rep = d;
		} else if (y == 0) {
			rep = c;
		} else {
			rep = (d - c) * y + c;
		}
		return rep;
	}

	public void reset() {
		this.resultRules.clear();
		this.resultAgregation.clear();
	}

	// Getter
	public ArrayList<Polygon> getResult() {
		return this.resultAgregation;
	}
}
