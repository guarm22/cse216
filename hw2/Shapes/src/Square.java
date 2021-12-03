import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Square extends Rectangle implements Snappable {

    public Square(List<TwoDPoint> vertices) {
        super(vertices);
    }

    /**
     * Given a list of vertices assumed to be provided in a counterclockwise order in a two-dimensional plane, checks
     * whether or not they constitute a valid square.
     *
     * @param vertices the specified list of vertices in a counterclockwise order
     * @return <code>true</code> if the four vertices can form a square, <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        return vertices.size() == 4 &&
                DoubleStream.of(getSideLengths()).boxed().collect(Collectors.toSet()).size() == 1;
    }

    /**
     * Snaps the sides of the square such that each corner (x,y) is modified to be a corner (x',y') where x' is the
     * the integer value closest to x and y' is the integer value closest to y. This, of course, may change the shape
     * to a general quadrilateral, hence the return type. The only exception is when the square is positioned in a way
     * where this approximation will lead it to vanish into a single point. In that case, a call to {@link #snap()}
     * will not modify this square in any way.
     */
    @Override
    public Quadrilateral snap() {
    	List<TwoDPoint> gp = this.getPosition(); //original pos
    	List<TwoDPoint> gp2 = new ArrayList<TwoDPoint>(); // to become snapped pos
    	Quadrilateral q = new Quadrilateral(this.getPosition());
    	
    	for (int i = 0; i<=3 ; i++) {
    		gp2.add(new TwoDPoint(Math.round(gp.get(i).getX()),
    				Math.round(gp.get(i).getY())));
    	}
    	//if any of the points closed in on themselves
    	if(gp2.get(0).equals(gp2.get(1)) || gp2.get(0).equals(gp2.get(2)) || 
    			gp2.get(0).equals(gp2.get(3))|| gp2.get(1).equals(gp2.get(2))|| 
    			gp2.get(1).equals(gp2.get(3))|| gp2.get(2).equals(gp2.get(3))) {
    		return this;
    	}
    	q.setPosition(gp2);
    	return q;
    }
}
