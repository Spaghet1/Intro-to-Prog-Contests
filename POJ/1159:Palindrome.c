#include <stdio.h>
#include <string.h>

int main() {
	int n;
	scanf("%d\n", &n);
	char string[n + 1];
	fgets(string, sizeof(string), stdin);
	int LCS[n + 1];
	memset(LCS, 0, sizeof(LCS));
	for (int i = 1; i < n + 1; i++) {
		int diag = 0;
		for (int j = 1; j < n + 1; j++) {
			int nextDiag = LCS[j];
			if (string[i - 1] == string[n - j]) {
				LCS[j] = diag + 1;
			} else {
				LCS[j] = LCS[j] > LCS[j - 1] ? LCS[j] : LCS[j - 1];
			}
			diag = nextDiag;
		}
	}
	printf("%d\n", n - LCS[n]);
}
