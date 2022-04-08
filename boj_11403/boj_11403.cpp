#include <iostream>
using namespace std;

void solve();
void floyd();

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}

int g[100][100];
int n;

void solve() {
    cin >> n;
    for (int i=0; i<n; i++)
        for (int j=0; j<n; j++)
            cin >> g[i][j];
    
    floyd();
    
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++)
            cout << g[i][j] << ' ';
        cout << '\n';
    }
}

void floyd() {
    for (int k=0; k<n; k++) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (g[i][k] && g[k][j])
                    g[i][j] = 1;
            }
        }
    }
}
