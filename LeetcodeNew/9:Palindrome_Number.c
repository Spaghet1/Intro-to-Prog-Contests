#include <stdbool.h>
#include <stdio.h>

bool isPalindrome(int x) {
	if (x < 0) return false;
	int upperDivisor = 1;
	for (int res = x / 10; res != 0; res /= 10) upperDivisor *= 10;
	while (x != 0) {
		if (x < 10 && upperDivisor == 1) break;
		if (x / upperDivisor != x % 10) return false;
		x %= upperDivisor;
		x /= 10;
		upperDivisor /= 100;
	}
	return true;
}

int main() {
	printf("%d\n", isPalindrome(100021));
}
