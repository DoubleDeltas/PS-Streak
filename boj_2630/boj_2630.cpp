#include <iostream>
using namespace std;

void solve();

void quadtree(int x1, int y1, int x2, int y2);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

int paper[128][128]; // [y][x]
int white, blue;

void solve() {
	int n; // 2, 4, ..., 128

	cin >> n;
	for (int j = 0; j < n; j++)
		for (int i = 0; i < n; i++)
			cin >> paper[j][i];

	quadtree(0, 0, n, n);

	cout << white << '\n';
	cout << blue << '\n';
}

// x2, y2 are exclusive
void quadtree(int x1, int y1, int x2, int y2) {
	int curcolor = paper[y1][x1];
	for (int j = y1; j < y2; j++) {
		for (int i = x1; i < x2; i++) {
			if (paper[j][i] != curcolor)
				goto DIVIDE;
		}
	}
	if (curcolor) blue++; else white++;
	return;
DIVIDE:
	int newsize = (x2 - x1) / 2;
	quadtree(x1, y1, x1 + newsize, y1 + newsize);
	quadtree(x1 + newsize, y1, x2, y1 + newsize);
	quadtree(x1, y1 + newsize, x1 + newsize, y2);
	quadtree(x1 + newsize, y1 + newsize, x2, y2);
}