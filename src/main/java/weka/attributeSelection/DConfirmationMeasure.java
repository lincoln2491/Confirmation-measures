package weka.attributeSelection;

import java.io.Serializable;

public class DConfirmationMeasure extends AbstractConfirmationMeasure {

	@Override
	public double getValue(int a, int b, int c, int d) {
		double ad = a;
		double bd = b;
		double cd = c;
		double nd = a + b + c + d;
		return ad / (ad + cd) - (ad + bd) / nd;
	}

}
