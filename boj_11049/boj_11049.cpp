#include <iostream>
#include <climits>
using namespace std;

void solve();

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}

#define N_MAX 500
#define R_MAX 500
#define C_MAX 500

int d[N_MAX + 1]; // A_i row d_i, column d_{i+1}
int m[R_MAX+1][C_MAX+1]; // m[i][j] means partial product A_i...A_j (i, j: 1..n)

int n;

int calcmin(int i, int j) {
    int cur;
    int min = INT_MAX;
    for (int k = i; k < j; k++) {
        cur = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];
        if (cur < min)
            min = cur;
    }
    return min;
}

void solve() {
    int _;

    // input
    cin >> n;
    cin >> d[0] >> d[1];
    for (int i = 2; i <= n; i++) {
        cin >> _ >> d[i];
    }

    // process
    int i, j;
    for (int diag = 1; diag < n; diag++) {
        for (i = 1; i <= n-diag; i++) {
            j = i + diag;
            m[i][j] = calcmin(i, j);
        }
    }

    // output
    cout << m[1][n];
}
