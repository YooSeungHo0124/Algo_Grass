
import java.util.*;
import java.io.*;

public class Main {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int n = Integer.parseInt(br.readLine());
	        
	        int[] A = new int[n];
	        int[] B = new int[n];
	        int[] C = new int[n];
	        int[] D = new int[n];
	        
	        for (int i = 0; i < n; i++) {
	            String[] input = br.readLine().split(" ");
	            A[i] = Integer.parseInt(input[0]);
	            B[i] = Integer.parseInt(input[1]);
	            C[i] = Integer.parseInt(input[2]);
	            D[i] = Integer.parseInt(input[3]);
	        }
	        
	        int[] AB = new int[n * n];
	        int[] CD = new int[n * n];
	        int index = 0;
	        
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                AB[index] = A[i] + B[j];
	                CD[index] = C[i] + D[j];
	                index++;
	            }
	        }
	        
	        Arrays.sort(AB);
	        Arrays.sort(CD);
	        
	        int left = 0;
	        int right = n * n - 1;
	        long count = 0;
	        
	        while (left < n * n && right >= 0) {
	            int sum = AB[left] + CD[right];
	            
	            if (sum == 0) {
	                long countAB = 1;
	                long countCD = 1;
	                
	                while (left + 1 < n * n && AB[left] == AB[left + 1]) {
	                    left++;
	                    countAB++;
	                }
	                
	                while (right - 1 >= 0 && CD[right] == CD[right - 1]) {
	                    right--;
	                    countCD++;
	                }
	                
	                count += countAB * countCD;
	                left++;
	                right--;
	            } else if (sum < 0) {
	                left++;
	            } else {
	                right--;
	            }
	        }
	        
	        System.out.println(count);
	    }

}
