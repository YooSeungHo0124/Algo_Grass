import java.util.*;
public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case=10;
		int result_cnt = 1;
		
		while (test_case>0) {
			int len = s.nextInt();      // 원본 암호문 개수
			List<Integer> list= new ArrayList<>(); 
			for (int i=0; i<len; i++) {
				int input = s.nextInt();
				list.add(input); 
			}
			
			int orderNum = s.nextInt();  // 명령어의 개수
			for (int j=0; j<orderNum; j++) {
				String order = s.next();
				if (order.equals("I")) { // I (삽입)
					int idx=s.nextInt();
					int repeat= s.nextInt();
					for (int k=0; k<repeat; k++) {
						int add =s.nextInt();
						list.add(idx++, add);
					}
				} // D (삭제)
				else if (order.equals("D")) {
					int idx=s.nextInt();
					int repeat = s.nextInt();
					for (int k=0; k<repeat; k++) {
						list.remove(idx);
					}
				}
				else {
					int repeat = s.nextInt();
					for (int k=0; k<repeat; k++) {
						int add = s.nextInt();
						list.add(list.size(), add);
					}
				}
			}
			System.out.print("#"+result_cnt);
			for (int i=0; i<10; i++) {
				System.out.print(" "+list.get(i));
			}
			System.out.println();
			
			test_case--;
			result_cnt++;
		}
		
	}
}
