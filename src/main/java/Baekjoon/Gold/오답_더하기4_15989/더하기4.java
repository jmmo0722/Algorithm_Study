package Baekjoon.Gold.오답_더하기4_15989;

import java.io.*;

public class 더하기4 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        int[] list = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            list[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, list[i]);
        }

        int[] dp = new int[max+1];
        dp[0] = 1;
        for(int i = 1; i <= 3; i++){

            for (int j = i; j <= max; j++) {
                dp[j] += dp[j-i];
            }
        }

        for (int i : list) bw.write(dp[i] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
