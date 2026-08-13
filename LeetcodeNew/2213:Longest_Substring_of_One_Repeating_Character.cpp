#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

struct SegmentNode {
	int prefix;
	int suffix;
	int best;
	char prefixChar;
	char suffixChar;
};

class Solution {
public:
	int getParent(int childIndex) {
		return (childIndex - 1) / 2;
	}

	int getLeft(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	int getRight(int parentIndex) {
		return 2 * parentIndex + 2;	
	}

	void updateNode(std::vector<SegmentNode>& segmentTree, int index, int leftWidth, int rightWidth) {
		SegmentNode& curr = segmentTree[index];
		SegmentNode& left = segmentTree[getLeft(index)];
		SegmentNode& right = segmentTree[getRight(index)];
		if (left.suffixChar == right.prefixChar) {
			curr.best = std::max({left.best, right.best, left.suffix + right.prefix});
			curr.prefix = left.prefix == leftWidth ? leftWidth + right.prefix : left.prefix;
			curr.suffix = right.suffix == rightWidth ? rightWidth + left.suffix : right.suffix;
		}
		else {
			curr.best = std::max(left.best, right.best);
			curr.prefix = left.prefix;
			curr.suffix = right.suffix;
		}
		curr.prefixChar = left.prefixChar;
		curr.suffixChar = right.suffixChar;
	}

	void initTree(std::vector<SegmentNode>& segmentTree, int parentIndex, int left, int right, const std::string& s) {
		if (left == right - 1) {
			segmentTree[parentIndex] = SegmentNode {
				.prefix = 1,
				.suffix = 1,
				.best = 1,
				.prefixChar = s[left],
				.suffixChar = s[left],
			};
			return;
		}
		int mid = (left + right) / 2;
		initTree(segmentTree, getLeft(parentIndex), left, mid, s);
		initTree(segmentTree, getRight(parentIndex), mid, right, s);
		updateNode(segmentTree, parentIndex, mid - left, right - mid);
	}

	void queryUpdate(std::vector<SegmentNode>& segmentTree, char queryChar, int queryIndex, int parentIndex, int left, int right) {
		if (left == right - 1) {
			segmentTree[parentIndex].prefixChar = queryChar;
			segmentTree[parentIndex].suffixChar = queryChar;
			return;
		}
		int mid = (left + right) / 2;
		if (queryIndex >= mid) queryUpdate(segmentTree, queryChar, queryIndex, getRight(parentIndex), mid, right);
		else queryUpdate(segmentTree, queryChar, queryIndex, getLeft(parentIndex), left, mid);
		updateNode(segmentTree, parentIndex, mid - left, right - mid);
	}

	std::vector<int> longestRepeating(std::string s, std::string queryCharacters, std::vector<int>& queryIndices) {
		std::vector<SegmentNode> segmentTree(4 * s.length());
		initTree(segmentTree, 0, 0, s.length(), s);
		std::vector<int> result;
		for (int i = 0; i < queryIndices.size(); i++) {
			queryUpdate(segmentTree, queryCharacters[i], queryIndices[i], 0, 0, s.length());
			result.push_back(segmentTree[0].best);
		}
		return result;
	}
};

int main() {
	std::string s = "babacc";
	std::string queryChars = "bcb";
	std::vector<int> queryIndex = {1,3,3};
	std::vector<int> res = Solution{}.longestRepeating(s, queryChars, queryIndex); 
	for (int val : res) std::cout << val << ' ';
}
