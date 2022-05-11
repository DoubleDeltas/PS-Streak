#include <iostream>
using namespace std;

#define N_MAX 15

int col[N_MAX];
int n;
int ret = 0;


bool isPromising(int y) {
	for (int i = 0; i < y; i++) {
		if (col[i] == col[y])
			return false;
		if (abs(col[i] - col[y]) == y - i)
			return false;
	}
	return true;
}

void backtrack(int y) {
	if (!isPromising(y)) {
		return;
	}

	if (y == n - 1) {
		ret++;
		return; // found answer
	}

	for (int i = 0; i < n; i++) {
		col[y + 1] = i;
		backtrack(y + 1);
	}

	return;
}

void solve() {
	cin >> n;
	backtrack(-1);
	cout << ret;
}

int main() {
	cin.sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	solve();
	return 0;
}