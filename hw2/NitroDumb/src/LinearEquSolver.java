import java.util.ArrayList;
import java.util.List;

public class LinearEquSolver {
	
	private String equ;
	
	public LinearEquSolver(String equ) {
		this.equ = equ;
	}
	
	public double solve() {
		
		String[] split = this.equ.split("[ ]");
		List<String> lst = new ArrayList<>();
		
		for(String s : split) {
			if(s.length() > 1) {
				lst.add(s);
			}
		}
		
		
		
		return 0;
	}

}
