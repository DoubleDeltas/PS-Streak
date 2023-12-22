#include <stdio.h>

void swap(int *a, int *b);

int main() {
	int a, b, c;
	while (1) {
		scanf("%d %d %d", &a, &b, &c);
		if (!(a|b|c))
			break; // while not a == b == c
			
		if (c < a)
			swap(&a, &c);
		else if (c < b)
			swap(&b, &c);
			
		if (a*a + b*b == c*c)
			printf("right\n");
		else
			printf("wrong\n");
	}
}

void swap(int *a, int *b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}