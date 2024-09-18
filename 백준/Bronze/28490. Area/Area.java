import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int N = s.nextInt();
		int max = Integer.MIN_VALUE;
		for (int i=0; i<N; i++) {
			max = Math.max(max, s.nextInt()*s.nextInt());
		}
		System.out.println(max);
	}
}
