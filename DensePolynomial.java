import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DensePolynomial implements Polynomial {
	
	private Integer[] coefficients;
	private Integer[] exponents; 
	private String s;

	/**
	 * Sets the coefficients and exponents of a given polynomial instance based on the String s.
	 * Throws an {@link IllegalArgumentException} if the string is empty or null, or if the polynomial is not wellFormed or
	 * not in canonical form.
	 * 
	 * @param s
	 */
	public DensePolynomial(String s) {
		this.s = s;
		if(!wellFormed()) {
			throw new IllegalArgumentException("Polynomial not well formed! (class invariant does not hold)");
		}
		create(s);	
	}
	private DensePolynomial(Integer[] expo, Integer[] coef) {
		this.exponents=expo;
		this.coefficients=coef;
	}
	
	/**
	 * Sets the coefficients and exponents of a given polynomial instance based on the String s.
	 * Throws an {@link IllegalArgumentException} if the string is empty or null.
	 * 
	 * @param s
	 */
	private void create(String s) {
		if(s.length()==0 || s == null) {
			throw new IllegalArgumentException("Not enough information to form a valid polynomial.");
		}
		String[] s2 = s.replaceAll("[ ^]", "").split("[x+]");
		int t1 = s2.length%2 == 0 ? 0 : 1;
		
		Integer[] test1 = new Integer[(s2.length/2) +t1];
		Integer[] expo = new Integer[(s2.length/2) +t1];
		
		if(s.charAt(s.length()-1) == 'x') {
			test1 = new Integer[(s2.length/2) +1];
			expo = new Integer[(s2.length/2) +1];
			test1[test1.length-1] = 1;
			expo[expo.length-1] = 1;
		}
		int x = 0;	
		boolean even = true;
		try {
			for(int i=0; i<=s2.length; i++) {
				if(i==s2.length && expo[expo.length-1] != null && test1[test1.length-1] != null) {
					continue;
				}
				if(i == s2.length && test1[x] != null && s.charAt(s.length()-1) == 'x') {
					expo[x] = 1;
					break;
				}
				if(s2.length==2 && s2[0].equals("")) {
					test1[0] = 1;
					expo[0] = Integer.parseInt(s2[1]);
					break;
				}
				if(t1 == 0 && i==s2.length) {
					even = !even;
					continue;
				}
				if(even) {
					if(x == expo.length) {
						x--;
					}
					if(s2[i].equals("")) {
						test1[x] = 1;
						even = !even;
						continue;
						}	
					else if(s2[i].equals("-")) {
						test1[x] = -1;
						even = !even;
						continue;
					}	
					if(s.contains("+ " + s2[i] + " +") && Integer.parseInt(s2[i+2])<0 && i<s2.length-3) {
						expo[x] = 0;
						test1[x] = Integer.parseInt(s2[i]);
						x++;
						test1[x] = Integer.parseInt(s2[i+1]);
						i++;
						even = !even;
						continue;
					}
					test1[x] = Integer.parseInt(s2[i]);
					even = !even;
				}
				else if(i == s2.length && test1[x] != null) {
					expo[x] = 0;
				}
				else {
					if(s2[i].equals("")) {
						expo[x] = 1;
						x++;
						even = !even;
						continue;
					}
					else if(s2[i-1].equals("") && !s2[i].equals("")) {
						expo[x]=Integer.parseInt(s2[i]);
						x++;
						even = !even;
						continue;
					}
					else if(s2[i-1].equals("") ) {
						expo[x]=0;
						x++;
						even = !even;
						continue;
					}
					expo[x] = Integer.parseInt(s2[i]);
					x++;
					even = !even;
					}
				}
			}
		catch(Exception e) {
			throw new IllegalArgumentException("Invalid input for a polynomial");
		}
		int count = 0;
		for(int i=0; i<expo.length-1; i++) {
			if(expo[i+1] > expo[i] || expo[i+1]==expo[i]) {
				throw new IllegalArgumentException("Polynomial not in canonical form!");
			}
			if(expo[i+1] != expo[i]-1) {
				count++;
			}
		}
		x=test1.length-1;
		int y = expo[expo.length-1] < 0 ? expo[expo.length-1] : 0;
		List<Integer> coef = new ArrayList<>();
		for(int i=expo.length+count; i>=0; i--) {		
			if(x<0) {
				continue;
			}		
			while(expo[x] != y) {
				coef.add(0);
				y++;
			}
			coef.add(test1[x]);
			x--;
			y++;	
		}
		this.coefficients = coef.toArray(new Integer[coef.size()]);
		this.exponents = expo;
	}

	/**
	 * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient. If all terms have zero exponents, it returns 0.
	 */
	@Override
	public int degree() {
		return this.exponents[0];
	}

	/**Returns a coefficient based on a given exponent value. Returns 0 if there are no terms with that exponent.
	 */
	@Override
	public int getCoefficient(int d) {	
		return Arrays.asList(this.exponents).contains(d) ? this.coefficients[d] : 0;
	}
    
	/**Returns true or false based on whether or not this polynomial has 0x^0 as its only term.
	 */
	@Override
	public boolean isZero() {
		return this.coefficients[0] == 0 && this.coefficients.length == 1;
	}

	/**
	 * Returns a polynomial by adding the parameter to the current instance. Neither the current instance nor the
     * parameter are modified 
     * 
	 * Throws a {@link NullPointerException} if either q is null.
	 * 
	 * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
	 */
	@Override
	public Polynomial add(Polynomial q) {
		if(q==null) {
			throw new NullPointerException("Null polynomial given.");
		}
		DensePolynomial q2 = new DensePolynomial(q.toString());
		List<Integer> coefs = new ArrayList<>();
		List<Integer> expos = new ArrayList<>();
		int k = q2.coefficients.length < this.coefficients.length ? this.coefficients.length : q2.coefficients.length;
		
		for(int i=0; i<k; i++) {
			if(q2.coefficients.length < this.coefficients.length) {
				if(i>=q2.coefficients.length) {
					coefs.add(this.coefficients[i]);
				}
				else {
					coefs.add(this.coefficients[i] + q2.coefficients[i]);
				}
			}
			else {
				if(i>=this.coefficients.length) {
					coefs.add(q2.coefficients[i]);
				}
				else {
					coefs.add(this.coefficients[i] + q2.coefficients[i]);
				}
			}
		}
		for(int i=coefs.size()-1; i>=0; i--) {
			if(coefs.get(i) != 0) {
				expos.add(i);
			}
		}
		return new DensePolynomial(expos.toArray(new Integer[expos.size()]), coefs.toArray(new Integer[coefs.size()]));
	}

	@Override
	/**
     * Returns a polynomial by multiplying the parameter with the current instance.  Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     */
	public Polynomial multiply(Polynomial q) {
		if(q==null) {
			throw new NullPointerException("Null polynomial given.");
		}
		Iterator<Integer> iter = new SparsePolynomial(q.toString()).getData().keySet().iterator();
		while(iter.hasNext()) {
			if(iter.next() < 0) {
				SparsePolynomial s2 = (SparsePolynomial) new SparsePolynomial(q.toString()).multiply(this);
				Iterator<Integer> iterS2 = s2.getData().keySet().iterator();
				while(iterS2.hasNext()) {
					if(iterS2.next() < 0) {
						throw new IllegalArgumentException("Cannot do this operation with this polynomial instance.");
					}
				}
				return new DensePolynomial(s2.toString());
			}
		}
		DensePolynomial q2 = new DensePolynomial(q.toString());
		
		List<Integer> expos = new ArrayList<>();
		Integer[] prod = new Integer[q2.coefficients.length+this.coefficients.length-1]; 
		for(int i = 0; i<q2.coefficients.length+this.coefficients.length-1; i++) {
			prod[i] = 0; 
		}
		for(int i=0; i<this.coefficients.length; i++) { 
			
		    for(int j=0; j<q2.coefficients.length; j++) {
		        prod[i+j] += this.coefficients[i]*q2.coefficients[j]; 
		    }
		} 
	    for(int i=prod.length-1; i>=0; i--) {
			if(prod[i] != 0) {
				expos.add(i);
		    }
		}
		 List<Integer> coefs = Arrays.asList(prod);
		 return new DensePolynomial(expos.toArray(new Integer[expos.size()]), coefs.toArray(new Integer[coefs.size()]));
	}

	@Override
	/**
     * Returns a  polynomial by subtracting the parameter from the current instance. Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     */
	public Polynomial subtract(Polynomial q) {
		if(q==null) {
			throw new NullPointerException("Null polynomial given.");
		}
		List<Integer> coefs = new ArrayList<>();
		List<Integer> expos = new ArrayList<>();
		DensePolynomial q2 = new DensePolynomial(q.toString());
		int k = q2.coefficients.length<this.coefficients.length ? this.coefficients.length : q2.coefficients.length;
		
		for(int i=0; i<k; i++) {
			if(q2.coefficients.length < this.coefficients.length) {
				if(i>=q2.coefficients.length) {
					coefs.add(this.coefficients[i]);
				}
				else {
					coefs.add(this.coefficients[i] - q2.coefficients[i]);
				}
			}
			else {
				if(i>=this.coefficients.length) {
					coefs.add(q2.coefficients[i]);
				}
				else {
					coefs.add(this.coefficients[i] - q2.coefficients[i]);
				}
			}
		}
		for(int i=coefs.size()-1; i>=0; i--) {
			if(coefs.get(i) != 0) {
				expos.add(i);
			}
		}
		return new DensePolynomial(expos.toArray(new Integer[expos.size()]), coefs.toArray(new Integer[coefs.size()]));
	}

	/**
	 * Returns the negation of the polynomial.
	 * 
	 * @return -this
	 */
	@Override
	public Polynomial minus() {	
		Integer[] negCoefs = new Integer[this.coefficients.length];
		for(int i=0; i<negCoefs.length; i++) {
			if(this.coefficients[i] == 0) {
				negCoefs[i] = 0;
				continue;
			}
			negCoefs[i] = this.coefficients[i] * -1;
		}
		return new DensePolynomial(this.exponents, negCoefs);
	}

	@Override
	 /**
     * Checks if the class invariant holds for the current instance.
     * Throws {@link NullPointerException} if this is null. 
     *
     * @return {@literal true} if the class invariant holds, and {@literal false} otherwise.
     */
	public boolean wellFormed() {
		if(s.contains("^-") || s.contains("+  ") || s.contains("+ +") || s.contains("++") || s.contains("--") ||
				s.contains("x  ") || s.contains("  +")||
				s.endsWith(" ")|| s.endsWith("+") || s.endsWith("-") ||  s.contains("^1 ")|| 
				s.contains("^0") || s.endsWith("+ 0") || s.contains(" 0x")) {
			return false;
		}
		String[] s2 = s.replaceAll("[ ^]", "").split("[x+]");
		for(int i=0; i<s2.length; i++) {
			if(s2[i].equals("") || s2[i].equals("-")) {
				continue;
			}
			else {
				try {
					if(s2.length==1) {
						Integer.parseInt(s2[0]);
					}
					else if(!s.contains(" ") && s2.length!=2) {
						return false;
					}
					Integer.parseInt(s2[i]);
				} catch(Exception e) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns a human-readable representation of a DensePolynomial.
	 */
	public String toString() {
		if(this.coefficients.length==1 && this.exponents[0]==0) {
			return this.coefficients[0] +"";
		}
		else if(this.coefficients.length==1 && this.exponents[0]==1) {
			return this.coefficients[0] + "x";
		}
		else if(this.coefficients.length==1 && this.exponents[0]>1 && this.coefficients[0]==1) {
			return "x^" + this.exponents[0];
		}
		else if(this.coefficients.length==1 && this.exponents[0]>1) {
			return this.coefficients[0] + "x^" + this.exponents[0];
		}
		String s = "";
		int x = this.coefficients.length-1;
		int y = 0;
		for(int i=0; i<this.coefficients.length; i++) {	
			if(this.coefficients[x]==0) {
				x--;
				continue;
			}		
			else if(i < this.coefficients.length-2) {
				if(this.coefficients[x] == 1) {
					s+= "x^" + this.exponents[y] + " + ";
					y++;
					x--;
					continue;
				}
				s+= this.coefficients[x] + "x^" + this.exponents[y] + " + ";
				y++;
				x--;
			}
			else if(i == this.coefficients.length-2) {	
				if(this.coefficients[x] == 1) {
					s+= "x" + " + ";
					x--;
					continue;
				}
				s+= this.coefficients[x] + "x + ";
				x--;
			}
			else if (i == this.coefficients.length-1) {
				s+= this.coefficients[x];
			}
		}
		if(s.substring(s.length()-3, s.length()).equals(" + ")) {
			s = new String(s.substring(0, s.length()-3));
		}
		return s;
	}
	
	/**
	 * Checks the equality of two DensePolynomials.
	 * @return true if the objects are equal, false if the objects are not equal.
	 */
	public boolean equals(Object o) {
		if(o instanceof DensePolynomial == false) {
			return false;
		}
		DensePolynomial d1 = new DensePolynomial(o.toString());
		if(this.coefficients.length!=d1.coefficients.length) {
			return false;
		}
		else {
			for(int i=0; i<this.coefficients.length; i++) {
				if(this.coefficients[i]!=d1.coefficients[i]) {
					return false;
				}
			}
		}
		return true;
	}
}
