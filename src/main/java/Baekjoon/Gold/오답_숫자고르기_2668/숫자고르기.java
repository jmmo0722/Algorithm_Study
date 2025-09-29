package Baekjoon.Gold.오답_숫자고르기_2668;

import java.io.*;
import java.util.*;

public class 숫자고르기 {
    static int n;
    static int[] list;
    static ArrayList<Integer> result;
    static int maxLength;

    static void dfs(ArrayList<Integer> indexList, ArrayList<Integer> valueList, boolean[] visited, int max, int curIndex){
        if(indexList.size() >= max){

            // 1. 복사본 생성
            ArrayList<Integer> sortedIndex = new ArrayList<>(indexList);
            ArrayList<Integer> sortedValue = new ArrayList<>(valueList);

            // 2. 복사본을 정렬
            Collections.sort(sortedIndex);
            Collections.sort(sortedValue);

            // 3. 복사본으로 비교
            for (int i = 0; i < sortedIndex.size(); i++) {
                if(!sortedIndex.get(i).equals(sortedValue.get(i))) return;
            }

            // 4. 결과 업데이트 (이때는 정렬된 valueList를 넣어도 됨)
            if(maxLength < indexList.size()){
                maxLength = max;
                result.clear();
                result.addAll(sortedValue); // 정렬된 복사본을 결과에 추가
            }
        }
        else{
            for(int i = curIndex; i <= n; i++){
                if(!visited[i]){
                    visited[i] = true;
                    indexList.add(i);
                    valueList.add(list[i]);

                    dfs(indexList, valueList, visited, max, curIndex+1);

                    visited[i] = false;
                    indexList.remove(indexList.size()-1);
                    valueList.remove(valueList.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        list = new int[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        result = new ArrayList<>();
        maxLength = -1;
        for(int i = 1; i <= n; i++){
            ArrayList<Integer> indexList = new ArrayList<>();
            ArrayList<Integer> valueList = new ArrayList<>();
            boolean[] visited = new boolean[n+1];
            dfs(indexList, valueList, visited, i, 1);
        }

        bw.write(maxLength + "\n");
        for (Integer i : result) {
            bw.write(i + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
