
public class Sphere implements ThreeDShape{
	
	private ThreeDPoint center;
    private double radius;

    public Sphere(double centerx, double centery, double centerz, double radius) {
    	if(radius<0) {
    		throw new IllegalArgumentException("Negative radius not allowed for Sphere");
    	}
        this.center = new ThreeDPoint(centerx, centery, centerz);
        this.radius = radius;
    }

	@Override
	public int compareTo(ThreeDShape o) {
		if (this.volume() > o.volume()) {
			return 1;
		}
		if  (this.volume() < o.volume()) {
			return -1;
		}
		return 0;
	}

	@Override
	public Point center() {
		return center;
	}

	@Override
	public double volume() {
		double v = (4/3) * Math.PI * radius * radius * radius;
		return v;
	}
	public double surfaceArea() {
		return 4 * Math.PI * radius * radius;
	}
	
	public static Sphere random() {
		//limit of 100 set to x,y,z
		double x = (Math.random()*((100)+1));
		double y = (Math.random()*((100)+1));
		double z = (Math.random()*((100)+1));
		double rad = (Math.random()*((100)+1));
		return new Sphere(x,y,z,rad);
	}

}
