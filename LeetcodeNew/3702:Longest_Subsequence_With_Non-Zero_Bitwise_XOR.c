int longestSubsequence(int* nums, int numSize) {
	int allZero = 1;
	int XORsum = 0;
	for (int i = 0; i < numSize; i++) {
		XORsum ^= nums[i];
		if (nums[i]) allZero = 0;
	}
	if (XORsum) return numSize;
	else if (allZero) return 0;
	return numSize - 1;
}

