#include <iostream>
#include <string>
using namespace std;

#define MAX_N 200000
#define MODULO 1000000007

char s[MAX_N + 1];
int w[MAX_N], e[MAX_N];

int powmod(int x) {
	if (x < 32)
		return (1 << x) % MODULO;
	long long s = powmod(x / 2);
	if (x % 2) // odd
		return (int)((s * s * 2) % MODULO);
	else
		return (int)((s * s) % MODULO);
}

void solve() {
	int n;

	cin >> n;
	cin >> s;

	w[0] = (s[0] == 'W') ? 1 : 0;
	for (int i = 1; i < n; i++) {
		if (s[i] == 'W')
			w[i] = w[i - 1] + 1;
		else
			w[i] = w[i - 1];
	}

	e[n - 1] = (s[n - 1] == 'E') ? 1 : 0;
	for (int i = n - 2; i > -1; i--) {
		if (s[i] == 'E')
			e[i] = e[i + 1] + 1;
		else
			e[i] = e[i + 1];
	}

	long long res = 0;
	long long e_term;
	for (int i = 0; i < n; i++) {
		if (s[i] == 'H') {
			e_term = powmod(e[i]) - e[i] - 1;
			if (e_term < 0)
				e_term += MODULO;
			res += w[i] * e_term;
			res %= MODULO;
		}
	}

	cout << res;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	solve();
	return 0;
}