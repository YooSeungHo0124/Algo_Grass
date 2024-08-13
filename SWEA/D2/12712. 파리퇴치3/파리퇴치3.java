import java.util.*;
public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case =s.nextInt();
		int result_cnt =1;
		while(test_case>0) {
			
			int N = s.nextInt();
			int M = s.nextInt();
			int[][]map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					map[i][j]=s.nextInt();
				}
			}
			
			int[] c_dr = {-1,1,0,0};
			int[] c_dc = {0,0,-1,1};
			int[] d_dr = {-1,-1,1,1};
			int[] d_dc = {-1,1,-1,1};
			
			int max=Integer.MIN_VALUE;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					int c_sum = map[i][j];  
					int d_sum = map[i][j];  
					for (int m=1; m<M; m++) {
						for (int k=0; k<4; k++) {
							int n_c_dr=i+c_dr[k]*m;
							int n_c_dc=j+c_dc[k]*m;
							int n_d_dr=i+d_dr[k]*m;
							int n_d_dc=j+d_dc[k]*m;
							
							if (0<= n_c_dr && n_c_dr<N && 0<= n_c_dc && n_c_dc<N) {
								c_sum+=map[n_c_dr][n_c_dc];
							}
							if (0<= n_d_dr && n_d_dr<N && 0<= n_d_dc && n_d_dc<N) {
								d_sum+=map[n_d_dr][n_d_dc];
							}
						}
					}
					max = Math.max(max, d_sum);
					max = Math.max(max, c_sum);
				}
			}
			System.out.println("#"+result_cnt+" "+max);
			test_case--;
			result_cnt++;
		}
	}
}
