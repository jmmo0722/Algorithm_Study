package Baekjoon.Gold.오답_문자열게임2_20437;

import java.io.*;
import java.util.*;

public class 문자열게임2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            Map<Character, ArrayList<Integer>> map = new HashMap<>();
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // 알파벳 카운팅
            for (int j = 0; j < W.length(); j++) {
                char c = W.charAt(j);
                map.computeIfAbsent(c, k -> new ArrayList<>()).add(j);
            }

            int shortest = Integer.MAX_VALUE;
            int longest = -1;

            for (ArrayList<Integer> value : map.values()) {
                if(value.size() >= K){
                    for(int x = 0; x <= value.size() - K; x++){
                        shortest = Math.min(shortest, value.get(x+K-1) - value.get(x) + 1);
                        longest = Math.max(longest, value.get(x+K-1) - value.get(x) + 1);
                    }
                }
            }

            if(shortest == Integer.MAX_VALUE || longest == -1){
                bw.write(-1 + "\n");
            }else{
                bw.write(shortest + " " + longest + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
