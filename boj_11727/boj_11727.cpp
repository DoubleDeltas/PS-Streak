#include <iostream>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	solve();

	return 0;
}

#define MAX 1000
#define MODULO 10007

int ar[MAX + 1];

void solve() {
	int n;
	cin >> n;
	
	ar[1] = 1;
	ar[2] = 3;
	for (int i = 3; i <= n; i++) {
		ar[i] = (ar[i - 1] + 2 * ar[i - 2]) % MODULO;
	}

	cout << ar[n];
}
