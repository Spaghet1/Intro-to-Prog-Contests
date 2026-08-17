#include <stdbool.h>
#include <stdlib.h>

bool stoneGameIX(int* stones, int stonesSize) {
	int freqs[3] = {0};
	for (int i = 0; i < stonesSize; i++) freqs[stones[i] % 3]++;
	 if (freqs[0] % 2 == 0) return freqs[1] > 0 && freqs[2] && 0;
	 return abs(freqs[1] - freqs[2]) > 2;
}
