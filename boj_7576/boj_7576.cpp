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

#define M_MAX 1000
#define N_MAX 1000

#define RED (1)
#define GREEN (0)
#define EMPTY (-1)

const int DX[] = { 0,  1, -1,  0 };
const int DY[] = { 1,  0,  0, -1 };

int box[M_MAX][N_MAX];
bool isSearched[M_MAX][N_MAX];
int m, n;

void solve() {
	cin >> m >> n;
	for (int j = 0; j < n; j++)
		for (int i = 0; i < m; i++)
			cin >> box[i][j];

	// bfs
	queue<pair<int, int>> q, tmpq;
	pair<int, int> cur;

	// search initial red tomato
	for (int j = 0; j < n; j++) {
		for (int i = 0; i < m; i++) {
			if (box[i][j] == RED)
				tmpq.push(make_pair(i, j));
		}
	}

	int i, j, ni, nj;
	int cnt = -1;
	while (!tmpq.empty()) {
		cnt++;

		// move tmpq -> q
		while (!tmpq.empty()) {
			q.push(tmpq.front());
			tmpq.pop();
		}

		// bfs
		while (!q.empty()) {
			cur = q.front();
			q.pop();
			i = cur.first;
			j = cur.second;

			isSearched[i][j] = true;
			
			for (int k = 0; k < 4; k++) {
				ni = i + DX[k];
				nj = j + DY[k];
				if (ni < 0 || ni >= m || nj < 0 || nj >= n)
					continue;
				if (isSearched[ni][nj])
					continue;
				if (box[ni][nj] != GREEN)
					continue;
				tmpq.push(make_pair(ni, nj));
				box[ni][nj] = RED;
			}
		}
	}
	
	// searching end, find green tomatoes
	for (int j = 0; j < n; j++) {
		for (int i = 0; i < m; i++) {
			if (box[i][j] == GREEN) {
				cout << -1;
				return;	// end
			}
		}
	}
	cout << cnt;
}
