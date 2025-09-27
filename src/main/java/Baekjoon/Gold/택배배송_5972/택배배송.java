package Baekjoon.Gold.택배배송_5972;

import java.io.*;
import java.util.*;

public class 택배배송 {

    static int N, M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        // 배열 초기화
        ArrayList<int[]>[] hut = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            hut[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            str = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(str.nextToken());
            int B = Integer.parseInt(str.nextToken());
            int C = Integer.parseInt(str.nextToken());

            hut[A].add(new int[]{B, C});
            hut[B].add(new int[]{A, C});
        }

        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[N+1];
        que.add(new int[]{1, 0});
        Arrays.fill(dist, -1);
        dist[1] = 0;

        while(!que.isEmpty()){

            int[] poll = que.poll();
            int curIndex = poll[0];
            int curCost = poll[1];
            if(dist[curIndex] == -1) dist[curIndex] = curCost;
            if(curIndex == N) break;

            // 중복 방지
            if (curCost > dist[curIndex]) {
                continue;
            }

            for(int[] i : hut[curIndex]){
                int nextIndex = i[0];
                int nextCost = i[1];

                if(dist[nextIndex] == -1){
                    que.add(new int[]{nextIndex, curCost+nextCost});
                }
            }
        }

        bw.write(dist[N] + "");
        br.close();
        bw.flush();
        bw.close();
    }
}