#include <string>
#include <vector>

using namespace std;

int W[] = {
    5,
    50,
    500,
    5'000,
    50'000,
    500'000,
};

vector<int> solution(int l, int r) {
    vector<int> answer;
    for (int b = 0; b < (1 << 6); b++) {
        int n = 0;
        for (int x = 0; x < 6; x++) {
            if (b & (1 << x))
                n += W[x];
        }
        if (l <= n && n <= r)
            answer.push_back(n);
    }
    if (answer.empty())
        answer.push_back(-1);
    return answer;
}