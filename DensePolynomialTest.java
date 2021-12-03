import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DensePolynomialTest {

	@Test
	void testDensePolynomial() {
		DensePolynomial d1 = new DensePolynomial("5x^3 + 2x^2 + x + 10");
		assertNotNull(d1);
		
		DensePolynomial d2 = new DensePolynomial("5x^10 + 2x^3 + 10");
		assertNotNull(d2);
		
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial("5x^10 + 2x^3 + 5 + 4x^-1 + 6x^-2"));
		
		DensePolynomial d4 = new DensePolynomial("-5x^10 + 2x^4 + -3x^2 + -1");
		assertNotNull(d4);
		
		DensePolynomial d5 = new DensePolynomial("1");
		assertNotNull(d5);
		
		
		
	}

	@Test
	void testDegree() {
		
		DensePolynomial d1 = new DensePolynomial("2x^2 + x + 10");
		assertTrue(d1.degree() == 2);
		assertFalse(d1.degree() == 3);
		assertFalse(d1.degree() == 1);
		
		DensePolynomial d2 = new DensePolynomial("10");
		assertTrue(d2.degree() == 0);
		assertFalse(d2.degree() == 1);
	}

	@Test
	void testGetCoefficient() {
		DensePolynomial d1 = new DensePolynomial("7x^5 + 5x^3 + 2x^2 + x + 10");
		assertTrue(d1.getCoefficient(2) == 2);
		assertTrue(d1.getCoefficient(0) == 10);
		assertFalse(d1.getCoefficient(2) == 1);
		assertTrue(d1.getCoefficient(52890) == 0); //returns 0 because 52890 is not an exponent in the polynomial
		assertTrue(d1.getCoefficient(4) == 0);
		assertTrue(d1.getCoefficient(5) == 7);
		assertTrue(d1.getCoefficient(-5) == 0);
		
	}

	@Test
	void testIsZero() {
		DensePolynomial d1 = new DensePolynomial("1");
		DensePolynomial d2 = new DensePolynomial("0");
		DensePolynomial d3 = new DensePolynomial("-1");
		
		assertFalse(d1.isZero());
		assertTrue(d2.isZero());
		assertFalse(d3.isZero());
		
	}

	@Test
	void testAdd() {
		DensePolynomial d1 = new DensePolynomial("5x^3 + 2x^2 + x + 10");
		DensePolynomial d2 = new DensePolynomial("10x^3 + 2x^2 + 2x + 5");
		DensePolynomial d3 = new DensePolynomial("15x^3 + 4x^2 + 3x + 15");
		assertTrue(d1.add(d2).equals(d3));
		
		DensePolynomial d4 = new DensePolynomial("2x^3 + 5x");
		DensePolynomial d5 = new DensePolynomial("7x^3 + 2x^2 + 6x + 10");
		assertTrue(d4.add(d1).equals(d5));
		
		DensePolynomial d6 = new DensePolynomial("15x^5 + -10x^4 + 4x^2 + 3x + 15");
		DensePolynomial d7 = new DensePolynomial("15x^5 + -10x^4 + 2x^3 + 4x^2 + 8x + 15");
		assertTrue(d6.add(d4).equals(d7));
		
		DensePolynomial d8 = new DensePolynomial("5x^2 + 2x + 7");
		SparsePolynomial s1 = new SparsePolynomial("5x^2 + 10x + 5 + 3x^-1 + 2x^-2");
		assertThrows(IllegalArgumentException.class, 
				() -> d8.add(s1));
		
		DensePolynomial d9 = new DensePolynomial("5x^2 + 2x + 7");
		SparsePolynomial s3 = new SparsePolynomial("5x^2 + 10x + 5");
	    DensePolynomial d10 = new DensePolynomial("10x^2 + 12x + 12");
		assertTrue(d9.add(s3).equals(d10));
		
		DensePolynomial d11 = new DensePolynomial("5x^5 + 3x^3 + x");
		DensePolynomial d12 = new DensePolynomial("4x^4 + 2x^2 + 1");
		DensePolynomial d13 = new DensePolynomial("5x^5 + 4x^4 + 3x^3 + 2x^2 + x + 1");
		assertTrue(d11.add(d12).equals(d13));
		
		
	}

	@Test
	void testMultiply() {
		
		DensePolynomial d1 = new DensePolynomial("2x^10 + 5x^7");
		DensePolynomial d2 = new DensePolynomial("x^2");
		assertTrue(d1.multiply(d2).equals(new DensePolynomial("2x^12 + 5x^9")));
		
		DensePolynomial d3 = new DensePolynomial("5x^3 + 4x^2 + 6x + 5");
		DensePolynomial d4 = new DensePolynomial("3x^2 + 2x + 10");
		assertTrue(d3.multiply(d4).equals(new DensePolynomial("15x^5 + 22x^4 + 76x^3 + 67x^2 + 70x + 50")));
		
		DensePolynomial d5 = new DensePolynomial("2x^10 + 5x^7");
		SparsePolynomial s1 = new SparsePolynomial("x^-2");
		assertTrue(d5.multiply(s1).equals(new DensePolynomial("2x^8 + 5x^5"))); //even though the sparse has negative exponents, the result
																				//only has positive exponents, so it is a valid result.
		
		SparsePolynomial s2 = new SparsePolynomial("x^-11");
		assertThrows(IllegalArgumentException.class, () -> d5.multiply(s2)); //it will equal ("2x^-1 + 5x^-4"), which has negative exponents.
	}

	@Test
	void testSubtract() {
		DensePolynomial d1 = new DensePolynomial("5x^3 + 2x^2 + x + 10");
		DensePolynomial d2 = new DensePolynomial("10x^3 + 2x^2 + 2x + 5");
		DensePolynomial d3 = new DensePolynomial("-5x^3 + -x + 5");
		assertTrue(d1.subtract(d2).equals(d3));
		
		DensePolynomial d4 = new DensePolynomial("15x^5 + -10x^4 + 4x^2 + 3x + 15");
		DensePolynomial d5 = new DensePolynomial("15x^5 + -10x^4 + -10x^3 + 2x^2 + x + 10");
		assertTrue(d4.subtract(d2).equals(d5));
		
		DensePolynomial d9 = null;
		assertThrows(NullPointerException.class, () -> d1.subtract(d9));
	}

	@Test
	void testMinus() {
		DensePolynomial d1 = new DensePolynomial("5x^3 + 2x^2 + x + 10");
		DensePolynomial d2 = new DensePolynomial("-5x^3 + -2x^2 + -x + -10");
		DensePolynomial d3 = (DensePolynomial) d1.minus();
		
		assertTrue(d2.equals(d3));
		
		DensePolynomial d4 = new DensePolynomial("-1");
		assertTrue(d4.minus().equals(new DensePolynomial("1")));
		
		DensePolynomial d5 = new DensePolynomial("0");
		assertTrue(d5.minus().equals(new DensePolynomial("0")));
		
		DensePolynomial d6 = new DensePolynomial("15x^5 + -10x^4 + 4x^2 + -3x + 15");
		DensePolynomial d7 = new DensePolynomial("-15x^5 + 10x^4 + -4x^2 + 3x + -15");
		assertTrue(d6.minus().equals(d7));
		
	}

	@Test
	void testWellFormed() {
		DensePolynomial d1 = new DensePolynomial("54590879x^3 + 2x^2 + x + 10");
		DensePolynomial d2 = new DensePolynomial("5x^634 + 2x^255 + x + 10");
		assertTrue(d1.wellFormed());
		assertTrue(d2.wellFormed());
		
		DensePolynomial d3 = new DensePolynomial("-0");
		assertTrue(d3.wellFormed());
		
		DensePolynomial d4 = new DensePolynomial("x");
		assertTrue(d4.wellFormed());
		
		//Any time the constructor throws IllegalArgumentException, wellFormed is returning false.
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial("5x^2 + + 1"));
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial("x^2 + x + "));
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial("2x^3 + 1 + 3x"));
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial("5x^634.910238 + 2x^255.5 + x + 10"));
		assertThrows(IllegalArgumentException.class,
				() -> new DensePolynomial(""));
		
	}
	
	@Test
	void testToString() {
		String s1 = "-2x + -1";
		String s4 = "8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 10";
		DensePolynomial d1 = new DensePolynomial(s1);
		DensePolynomial d2 = new DensePolynomial("100x^50 + 1");
		DensePolynomial d4 = new DensePolynomial(s4);
		DensePolynomial d5 = new DensePolynomial(d4.toString());
		DensePolynomial d6 = new DensePolynomial("x^5");

		assertTrue(d1.toString().equals("-2x + -1"));
		assertFalse(d1.toString().equals("2x - 1"));
		assertFalse(d1.toString().equals("-2x + 1"));
		assertTrue(d2.toString().equals("100x^50 + 1"));
		assertTrue(d4.toString().equals("8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 10"));
		assertTrue(d4.equals(d5));
		assertTrue(d6.toString().equals("x^5"));

	}
	
	@Test
	void testEquals() {
		DensePolynomial d1 = new DensePolynomial("8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 10");
		DensePolynomial d2 = new DensePolynomial("8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 10");
		DensePolynomial d3 = new DensePolynomial("8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 11");
		DensePolynomial d4 = new DensePolynomial("8x^9 + 5x^8 + 12x^6 + 14x^4 + 2x^2 + x + 11");
				
		assertTrue(d1.equals(d2));
		assertFalse(d2.equals(d3));
		assertFalse(d3.equals(d4));
		assertFalse(d3.equals(new SparsePolynomial("8x^9 + 5x^7 + 12x^6 + 14x^4 + 2x^2 + x + 11")));
		
		DensePolynomial d5 = null;
		
		assertFalse(d1.equals(d5));
		
	}

}
