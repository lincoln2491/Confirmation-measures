package weka.attributeSelection;

public class DConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d, int n) {
		double ad = a;
		double bd = b;
		double cd = c;
		double nd = n;
		return ad / (ad + cd) - (ad + bd) / nd;
	}

//	@Override
//	public String getExpresion() {
//		return "a/(a+c) - (a+b)/n";
//	}
}
