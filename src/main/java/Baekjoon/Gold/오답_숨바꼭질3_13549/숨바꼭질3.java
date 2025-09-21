package Baekjoon.Gold.오답_숨바꼭질3_13549;

import java.io.*;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 숨바꼭질3 {


    static final int MAX = 100000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        // 만약 n >= k 라면
        if(n >= k && n <= 100000){
            bw.write((n - k) + "");
        }
        else{

            Deque<Integer> que = new java.util.ArrayDeque<>();
            int[] dp = new int[MAX + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);

            dp[n] = 0;
            que.offer(n);

            while(!que.isEmpty()){

                int index = que.poll();

                if(index == k) {
                    bw.write(dp[index] + "");
                    break;
                }

                // 앞으로 걷기
                if(index + 1 <= 100000 && dp[index+1] > dp[index] + 1){
                    que.offer(index+1);
                    dp[index+1] = dp[index] + 1;
                }

                // 뒤로 걷기
                if(index - 1 >= 0 && dp[index-1] > dp[index] +1){
                    que.offer(index-1);
                    dp[index-1] = dp[index] + 1;
                }

                // 순간이동
                if(2 * index <= 100000 && dp[2 * index] > dp[index]){
                    que.offer(2*index);
                    dp[2*index] = dp[index];
                }
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
