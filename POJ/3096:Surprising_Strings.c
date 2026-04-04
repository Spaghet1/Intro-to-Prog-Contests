#include <stdio.h>
#include <string.h>
#include <stdint.h>

int8_t checkPairs(const char* string, int8_t* pairs, int gap, size_t length) {
	for (int i = 0; i + gap + 1 < length; i++) {
		int pair = (string[i] - 'A') * 26 + string[i + gap + 1] - 'A';
		if(pairs[pair]) return 1;
		pairs[pair] = 1;
	}
	return 0;
}

void trimNewline(char* string) {
	if (string[strlen(string) - 1] == '\n') {
		string[strlen(string) - 1] = '\0';	
	}
}

int main() {
	char buff[128];
	int8_t pairs[26 * 26];
	memset(pairs, 0, 26 * 26);
	while (1) {
		fgets(buff, sizeof(buff), stdin);
		if (*buff == '*') break;
		trimNewline(buff);
		int8_t isSurprising = 1;
		int length = strlen(buff);
		for (int i = 0; i < length - 2; i++) {
			memset(pairs, 0, 26 * 26);
			if (checkPairs(buff, pairs, i, length)) {
				isSurprising = 0;
				break;
			}
		}
 		if (isSurprising) printf("%s is surprising.\n", buff);
		else printf("%s is NOT surprising.\n", buff);
	}
	return 0;
}

