#include <iostream>
#include <vector>
using namespace std;

void solve();
void dfs(int v);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

#define MAX_N 1000
vector<int> neighbors[MAX_N+1];
bool isSearched[MAX_N + 1]; // all false

void solve() {
	int n, m; // n nodes, m edges
	int v1, v2;
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		cin >> v1 >> v2;
		neighbors[v1].push_back(v2);
		neighbors[v2].push_back(v1);
	}

	int ans = 0;
	for (int i = 1; i <= n; i++) {
		if (!isSearched[i]) {
			ans++;
			dfs(i);
		}
	}

	cout << ans;
}

void dfs(int v) {
	if (isSearched[v])
		return;
	isSearched[v] = true;
	for (auto it = neighbors[v].begin(); it != neighbors[v].end(); it++) {
		dfs(*it);
	}
}