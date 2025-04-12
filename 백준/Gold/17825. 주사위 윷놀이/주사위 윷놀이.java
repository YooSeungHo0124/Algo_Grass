import java.util.*;
import java.io.*;


public class Main {
    static int[] dice = new int[10];
    static int max = 0;
    static Node start;
    static Node[] horses = new Node[4];

    static class Node {
        int score;
        Node next;
        Node shortcut;

        public Node(int score) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            dice[i] = sc.nextInt();
        }

        initBoard();
        Arrays.fill(horses, start);
        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int turn, int total) {
        if (turn == 10) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Node curr = horses[i];
            if (curr == null) continue; // 이미 도착한 말은 건너뜀

            Node next = move(curr, dice[turn]);

            // 다음 위치가 다른 말과 겹치는지 확인
            boolean overlap = false;
            for (int j = 0; j < 4; j++) {
                if (i != j && horses[j] == next && next != null) {
                    overlap = true;
                    break;
                }
            }

            if (overlap) continue;

            horses[i] = next;
            dfs(turn + 1, total + (next == null ? 0 : next.score));
            horses[i] = curr; // 백트래킹
        }
    }

    static Node move(Node node, int steps) {
        Node curr = node;
        if (curr.shortcut != null) {
            curr = curr.shortcut;
            steps--;
        } else {
            curr = curr.next;
            steps--;
        }

        while (steps-- > 0 && curr != null) {
            curr = curr.next;
        }

        return curr;
    }

    static void initBoard() {
        // 메인 경로
        Node n0 = new Node(0);
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node n6 = new Node(6);
        Node n8 = new Node(8);
        Node n10 = new Node(10);
        Node n12 = new Node(12);
        Node n14 = new Node(14);
        Node n16 = new Node(16);
        Node n18 = new Node(18);
        Node n20 = new Node(20);
        Node n22 = new Node(22);
        Node n24 = new Node(24);
        Node n26 = new Node(26);
        Node n28 = new Node(28);
        Node n30 = new Node(30);
        Node n32 = new Node(32);
        Node n34 = new Node(34);
        Node n36 = new Node(36);
        Node n38 = new Node(38);
        Node n40 = new Node(40);
        Node end = new Node(0); // 도착 노드

        // 메인 경로 연결
        n0.next = n2;
        n2.next = n4;
        n4.next = n6;
        n6.next = n8;
        n8.next = n10;
        n10.next = n12;
        n12.next = n14;
        n14.next = n16;
        n16.next = n18;
        n18.next = n20;
        n20.next = n22;
        n22.next = n24;
        n24.next = n26;
        n26.next = n28;
        n28.next = n30;
        n30.next = n32;
        n32.next = n34;
        n34.next = n36;
        n36.next = n38;
        n38.next = n40;
        n40.next = end;

        // 분기 1 (10 → 13 → 16 → 19 → 25 → 30 → 35 → 40)
        Node n13 = new Node(13);
        Node n16b = new Node(16);
        Node n19 = new Node(19);
        Node n25 = new Node(25);
        Node n30b = new Node(30);
        Node n35 = new Node(35);
        n10.shortcut = n13;
        n13.next = n16b;
        n16b.next = n19;
        n19.next = n25;
        n25.next = n30b;
        n30b.next = n35;
        n35.next = n40;

        // 분기 2 (20 → 22 → 24 → 25 → ...)
        Node n22b = new Node(22);
        Node n24b = new Node(24);
        n20.shortcut = n22b;
        n22b.next = n24b;
        n24b.next = n25;

        // 분기 3 (30 → 28 → 27 → 26 → 25 → ...)
        Node n28b = new Node(28);
        Node n27 = new Node(27);
        Node n26b = new Node(26);
        n30.shortcut = n28b;
        n28b.next = n27;
        n27.next = n26b;
        n26b.next = n25;

        start = n0;
    }
}
