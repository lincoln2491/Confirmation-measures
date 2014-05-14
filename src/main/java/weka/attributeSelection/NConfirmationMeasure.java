package weka.attributeSelection;

public class NConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d) {
		double ad = a;
		double bd = b;
		double cd = c;
		double dd = d;
		return ad / (ad + bd) - cd /(cd + dd);

	}

//	@Override
//	public String getExpresion() {
//		return "a/(a+b) - (a+c)/n";
//	}

}
