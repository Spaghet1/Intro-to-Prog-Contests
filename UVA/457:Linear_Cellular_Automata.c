#include <stdio.h>
#include <string.h>

int main() {
	char densityToSymbol[] = {' ', '.', 'x', 'W'};
	int DNA[10];
	int dishes[42];
	int testCases;
	scanf("%d", &testCases);
	for (int testCase = 0; testCase < testCases; testCase++) {
		memset(dishes, 0, sizeof(dishes));
		dishes[20] = 1;
		for (int i = 0; i < 10; i++) scanf("%d", DNA + i);
		for (int i = 0; i < 50; i++) {
			int prev = 0;
			for (int j = 1; j < 41; j++) {
				int K = prev + dishes[j] + dishes[j + 1];
				prev = dishes[j];
				dishes[j] = DNA[K];
				printf("%c", densityToSymbol[prev]);
			}
			printf("\n");
		}
		if (testCase != testCases - 1) printf("\n");
	}
	return 0;
}
