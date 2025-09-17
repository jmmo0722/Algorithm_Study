package Baekjoon.Bronze.일곱난쟁이_2309;


import java.io.*;
import java.util.*;

public class 일곱난쟁이_2309 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] list = new int[9];
        int sum = 0;

        // int i : list 형식으로 for문 잘못 사용.
        for (int i = 0; i < 9; i++) {
            list[i] = Integer.parseInt(br.readLine());
            sum += list[i];
        }

        Arrays.sort(list);

        outer: for (int i = 0; i < 8; i++) {

            for(int j = i+1; j < 9; j++){
                if((sum - list[i] - list[j]) == 100){
                    list[i] = -1; // i+1 이라는 잘못된 인덱스 사용.
                    list[j] = -1;
                    break outer;
                }
            }
        }

        for(int i = 0; i < 9; i++) if(list[i] != -1) bw.write(list[i] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
