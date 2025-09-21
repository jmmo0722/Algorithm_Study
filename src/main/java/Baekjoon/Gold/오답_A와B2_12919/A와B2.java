package Baekjoon.Gold.오답_A와B2_12919;

import java.io.*;
import java.util.*;

public class A와B2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        String t = br.readLine();
        boolean result = false;

        Deque<String> que = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        // t에서 s로 역방향 탐색 시작
        que.add(t);
        visited.add(t); // 시작 문자열 t도 방문 처리

        while(!que.isEmpty()){

            String curString = que.poll();

            // 현재 문자열이 S와 같으면 성공
            if(curString.equals(s)){
                result = true;
                break;
            }

            // S보다 길이가 짧아지면 더 이상 S를 만들 수 없음
            if(curString.length() <= s.length()){
                continue;
            }

            // 1. 뒤가 'A'면 'A'를 삭제하는 연산 (역연산)
            if(curString.charAt(curString.length()-1) == 'A'){
                String nextString = curString.substring(0, curString.length()-1);

                // 새로 만든 문자열이 S보다 길거나 같고, 아직 방문하지 않았다면 큐에 추가
                if (nextString.length() >= s.length() && !visited.contains(nextString)) {
                    visited.add(nextString);
                    que.offer(nextString);
                }
            }

            // 2. 앞쪽이 'B' 라면 'B' 삭제 후 뒤집는 연산 (역연산)
            if(curString.charAt(0) == 'B'){
                StringBuilder sb = new StringBuilder(curString);
                sb.deleteCharAt(0); // 첫 글자 'B' 제거
                String nextString = sb.reverse().toString(); // 나머지 문자열 뒤집기

                // 새로 만든 문자열이 S보다 길거나 같고, 아직 방문하지 않았다면 큐에 추가
                if (nextString.length() >= s.length() && !visited.contains(nextString)) {
                    visited.add(nextString);
                    que.offer(nextString);
                }
            }
        }

        bw.write(result ? "1" : "0");
        br.close();
        bw.flush();
        bw.close();
    }
}
