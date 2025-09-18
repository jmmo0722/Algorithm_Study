package Baekjoon.Silver.색종이_2563;

import java.io.*;
import java.util.StringTokenizer;
public class 색종이 {

    static int[][] board = new int[100][100];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            for (int a = x; a < x + 10; a++) {
                for (int b = y; b < y + 10; b++) board[a][b] = 1;
            }
        }

        int count = 0;
        for (int[] ints : board) {
            for (int anInt : ints) if(anInt == 1) count++;
        }

        br.close();
        bw.write(count + "");
        bw.flush();
        bw.close();
    }
}
