import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HigherOrderUtils {
	
	interface NamedBiFunction<T, U, R> extends java.util.function.BiFunction<T, U, R>{
		String name();
	}
	
	public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
		
		@Override
		public Double apply(Double x, Double y) {
			return x+y;
		}
		@Override
		public String name() {
			return "add";
		}
	};
	
	public static NamedBiFunction<Double, Double, Double> mult = new NamedBiFunction<Double, Double, Double>() {
		
		@Override
		public Double apply(Double x, Double y) {
			return x*y;
		}
		@Override
		public String name() {
			return "mult";
		}
	};
	
	public static NamedBiFunction<Double, Double, Double> diff = new NamedBiFunction<Double, Double, Double>() {
		
		@Override
		public Double apply(Double x, Double y) {
			return x-y;
		}
		@Override
		public String name() {
			return "diff";
		}
	};
	
	public static NamedBiFunction<Double, Double, Double> div = new NamedBiFunction<Double, Double, Double>() {
		
		@Override
		public Double apply(Double x, Double y) {
				if (y==0) {
					throw new ArithmeticException ("Cannot divide by zero!");
				}
			return x/y;
		}	
		@Override
		public String name() {
			return "div";
		}
	};
	
	
	public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) {
		if ((args.size()==0) || (bifunctions.size()==0) ||
				(args.size() != bifunctions.size()+1)) {
			throw new IllegalArgumentException ("Invalid Arguments");
		}
		if (args.size()==1) {
			return args.get(0);
		}
		for (int i=1; i<args.size(); i++ ) {
			args.set(i, bifunctions.get(i-1).apply(args.get(i-1), args.get(i)));
		}
		return args.get(args.size()-1);
	}
	
	
	public static class FunctionComposition<T, U, R> {
		public BiFunction<Function<T,U>, Function<U,R>, Function<T,R>> composition = new BiFunction<Function<T,U>,Function<U,R>,Function<T,R>>() {
			
			@Override
			public Function<T,R> apply(Function<T,U> f1, Function<U,R> f2) {
			return f1.andThen(f2);
			}
		};
	}
	
	public static void main(String[] args) {
		List<Double> argsList = new ArrayList<>();
		argsList.add(2d); argsList.add(1d); argsList.add(3d); argsList.add(0d); argsList.add(4d);
		
		List<NamedBiFunction<Double,Double,Double>> biFList = new ArrayList<>();
		biFList.add(add); biFList.add(mult); biFList.add(diff); biFList.add(div);
		
		System.out.println(zip(argsList, biFList));
	}
	
	
	
}
