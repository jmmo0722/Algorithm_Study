# 빗물 - 14719

![Solved.ac Tier](https://img.shields.io/badge/solved.ac-Gold%20V-F29D16?style=for-the-badge&logo=solved.ac)

[문제 링크](https://www.acmicpc.net/problem/14719)


`#다이나믹 프로그래밍` `#O(N)` `정답 코드 참고`

---

### 문제
2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.


![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14719/1.png)
![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14719/2.png)

비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?

### 입력
첫 번째 줄에는 2차원 세계의 세로 길이 H과 2차원 세계의 가로 길이 W가 주어진다. (1 ≤ H, W ≤ 500)

두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.

따라서 블록 내부의 빈 공간이 생길 수 없다. 또 2차원 세계의 바닥은 항상 막혀있다고 가정하여도 좋다.

### 출력
2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.

빗물이 전혀 고이지 않을 경우 0을 출력하여라.

<hr>

### 핵심 풀이 전략

`메모리: 16028 KB`
`시간: 132 ms`

빈 공간을 0,
블록을 1,
빗물을 2라고 하였을 때,

블록(1)을 만났을 때, 가로 인덱스를 +1씩 증가 -> 블록(1)을 만나면, 그 사이에 있는 공간들은 모두 2로 채움.
만약 블록(1)을 못만났을 경우에는 넘어감

블록을 하나씩 지나갈 때마다 1로 방문 표시

그 후 2로 채워진 공간을 카운팅 하여 출력

---

### 삽질 기록 🧠

- **코드 순서 오류**
    - if(board[height][curIndex] == 0){ <br>
      board[height][curIndex] = 1;  <br>
      curIndex++; <br>
      } <br>
    에서 curIndex를 먼저 실행하고 그 뒤에 board[][] 값을 채우도록 하여서 인덱스 오류 발생.

---

### 배운 점 & 보완할 점
- 코드 순서에 유의하자