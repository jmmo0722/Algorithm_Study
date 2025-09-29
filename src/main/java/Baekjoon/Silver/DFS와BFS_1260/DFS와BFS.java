package Baekjoon.Silver.DFS와BFS_1260;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class DFS와BFS {

    static int N, M, V;
    static boolean[][] board;

    static ArrayList<Integer> dfsResult = new ArrayList<>();
    static boolean[] dfsVisited;
    static void dfs(int index){

        for(int i = 1; i < board[index].length; i++){
            if(board[index][i] && !dfsVisited[i]){
                dfsVisited[i] = true;
                dfsResult.add(i);
                dfs(i);
            }
        }
    }

    static ArrayList<Integer> bfsResult = new ArrayList<>();
    static void bfs(){
        boolean[] visited = new boolean[N+1];
        Deque<Integer> que = new ArrayDeque<>();
        que.add(V);
        bfsResult.add(V);
        visited[V] = true;

        while(!que.isEmpty()){

            int poll = que.poll();

            for(int i = 1; i < board[poll].length; i++){

                if(board[poll][i] && !visited[i]){
                    que.add(i);
                    bfsResult.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str = new StringTokenizer(br.readLine());

        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        V = Integer.parseInt(str.nextToken());

        board = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            str = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            board[a][b] = true;
            board[b][a] = true;
        }

        // DFS
        dfsVisited = new boolean[N+1];
        dfsResult.add(V);
        dfsVisited[V] = true;
        dfs(V);

        bfs();

        for (Integer i : dfsResult) {
            bw.write(i + " ");
        }
        bw.write("\n");
        for (Integer i : bfsResult) {
            bw.write(i + " ");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
