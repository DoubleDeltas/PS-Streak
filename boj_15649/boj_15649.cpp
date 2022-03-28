#include <iostream>
#include <list>
#include <string>
using namespace std;

void solve();
void backtrack(list<int>& lst, int length, string s);
list<int> copyExcept(list<int>& src, int exc);


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	solve();
}

void solve() {
	int n, m;
	// n: range of seq.
	// m: length of seq.

	cin >> n >> m;

	list<int> lst; // initial lst: {1 to N}
	for (int i = 1; i <= n; i++)
		lst.push_back(i);

	string s;
	backtrack(lst, m, s);
}

void backtrack(list<int>& lst, int length, string s) {
	list<int> l;

	for (list<int>::iterator it = lst.begin(); it != lst.end(); it++) {
		l = copyExcept(lst, *it);
		if (length > 1)
			backtrack(l, length - 1, s + to_string(*it) + ' ');
		else
			cout << (s + to_string(*it) + '\n');
		l = lst;
	}
}

list<int> copyExcept(list<int>& src, int exc) {
	list<int> res;
	for (list<int>::iterator it = src.begin(); it != src.end(); it++) {
		if (*it == exc) continue;
		res.push_back(*it);
	}
	return res;
}
