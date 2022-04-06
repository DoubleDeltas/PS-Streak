#include <iostream>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

#define MAX_N 100000

int sum[MAX_N + 1];

void solve() {
	int n, m; // n numbers, m queries
	int in1, in2;

	cin >> n >> m;

	// note: sum[0] == 0
	for (int i = 1; i <= n; i++) {
		cin >> in1;
		sum[i] = sum[i - 1] + in1;
	}

	for (int i = 0; i < m; i++) {
		cin >> in1 >> in2;
		cout << sum[in2] - sum[in1 - 1] << '\n';
	}
}