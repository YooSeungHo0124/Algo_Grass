import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int M = s.nextInt();
		int K = s.nextInt();
		
		if ( N+M-1 > K ) System.out.println("NO"); 
		else {
			System.out.println("YES");
			int num =1;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
			    for (int j = 0; j < M; j++) {
			        sb.append(num + j).append(" ");
			    }
			    num++;
			    sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}
}
