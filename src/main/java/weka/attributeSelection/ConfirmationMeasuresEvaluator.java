package weka.attributeSelection;

import java.util.HashMap;

import javax.smartcardio.ATR;

import weka.attributeSelection.ASEvaluation;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.Tag;
import weka.core.Utils;

public class ConfirmationMeasuresEvaluator extends ASEvaluation {

	private static final long serialVersionUID = 5175734680114755353L;

	protected static final int D_CONFIRMATION_MEASURE = 0;
	protected static final int M_CONFIRMATION_MEASURE = 1;
	protected static final int S_CONFIRMATION_MEASURE = 2;
	protected static final int N_CONFIRMATION_MEASURE = 3;
	protected static final int C_CONFIRMATION_MEASURE = 4;
	protected static final int F_CONFIRMATION_MEASURE = 5;
	protected static final int PHI_CONFIRMATION_MEASURE = 6;

	private HashMap<Integer, AbstractConfirmationMeasure> idOfConfirmationMeasures = new HashMap<>();

	public static final Tag[] TAGS_SELECTION = {
			new Tag(D_CONFIRMATION_MEASURE, "D"),
			new Tag(M_CONFIRMATION_MEASURE, "M"),
			new Tag(S_CONFIRMATION_MEASURE, "S"),
			new Tag(N_CONFIRMATION_MEASURE, "N"),
			new Tag(C_CONFIRMATION_MEASURE, "C"),
			new Tag(F_CONFIRMATION_MEASURE, "F"),
			new Tag(PHI_CONFIRMATION_MEASURE, "PHI") };

	private double confirmationMeasures[];
	private int aValues[];
	private int bValues[];
	private int cValues[];
	private int dValues[];
	private String yesValues[];
	private String yesClass;
	int numberOfDescriptiveAttributes;

	private int confirmationMeasureId;

	public ConfirmationMeasuresEvaluator() {
		idOfConfirmationMeasures.put(0, new DConfirmationMeasure());
		idOfConfirmationMeasures.put(1, new MConfirmationMeasure());
		idOfConfirmationMeasures.put(2, new SConfirmationMeasure());
		idOfConfirmationMeasures.put(3, new NConfirmationMeasure());
		idOfConfirmationMeasures.put(4, new CConfirmationMeasure());
		idOfConfirmationMeasures.put(5, new FConfrimationMeasure());
		idOfConfirmationMeasures.put(6, new PhiConfirmationMeasure());
		resetOptions();
	}

	public void buildEvaluator(Instances data) throws Exception {
		if (data.numAttributes() != data.classIndex() + 1) {
			throw new Exception("Class is not last atribute");
		}
		AbstractConfirmationMeasure confirmationMeasure;
		confirmationMeasure = idOfConfirmationMeasures
				.get(confirmationMeasureId);
		numberOfDescriptiveAttributes = data.numAttributes() - 1;
		confirmationMeasures = new double[numberOfDescriptiveAttributes];
		aValues = new int[numberOfDescriptiveAttributes];
		bValues = new int[numberOfDescriptiveAttributes];
		cValues = new int[numberOfDescriptiveAttributes];
		dValues = new int[numberOfDescriptiveAttributes];
		yesValues = new String[numberOfDescriptiveAttributes];
		for (int i = 0; i < numberOfDescriptiveAttributes; i++) {
			aValues[i] = 0;
			bValues[i] = 0;
			cValues[i] = 0;
			dValues[i] = 0;
			yesValues[i] = data.attribute(i).value(0);
		}
		yesClass = data.classAttribute().value(0);

		for (int i = 0; i < data.numInstances(); i++) {
			Instance instance = data.instance(i);
			String classOfInstance = data.classAttribute().value(
					(int) instance.value(data.classIndex()));
			if (classOfInstance.equals(yesClass)) {
				for (int j = 0; j < numberOfDescriptiveAttributes; j++) {
					Attribute attribute = data.attribute(j);
					if (attribute.value((int) instance.value(attribute))
							.equals(yesValues[j])) {
						aValues[j]++;
					} else {
						bValues[j]++;
					}
				}
			} else {
				for (int j = 0; j < numberOfDescriptiveAttributes; j++) {
					Attribute attribute = data.attribute(j);
					if (attribute.value((int) instance.value(attribute))
							.equals(yesValues[j])) {
						cValues[j]++;
					} else {
						dValues[j]++;
					}
				}
			}
		}

		for (int i = 0; i < numberOfDescriptiveAttributes; i++) {
			confirmationMeasures[i] = confirmationMeasure.getValue(aValues[i],
					bValues[i], cValues[i], dValues[i], data.numInstances());
		}
		System.out.println("aa");
	}

	public void setOptions(String[] options) throws Exception {
		String optionString;
		resetOptions();

		optionString = Utils.getOption('C', options);
		if (optionString.length() != 0) {
			setConfirmationMeasure(new SelectedTag(
					Integer.parseInt(optionString), TAGS_SELECTION));
		}
	}

	private void resetOptions() {
		confirmationMeasureId = 0;
	}

	public double[] getConfirmationMeasures() {
		return confirmationMeasures;
	}

	// TODO
	public String confirmationMeasureTipText() {
		return " ddd";
	}

	public void setMeasure(AbstractConfirmationMeasure confirmationMeasure) {
		for (int i : idOfConfirmationMeasures.keySet()) {
			if (idOfConfirmationMeasures.get(i).getClass() == confirmationMeasure
					.getClass()) {
				confirmationMeasureId = i;
				break;
			}
		}
	}

	public AbstractConfirmationMeasure getMeasure() {
		return idOfConfirmationMeasures.get(confirmationMeasureId);
	}

	public void setConfirmationMeasure(SelectedTag selectedTag) {
		if (selectedTag.getTags() == TAGS_SELECTION) {
			confirmationMeasureId = selectedTag.getSelectedTag().getID();
		}
	}

	public SelectedTag getConfirmationMeasure() {
		return new SelectedTag(confirmationMeasureId, TAGS_SELECTION);
	}
}
