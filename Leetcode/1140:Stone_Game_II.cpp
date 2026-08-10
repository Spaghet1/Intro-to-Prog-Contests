#include <vector>
#include <numeric>
#include <algorithm>
#include <iostream>

class Solution {
public:
	int stoneGameII(std::vector<int>& piles) {
		const int SIZE = piles.size();
		std::vector<int> suffixSum(SIZE);
		std::partial_sum(piles.rbegin(), piles.rend(), suffixSum.rbegin());
		// dp[i][M] = max stones a player can get from i ... end given M - 1
		std::vector<std::vector<int>> dp(SIZE + 1, std::vector<int>(SIZE + 1, 0));
		// base case -> last pile only
		for (int i = SIZE - 1; i >= 0; i--) {
			for (int M = 1; M <= SIZE; M++) {
				if (2 * M >= SIZE - i) dp.at(i).at(M - 1) = suffixSum.at(i);
				else {
					for (int X = 1; X <= 2 * M && i + X <= SIZE; X++) {
						dp.at(i).at(M - 1) = std::max(suffixSum.at(i) - dp.at(i + X).at(std::max(M, X) - 1), dp.at(i).at(M - 1));
					}
				}
			}
		}
		return dp.at(0).at(0);
	}
};

int main() {
	Solution sol;
	std::vector<int> testcase = {1,2,3,4,5,100};
	std::cout << sol.stoneGameII(testcase) << std::endl;	
}
