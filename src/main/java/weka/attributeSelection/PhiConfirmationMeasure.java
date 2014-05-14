package weka.attributeSelection;

public class PhiConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d) {
		double ad = a;
		double bd = b;
		double cd = c;
		double dd = d;
		return (ad * dd - bd * cd)
				/ Math.sqrt((ad + bd) * (ad + cd) * (bd + dd) * (cd + dd));

	}
	// ad-bc)/sqrt((a+b)*(a+c)*(b+d)*(c+d)).
	// @Override
	// public String getExpresion() {
	// return "a/(a+b) - (a+c)/n";
	// }

}
