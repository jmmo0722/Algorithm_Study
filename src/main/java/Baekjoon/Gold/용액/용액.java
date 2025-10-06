package Baekjoon.Gold.용액;

import java.io.*;
import java.util.*;

public class 용액 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer str = new StringTokenizer(br.readLine());

        // 용액 값 초기화
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(str.nextToken());
            list[i] = value; // list 에 절대값 삽입
        }

        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        int left = 0;
        int right = N-1;

        while(left < right){

            int diff = list[left] + list[right];

            if(Math.abs(diff) == 0){
                result[0] = list[left];
                result[1] = list[right];
                break;
            }

            // 만약 현재 diff 가 작다면
            if(minDiff >= Math.abs(diff)){
                result[0] = list[left];
                result[1] = list[right];
                minDiff = Math.abs(diff);
            }

            if(diff > 0) right--;
            else if(diff < 0) left++;

        }

        for (int i : result) {
            bw.write(i + " ");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}