package Baekjoon.Gold.탑_2493;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 탑 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] tower = new int[n];
        int[] result = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) tower[i] = Integer.parseInt(str.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            while(!stack.isEmpty()){

                if(tower[stack.peek()] > tower[i]){
                    result[i] = stack.peek() + 1;
                    stack.push(i);
                    break;
                }

                if(tower[stack.peek()] < tower[i]){
                    stack.pop();
                }
            }

            if(stack.isEmpty()) {
                stack.push(i);
            }
        }

        for (int i : result) bw.write(i + " ");

        br.close();
        bw.flush();
        bw.close();
    }
}