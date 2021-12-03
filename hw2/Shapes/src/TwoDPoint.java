import java.util.ArrayList;
import java.util.List;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {
	private double x;
	private double y;

    public TwoDPoint(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public TwoDPoint(Point point) {
    	TwoDPoint td = (TwoDPoint) point;
		this.x=td.x;
		this.x=td.y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	/**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
    	double[] d = {this.x, this.y};
        return d;
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        if(coordinates.length%2==1) {
        	throw new IllegalArgumentException("Odd number of elements, pairs only");
        }
        List<TwoDPoint> ret = new ArrayList<>();
        for(int i=0; i<coordinates.length; i++) {
        	ret.add(new TwoDPoint(coordinates[i++],coordinates[i]));
        }
    	return ret;
    }
    
    public boolean equals(TwoDPoint td) {
    	return td.getX()==this.x && td.getY()==this.y;
    }
}
