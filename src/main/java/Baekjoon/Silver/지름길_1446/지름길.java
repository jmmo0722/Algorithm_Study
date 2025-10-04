package Baekjoon.Silver.지름길_1446;

import java.io.*;
import java.util.*;

public class 지름길 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();

        // 지름길 정보를 저장하는 리스트. 시작 지점 기준으로 정렬
        List<int[]>[] shortcuts = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            shortcuts[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int len = sc.nextInt();
            // 지름길의 도착점이 고속도로 길이(d)를 넘지 않고, 지름길이 일반 도로보다 짧은 경우만 추가
            if (end <= d && end - start > len) {
                shortcuts[start].add(new int[]{end, len});
            }
        }

        // dist[i] = 0부터 i까지의 최단 거리
        int[] dist = new int[d + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // 우선순위 큐 (거리, 노드) 형태로 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0}); // (거리, 노드)

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int currentNode = current[1];

            // 이미 더 짧은 경로를 찾았다면 스킵
            if (currentDist > dist[currentNode]) {
                continue;
            }

            // 현재 노드에서 갈 수 있는 일반 도로와 지름길 탐색
            // 1. 일반 도로: 현재 노드에서 다음 노드로 한 칸 이동
            if (currentNode + 1 <= d) {
                if (dist[currentNode + 1] > currentDist + 1) {
                    dist[currentNode + 1] = currentDist + 1;
                    pq.offer(new int[]{dist[currentNode + 1], currentNode + 1});
                }
            }

            // 2. 지름길: 현재 노드에서 시작하는 모든 지름길 탐색
            for (int[] shortcut : shortcuts[currentNode]) {
                int nextNode = shortcut[0];
                int weight = shortcut[1];

                if (dist[nextNode] > currentDist + weight) {
                    dist[nextNode] = currentDist + weight;
                    pq.offer(new int[]{dist[nextNode], nextNode});
                }
            }
        }

        System.out.println(dist[d]);
    }
}
