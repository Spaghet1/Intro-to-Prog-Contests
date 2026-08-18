#include <vector>
#include <algorithm>
#include <iostream>

class Solution {
public:
	int largestInteger(std::vector<int>& nums, int k) {
		if (k == nums.size()) {
			return *std::max_element(nums.begin(), nums.end());
		}
		if (k == 1) {
			int freqs[51];
			for (int num : nums) {
				freqs[num]++;
			}
			for (int i = 50; i >= 0; i--) {
				if (freqs[i] == 1) return i;
			}
			return -1;
		}
		bool beginDupe = std::find(nums.begin() + 1, nums.end(), nums.front()) != nums.end();
		bool endDupe = std::find(nums.begin(), nums.end() - 1, nums.back()) != nums.end() - 1;
		if (beginDupe && endDupe) return -1;
		else if (beginDupe) return nums.back();
		else if (endDupe) return nums.front();
		else return std::max(nums.front(), nums.back());
	}
};

int main() {
	std::vector<int> nums = {3,9,2,1,7};
	std::cout << Solution{}.largestInteger(nums, 3) << std::endl;
}
