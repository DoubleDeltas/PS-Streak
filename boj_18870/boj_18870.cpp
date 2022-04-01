#include <iostream>
#include <utility> // pair
using namespace std;

void solve();

void quicksort(int low, int high);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	solve();
	
	return 0;
}

#define MAX_N 1000000

pair<int, int> ar[MAX_N];

void solve() {
	int n;
	int tmp;

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		ar[i] = make_pair(tmp, i);
	}

	quicksort(0, n - 1);

	// re-rank for same order
//	int dupcnt = 0; // counter for duplicated data
//	for (int i = 1; i < n; i++) {
//		if (ar[i].first == ar[i - 1].first) {
//			dupcnt++;
//			ar[i].second -= dupcnt;
//		}
//	}

	//test
	for (int i = 0; i < n; i++)
		cout << ar[i].first << ' ';
	cout << '\n';
	for (int i = 0; i < n; i++)
		cout << ar[i].second << ' ';

}

void swapPair(pair<int, int>& x, pair<int, int>& y) {
	int tmp = x.first;
	x.first = y.first;
	y.first = tmp;
}

void quicksort(int low, int high) {
	if (low >= high)
		return; // end recursive
	
	// ar[low] is pivot

	int border = low;
	for (int i = low + 1; i <= high; i++) {
		if (ar[i].first < ar[low].first) {
			border++;
			swap<int, int>(ar[i], ar[border]);
		}
	}
	swap<int, int>(ar[low], ar[border]);

	// for all x, y s.t. x < border < y -> ar[x] < ar[border](pivotitem) < ar[y]
	// and pivot point is ordered

	int dupcnt = 1;
	int minrank = ar[border].second; // min. rank of data that equals ar[border].first
	for (int i=border+1; i<=high; i++) {
		if (ar[border] == ar[i]) {
			dupcnt++;
			if (ar[i].second < minrank)
				minrank = ar[i].second;
		}
	}
	for (int i=border; i<=high; i++) {
		if (ar[border] == ar[i])
			ar[i].second = minrank;
		else
			ar[i].second -= dupcnt - 1;
	}

	// recursive
	quicksort(low, border - 1);
	quicksort(border + 1, high);
}
