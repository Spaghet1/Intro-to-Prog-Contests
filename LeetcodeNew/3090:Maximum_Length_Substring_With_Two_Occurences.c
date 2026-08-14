#include <string.h>
#include <stdio.h>

int maximumLengthSubstring(char* s) {
	int freqs[26];
	memset(freqs, 0, 26 * sizeof(int));
	int maxLength = 0;
	int left = 0;
	int right = 0;
	while (s[right] != '\0') { 
		freqs[s[right] - 'a']++;
		if (freqs[s[right] - 'a'] > 2) {
			maxLength = right - left > maxLength ? right - left : maxLength;
			while (s[left] != s[right]) freqs[s[left++] - 'a']--;
			freqs[s[left++] - 'a']--;
		}
		right++;
	}
	maxLength = right - left > maxLength ? right - left : maxLength;
	return maxLength;
}

int main(int argc, char** argv) {
	printf("%d\n", maximumLengthSubstring(argv[1]));
}
