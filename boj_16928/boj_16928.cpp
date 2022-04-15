#include <iostream>
#include <queue>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	solve();
	return 0;
}

int n, m; // ladder, snake
int dest[101];
bool isSearched[101];

void solve() {
	int ladder, snake, in, out;

	cin >> ladder >> snake;
	for (int i = 0; i < ladder + snake; i++) {
		cin >> in >> out;
		dest[in] = out;
	}

	// bfs
	queue<int> tmpq, q;
	int cur, next;
	int cnt = 0;

	tmpq.push(1);
	while (true) {
		while (!tmpq.empty()) {
			cur = tmpq.front();
			tmpq.pop();
			q.push(cur);
		}
		cnt++;

		while (!q.empty()) {
			cur = q.front();
			q.pop();

			for (int i = cur + 1; i <= cur + 6; i++) {
				next = dest[i] ? dest[i] : i;
				if (next == 100)
					goto OUTPUT;
				if (isSearched[next])
					continue;
				tmpq.push(next);
				isSearched[next] = true;
			}
		}
	}

	OUTPUT:
	cout << cnt;
}