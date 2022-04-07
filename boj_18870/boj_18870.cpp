#include <iostream>
#include <vector>
#include <climits>
using namespace std;

void solve();

int binsearch(int x, int lo, int hi);
void swap(int& x1, int& x2);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	solve();

	return 0;
}

#define MAX_N 1000000

class Heap {
private:
	int data[MAX_N + 1];
	int size;

public:
	Heap() { data[0] = INT_MIN; size = 0; }
	void push(int x) {
		size++;
		data[size] = x;

		int cur = size;

		while (!(data[cur / 2] < x)) {
			swap(data[cur], data[cur / 2]);
			cur = cur / 2;
		}
		// heap[0](heap[1]'s parent) is always 0, loop would be stopped
	}
	int pop() {
		if (size == 0)
			return -1; // error

		int res = data[1];

		data[1] = data[size];
		size--;

		int cur = 1;
		int child;
		while (true) {
			child = cur * 2;
			if (child > size)
				break;

			if (child + 1 <= size && data[child + 1] < data[child]) {
				child++; // choose right child
			}

			if (!(data[cur] < data[child])) {
				swap(data[cur], data[child]);
				cur = child;
			}
			else break;
		}
		return res;
	}
	int getSize() { return size; }
};

int in[MAX_N];
int pre[MAX_N];
Heap h;

void solve() {
	int n;
	int tmp;

	// input
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		in[i] = tmp;
		h.push(tmp);
	}

	// pop
	int p = 0;
	while (h.getSize() > 0) {
		tmp = h.pop();
		if (p == 0 || pre[p - 1] != tmp) {
			pre[p] = tmp;
			p++;
		}
	}

	// search rank and output
	for (int i = 0; i < n; i++) {
		cout << binsearch(in[i], 0, p-1) << ' ';
	}
}

int binsearch(int x, int lo, int hi) {
	int mid;

	while (true) {
		mid = (lo + hi) / 2;
		if (pre[mid] == x)
			return mid;
		else if (x < pre[mid])
			hi = mid - 1;
		else // pre[mid] < x
			lo = mid + 1;
	}
	// it's garenteed that x exists in pre[]
	// so no need termination condition
}

void swap(int& x1, int& x2) {
	int tmp = x1;
	x1 = x2;
	x2 = tmp;
}