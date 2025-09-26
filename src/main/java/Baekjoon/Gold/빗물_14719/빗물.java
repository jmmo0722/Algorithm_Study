package Baekjoon.Gold.빗물_14719;

import java.io.*;
import java.util.*;

public class 빗물 {

    static int[][] board;
    static int H, W;
    static void check(int height, int index){

        int curIndex = index + 1;
        while(curIndex < W){

            if(board[height][curIndex] == 0){
                board[height][curIndex] = 1; // 방문 표시
                curIndex++;
            }
            else if(board[height][curIndex] == 1){
                fill(index+1, curIndex-1, height);
                break;
            }
        }
    }

    static void fill(int startIndex, int endIndex, int height){

        for(int i = startIndex; i <= endIndex; i++){
            board[height][i] = 2;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer str = new StringTokenizer(br.readLine());

        H = Integer.parseInt(str.nextToken());
        W = Integer.parseInt(str.nextToken());

        board = new int[H][W];

        str = new StringTokenizer(br.readLine());

        int[] blocks = new int[W];
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(str.nextToken());

            // board 초기화
            // 블록은 1, 빈 공간이면 0
            for(int j = 0; j < blocks[i]; j++){
                board[j][i] = 1;
            }
            for(int j = blocks[i]; j < H; j++){
                board[j][i] = 0;
            }
        }

        // 빗물 체크
        for(int i = 0; i < H; i++){
            for (int j = 0; j < W; j++) {

                // 만약 블록이면
                if(board[i][j] == 1){
                    check(i, j);
                }
            }
        }

        int count = 0;
        // 빗물 고인(값이 2인) 곳 카운팅
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(board[i][j] == 2) count++;
            }
        }

        bw.write(count + "");
        br.close();
        bw.flush();
        bw.close();
    }
}
