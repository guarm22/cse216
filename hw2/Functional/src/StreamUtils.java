import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
	
	
	
	
	public static void main(String[] args) {
		
		Collection<String> strings = new ArrayList<>();
		strings.add("A");
		strings.add("a");
		strings.add("Hi");
		strings.add("higfdssss");
		strings.add("yooygfddgf");
		strings.add("Yes");
		strings.add("yot");
		strings.add("no");
		strings.add("Ao");
		strings.add("Zo");
		strings.add("zkjh");
		
		Collection<Integer> integers = new ArrayList<>();
		integers.add(2);
		integers.add(1);
		integers.add(21);
		integers.add(3);
		integers.add(15);
		integers.add(-19);
		integers.add(20);
		integers.add(10);
		
		Map<String, List<String> > map = new HashMap<>(); 
        map.put("1a", Arrays.asList("1a", "2a")); 
        map.put("2b", Arrays.asList("3b", "4b", "5b", "6b")); 
        map.put("3c", Arrays.asList("7b", "8b", "9b"));  
		
		Collection<String> res = capitalized(strings);
		System.out.println(res);
		
		System.out.println(longest(strings,true));
		System.out.println(least(strings,true));
		System.out.println(least(integers,true));
		
		System.out.println(1==1 ? "hi" : "no");
		
		System.out.printf("\nMap\n");
		System.out.println(map + "\nAfter Flatten:");
		System.out.println(flatten(map));
		
		
		
	}
	/**
	* @param strings: the input collection of <code>String</code>s.
	* @return a collection of those <code>String</code>s in the input collection

	that start with a capital letter.

	*/
	public static Collection<String> capitalized(Collection<String> strings) {		
		return (Collection<String>) strings.stream().filter(Objects::nonNull)
			.filter(s -> s.charAt(0) > 65 && s.charAt(0) < 90).collect(Collectors.toList());
	}
	/**
	* Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
	*
	* @param strings: the given collection of <code>String</code>s.
	* @param from_start: a <code>boolean</code> flag that decides how ties are broken.
	If <code>true</code>, then the element encountered earlier in
	* the iteration is returned, otherwise the later element is returned.
	* @return the longest <code>String</code> in the given collection,
	* where ties are broken based on <code>from_start</code>.
	*/
	public static String longest(Collection<String> strings, boolean from_start) {
		return strings.stream().filter(Objects::nonNull)
				.reduce((str1, str2) -> from_start ? 
						(str1.length() >= str2.length() ? 
						str1 : str2) : (str1.length() <= str2.length() ? 
						str2 : str1))
				.orElse(null);
	}
	/**
	* Find and return the least element from a collection of given elements that are comparable.
	*
	* @param items: the given collection of elements
	* @param from_start: a <code>boolean</code> flag that decides how ties are broken.
	* If <code>true</code>, the element encountered earlier in the
	* iteration is returned, otherwise the later element is returned.
	* @param <T>: the type parameter of the collection (i.e., the items are all of type T).
	* @return the least element in <code>items</code>, where ties are
	* broken based on <code>from_start</code>.
	*/
	public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
		return items.stream().filter(Objects::nonNull)
				.reduce((item1, item2) -> (from_start ? 
				(item1.compareTo(item2) < 0 ? item1 : item2) : 
				(item1.compareTo(item2) <= 0 ? item2 : item1))) 
				.orElse(null); 
	}
	/**
	* Flattens a map to a stream of <code>String</code>s, where each element in the list
	* is formatted as "key -> value".
	*
	* @param aMap the specified input map.
	* @param <K> the type parameter of keys in <code>aMap</code>.
	* @param <V> the type parameter of values in <code>aMap</code>.
	* @return the flattened list representation of <code>aMap</code>.
	*/
	public static <K, V> List<String> flatten(Map<K, V> aMap) {
		return (List<String>) aMap.entrySet().stream().filter(Objects::nonNull)
				 .map(e -> e.getKey() + " -> "  + e.getValue())
				 .collect(Collectors.toList());
	}

}
