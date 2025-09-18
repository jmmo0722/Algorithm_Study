package Baekjoon.Bronze.줄_세우기_2605;

import java.io.*;
import java.util.*;
public class 줄_세우기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer str = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){

            int pick = Integer.parseInt(str.nextToken());
            int num = i - pick;

            list.add(num, i+1);
        }

        for (Integer i : list) bw.write(i + " ");
        br.close();
        bw.flush();
        bw.close();
    }
}
