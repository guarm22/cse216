import java.util.Arrays;
import java.util.List;

public class Quadrilateral implements Positionable, TwoDShape {

    private final TwoDPoint[] vertices = new TwoDPoint[4];

    public Quadrilateral(double... vertices) { 
        this(TwoDPoint.ofDoubles(vertices));
    }

    public Quadrilateral(List<TwoDPoint> vertices) {
        int n = 0;
        for (TwoDPoint p : vertices) this.vertices[n++] = p;
        if (!isMember(vertices))
        	throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getCanonicalName()));
    }

    /**
     * Given a list of four points, adds them as the four vertices of this quadrilateral in the order provided in the
     * list. This is expected to be a counterclockwise order of the four corners.
     *
     * @param points the specified list of points.
     * @throws IllegalStateException if the number of vertices provided as input is not equal to four.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
    	for(int i=0; i<4; i++) {
        if (points.get(i) instanceof TwoDPoint == false)
            throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getCanonicalName()));
    	}
    	if(!this.isMember((List<TwoDPoint>)points)) {
    		throw new IllegalArgumentException(
    				"Invalid set of vertices for "
    				+ this.getClass().getCanonicalName());
    	}
    	TwoDPoint[] vert = {(TwoDPoint) points.get(0), (TwoDPoint) points.get(1), 
    			(TwoDPoint) points.get(2), (TwoDPoint) points.get(3)};
    	int n=0;
    	for (TwoDPoint p : vert) this.vertices[n++] = p;

    }

    @Override
    public List<TwoDPoint> getPosition() {
        return Arrays.asList(vertices);
    }

    /**
     * @return the lengths of the four sides of the quadrilateral. Since the setter {@link Quadrilateral#setPosition(List)}
     *         expected the corners to be provided in a counterclockwise order, the side lengths are expected to be in
     *         that same order.
     */
    protected double[] getSideLengths() {
    	double[] sideLength = new double[4];
    	sideLength[0] = Math.sqrt(Math.abs(
    			Math.pow((vertices[0].getY() - vertices[1].getY()),2)
    			) + Math.abs(Math.pow((vertices[0].getX()-vertices[1].getX()),2)));
    			
    	sideLength[1] = Math.sqrt(Math.abs(
    			Math.pow((vertices[1].getY() - vertices[2].getY()),2)
    			) + Math.abs(Math.pow((vertices[1].getX()-vertices[2].getX()),2)));
    			
    	sideLength[2] = Math.sqrt(Math.abs(
    			Math.pow((vertices[3].getY() - vertices[2].getY()),2)
    			) + Math.abs(Math.pow((vertices[3].getX()-vertices[2].getX()),2)));
    			
    	sideLength[3] =Math.sqrt(Math.abs(
    			Math.pow((vertices[0].getY() - vertices[3].getY()),2)
    			) + Math.abs(Math.pow((vertices[0].getX()-vertices[3].getX()),2)));
    	
        return sideLength;
    }
    public double centerX() {
    	return (vertices[3].getX()+vertices[1].getX())/2;
    }

    @Override
    public int numSides() { return 4; }

    @Override
    public boolean isMember(List<? extends Point> vertices) { return vertices.size() == 4; }
}
