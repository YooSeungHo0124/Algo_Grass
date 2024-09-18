import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum = s.nextInt() + s.nextInt() + s.nextInt() + s.nextInt();
		int N = s.nextInt();
		System.out.println(sum >= 4*N ? 0 : 4*N - sum);
	}
}
