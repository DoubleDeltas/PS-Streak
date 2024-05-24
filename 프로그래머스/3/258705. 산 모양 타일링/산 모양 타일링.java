class Solution {
    static final int MOD = 10007;
    
    static int[] sadari, pyeong;
    
    static int n;
    
    static int ans;
    
    public int solution(int n, int[] tops) {
        n = tops.length;
        sadari = new int[n+2];
        pyeong = new int[n+2];
        
        sadari[1] = 1;
        pyeong[1] = tops[0] == 1 ? 3 : 2;
        for (int i=2; i<=n+1; i++) {
            sadari[i] = add(sadari[i-1], pyeong[i-1]);
            pyeong[i] = add(sadari[i], pyeong[i-1]);
            if (i-1 < n && tops[i-1] == 1)
                pyeong[i] = add(pyeong[i], sadari[i]);
        }
        
        return sadari[n+1];
    }
    
    static int add(int a, int b) {
        return (a + b) % MOD;
    }
}