import java.util.List;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {

    public Rectangle(List<TwoDPoint> vertices) {
		super(vertices);
	}

	/**
     * The center of a rectangle is calculated to be the point of intersection of its diagonals.
     *
     * @return the center of this rectangle.
     */
    @Override
    public Point center() {
    	List<TwoDPoint> vertices = this.getPosition();
    	double cenX = (vertices.get(3).getX()+vertices.get(1).getX())/2;
    	double cenY = (vertices.get(3).getY()+vertices.get(1).getY())/2;
        return new TwoDPoint(cenX, cenY);
    }

    @Override
    public boolean isMember(List<? extends Point> vertices) {
    	double[] sideLengths = this.getSideLengths();
        return vertices.size()==4 && 
        		sideLengths[0]==sideLengths[2]&&
        		sideLengths[1]==sideLengths[3];
    }
    
    @Override
    public double area() {
		double[] sideLengths = this.getSideLengths();
        return sideLengths[0]*sideLengths[1];
    }
}
