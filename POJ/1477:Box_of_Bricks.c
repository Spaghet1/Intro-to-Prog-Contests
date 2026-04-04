#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <stdlib.h>

int main() {
	int8_t numset = 1;
	char buff[256];
	while (1) {
		fgets(buff, sizeof(buff), stdin);
		int8_t size = atoi(buff);
		if (size == 0) break;
		int8_t heights[size];
		fgets(buff, sizeof(buff), stdin);
		int avg = 0;
		char* h = strtok(buff, " ");
		for (int i = 0; h != NULL; i++) {
			heights[i] = atoi(h);
			avg += heights[i];
			h = strtok(NULL, " ");
		}
		avg /= size;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			if (heights[i] > avg) sum += heights[i] - avg;
		}
		printf("Set #%d\n", numset++);
		printf("The minimum number of moves is %d.\n\n", sum);
	}
	return 0;
}
