#include <iostream>
#include <vector>
using namespace std;

void solve();
int dfs(int v);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	solve();
	return 0;
}

vector<int> neighbors[101];
bool isSearched[101];

void solve() {
	int n; // number of computers
	int cnt = 0;
	int pairnum, v1, v2;

	//input and assignment
	cin >> n >> pairnum;

	for (int i = 0; i < pairnum; i++) {
		cin >> v1 >> v2;
		neighbors[v1].push_back(v2);
		neighbors[v2].push_back(v1);
	}

	//process
	cnt = dfs(1);

	//output
	cout << (cnt - 1);
}

int dfs(int v) { // ac : accumulator
	int cnt = 1;
	isSearched[v] = true;
	for (auto it = neighbors[v].begin(); it != neighbors[v].end(); it++) {
		if (isSearched[*it]) // already searched
			continue;
		cnt += dfs(*it);
	}
	return cnt;
}