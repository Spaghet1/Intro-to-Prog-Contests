#include <string>
#include <iostream>

class Solution {
public:
	std::string convert(std::string s, int numRows) {
		if (s.length() == 1 || numRows == 1) return s;
		std::string result;
		result.reserve(s.size());
		int defaultDist = 2 * numRows - 2;
		for (int i = 0; i < s.length(); i += defaultDist) result += s[i];
		for (int level = 1; level < numRows - 1; level++) {
			int levelDist = defaultDist - 2 * level;
			int i = level;
			while (i < s.length()) {
				result += s[i];
				i += levelDist;
				levelDist = defaultDist - levelDist;
			}
		}
		for (int i = numRows - 1; i < s.length(); i += defaultDist) result += s[i];
		return result;
	}
};

int main() {
	std::string s = "PAYPALISHIRING";
	int numRows = 4;
	std::cout << Solution{}.convert(s, numRows) << std::endl;
}
