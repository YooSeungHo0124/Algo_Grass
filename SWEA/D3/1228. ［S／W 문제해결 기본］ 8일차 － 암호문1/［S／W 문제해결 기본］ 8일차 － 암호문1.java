import java.util.*;

public class Solution {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int test_case = 10;
		int result_cnt = 1;
		while (test_case > 0) {
			
			int len = s.nextInt(); // 원본 암호문 길이
			int[] amho = new int[len]; // 암호문 담는 배열
			for(int i=0; i<len; i++) {
				amho[i]=s.nextInt();
			}
			int orderNum = s.nextInt();  // 명령어의 개수
			for(int i=0; i<orderNum; i++) {
				s.next(); // I 무시
				int pos = s.nextInt();     // 위치
				int insertNum= s.nextInt();  // 삽입 개수
				int[] insertArr= new int[insertNum];  // 삽입하는 배열
				for(int j=0; j<insertNum; j++) {
					insertArr[j]=s.nextInt();
				}
				int input_1 = 0;
				int input_2=pos;
				if ((amho.length-pos)<insertNum) {// 끝까지 덮어 버리니까 뒤로 밀리는거 고려 X 
					for (int j=pos; j<amho.length; j++) {
						amho[j]=insertArr[input_1++];
					}
				}
				else { // 뒤에 남는 칸 만큼 뒤로 밀어 넣어야함
					int[] clone = amho.clone(); // 클론 만들어서 삽입에 사용
					
					for (int j=pos; j<pos+insertNum; j++) {
						amho[j]=insertArr[input_1++];
					}
//					int left = amho.length-pos-insertNum; 
					for (int j=pos+insertNum; j<amho.length; j++) {
						amho[j]=clone[input_2++];
					}
				}
			}
			
			System.out.print("#"+result_cnt);
			for (int i=0; i<10; i++) {
				System.out.print(" "+amho[i]);
			}
			System.out.println();
			test_case--;
			result_cnt++;
		}
	}
}
