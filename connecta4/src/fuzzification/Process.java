package fuzzification;

public class Process {

	private SymbolTable symbolTable;

	private Fuzzification fuzzification;
	private Inference inferenceProcess;
	private Defuzzification defuzzification;

	public Process(int a, int b, int c, int d) {
		symbolTable = new SymbolTable();

		// Fuzzification Process
		fuzzification = new Fuzzification(a, b, c, d);

		// Load inference process
		this.inferenceProcess = new Inference(a, b, c, d);
		// Agregation result

		// Defuzzification process
		this.defuzzification = new Defuzzification();

	}

	/**
	 * Apply the entire process, fuzzyfication, inference and defuzzification.
	 * 
	 * @param entries
	 */
	public void apply(float[][] entries) {
		// Fuzzyfication of the entries
		for (int i = 0; i < entries[0].length; i++)
			this.symbolTable.addEntriesFuzzyficated(this.fuzzification
					.fuzzyfication(entries[0][i], entries[1][i]));

		this.inferenceProcess.apply(this.symbolTable.getEntriesFuzzyficated());

		this.symbolTable.setResultInference(this.inferenceProcess.getResult());

		this.defuzzification.apply(this.symbolTable.getResultInference());

		this.symbolTable.setResultDefuzzification(this.defuzzification
				.getResultDefuzzification());
	}

	/**
	 * Give the most adapted column.
	 * 
	 * @return
	 */
	public int chooseColumn() {
		// Retourner la colonne où le float est max;
		int columnNumber = 0;
		float max = this.symbolTable.getResultDefuzzification().get(0);
		for (int i = 1; i < this.symbolTable.getResultDefuzzification().size(); i++) {
			if (max < this.symbolTable.getResultDefuzzification().get(i)) {
				max = this.symbolTable.getResultDefuzzification().get(i);
				columnNumber = i;
			}
		}
		this.reset();
		return columnNumber;
	}

	/**
	 * Reset all the lists.
	 */
	public void reset() {
		this.symbolTable.reset();
		this.inferenceProcess.reset();
		this.defuzzification.reset();
	}
}
