# [Bronze IV] Sticky Keys - 31656 

[문제 링크](https://www.acmicpc.net/problem/31656) 

### 성능 요약

메모리: 108080 KB, 시간: 112 ms

### 분류

구현, 문자열

### 제출 일자

2024년 6월 2일 12:16:31

### 문제 설명

<p>Bob is texting Alice about his favourite programming problems, but he spilled coffee on his keyboard and now the keys get stuck when he types. The other day, he tried to tell her about the upcoming contest “UAPC”, but accidentally sent “UAAAAAPC” since his A key got stuck! Bob is an undergraduate so he can’t afford a new keyboard. He has asked you to write a program to correct his messages.</p>

<p>To make your life easier, Bob has agreed to avoid using messages with adjacent copies of the same letter (e.g., he won’t write about the “coffee” incident). So, you should always reduce duplicates down to a single character.</p>

### 입력 

 <p>The input consists of a string $S$, where $1 \le |S| \le 1000$, which is Bob’s entire message to Alice. There may be multiple space-separated words in the message, so the entire string $s$ <i>including</i> spaces should be considered part of the message.</p>

<p>The string $S$ is guaranteed to only include upper and lower-case characters, digits, and spaces. Thankfully, the coffee did not reach Bob’s spacebar so there will never be multiple spaces in a row.</p>

### 출력 

 <p>Output the message with repeated consecutive characters trimmed to a single character.</p>

