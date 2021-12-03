import java.util.ArrayList;
import java.util.List;

public class Cuboid implements ThreeDShape {

    private final ThreeDPoint[] vertices = new ThreeDPoint[8];

    /**
     * Creates a cuboid out of the list of vertices. It is expected that the vertices are provided in
     * the order as shown in the figure given in the homework document (from v0 to v7).
     * 
     * @param vertices the specified list of vertices in three-dimensional space.
     */
    public Cuboid(List<ThreeDPoint> vertices) {
        if (vertices.size() != 8 || !isMember(vertices))
            throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getName()));
        int n = 0;
        for (ThreeDPoint p : vertices) this.vertices[n++] = p;
    }
    
    public boolean isMember(List<ThreeDPoint> vertices) {
    	double v0x = vertices.get(0).getX();
		double v0y = vertices.get(0).getY();
		double v0z = vertices.get(0).getZ();
		
		boolean[] checks = new boolean[21];
		
		checks[0]= vertices.get(1).getX() < v0x;		
		checks[1]= vertices.get(1).getY() == v0y;
		checks[2]= vertices.get(1).getZ() == v0z;
		
		checks[3]= vertices.get(2).getX() == vertices.get(1).getX();
		checks[4]= vertices.get(2).getY() < vertices.get(1).getY();			
		checks[5]= vertices.get(2).getZ() == vertices.get(1).getZ();
		
		checks[6]= vertices.get(3).getX() == v0x;	
		checks[7]= vertices.get(3).getY() == vertices.get(2).getY();
		checks[8]= vertices.get(3).getZ() == vertices.get(2).getZ();
		
		checks[9]= vertices.get(4).getX() == vertices.get(3).getX();
		checks[10]= vertices.get(4).getY() == vertices.get(3).getY();
		checks[11]= vertices.get(4).getZ() != vertices.get(3).getZ();			
		
		checks[12]= vertices.get(5).getX() == vertices.get(4).getX();	
		checks[13]= vertices.get(5).getY() == vertices.get(0).getY();
		checks[14]= vertices.get(5).getZ() == vertices.get(4).getZ();
		
		checks[15]= vertices.get(6).getX() == vertices.get(1).getX();	
		checks[16]= vertices.get(6).getY() == vertices.get(5).getY();
		checks[17]= vertices.get(6).getZ() == vertices.get(5).getZ();
		
		checks[18]= vertices.get(7).getX() == vertices.get(6).getX();	
		checks[19]= vertices.get(7).getY() == vertices.get(2).getY();
		checks[20]= vertices.get(7).getZ() == vertices.get(6).getZ();
		
		for(int i=0; i<checks.length; i++) {
			if(checks[i]) {
			}
			else {
				return false;
			}
		}
		return true;
    }
    
    public double getSideLength() {
    	return Math.sqrt(Math.pow(Math.abs(vertices[0].getX()-vertices[1].getX()), 2)+
    			Math.pow(Math.abs(vertices[0].getY()-vertices[1].getY()), 2) +
    			Math.pow(Math.abs(vertices[0].getZ()-vertices[1].getZ()), 2));
    }

    @Override
    public double volume() {
        return Math.pow(this.getSideLength(),3); 
    }
    
    public double surfaceArea() {
    	return 6 * Math.pow(this.getSideLength(),2); 
    }

    @Override
    public ThreeDPoint center() {
		double x =((this.vertices[0].getX() + 
				this.vertices[1].getX())/2);
		
		double y = ((this.vertices[1].getY() + 
				this.vertices[2].getY())/2);
		
		double z = ((this.vertices[4].getZ() + 
				this.vertices[3].getZ())/2);
		 
		return new ThreeDPoint(x,y,z); 
    }

	@Override
	public int compareTo(ThreeDShape arg0) {
		if (this.volume() > arg0.volume()) {
			return 1;
		}
		if  (arg0.volume() > this.volume()) {
			return -1;
		}
		return 0;
	}
	
	public static Cuboid random() {
		//Random numbers between 0 and 100.		
		
		int limit = 100;
		ThreeDPoint v0 = new ThreeDPoint(
				(Math.random()*((limit)) +1), 
				(Math.random()*((limit)) +1), 
				(Math.random()*((limit)) +1));

		ThreeDPoint v1 = new ThreeDPoint((
				Math.random()*((v0.getX()))), 
				v0.getY(), v0.getZ());

		ThreeDPoint v2 = new ThreeDPoint(
				v1.getX(), (Math.random()*((v1.getY()))), 
				v1.getZ());
		
		ThreeDPoint v3 = new ThreeDPoint(
				v0.getX(), v2.getY(), v2.getZ());
			
		ThreeDPoint v4 = new ThreeDPoint(
				v3.getX(), v3.getY(), 
				(Math.random()*((v3.getZ()))));
		
		ThreeDPoint v5 = new ThreeDPoint(
				v4.getX(), v0.getY(), v4.getZ());
		
		ThreeDPoint v6 = new ThreeDPoint(
				v1.getX(), v5.getY(), v5.getZ());
		
		ThreeDPoint v7 = new ThreeDPoint(
				v6.getX(), v2.getY(), v6.getZ());
		
		List<ThreeDPoint> listOfVert = new ArrayList<>();
		listOfVert.add(v0); 
		listOfVert.add(v1); 
		listOfVert.add(v2); 
		listOfVert.add(v3); 
		listOfVert.add(v4); 
		listOfVert.add(v5); 
		listOfVert.add(v6); 
		listOfVert.add(v7); 
		Cuboid c = new Cuboid(listOfVert);
		
		return c;
	}
	
}
