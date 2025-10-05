package Baekjoon.Silver.쉬운최단거리_14940;

import java.io.*;
import java.util.*;

public class 쉬운최단거리 {

    static int N, M;
    static int[][] board;
    static int[][] result;
    static int[] moveY = {0, 0, 1, -1};
    static int[] moveX = {1, -1, 0, 0};
    static void bfs(int n, int m){

        Deque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][M+1];
        que.add(new int[]{n, m, 0});
        visited[n][m] = true;

        while(!que.isEmpty()){

            int[] poll = que.poll();
            int curN = poll[0];
            int curM = poll[1];
            int curCost = poll[2];

            // 값이 1보다 크고, 또한 N, M 범위를 벗어나지 않는다면
            for(int i = 0; i < 4; i++){
                int dy = moveY[i] + curN;
                int dx = moveX[i] + curM;

                if(dy > 0 && dy <= N && dx > 0 && dx <= M && board[dy][dx] == 1  && !visited[dy][dx]){

                    que.add(new int[]{dy, dx, curCost+1});
                    visited[dy][dx] = true;
                    result[dy][dx] = curCost + 1;
                }
            }
        }

        result[n][m] = 0;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str = new StringTokenizer(br.readLine());

        // N은 세로, M은 가로 길이
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        // board 초기화
        board = new int[N+1][M+1];
        result = new int[N+1][M+1];

        int start = 0, end = 0;
        for (int i = 1; i <= N; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(str.nextToken());

                if(board[i][j] == 0) result[i][j] = 0;
                else result[i][j] = -1;

                if(board[i][j] == 2){
                    start = i;
                    end = j;
                }
            }
        }

        // bfs
        bfs(start, end);

        // 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
