import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int M = s.nextInt();
		
		int[] arr = new int[N+1];
		for (int i=1; i<=N; i++) {
			arr[i] = i; 
		}
		
		for (int k=0; k<M; k++) {
			int[] tmp = arr.clone();
			int i = s.nextInt();
			int j = s.nextInt();
			
			for (int r = 0; r<=(j-i); r++) {
				arr[i+r] = tmp[j-r];
			}
		}
		
		for (int i=1; i<=N; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}

