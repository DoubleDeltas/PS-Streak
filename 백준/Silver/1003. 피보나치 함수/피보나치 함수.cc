#include <stdio.h>
#define FIBO_MAX 40


int main(void) {
	int f[FIBO_MAX+1], i, t, n;
	
	f[0] = 0;
	f[1] = 1;
	for (i=2; i<FIBO_MAX+1; ++i) {
		f[i] = f[i-2] + f[i-1];
	}
	
	scanf("%d",&t);
	for(i=0; i<t; ++i) {
		scanf("%d", &n);
		if (n==0) {
			printf("1 0\n");
		} else {
			printf("%d %d\n", f[n-1], f[n]);
		}
	}
	return 0;
}
