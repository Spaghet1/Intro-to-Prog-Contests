#include <stdio.h>

int main() {
	int n;
	scanf("%d", &n);
	int triangle[n * (n + 1) / 2];
	for (int i = 0; i < n * (n + 1) / 2; i++) scanf("%d", triangle + i);
	for (int i = n - 1; i > 0; i--) {
		for (int j = i * (i + 1) / 2 - 1; j > i * (i - 1) / 2 - 1; j--) {
			triangle[j] = triangle[j] + (triangle[j + i] > triangle[j + i + 1] ? triangle[j + i] : triangle[j + i + 1]);
		}
	}
	printf("%d\n", triangle[0]);
}
