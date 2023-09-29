#include <iostream>
#include <string>
using namespace std;
typedef long long ll;

void solve();
ll  numCables(ll len);
int maxCable();

int k;    // number of cables
int cables[10000];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    solve();
}

void solve() {
    int n; // k is global

    cin >> k >> n;
    for (int i = 0; i < k; i++)
        cin >> cables[i];

    ll low = 1;
    ll high = maxCable();
    ll	mid;
	ll  numMid;
	ll	res = 0;
	
	while (low <= high) {
		mid = (low + high) / 2;
		numMid = numCables(mid);
		
		if (numMid >= n) {		// len is too small
			low = mid + 1;
			if (res < mid) res = mid;
		}
		else					// len is too big
			high = mid - 1;
	}
	
    cout << res;
}

ll numCables(ll len) {
    // number of cables when all cables are cut in length 'len'
    ll res = 0;
    for (ll i = 0; i < k; i++)
        res += cables[i] / len;
    return res;
}

int maxCable() {
    int res = cables[0];
    for (int i=1; i<k; i++)
        if (res < cables[i])
            res = cables[i];
    return res;
}