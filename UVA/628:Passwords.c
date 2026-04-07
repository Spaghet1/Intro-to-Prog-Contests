#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printRule(const char* rule, char** words, const int numWords, char* digits) {
	int wordIndexes[numWords];
	int digitIndexes[8];
	memset(wordIndexes, 0, sizeof(wordIndexes));
	memset(digitIndexes, 0, sizeof(digitIndexes));
	char* stack[256];
	int stackptr = 0;
	int wordptr = 0;
	int digitptr = 0;
	while (stackptr >= 0) {
		switch (rule[stackptr]) {
			case '#': 
				if (wordIndexes[stackptr] < numWords) {
					stack[stackptr] = words[wordIndexes[stackptr]];
					wordIndexes[stackptr]++;
					stackptr++;
				}
				else {
					wordIndexes[stackptr] = 0;
					stackptr--;
				}
				break;
			case '0':
				if (digitIndexes[stackptr] < 10) {
					stack[stackptr] = digits + 2 * digitIndexes[stackptr];
					digitIndexes[stackptr]++;
					stackptr++;
				}
				else {
					digitIndexes[stackptr] = 0;
					stackptr--;
				}
				break;
			case '\0':
				for (int i = 0; i < stackptr; i++) {
					printf("%s", stack[i]);
				}
				printf("\n");
				stackptr--;
				break;
		}
	}
}

int main() {
	char buffer[256];
	char digits[] = {'0', '\0', '1', '\0', '2', '\0', '3', '\0', '4', '\0', '5', '\0', '6', '\0', '7', '\0', '8', '\0', '9', '\0'};
	while (fgets(buffer, sizeof(buffer), stdin)) {
		int numWords = atoi(buffer);
		char* words[numWords];
		for (int i = 0; i < numWords; i++) {
			fgets(buffer, sizeof(buffer), stdin);
			if (buffer[strlen(buffer) - 1] == '\n') buffer[strlen(buffer) - 1] = '\0';
			char* word = malloc(strlen(buffer) + 1);
			strcpy(word, buffer);
			words[i] = word;
		}
		fgets(buffer, sizeof(buffer), stdin);
		int numRules = atoi(buffer);
		for (int i = 0; i < numRules; i++) {
			fgets(buffer, sizeof(buffer), stdin);
			if (buffer[strlen(buffer) - 1] == '\n') buffer[strlen(buffer) - 1] = '\0';
			printRule(buffer, words, numWords, digits);
		}
		
		for (int i = 0; i < numWords; i++) free(words[i]);
	}
	return 0;
}
