package Baekjoon.Gold.오답_뱀과사다리게임_16928;

import java.io.*;
import java.util.*;

public class 뱀과사다리게임 {

    static int[] jump;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str = new StringTokenizer(br.readLine());

        jump = new int[101];

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        // 사다리 입력
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine());
            jump[Integer.parseInt(str.nextToken())] = Integer.parseInt(str.nextToken());
        }

        // 뱀 입력
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            jump[Integer.parseInt(str.nextToken())] = Integer.parseInt(str.nextToken());
        }

        Deque<Integer> que = new ArrayDeque<>();
        int[] dist = new int[101];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        que.add(1);

        while(!que.isEmpty()){

            int index = que.poll();
            if(index == 100) break;

            // 1~6까지 더하였을 때, 뱀이 아닌지 검사
            for(int i = 1; i <= 6; i++){

                int next = index + i;
                if(next > 100) continue;
                if(jump[next] != 0){
                    next = jump[next];
                }

                if(dist[next] == -1){
                    dist[next] = dist[index] + 1;
                    que.add(next);
                }

            }
        }

        bw.write(dist[100] + "");
        br.close();
        bw.flush();
        bw.close();
    }
}
