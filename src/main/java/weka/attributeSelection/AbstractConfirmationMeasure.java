package weka.attributeSelection;

import java.io.Serializable;

/**
 * 
 * @author K. Surdyk
 * 
 */
public abstract class AbstractConfirmationMeasure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4847994895446157945L;

	public abstract double getValue(int a, int b, int c, int d);

}
