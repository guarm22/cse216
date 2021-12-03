/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {	
	private double x;
	private double y;
	private double z;
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}

    public ThreeDPoint(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
    	double[] d = {this.x, this.y, this.z};
        return d;
    }
}
