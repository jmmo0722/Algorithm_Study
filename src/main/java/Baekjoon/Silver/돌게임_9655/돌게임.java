package Baekjoon.Silver.돌게임_9655;

import java.io.*;
import java.util.StringTokenizer;
public class 돌게임 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        //boolean[] dist = new boolean[N+1];
        boolean[] dp = new boolean[Math.max(N + 1, 4)];

        if(N == 1) dp[1] = true;
        else if(N == 2){
            dp[1] = true;
            dp[2] = false;
        }
        else if(N>=3){
            dp[1] = true;
            dp[2] = false;
            dp[3] = true;
        }

        for (int i = 4; i <= N; i++) {
            if(dp[i-1]) dp[i] = false;
            else dp[i] = true;

            if(dp[i-3]) dp[i] = false;
            else dp[i] = true;
        }

        if(dp[N]) bw.write("SK");
        else bw.write("CY");
        br.close();
        bw.flush();
        bw.close();
    }
}
