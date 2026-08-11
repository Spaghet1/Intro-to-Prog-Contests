#include <vector>
#include <cstdint>
#include <iostream>

class Solution {
public:
	int missingInteger(std::vector<int>& nums) {
		int endIndex = 1;
		while (endIndex < nums.size() && nums[endIndex] == nums[endIndex - 1] + 1) endIndex++;
		int minSum = endIndex * (nums[0] + nums[endIndex - 1]) / 2; 
		if (minSum > 50) return minSum;
		uint64_t seen = 1L << nums[0];
		while (endIndex < nums.size()) {
			seen |= 1L << nums[endIndex];
			while ((seen >> minSum) & 1L) minSum++;
			endIndex++;
		}
		return minSum;
	}
};

int main() {
	std::vector<int> nums = {1,3,4,5,6};
	std::cout << Solution{}.missingInteger(nums) << std::endl;
}
