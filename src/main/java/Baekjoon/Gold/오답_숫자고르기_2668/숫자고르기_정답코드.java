package Baekjoon.Gold.오답_숫자고르기_2668;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class 숫자고르기_정답코드 {
    static int N;
    static int[] list;
    static boolean[] visited;
    static boolean[] finished;
    static ArrayList<Integer> result;

    static void dfs(int cur) {
        visited[cur] = true;
        int next = list[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) { // 사이클이 형성된 경우
            // 사이클의 시작점인 next부터 현재 노드인 cur까지를 결과에 추가
            for (int i = next; i != cur; i = list[i]) {
                result.add(i);
            }
            result.add(cur);
        }

        finished[cur] = true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        finished = new boolean[N + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        Collections.sort(result);

        bw.write(result.size() + "\n");
        for (int num : result) {
            bw.write(num + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
