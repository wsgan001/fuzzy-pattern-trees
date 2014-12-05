/**
 * 
 */
package weka.classifiers.trees.pt.measures;

/**
 * @author Robin Senge [senge@mathematik.uni-marburg.de]
 */
public class RootMeanSquaredError extends AbstractErrorMeasure {

	private static final long serialVersionUID = -8487050440527765336L;
	
	/** Singleton instance. */
	public static final RootMeanSquaredError INSTANCE = new RootMeanSquaredError();
	
	@Override
	public double eval(double[] vec1, double[] vec2) {
		double count = 0d;
		double sum = 0d;
		for (int i = 0; i < vec1.length; i++) {
			if (Double.isNaN(vec1[i]) || Double.isNaN(vec2[i])) {
				continue;
			} else {
				sum += Math.pow(vec1[i] - vec2[i], 2);
				count++;
			}
		}
		return Math.sqrt(sum / count);
	}
	
	@Override
	public double eval(double[] vec1, double[] vec2, double[] weights) {
		double count = 0d;
		double sum = 0d;

		for (int i = 0; i < vec1.length; i++) {
			if (Double.isNaN(vec1[i]) || Double.isNaN(vec2[i])) {
				continue;
			} else {
				sum += Math.pow(vec1[i] - vec2[i], 2) * weights[i];
				count += weights[i];
			}

		}

		return Math.sqrt(sum / count);
	}

	
	/**
	 * Just takes the squared error.
	 */
	@Override
	public double eval(double val1, double val2) {
		if (Double.isNaN(val1) || Double.isNaN(val2)) {
			return Double.NaN;
		}
		return Math.pow(val1-val2, 2d);
	}
	
	
}
