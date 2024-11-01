# [Bronze IV] Jumbled Scoreboards - 32604 

[문제 링크](https://www.acmicpc.net/problem/32604) 

### 성능 요약

메모리: 108080 KB, 시간: 92 ms

### 분류

구현

### 제출 일자

2024년 11월 1일 15:07:16

### 문제 설명

<p>You were so hyped to attend the final game of the Ball And Paddle Competition, where the two best teams in the world compete to paddle as many balls into the opponent's goal as possible. But alas, you fell ill, and cannot join your friends. Luckily, your friends took lots of pictures during the match, and after the match concluded, they sent you all the pictures that they have. Because the messaging app uploads and downloads the pictures in parallel, you are wondering whether you received them in chronological order. It looks like the scoreboards in each picture are unique, and knowing that the score of a team can only increase over time, you should be able to figure this out. Feeling too sick to check the order of the pictures manually, you decide to write a program that checks temporal consistency based on the scoreboards that are in the picture.</p>

<p>Given a list of intermediate scores from the match, determine whether the scores are in chronological order.</p>

### 입력 

 <p>The input consists of:</p>

<ul>
	<li>One line with an integer $n$ ($1\leq n\leq 100$), the number of pictures you received.</li>
	<li>$n$ lines, each with two integers $a$ and $b$ ($0 \leq a,b \leq 100$), the scores of the two teams in one of the pictures.</li>
</ul>

<p>Every pair of scores $(a, b)$ in the input is unique.</p>

<p>The order of the scores in the input is the order in which you received the pictures.</p>

### 출력 

 <p>Output "<code>yes</code>" if the scores are in chronological order, or "<code>no</code>" if they are not.</p>

