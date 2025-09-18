package Baekjoon.Silver.빙고_2578;

import java.io.*;
import java.util.*;
public class 빙고 {

    static Map<Integer, int[]> map;
    static int[][] board;
    public static boolean checkBingo(){

        int count = 0;
        StringBuilder check;
        // 가로
        for (int i = 0; i < 5; i++) {
            check = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                check.append(String.valueOf(board[i][j]));
            }
            if(check.toString().equals("00000")) count++;
        }

        // 세로
        for (int i = 0; i < 5; i++) {
            check = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                check.append(String.valueOf(board[j][i]));
            }
            if(check.toString().equals("00000")) count++;
        }

        // 대각선
        check = new StringBuilder();
        for(int i = 0; i < 5; i++) check.append(String.valueOf(board[i][i]));
        if(check.toString().equals("00000")) count++;

        check = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            check.append(board[i][4-i]);
        }
        if(check.toString().equals("00000")) count++;

        return count >= 3;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str;

        map = new HashMap<>();
        board = new int[5][5];

        // 철수가 입력한 수
        for (int i = 0; i < 5; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int pop = Integer.parseInt(str.nextToken());
                map.put(pop, new int[]{i, j});
                board[i][j] = pop;
            }
        }

        // 사회자가 부르는 수
        int result = Integer.MAX_VALUE;
        outer: for (int i = 0; i < 5; i++) {
            str = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {

                int pop = Integer.parseInt(str.nextToken());
                int[] index = map.get(pop);
                board[index[0]][index[1]] = 0;

                if(checkBingo()) result = Math.min(result, (i * 5 + j + 1));

            }
        }

        bw.write(result+"");
        br.close();
        bw.flush();
        bw.close();
    }
}
