#include <string>
#include <iostream>

class Solution {
public:
	int expand(std::string& s, int left, int right) {
		while (left >= 0 && right < s.length() && s[left] == s[right]) {
			--left;
			++right;
		}
		return (0 | right) | (left + 1 << 16);
	}
	std::string longestPalindrome(std::string s) {
		int left = 0;
		int right = 1;
		for (int i = 0; i < s.length(); i++) {
			int vals = expand(s, i, i);
			if ((vals & 0xFFFF) - (vals >> 16 & 0xFFFF) > right - left) {
				right = vals & 0xFFFF;
				left = vals >> 16 & 0xFFFF;
			}
			vals = expand(s, i, i + 1);
			if ((vals & 0xFFFF) - (vals >> 16 & 0xFFFF) > right - left) {
				right = vals & 0xFFFF;
				left = vals >> 16 & 0xFFFF;
			}
		}
		return s.substr(left, right - left);
	}
};

int main() {
	std::string s;
	std::cin >> s;
	Solution sol;
	std::cout << sol.longestPalindrome(s) << std::endl;	
}
