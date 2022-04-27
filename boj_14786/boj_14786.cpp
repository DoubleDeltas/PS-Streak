#include <iostream>
#include <cmath>
using namespace std;

void solve();

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cout.precision(6);
	solve();
	return 0;
}

void solve() {
	int a, b, c;

	cin >> a >> b >> c;

	double x = (double)c/a;
	double y, yp;
	while (true) {
		y = a * x + b * sin(x) - c;
		yp = a + b * cos(x);

		if (abs(y) < 1e-14)
			break;

		x -= y/yp;
	}
	cout << x;
}
