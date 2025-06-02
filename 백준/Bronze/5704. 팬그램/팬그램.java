import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            boolean[] alphabets = new boolean[26];
            String s = br.readLine();

            // 입력이 *이면 종료
            if (s.equals("*")) break;

            // 알파벳 체크
            for (char ch : s.toCharArray()) {
                if (Character.isLetter(ch)) {
                    alphabets[Character.toLowerCase(ch) - 'a'] = true;
                }
            }

            // 모든 알파벳이 등장했는지 확인
            boolean isPangram = true;
            for (boolean b : alphabets) {
                if (!b) {
                    isPangram = false;
                    break;
                }
            }

            System.out.println(isPangram ? "Y" : "N");
        }
    }
}