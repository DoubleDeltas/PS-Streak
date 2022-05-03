#include <iostream>
#include <algorithm>
using namespace std;

#define MAX_N 100000
int n;
pair<int, int> meetings[MAX_N];

void solve() {
	int t1, t2;

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> t1 >> t2;
		meetings[i] = make_pair(t1, t2);
	}

	sort(&meetings[0], &meetings[n],
		[](pair<int, int> p1, pair<int, int> p2) { 
			if (p1.second == p2.second)
				return p1.first < p2.first;
			return p1.second < p2.second;
		}
	);

	int time = 0;
	int cnt = 0;
	int i = 0;
	while (i < n) {
		if (meetings[i].first < time) {
			i++;	// pass
			continue;
		}
		time = meetings[i].second;
		i++;
		cnt++;
	}

	cout << cnt;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	solve();
	return 0;
}