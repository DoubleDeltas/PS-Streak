#include <iostream>
using namespace std;

int getBinaryOneCount(int x) {
	int cnt = 0;
	while (x > 0) {
		if (x & 1)
			cnt++;
		x >>= 1;
	}
	return cnt;
}

void solve() {
	int n, k;
	int buy = 0;

	cin >> n >> k;

	while (true) {
		if (getBinaryOneCount(n + buy) <= k)
			break;
		buy++;
	}

	cout << buy;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	solve();
	return 0;
}