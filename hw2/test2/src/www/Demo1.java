package www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo1 {
	
	public static void main(String[] args) {
		List<String> words = Arrays.asList("apple","ant","bet");
		List<String> words2 = Arrays.asList("apple2","ant4","bet3");
		List<String> words3 = Arrays.asList("apple3","ant3","bet2");
		List<String> words4 = Arrays.asList("apple4","ant2","bet1");
		
		List<List<String>> strings = Arrays.asList(words, words2, words3, words4);
		List<String> newList = aMethod(strings);
		for(int i=0; i<newList.size(); i++) {
			System.out.println(newList.get(i));
		}
		
	}
	
	public static List<String> aMethod(List<List<String>> lists) {
		return lists.stream().flatMap(List::stream).collect(Collectors.toList());
	}
		

}
