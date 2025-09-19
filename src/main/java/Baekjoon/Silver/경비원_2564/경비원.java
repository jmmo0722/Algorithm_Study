package Baekjoon.Silver.경비원_2564;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class 경비원 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str;

        str = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(str.nextToken());
        int h = Integer.parseInt(str.nextToken());

        int n = Integer.parseInt(br.readLine());
        int[] shops = new int[n+1];

        // 상점들 | 동근이 위치 입력
        for (int i = 0; i < n+1; i++) {

            str = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(str.nextToken());
            int position = Integer.parseInt(str.nextToken());

            // 1 북, 2 남, 3 서, 4 동
            switch(direction){

                // 남
                case 2: shops[i] = position; break;
                // 동
                case 4: shops[i] = w + (h - position); break;
                // 북
                case 1: shops[i] = w + h + (w - position); break;
                // 서
                case 3: shops[i] = w + h + w + position; break;
            }
        }

        // 최단 거리 구하기
        int sum = 0;
        int cir = 2 * (w + h);
        for (int i = 0; i < n; i++) {

            // 첫 번째 경우
            int first = Math.abs(shops[i] - shops[n]);

            // 두 번째 경우
            int second = (cir - shops[i]) + (shops[n]);

            int result = Math.min(first, second);

            // 세 번째 경우
            int third = (cir - shops[n]) + (shops[i]);

            result = Math.min(result, third);

            sum += result;
        }

        br.close();
        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}
