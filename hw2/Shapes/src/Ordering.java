import java.util.*;

public class Ordering {

    static class XLocationComparator implements Comparator<TwoDShape> {
        @Override public int compare(TwoDShape o1, TwoDShape o2) {
        	//compares the X value in the center of each shape.
        	double x1=0, x2=0;
        	
        	if(o1 instanceof Square) {
        		Square s = (Square) o1;
        		x1=s.centerX();
        	}
        	if(o2 instanceof Square) {
        		Square s = (Square) o2;
        		x2=s.centerX();
        	}
        	if(o1 instanceof Rectangle) {
        		Rectangle r = (Rectangle) o1;
        		x1=r.centerX();
        	}
        	if(o2 instanceof Rectangle) {
        		Rectangle r = (Rectangle) o2;
        		x2=r.centerX();
        	}
        	if(o1 instanceof Circle) {
        		Circle c = (Circle) o1;
        		TwoDPoint cent = (TwoDPoint) c.center();
        		x1=cent.getX();
        	}
        	if(o2 instanceof Circle) {
        		Circle c = (Circle) o2;
        		TwoDPoint cent = (TwoDPoint) c.center();
        		x2=cent.getX();
        	}
        	
        	if(x1>x2) {
        		return 1;
        	}
        	if(x2>x1) {
        		return -1;
        	}
        	
            return 0;
        }
    }

    static class AreaComparator implements Comparator<SymmetricTwoDShape> {
        @Override public int compare(SymmetricTwoDShape o1, SymmetricTwoDShape o2) {
        	double ar1=0, ar2=0;
        	
            if(o1 instanceof Circle) {
            	Circle c = (Circle)o1;
            	ar1=c.area();
            }
            
            if(o2 instanceof Circle) {
            	Circle c = (Circle)o2;
            	ar2=c.area();
            }
            
            if(ar1>ar2) {
            	return 1;
            }
            if(ar2>ar1) {
            	return -1;
            }
            return 0;
        }
    }

    static class SurfaceAreaComparator implements Comparator<ThreeDShape> {
        @Override public int compare(ThreeDShape o1, ThreeDShape o2) {
            double sar1=0, sar2=0;
            
            if(o1 instanceof Sphere) {
            	Sphere s = (Sphere) o1;
            	sar1=s.surfaceArea();
            }
            if(o2 instanceof Sphere) {
            	Sphere s = (Sphere) o2;
            	sar2=s.surfaceArea();
            }
        	if(o1 instanceof Cuboid) {
        		Cuboid c = (Cuboid) o1;
        		sar1 = c.surfaceArea();
        	}
        	if(o2 instanceof Cuboid) {
        		Cuboid c = (Cuboid)o2;
        		sar2 = c.surfaceArea();
        	}
            
            if(sar1>sar2) {
            	return 1;
            }
            if(sar2>sar1) {
            	return -1;
            }
        	return 0;
        }
    }

    static <E> void copy (Collection<? extends E> source, Collection<E> destination) {
    	Object[] a = source.toArray();
    	for(int i=0; i<a.length; i++) {
    		destination.add((E) a[i]);
    	}
    }

