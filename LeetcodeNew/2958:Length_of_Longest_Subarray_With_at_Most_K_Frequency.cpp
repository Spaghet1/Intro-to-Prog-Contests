#include <vector>
#include <unordered_map>
#include <iostream>

class Solution {
public:
	int maxSubarrayLength(std::vector<int>& nums, int k) {
		int maxLength = 0;
		std::unordered_map<int, int> freqs;
		int left = 0;
		for (int right = 0; right < nums.size(); right++) {
			if (++freqs[nums[right]] > k) {
				maxLength = right - left > maxLength ? right - left : maxLength;
				do {
					freqs[nums[left]]--;
				} while (nums[left++] != nums[right]);
			}
		}
		maxLength = nums.size() - left > maxLength ? nums.size() - left : maxLength;
		return maxLength;
	}
};

int main() {
	std::vector<int> nums = {1,2,2,1,3};
	std::cout << Solution{}.maxSubarrayLength(nums, 1) << std::endl;
}
