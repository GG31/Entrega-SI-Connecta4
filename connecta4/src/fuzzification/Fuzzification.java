package fuzzification;

/**
 * This class create the fuzzyfication's functions and give a result 2 sets of 3
 * terms for 2 entries.
 * 
 */
public class Fuzzification {
	private int a;
	private int b;
	private int c;
	private int d;

	public Fuzzification(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public float low(float x) {
		if (x <= a)
			return 1;
		else if (x > a && x <= b)
			return (1f / (a - b)) * x - ((float) b / (a - b));
		else
			return 0;
	}

	public float medium(float x) {
		if (x <= a)
			return 0;
		else if (x > a && x <= b)
			return (float) (1f / (b - a)) * x - ((float) a / (b - a));
		else if (x > b && x <= c)
			return 1;
		else if (x > c && x <= d)
			return (float) (1f / (c - d)) * x + ((float) d / (d - c));
		else
			return 0;
	}

	public float high(float x) {
		if (x <= c)
			return 0;
		else if (x > c && x <= d)
			return (1f / (d - c)) * x + ((float) c / (float) (c - d));
		else
			return 1;
	}

	/**
	 * Fuzzyfication process, give 3 numbers with the 3 fuzzyfications'
	 * functions for each entry.
	 * 
	 * @param entry
	 * @return
	 */
	public float[][] fuzzyfication(float entry1, float entry2) {
		float[][] result = new float[2][3];
		// Fuzzyfication entry1
		result[0][0] = low(entry1);
		result[0][1] = medium(entry1);
		result[0][2] = high(entry1);
		// Fuzzyfication entry2
		result[1][0] = low(entry2);
		result[1][1] = medium(entry2);
		result[1][2] = high(entry2);
		return result;
	}
}
