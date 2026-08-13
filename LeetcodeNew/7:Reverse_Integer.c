#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int reverse(int x) {
	int result = 0;
	int sign = x > 0;
	while (x != 0) {
		int digit = x % 10;
		if (sign && (INT_MAX - digit) / 10 < result) return 0;
		else if (!sign && (INT_MIN - digit) / 10 > result) return 0;
		result = result * 10 + digit;
		x /= 10;
	}
	return result;
}

int main() {
	char buff[16];
	fgets(buff, sizeof(buff), stdin);
	printf("%d\n", reverse(atoi(buff)));
}
