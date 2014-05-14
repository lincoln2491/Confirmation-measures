package weka.attributeSelection;

public class CConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d) {
		double ad = a;
		double bd = b;
		double cd = c;
		double nd = a + b + c + d;
		return ad / nd - (ad + cd) * (ad + bd) / (nd * nd);

	}

	// @Override
	// public String getExpresion() {
	// return "a/(a+b) - (a+c)/n";
	// }

}
