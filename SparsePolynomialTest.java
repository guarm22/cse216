import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SparsePolynomialTest {

	@Test
	void testSparsePolynomial() {
		SparsePolynomial s1 = new SparsePolynomial("5x^4 + 2x^2 + 2x + 5");
		assertNotNull(s1);
		
		SparsePolynomial d2 = new SparsePolynomial("5x^10 + 2x^3 + 10");
		assertNotNull(d2);
		
		SparsePolynomial d4 = new SparsePolynomial("-5x^10 + 2x^4 + -3x^2 + -1");
		assertNotNull(d4);
		
		SparsePolynomial d5 = new SparsePolynomial("1");
		assertNotNull(d5);
		
		SparsePolynomial s6 = new SparsePolynomial("-7x^10 + 2x^4 + -x^2 + -x + -1 + -2x^-3 + 5x^-15");
		assertNotNull(s6);
		
		Polynomial s2 = new SparsePolynomial("x + 1");
		assertNotNull(s2);
		
		assertThrows(IllegalArgumentException.class, () -> new SparsePolynomial("I am a polynomial"));
		
	}

	@Test
	void testDegree() {
		SparsePolynomial s1 = new SparsePolynomial("7x^5 + 3x^4 + 2x^2 + 2x + 5 + x^-2 + 6x^-4 + 12x^-6");
		assertTrue(s1.degree() == 5);
		
		SparsePolynomial s2 = new SparsePolynomial("2x^2 + 10");
		assertTrue(s2.degree() == 2);
		assertFalse(s2.degree() == 3);
		assertFalse(s2.degree() == 1);
		
		SparsePolynomial s3 = new SparsePolynomial("10");
		assertTrue(s3.degree() == 0);
		assertFalse(s3.degree() == 1);
		
	}

	@Test
	void testGetCoefficient() {
		SparsePolynomial s1 = new SparsePolynomial("2x^2 + 2x + 5");
		SparsePolynomial s2 = new SparsePolynomial("2x^4 + 3x^2 + 2x + 7 + 2x^-2");
		assertTrue(s1.getCoefficient(2) == 2);
		assertTrue(s2.getCoefficient(-2) == 2);
		assertTrue(s2.getCoefficient(0) == 7);
		assertTrue(s2.getCoefficient(3) == 0);
	}

	@Test
	void testIsZero() {
		SparsePolynomial s1 = new SparsePolynomial("0");
		assertTrue(s1.isZero());
		SparsePolynomial s2 = new SparsePolynomial("1");
		assertFalse(s2.isZero());
		SparsePolynomial s3 = new SparsePolynomial("-1");
		assertFalse(s3.isZero());
		
	}

	@Test
	void testAdd() {
		SparsePolynomial s1 =         new SparsePolynomial("x^2 + 2x + 5");
		SparsePolynomial s2 = new SparsePolynomial("2x^4 + 3x^2 + 2x + 5 + 2x^-2");
		SparsePolynomial s3 = new SparsePolynomial("2x^4 + 4x^2 + 4x + 10 + 2x^-2");
		assertTrue(s1.add(s2).equals(s3));
		
		SparsePolynomial s4 = new SparsePolynomial("3x^2 + 5x + 2");
		DensePolynomial d1 = new DensePolynomial(  "5x^2 + 2x + 10");
		SparsePolynomial s5 = new SparsePolynomial("8x^2 + 7x + 12");
		assertTrue(s4.add(d1).equals(s5));
		
		DensePolynomial d8 = new DensePolynomial("5x^2 + 2x + 7");
		SparsePolynomial s7 = new SparsePolynomial("10x^2 + 12x + 12 + 3x^-1 + 2x^-2"); 
		assertThrows(IllegalArgumentException.class,
				() -> d8.add(s7));
		
		DensePolynomial d9 = new DensePolynomial("5x^2 + 2x + 7");
		SparsePolynomial s8 = new SparsePolynomial("5x^2 + 10x + 5");
	    SparsePolynomial s10 = new SparsePolynomial("10x^2 + 12x + 12");
		assertTrue(s8.add(d9).equals(s10));
		
		DensePolynomial d10 = new DensePolynomial("5x^2 + 2x + 7");
		SparsePolynomial s11 = new SparsePolynomial("5x^2 + 10x + 5 + 3x^-2 + -5x^-6");
	    SparsePolynomial s12 = new SparsePolynomial("10x^2 + 12x + 12 + 3x^-2 + -5x^-6");
		assertTrue(s11.add(d10).equals(s12));
		
		SparsePolynomial s13 = null;
		assertThrows(NullPointerException.class, () -> s12.add(s13));
		
	}

	@Test
	void testMultiply() { 
		SparsePolynomial s1 = new SparsePolynomial("2x^10 + 5x^7");
		SparsePolynomial s2 = new SparsePolynomial("x^2");
		assertTrue(s1.multiply(s2).equals(new SparsePolynomial("2x^12 + 5x^9")));
		
		SparsePolynomial s3 = new SparsePolynomial("5x^3 + 4x^2 + 6x + 5");
		SparsePolynomial s4 = new SparsePolynomial("3x^2 + 2x + 10");
		
		assertTrue(s3.multiply(s4).equals(new SparsePolynomial("15x^5 + 22x^4 + 76x^3 + 67x^2 + 70x + 50")));
		
		SparsePolynomial s5 = new SparsePolynomial("5x^5 + 2x^3 + 1");
		DensePolynomial d1 = new DensePolynomial("2x^2");
		assertTrue(s5.multiply(d1).equals(new SparsePolynomial("10x^7 + 4x^5 + 2x^2")));
		
		SparsePolynomial s6 = null;
		assertThrows(NullPointerException.class, () -> s5.multiply(s6));
		
	}

	@Test
	void testSubtract() {
		SparsePolynomial s2 = new SparsePolynomial("2x^4 + 3x^2 + 2x + 5");
		SparsePolynomial s1 =         new SparsePolynomial("x^2 + 2x + 5");
		SparsePolynomial s3 = new SparsePolynomial("-2x^4 + -2x^2");
		assertTrue(s1.subtract(s2).equals(s3));
		
		SparsePolynomial s4 = new SparsePolynomial("2x^4 + 1 + -3x^-2");
		SparsePolynomial s5 = new SparsePolynomial("3x^4 + 6x^-2");
		SparsePolynomial s6 = new SparsePolynomial("-x^4 + 1 + -9x^-2");
		assertTrue(s4.subtract(s5).equals(s6));
		
		SparsePolynomial s7 = new SparsePolynomial("-5x^7 + 1512x^5 + -x + -1 + 9x^-2 + 3x^-4");
		SparsePolynomial s8 = new SparsePolynomial("5x^7 + 1511x^5 + -2x + -1 + 9x^-2 + -6x^-4");
		SparsePolynomial s9 = new SparsePolynomial("-10x^7 + x^5 + x + 9x^-4");
		assertTrue(s7.subtract(s8).equals(s9));
		
		SparsePolynomial s10 = new SparsePolynomial("2x + 5 + -3x^-2");
		DensePolynomial d1 = new DensePolynomial("x + 1");
		SparsePolynomial s11 = new SparsePolynomial("x + 4 + -3x^-2");
		assertTrue(s10.subtract(d1).equals(s11));
		
		SparsePolynomial s12 = null;
		assertThrows(NullPointerException.class, () -> s5.multiply(s12));
		
	}

	@Test
	void testMinus() {
		SparsePolynomial s1 = new SparsePolynomial("2x^2 + 2x + 5");
		SparsePolynomial s2 = new SparsePolynomial("-2x^2 + -2x + -5");
		assertTrue(s1.minus().equals(s2));
		
		SparsePolynomial s3 = new SparsePolynomial("-5x^7 + 1512x^5 + -x + -1 + 9x^-2 + -3x^-4");
		SparsePolynomial s4 = new SparsePolynomial("5x^7 + -1512x^5 + x + 1 + -9x^-2 + 3x^-4");
		assertTrue(s3.minus().equals(s4));
	}

	@Test
	void testWellFormed() {
		new SparsePolynomial("2x^2 + 2x + 4");
		SparsePolynomial s1 = new SparsePolynomial("2x^2 + 2x + 4");
		assertTrue(s1.wellFormed());
		
		SparsePolynomial s2 = new SparsePolynomial("-5x^7 + 1512x^5 + -x + -1 + 9x^-2 + -3x^-4");
		assertTrue(s2.wellFormed());
		
		assertThrows(IllegalArgumentException.class, () -> new SparsePolynomial("5.5x^3 + 12 + 2x^-3.2"));
		
		assertThrows(IllegalArgumentException.class, () -> new SparsePolynomial("fivex^3 + 12 + 2x^three"));
		
		assertThrows(IllegalArgumentException.class, () -> new SparsePolynomial("55x^3 + 12 + 2x^354"));
		
	}

	@Test
	void testEquals() {
		SparsePolynomial s1 = new SparsePolynomial("2x^2 + 2x + 5");
		SparsePolynomial s2 = new SparsePolynomial("2x^2 + 2x + 5");
		SparsePolynomial s3 = new SparsePolynomial("2x^2 + 2x + 4");
		SparsePolynomial s4 = new SparsePolynomial("2x^3 + 2x + 4");
		assertTrue(s1.equals(s2));
		assertFalse(s2.equals(s3));
		assertFalse(s3.equals(s4));
		
		Polynomial s5 = new SparsePolynomial("x + 1");
		SparsePolynomial s6 = new SparsePolynomial(s5.toString());
		assertTrue(s5.equals(s6));
		
	}

	@Test
	void testToString() {
		SparsePolynomial s2 = new SparsePolynomial("2x^4 + 3x^2 + 2x + 5");
		assertTrue(s2.toString().equals("2x^4 + 3x^2 + 2x + 5"));
		
		SparsePolynomial s6 = new SparsePolynomial("-7x^10 + 2x^4 + -x^2 + -x + -1 + 2x^-3 + 5x^-15");
		assertTrue(s6.toString().equals("-7x^10 + 2x^4 + -x^2 + -x + -1 + 2x^-3 + 5x^-15"));
		
	}

}
