import java.io.BufferedReader;
import java.io.FileReader;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.ConfirmationMeasuresEvaluator;
import weka.attributeSelection.ConfirmationMeasuresSearch;
import weka.attributeSelection.DConfirmationMeasure;
import weka.core.Instances;

public class Main {

	BufferedReader bf;
	Instances data;
	AttributeSelection selection;
	ConfirmationMeasuresEvaluator eval;
	ConfirmationMeasuresSearch search;

	public Main() throws Exception {
//		bf = new BufferedReader(
//				new FileReader("C:/projekty/zp/pliki/test.arff"));
		bf = new BufferedReader(new FileReader("C:/Program Files/Weka-3-6/data/weather.nominal.arff"));
		data = new Instances(bf);
		data.setClassIndex(data.numAttributes() - 1);

		selection = new AttributeSelection();

		eval = new ConfirmationMeasuresEvaluator();
		eval.setMeasure(new DConfirmationMeasure());

		search = new ConfirmationMeasuresSearch();
		search.setTreshold(0.1);

		selection.setEvaluator(eval);
		selection.setSearch(search);

	}

	public static void main(String[] args) throws Exception {
		Main m = new Main();
//		m.check();

		 m.test();

	}

	private void check() throws Exception {
		// for (int i = 0; i < data.numAttributes(); i++) {
		//
		// System.out.println(data.attribute(i).value(0));
		// System.out.println(data.attribute(i).value(1));
		// }
		// System.out.println(data.numAttributes());
		// System.out.println(data.classIndex());
		eval.buildEvaluator(data);
	}

	private void test() throws Exception {
		selection.SelectAttributes(data);
		System.out.println(selection.toResultsString());
	}

}
