package weka.attributeSelection;

public class MConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d, int n) {
		double ad = a;
		double bd = b;
		double cd = c;
		double nd = n;
		return ad / (ad + bd) - (ad + cd) / nd;

	}

//	@Override
//	public String getExpresion() {
//		return "a/(a+b) - (a+c)/n";
//	}

}
