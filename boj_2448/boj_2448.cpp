#include <iostream>
using namespace std;

void solve();

#define K_MAX 10

int mat[3 * (1 << K_MAX)][6 * (1 << K_MAX)]; // y x

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

void recur(int expk, int x, int y) {
	if (expk == 1) {
		mat[y][x]
			= mat[y + 1][x] = mat[y + 1][x + 2]
			= mat[y + 2][x] = mat[y + 2][x + 1] = mat[y + 2][x + 2] = mat[y + 2][x + 3] = mat[y + 2][x + 4]
			= 1;
		return;
	}
	recur(expk / 2, x, y);
	recur(expk / 2, x, y + 3*expk/2);
	recur(expk / 2, x + 6*expk/2, y + 3*expk/2);
}

void solve() {
	int n, expk;
	cin >> n;

	expk = n / 3;
	recur(expk, 0, 0);

	for (int j = 0; j < n; j++) {
		for (int k = n - j - 1; k > 0; k--) cout << ' ';
		for (int i = 0; i <= j*2; i++) {
			if (mat[j][i])
				cout << '*';
			else
				cout << ' ';
		}
		for (int k = 0; k < n-j-1; k++) cout << ' ';
		cout << '\n';
	}
}