    public static void main(String[] args) {
    	
    	Circle circle = new Circle(0.0, 0.0, 2.0);
    	List<TwoDPoint> lqtwodpoint = new ArrayList<>();
    	lqtwodpoint.add(new TwoDPoint(10.0, 68.0));
    	lqtwodpoint.add(new TwoDPoint(62.0, 96.0));
    	lqtwodpoint.add(new TwoDPoint(69.0, 84.0));
    	lqtwodpoint.add(new TwoDPoint(15.0, 51.0));
    	List<TwoDPoint> lrtwodpoint = new ArrayList<>();
    	lrtwodpoint.add(new TwoDPoint(7.0, 3.0));
    	lrtwodpoint.add(new TwoDPoint(3.0, 3.0));
    	lrtwodpoint.add(new TwoDPoint(3.0, -2.0));
    	lrtwodpoint.add(new TwoDPoint(7.0, -2.0));
    	List<TwoDPoint> lstwodpoint = new ArrayList<>();
    	lstwodpoint.add(new TwoDPoint(7.0, 7.0));
    	lstwodpoint.add(new TwoDPoint(3.0, 7.0));
    	lstwodpoint.add(new TwoDPoint(3.0, 3.0));
    	lstwodpoint.add(new TwoDPoint(7.0, 3.0));
    	Quadrilateral quad = new Quadrilateral(lqtwodpoint);
    	Rectangle rec = new Rectangle(lrtwodpoint);
    	Square squ = new Square(lstwodpoint);


    	// XLocation
    	List<TwoDShape> lxLocationTest = new ArrayList<>();
    	lxLocationTest.add(quad);
    	lxLocationTest.add(rec);
    	lxLocationTest.add(squ);
    	lxLocationTest.add(circle);
    	TwoDShape[] lxLocationAnswer = {circle, rec, squ, quad};
    	try {
    	lxLocationTest.sort(new Ordering.XLocationComparator());
    	if (Arrays.toString(lxLocationTest.toArray()).equals(Arrays.toString(lxLocationAnswer))) {
    	System.out.println("l:XLocation success.");
    	} else {
    	System.out.println("l:XLocation failed.");
    	}
    	} catch (Exception e) {
    	System.out.println("l:XLocation failed.");
    	}

    	List<SymmetricTwoDShape> lareaTest = new ArrayList<>();
    	lareaTest.add(squ);
    	lareaTest.add(rec);
    	lareaTest.add(circle);
    	SymmetricTwoDShape[] lareaAnswer = {circle, squ, rec};
    	try {
    	lareaTest.sort(new Ordering.AreaComparator());
    	if (Arrays.toString(lareaTest.toArray()).equals(Arrays.toString(lareaAnswer))) {
    	System.out.println("l:area success.");
    	} else {
    	System.out.println("l:area failed.");
    	}
    	} catch (Exception e) {
    	System.out.println("l:area failed.");
    	}

    	// Surface Area
    	List<ThreeDPoint> lthreedpoint1 = new ArrayList<>();
    	lthreedpoint1.add(new ThreeDPoint(-0.5,-0.5,-0.5));
    	lthreedpoint1.add(new ThreeDPoint( 0.5,-0.5,-0.5));
    	lthreedpoint1.add(new ThreeDPoint( 0.5, 0.5,-0.5));
    	lthreedpoint1.add(new ThreeDPoint(-0.5, 0.5,-0.5));
    	lthreedpoint1.add(new ThreeDPoint(-0.5, 0.5, 0.5));
    	lthreedpoint1.add(new ThreeDPoint(-0.5,-0.5, 0.5));
    	lthreedpoint1.add(new ThreeDPoint( 0.5,-0.5, 0.5));
    	lthreedpoint1.add(new ThreeDPoint( 0.5, 0.5, 0.5));
    	List<ThreeDPoint> lthreedpoint2 = new ArrayList<>();
    	lthreedpoint2.add(new ThreeDPoint(0.0,0.0,0.0));
    	lthreedpoint2.add(new ThreeDPoint(3.0,0.0,0.0));
    	lthreedpoint2.add(new ThreeDPoint(3.0,4.0,0.0));
    	lthreedpoint2.add(new ThreeDPoint(0.0,4.0,0.0));
    	lthreedpoint2.add(new ThreeDPoint(0.0,4.0,2.0));
    	lthreedpoint2.add(new ThreeDPoint(0.0,0.0,2.0));
    	lthreedpoint2.add(new ThreeDPoint(3.0,0.0,2.0));
    	lthreedpoint2.add(new ThreeDPoint(3.0,4.0,2.0));

    	try {
    	Sphere lsphere1 = new Sphere(0.0, 0.0, 0.0, 1.0);
    	Sphere lsphere2 = new Sphere(-3.0, -3.0, -3.0, 1.5);

    	List<ThreeDShape> lsurfaceTest = new ArrayList<>();
    	lsurfaceTest.add(lsphere1);
    	lsurfaceTest.add(lsphere2);
    	ThreeDShape[] lsurfaceAnswer = { lsphere1, lsphere2};

    	lsurfaceTest.sort(new Ordering.SurfaceAreaComparator());
    	if (Arrays.toString(lsurfaceTest.toArray()).equals(Arrays.toString(lsurfaceAnswer))) {
    	System.out.println("l:area success.");
    	} else {
    	System.out.println("l:area failed.");
    	}

    	} catch (Exception e) {
    	System.out.println("l:surface area failed");
    	}
    	
    	double[] doub = {4,2,0,2,0,0,4,0};
    	List<TwoDPoint> listo = TwoDPoint.ofDoubles(doub); 
    	Rectangle r = new Rectangle(listo);
    	
    	Circle c = new Circle(3.0,4.0,7.0);
    	List<TwoDPoint> test1 = new ArrayList<>();
    	test1.add(new TwoDPoint(3.0,10.0));
    	c.setPosition(test1);
    	
    	List<TwoDPoint> test2 = new ArrayList<>();
    	test2.add(new TwoDPoint(32.0,0.0));
    	test2.add(new TwoDPoint(0.0,0.0));
    	test2.add(new TwoDPoint(0.0,-32.0));
    	test2.add(new TwoDPoint(32.0,-32.0));
    	Square s = new Square(test2);
    	
    	List<TwoDPoint> test3 = new ArrayList<>();
    	test3.add(new TwoDPoint(0.4,0.0));
    	test3.add(new TwoDPoint(0.0,0.0));
    	test3.add(new TwoDPoint(0.0,-0.4));
    	test3.add(new TwoDPoint(0.4,-0.4));
    	Square s3 = new Square(test3);
    	s3.snap(); //should return original because 
    			   // all points will be (x=0,y=0) after snap
    	s.snap();
    	
    	List<ThreeDPoint> threedp = new ArrayList<>();
    	threedp.add(new ThreeDPoint(10,10,10));
    	threedp.add(new ThreeDPoint(0,10,10));
    	threedp.add(new ThreeDPoint(0,0,10));
    	threedp.add(new ThreeDPoint(10,0,10));
    	threedp.add(new ThreeDPoint(10,0,20));
    	threedp.add(new ThreeDPoint(10,10,20));
    	threedp.add(new ThreeDPoint(0,10,20));
    	threedp.add(new ThreeDPoint(0,0,20));
    	
    	Cuboid cube = new Cuboid(threedp);
    	Sphere s1 = Sphere.random();
    	Sphere s2 = Sphere.random();
    	Cuboid cube1 = Cuboid.random();
    	Cuboid cube2 = Cuboid.random();
    	
        List<TwoDShape>          shapes          = new ArrayList<>();
        List<SymmetricTwoDShape> symmetricshapes = new ArrayList<>();
        List<ThreeDShape>        threedshapes    = new ArrayList<>();

        /*
         * uncomment the following block and fill in the "..." constructors to create actual instances. If your
         * implementations are correct, then the code should compile and yield the expected results of the various
         * shapes being ordered by their smallest x-coordinate, area, volume, surface area, etc. */
        
        TwoDPoint cent = (TwoDPoint) c.center();
		double x3=cent.getX();
        System.out.println(r.centerX() +"\n " + s.centerX()
        +"\n " +x3);
        
        symmetricshapes.add(new Rectangle(listo));
        symmetricshapes.add(new Square(test2));
        symmetricshapes.add(new Circle(2.0,4.0,6.0));

        copy(symmetricshapes, shapes); // note-1 //
        shapes.add(new Quadrilateral(new ArrayList<>(listo)));
        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationComparator());
        symmetricshapes.sort(new XLocationComparator());
        symmetricshapes.sort(new AreaComparator());

        System.out.println(s1.volume() + "\n" +
        s2.volume() + "\n" + cube1.volume() + "\n" + cube2.volume() + "\n" + cube.volume());
        threedshapes.add(cube);
        threedshapes.add(s1);
        threedshapes.add(s2);
        threedshapes.add(cube1);
        threedshapes.add(cube2);
        // sorting 3d shapes according to various criteria
        Collections.sort(threedshapes);
        threedshapes.sort(new SurfaceAreaComparator());

        /*
         * if your changes to copy() are correct, uncommenting the following block will also work as expected note that
         * copy() should work for the line commented with 'note-1' while at the same time also working with the lines
         * commented with 'note-2' and 'note-3'. */

        List<Number> numbers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();
        Set<Square>        squares = new HashSet<>();
        Set<Quadrilateral> quads   = new LinkedHashSet<>();

        copy(doubles, numbers); // note-2 //
        copy(squares, quads);   // note-3 //
        
    }
}
