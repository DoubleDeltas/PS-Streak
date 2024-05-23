import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] num_list, int n) {
        return IntStream.concat(
			Arrays.stream(Arrays.copyOfRange(num_list, n, num_list.length)),
			Arrays.stream(Arrays.copyOfRange(num_list, 0, n))
		).toArray();
    }
}