import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int start = s.nextInt();
		int end = s.nextInt();
		int N = s.nextInt();
		int result = Integer.MAX_VALUE;
		
		for (int i=0; i<N; i++) {
			int cost = s.nextInt();
			int city = s.nextInt();
			
			boolean isMove = false;
			for (int j=0; j<city; j++) {
				int input = s.nextInt();
				if (input == start) { isMove = true;}
				else if (isMove == true && input == end) {
					result = Math.min(result, cost);
				}
			}
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
}
