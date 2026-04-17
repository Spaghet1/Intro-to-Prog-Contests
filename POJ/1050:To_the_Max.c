#include <stdio.h>
#include <stdint.h>
#include <string.h>

int main() {
	int n;
	scanf("%d", &n);
	int rectangle[n][n];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &rectangle[i][j]);
		}
	}
	int fixed_row[n];
	int kadane[n];
	int max = -1e9;
	for (int i = 0; i < n; i++) {
		memset(fixed_row, 0, sizeof(fixed_row));
		for (int j = i; j < n; j++) {
			fixed_row[0] += rectangle[j][0];
			kadane[0] = fixed_row[0] > 0 ? fixed_row[0] : 0;
			int currMax = -1e9;
			for (int k = 1; k < n; k++) {
				fixed_row[k] += rectangle[j][k];
				kadane[k] = kadane[k - 1] > 0 ? kadane[k - 1] + fixed_row[k] : fixed_row[k];
				currMax = currMax > kadane[k] ? currMax : kadane[k];
			}
			max = max > currMax ? max : currMax;
		}
	}
	printf("%d", max);
	return 0;
}
