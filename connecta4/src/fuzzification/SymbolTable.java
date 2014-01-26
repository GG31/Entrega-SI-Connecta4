package fuzzification;

import java.util.ArrayList;

import polygon.Polygon;

/**
 * This class take the set of entries and apply the fuzzification, inference
 * process and defuzzification
 * 
 */
public class SymbolTable {
	// Each terms of the arraylist has 2 sets of 3 terms corresponding to the
	// fuzzyficated's terms of the entries
	private ArrayList<float[][]> entriesFuzzyficated;
	private ArrayList<Polygon> resultInference;
	private ArrayList<Float> resultDefuzzification;

	public SymbolTable() {
		this.entriesFuzzyficated = new ArrayList<float[][]>();
		this.resultInference = new ArrayList<>();
		this.resultDefuzzification = new ArrayList<>();
	}

	public ArrayList<float[][]> getEntriesFuzzyficated() {
		return entriesFuzzyficated;
	}

	public ArrayList<Polygon> getResultInference() {
		return resultInference;
	}

	public void addEntriesFuzzyficated(float[][] e) {
		this.entriesFuzzyficated.add(e);
	}
	
	public ArrayList<Float> getResultDefuzzification() {
		return resultDefuzzification;
	}
	
	public void setResultInference(ArrayList<Polygon>e){
		this.resultInference = e;
	}
	
	public void setResultDefuzzification(ArrayList<Float>e){
		this.resultDefuzzification = e;
	}
	
	public void reset(){
		this.entriesFuzzyficated.clear();
		this.resultInference.clear();
		this.resultDefuzzification.clear();
	}
}
