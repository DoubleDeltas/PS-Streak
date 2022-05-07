#include <iostream>
#include <queue>
using namespace std;

int a, b;

void solve() {
	cin >> a >> b;

	int res;

	queue<pair<long long, int>> q;
	q.push(make_pair(a, 0));
	pair<long long, long> p;
	while (true) {
		p = q.front();
		q.pop();

		if (p.first == b) {
			res = p.second + 1;
			break;
		}
		else if (p.first < b) {
			q.push(make_pair(p.first * 2, p.second + 1));
			q.push(make_pair(p.first * 10 + 1, p.second + 1));
		}

		if (q.empty()) {
			res = -1;
			break;
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