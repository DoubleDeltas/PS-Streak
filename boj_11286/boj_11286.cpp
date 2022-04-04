#include <iostream>
#include <cmath>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	solve();
	return 0;
}

#define HEAP_MAX 100000

void swap(int& x1, int& x2) {
	int tmp = x1;
	x1 = x2;
	x2 = tmp;
}

class Heap {
private:
	int data[HEAP_MAX + 1];
	int size;

	bool compareAbs(int x1, int x2) {
		int absx1 = abs(x1);
		int absx2 = abs(x2);
		if (absx1 != absx2)
			return absx1 < absx2;
		return x1 < x2;
	}

public:
	Heap() { data[0] = 0; size = 0; }
	void push(int x) {
		size++;
		data[size] = x;

		int cur = size;

		while (!compareAbs(data[cur / 2], x)) {
			swap(data[cur], data[cur / 2]);
			cur = cur / 2;
		}
		// heap[0](heap[1]'s parent) is always 0, loop would be stopped
	}
	int pop() {
		if (size == 0)
			return 0;

		int res = data[1];

		data[1] = data[size];
		size--;

		int cur = 1;
		int child; // whose child satisfy the condition?
		while (true) {
			child = cur * 2;
			if (child > size)
				break;

			if (child + 1 <= size && compareAbs(data[child + 1], data[child])) {
				child++; // choose right child
			}

			if (!compareAbs(data[cur], data[child])) {
				swap(data[cur], data[child]);
				cur = child;
			}
			else break;
		}
		return res;
	}
};

void solve() {
	Heap h;
	int n;
	int in;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> in;
		if (in) // != 0
			h.push(in);
		else
			cout << h.pop() << '\n';
	}
}