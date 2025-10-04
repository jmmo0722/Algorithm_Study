package Baekjoon.Silver.수열_1446;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class 수열 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str;

        List<Integer> list = new ArrayList<>();
        int increased = 1;
        int decreased = 1;
        int max = Integer.MIN_VALUE;

        int n = Integer.parseInt(br.readLine());
        str = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {

            list.add(Integer.parseInt(str.nextToken()));
            if(n == 1) max = 1;

            if(i > 0){
                // 증가 카운팅
                if(list.get(i-1) <= list.get(i)){
                    increased++;
                    max = Math.max(max, increased);
                }
                else increased = 1;


                // 감소 카운팅
                if(list.get(i-1) >= list.get(i)){
                    decreased++;
                    max = Math.max(max, decreased);
                }
                else decreased = 1;
            }
        }
        br.close();
        bw.write(max + "");
        bw.flush();
        bw.close();
    }
}
