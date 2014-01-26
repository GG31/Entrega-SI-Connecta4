package fuzzification;

import java.util.ArrayList;

import polygon.Polygon;

/**
 * This class is the defuzzification's process, from polygons gives the X's
 * centroid's polygon.
 * 
 */
public class Defuzzification {
	private ArrayList<Float> resultDefuzzification;

	public Defuzzification() {
		this.resultDefuzzification = new ArrayList<>();
	}

	public void apply(ArrayList<Polygon> resultInference) {
		for (int i = 0; i < resultInference.size(); i++) {
			this.resultDefuzzification.add(resultInference.get(i).centroid()
					.getX());
		}
	}

	public ArrayList<Float> getResultDefuzzification() {
		return this.resultDefuzzification;
	}

	/**
	 * Reset the resultDefuzzification's list for the next choice.
	 */
	public void reset() {
		this.resultDefuzzification.clear();
	}
}
