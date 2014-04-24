package weka.attributeSelection;

public class FConfrimationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d, int n) {
		double ad = a;
		double bd = b;
		double cd = c;
		double dd = d;
		return (ad * dd - bd * cd) / (ad * dd + bd * cd + 2 * ad * cd);
	}

	// @Override
	// public String getExpresion() {
	// return "a/(a+b) - (a+c)/n";
	// }

}
