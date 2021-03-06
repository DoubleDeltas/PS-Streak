# 백준 11049: 행렬 곱셈 순서
_Code: 20220413, Comment: 20220413, Last Edit: -_

## 들어가는 말
 오늘은 알고리즘 강의에서 DP를 이용해 행렬 곱셈의 최적 순서를 찾는 알고리즘을 배웠다. 복습을 겸해서 문제를 풀었다. 행렬의 연쇄 곱셈에서 최적의 순서를 찾는 것이 중요한 이유는 문제의 지문에 잘 나와있다.

## 본문

### 아이디어
곱하고자 하는 행렬 $A_1, A_2, ..., A_n$이 있을 때, 곱셈 순서를 정하는 마지막 절차를 밟는 상황을 생각해보자. 그렇게 되면 구하는 곱은 반드시 이렇게 나뉠 것이다.

$$\left( A_1A_2A_3...A_k \right)\left( A_{k+1}A_{k+2}A_{k+3}...A_n \right)$$ 

물론 $k$는 $1 \leq k \lt n $인 자연수다.

이는 우리가 이 문제를 분할할 수 있다는 것을 나타낸다.

풀이에 앞서 설명을 위해 몇 가지 약속을 하자.

### 재귀식 찾기

우선 첫 행렬 $A_1$의 행 수를 $d_0$라 하고, 행렬 $A_i$의 열 수와 $A_{i+1}$의 행 수는 같으므로 이를 $d_i$라고 표시하자. 그렇게 한다면, $A_1,A_2,...A_n$의 크기는 각각 

$$ d_0 \times d_1, d_1 \times d_2, ..., d_{n-1} \times d_n $$

으로 나타낼 수 있다. 예를 들어 문제에서 말하는 두 행렬 $A_i$와 $A_{i+1}$을 곱할 때의 곱셈의 횟수는 위의 표기법을 쓰면 아래와 같다.

$$d_{i-1}d_id_{i+1}$$

한편, 부분곱 $\left( A_{i}A_{i+1}...A_k \right)$의 크기는 $d_{i-1} \times d_k$이고, $\left( A_{k+1}A_{k+2}...A_j \right)$의 크기는 $d_k \times d_j$이므로, 둘이 이미 계산되어 있다는 가정 하에 둘을 곱한 행렬 $\left( A_{i}A_{i+1}...A_j \right)$을 만들기 위해 필요한 곱셈의 수는 다음과 같다.

$$d_{i-1}d_kd_j$$

그러므로 $A_i$부터 $A_j$ $(i \lt j)$까지의 부분곱을 $M_{ij}$이라고 하면, 이는 이러한 식으로 구할 수 있고,

$$M_{ij} = \min_{i \leq k\lt j}(M_{ik} + M_{kj} + d_{i-1}d_kd_j)$$

추가로 $i=j$일 때는 자기 자신, 즉 곱셈이 없으므로 $M_{ii} = 0$이라는 식도 얻을 수 있다. 이 두 식을 이용해 동적 계획법(DP)을 적용한다. 그러나 이를 단순히 재귀로 구현하면, 호출 횟수가 기하급수적으로 늘어날 것이 뻔하다. 그러므로 우리는 또다시 메모아이제이션(memoization)을 사용해 상향식으로 접근해야 된다.

### 메모아이제이션

연산이 가장 적은 경우는 위에서도 봤다 싶이 $i=j$이다. 그럼 그 다음은? 바로 $i+1=j$일 때다. 그 다음은 $i+2=j$인 경우다. 그러므로, 이 순서를 따라 $M_{ij}$를 계산한다. 이렇게 되면 $(i, j)$ 좌표를 따라 그린 선은 대각선의 모양이다. 그러므로 이를 `diag`(diagonal)라는 변수로 그리며, 상향적으로 계산이 이루어지도록 하였다.

자, 루프에 따른 변수의 변화를 꼼꼼히 살피며 다시 한 번 코드를 살펴보자.

## 나가는 말
 추가 정보: $n$개의 행렬을 곱하는 모든 경우의 수는 다음과 같다고 한다.
 
 $$\frac{1}{n} {{2n-2} \choose {n-1}}$$

 이는 $O(2^n)$보다 크므로, 노가다로는 어림도 없음을 알 수 있다.