package weka.attributeSelection;

import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;
import weka.core.Utils;

/**
 * 
 * @author K. Surdyk
 * 
 */
public class ConfirmationMeasuresSearch extends ASSearch {

	private double treshold;

	public ConfirmationMeasuresSearch() {
		resetOptions();
	}

	public int[] search(ASEvaluation ASEvaluator, Instances data)
			throws Exception {
		if (!(ASEvaluator instanceof ConfirmationMeasuresEvaluator)) {
			throw new Exception(ASEvaluator.getClass().getName() + " is not a "
					+ "confirmation measures evaluator!");
		}
		ConfirmationMeasuresEvaluator eval = (ConfirmationMeasuresEvaluator) ASEvaluator;
		List<Integer> selectedAttributes = new ArrayList<Integer>();
		int[] result;
		double[] measures = eval.getConfirmationMeasures();

		for (int i = 0; i < measures.length; i++) {
			if (i == data.classIndex()) {
				continue;
			}
			if (measures[i] >= treshold) {
				selectedAttributes.add(i);
			}
		}

		result = new int[selectedAttributes.size()];
		int i = 0;
		for (Integer value : selectedAttributes) {
			result[i] = value.intValue();
			i++;
		}
		return result;
	}

	public double getTreshold() {
		return treshold;
	}

	public void setTreshold(double treshold) {
		this.treshold = treshold;
	}

	// TODO
	public String tresholdTipText() {
		return " Jakiœ opis ";
	}

	public void setOptions(String[] options) throws Exception {
		String optionString;
		resetOptions();

		optionString = Utils.getOption('T', options);
		if (optionString.length() != 0) {
			setTreshold(Double.parseDouble(optionString));
		}

	}

	protected void resetOptions() {
		treshold = 0.5;
	}
}
