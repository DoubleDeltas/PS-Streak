#include <iostream>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

// each bits "... FEDC BA98 7654 3210" means:
// 0123
// 4567
// 89AB
// CDEF

#define TETRO_NUM 19
#define N_MAX 500
#define M_MAX 500

int tetromino[TETRO_NUM] = {
	0x000F, 0x1111, // I
	0x0033, // O
	0x0311, 0x0017, 0x0223, 0x0074, // L
	0x0322, 0x0071, 0x0113, 0x0047,
	0x0231, 0x0036, 0x0132, 0x0063, // S
	0x0027, 0x0232, 0x0072, 0x0131  // T
};

int rowmask[4] = { 0x000F, 0x00F0, 0x0F00, 0xF000 };
int colmask[4] = { 0x1111, 0x2222, 0x4444, 0x8888 };

int paper[N_MAX][M_MAX];

void solve() {
	int n, m;
	int tmp;

	// input: n, m, paper
	cin >> n >> m;
	for (int j = 0; j < n; j++) {
		for (int i = 0; i < m; i++) {
			cin >> tmp;
			paper[j][i] = tmp;
		}
	}

	int cursum, maxsum = 0;
	int ylenm1, xlenm1;
	// process
	for (int k = 0; k < TETRO_NUM; k++) {
		for (int i = 0; i < 4; i++) {
			if (tetromino[k] & rowmask[i])
				ylenm1 = i;
			if (tetromino[k] & colmask[i])
				xlenm1 = i;
		}

		for (int j = 0; j < n - ylenm1; j++) {
			for (int i = 0; i < m - xlenm1; i++) {
				cursum = 0;
				for (int c = 0; c < 16; c++) {
					if (tetromino[k] & (1 << c))
						cursum += paper[j + c / 4][i + c % 4];
				}
				if (cursum > maxsum)
				{
					maxsum = cursum;
				}
			}
		}

	}

	//output
	cout << maxsum;
}