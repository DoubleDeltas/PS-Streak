#include <iostream>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	solve();

	return 0;
}

#define MAX 1001
#define MODULO 10007

int fibonacci[MAX + 1];

void solve() {
	fibonacci[0] = 0;
	fibonacci[1] = 1;
	for (int i = 2; i <= MAX; i++) {
		fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % MODULO;
	}

	int n;

	cin >> n;

	cout << fibonacci[n + 1];
}