#include <stdio.h>

int main() {
	int solved[16];
	solved[0] = 1;
	solved[1] = 3;
	for (int i = 2; i < 16; i++) solved[i] = 4 * solved[i - 1] - solved[i - 2];
	int n;
	scanf("%d", &n);
	while (n != -1) {
		if (n & 1) printf("0\n");
		else printf("%d\n", solved[n / 2]);
		scanf("%d", &n);
	}
	return 0;
}
