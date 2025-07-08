#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string S, vector<vector<int>> Q) {
    for (const auto& q : Q) {
        reverse(S.begin()+q[0], S.begin()+q[1]+1);
    }
    return S;
}