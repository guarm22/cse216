import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SparsePolynomial implements Polynomial {
	
	private Map<Integer, Integer> data;
	private String s;
	
	/**
	 * Sets the coefficients and exponents of a given polynomial instance based on the String s.
	 * Throws an {@link IllegalArgumentException} if the string is empty or null or the polynomial is not well formed or
	 * not in canonical form.
	 * 
	 * @param s
	 */

	public SparsePolynomial(String s) {
		this.s=s;
		if(!wellFormed()) {
			throw new IllegalArgumentException("Polynomial not well formed! (class invariant does not hold)");
		}
		create(s);
	}
	
	private SparsePolynomial(TreeMap<Integer, Integer> hm) {
		assert hm.size() != 0;
		this.data = hm;
	}
	
	/**
	 * @return the data of this SparsePolynomial which is a Map<Integer(exponents), Integer(coefficients)>.
	 */
	public Map<Integer, Integer> getData() {
		return this.data;
	}

	/**
	 * Sets the coefficients and exponents of a given polynomial instance based on the String s.
	 * Throws an {@link IllegalArgumentException} if the string is empty or null.
	 * 
	 * @param s
	 */
	private void create(String s) {
		Map<Integer, Integer> hm = new TreeMap<>(Collections.reverseOrder());
		if(s.length()==0 || s == null) {
			throw new IllegalArgumentException("Not enough information to form a valid polynomial.");
		}
		String[] s2 = s.replaceAll("[ ^]", "").split("[x+]");
		for(int i=0; i<s2.length; i++) {
			
			if(i<s2.length-2) {
				if(s.contains(" " + s2[i] + " ") && Integer.parseInt(s2[i+2]) < 0 && s2[i+1].equals("")==false) {
					hm.put(0, Integer.parseInt(s2[i]));
					continue;
				}
			}
			if(s2[i].equals("-") && s2[i+1].equals("")) {
				hm.put(1, -1);
				i++;
				continue;
			}
			if(s2.length==1) {
				hm.put(0, Integer.parseInt(s2[0]));
				break;
			}
			if(i == s2.length-1 && s.charAt(s.length()-1) == 'x') {
				hm.put(1, Integer.parseInt(s2[i]));
				break;
			}
			if(i!=0 && i+1 == s2.length) {
				if(s2[i-1].equals("")) {
					hm.put(0, Integer.parseInt(s2[i]));
					continue;
				}
			}
			else if(s2[i+1].equals("") && s2[i].equals("") == false) {
				hm.put(1, Integer.parseInt(s2[i]));
				i++;
				continue;
			}
			if(i<s2.length-2) {
				if(i>0 && s2[i+2].equals("")==false) {
					if(s2[i-1].equals("") && Integer.parseInt(s2[i+2])<0) {
						hm.put(0, Integer.parseInt(s2[i]));
						continue;
					}
				}
			}
			if(i==s2.length-1) {
				hm.put(0, Integer.parseInt(s2[i]));
				break;
			}
			if(s2[i+1].equals("") && s2[i].equals("")) {
				hm.put(1, 1);
				i++;
				continue;
			}
			if(s2[i+1].equals("")) {
				hm.put(1, Integer.parseInt(s2[i]));
				i++;
				continue;
			}
			
			if(s2.length == 1) {
				hm.put(0, Integer.parseInt(s2[i]));
				break;
			}
			else if(s2[i].equals("")) {
				hm.put(Integer.parseInt(s2[i+1]), 1);
				i++;
				continue;
			}
			else if(s2[i].equals("-")) {
				hm.put(Integer.parseInt(s2[i+1]), -1);
				i++;
				continue;
			}
			hm.put(Integer.parseInt(s2[i+1]), Integer.parseInt(s2[i]));
			i++;
		}
		this.data = hm;
	}

	 /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
	@Override
	public int degree() {
		return Collections.max(this.data.keySet());
	}

	/**
     * Returns the coefficient corresponding to the given exponent.  Returns 0 if there is no term with that exponent
     * in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     */
	@Override
	public int getCoefficient(int d) {		
		return data.containsKey(d) ? data.get(d) : 0;
	}

	@Override
	/**
     * @return true if the polynomial represents the zero constant. False if not.
     */
	public boolean isZero() {	
		return data.containsKey(0) && data.containsValue(0) && data.entrySet().size()==1;
	}

	 /**
     * Returns a polynomial by adding the parameter to the current instance. Neither the current instance nor the
     * parameter are modified.
     *
     * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
     * @throws NullPointerException if q is null
     */
	@Override
	public Polynomial add(Polynomial q) {
		if(q == null) {
			throw new NullPointerException("Null polynomial given.");
		}
		SparsePolynomial s1 = new SparsePolynomial(q.toString());
		
		TreeSet<Integer> keys = new TreeSet<>();
		keys.addAll(this.data.keySet());
		keys.addAll(s1.data.keySet());
		Iterator<Integer> iter = keys.iterator();
		
		TreeMap<Integer, Integer> hm = new TreeMap<>(Collections.reverseOrder());
		for(int i=0; i<keys.size(); i++) {
			Integer k = iter.next();
			if(!this.data.containsKey(k)) {
				hm.put(k, s1.data.get(k));
				continue;
			}
			else if(!s1.data.containsKey(k)) {
				hm.put(k, this.data.get(k));
				continue;
			}
			hm.put(k, this.data.get(k) + s1.data.get(k));
		}
		return new SparsePolynomial(hm);
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
		if(q == null) {
			throw new NullPointerException("Null polynomial given.");
		}
		SparsePolynomial s1 = new SparsePolynomial(q.toString());
		
		TreeMap<Integer, Integer> hm = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		Iterator<Integer> thisIter = this.data.keySet().iterator();
		for(int i=0; i<this.getData().size(); i++) {
			
			Iterator<Integer> otherIter = s1.data.keySet().iterator();
			Integer k = thisIter.next();
			while(otherIter.hasNext()) {
				Integer x= otherIter.next();
				
				if(hm.containsKey(k+x)) {
					hm.put(k+x, hm.get(k+x) + (this.data.get(k) * s1.data.get(x)));
					continue;
				}
				hm.put(k+x, this.data.get(k) * s1.data.get(x));
			}	
		}
		return new SparsePolynomial(hm);
	}

	 /**
     * Returns a  polynomial by subtracting the parameter from the current instance. Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     */
	@Override
	public Polynomial subtract(Polynomial q) {
		if(q == null) {
			throw new NullPointerException("Null polynomial given.");
		}
		SparsePolynomial s1 = new SparsePolynomial(q.toString());
		TreeSet<Integer> keys = new TreeSet<>();
		keys.addAll(this.data.keySet());
		keys.addAll(s1.data.keySet());
		Iterator<Integer> iter = keys.iterator();
		
		TreeMap<Integer, Integer> hm = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		
		for(int i=0; i<keys.size(); i++) {
			Integer k = iter.next();
			
			if(!this.data.containsKey(k)) {
				hm.put(k, -s1.data.get(k));
				continue;
			}
			else if(!s1.data.containsKey(k)) {
				hm.put(k,  this.data.get(k));
				continue;
			}	
			if(this.data.get(k) - s1.data.get(k) == 0) {
				continue;
			}		
			hm.put(k, this.data.get(k) - s1.data.get(k));
		}		
		return new SparsePolynomial(hm);
	}

	 /**
     * Returns a polynomial by negating the current instance. The current instance is not modified.
     *
     * @return -this
     */
	@Override
	public Polynomial minus() {
		Iterator<Integer> iter = this.data.keySet().iterator();
		TreeMap<Integer, Integer> hm = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		for(int i=0; i<this.data.keySet().size(); i++) {
			Integer x = iter.next();
			hm.put(x, -1*data.get(x));
		}
		return new SparsePolynomial(hm);
	}

	@Override
	 /**
     * Checks if the class invariant holds for the current instance.
     *
     * @return {@literal true} if the class invariant holds, and {@literal false} otherwise.
     */
	public boolean wellFormed() {
		String[] s2 = s.replaceAll("[ ^]", "").split("[x+]");
		int lastExpo = Integer.MIN_VALUE;
		boolean first = true;
		boolean even = false;

		for(int i=0; i<s2.length; i++) {
			if(s2[i].equals("") || s2[i].equals("-")) {
				continue;
			}
			else {
				try {
					boolean test = even ? i%2==0 : i%2==1;
					if(s2.length==1) {
						Integer.parseInt(s2[0]);
						return true;
					}
					if(i<s2.length-2) {
						if(s.contains(" " + s2[i] + " ") && s2[i+1].equals("")==false) {
							even = true;
							continue;
						}
						else if(s.contains(" " + s2[i] + " ") && s2[i-1].equals("")) {
							even = true;
							continue;
						}
					}
					if(s.contains("^"+s2[i]) && test) {
						if(first) {
							lastExpo=Integer.parseInt(s2[i]);
							first=false;
						}
						if(lastExpo < Integer.parseInt(s2[i])) {
							return false;
						}
						lastExpo = Integer.parseInt(s2[i]);
					}
					if(s.contains("+  ") || s.contains("+ +") || s.contains("++") || s.contains("--") ||
							(!s.contains(" ") && s2.length!=2) || s.contains("x  ") || s.contains("  +")||
							s.endsWith(" ") || s.endsWith("+") || s.endsWith("-") || s.endsWith("^1") || s.contains("^1 ")|| 
							s.contains("^0") || s.endsWith("+ 0") || s.contains(" 0x")) {
						return false;
					}
					Integer.parseInt(s2[i]);
				} catch(NumberFormatException e) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks the equality of two SparsePolynomials.
	 * 
	 * Throws a {@link NullPointerException} if either Object is null.
	 * @return false if the object is not a SparsePolynomial or not equal to this. true if they are equal.
	 */
	public boolean equals(Object o) {
		if(o instanceof SparsePolynomial == false) {
			return false; 
		}
		SparsePolynomial s = new SparsePolynomial(o.toString());
		Integer[] s3 = s.data.values().toArray(new Integer[data.size()]);
		Integer[] s4 = this.data.values().toArray(new Integer[data.size()]);
		
		if(s3.length != s4.length || !s.data.keySet().equals(this.data.keySet())) {
			return false;
		}
		for(int i=0; i<s3.length; i++) {
			if(s3[i].equals(s4[i])) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a human-readable representation of a SparsePolynomial.
	 */
	public String toString() {
		
		List<Integer> keys = new ArrayList<>();
		keys.addAll(this.data.keySet());
		List<Integer> coefs = new ArrayList<>();
		coefs.addAll(this.data.values());
		
		Iterator<Integer> coefIter = coefs.iterator();
		Iterator<Integer> keysIter = keys.iterator();
		
		String s = "";
		for(int i=0; i<coefs.size(); i++) {
			Integer currentCoef = coefIter.next();
			Integer currentKey = keysIter.next();
			
			if(i == coefs.size()-1 && currentKey != 0 && currentKey!=1) {
				s+= currentCoef + "x^" + currentKey;
				break;
			}
			else if(i == coefs.size()-1 && currentKey == 0) {
				s+= currentCoef;
				break;
			}
			else if(i == coefs.size()-1 && currentKey == 1) {
				s+= currentCoef + "x";
				break;
			}
			else if(currentCoef == -1 && currentKey!=1 && currentKey!=0) {
				s+= "-x^" + currentKey +" + ";
				continue;
			}
			else if(currentKey == 1 && currentCoef == -1 && i != coefs.size()-1) {
				s+= "-x + ";
				continue;
			}
			else if(currentKey == 1 && currentCoef != 1 && i != coefs.size()-1) {
				s+= currentCoef + "x + ";
				continue;
			}
			else if(currentKey == 1 && currentCoef == 1 && i != coefs.size()-1) {
				s+= "x + ";
				continue;
			}
			else if(currentKey == 0 && i==coefs.size()-1) {
				s+= currentCoef;
				continue;
			}
			else if(currentKey == 0) {
				s+= currentCoef+ " + ";
				continue;
			}
			else if(currentCoef == 1 && currentKey !=0) {
				s+= "x^" +currentKey + " + ";
				continue;
			}
			else if(currentKey == 1) {
				s+= currentCoef + "x + ";
				continue;
			}
			s+= currentCoef + "x^" + currentKey + " + ";
		}
		return s;
	}

}
