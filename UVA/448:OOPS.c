#include <stdio.h>

const char* OPERATIONS[] = {"ADD", "SUB", "MUL", "DIV", "MOV", "BREQ", "BRLE", "BRLS", "BRGE", "BRGR", "BRNE", "BR", "AND", "OR", "XOR", "NOT"};
const char* OPERAND_TYPE[] = {"R", "$", "PC+", ""};
const int NUM_OPERANDS[] = {2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1};

int ctoi(char c) {
	if (c <= '9') return c - '0';
	return c - 'A' + 10;
}

int extractOperand(char* cptr) {
	int operand = ctoi(*cptr++) & 3;
	operand = operand * 16 + ctoi(*cptr++);
	operand = operand * 16 + ctoi(*cptr++);
	operand = operand * 16 + ctoi(*cptr++);
	return operand;	
}

int readBytes(char* bytes, int numBytes) {
	for (int i = 0; i < numBytes; i++) {
		if (scanf("%c", bytes + i, stdin) == -1) return 0;
		if (bytes[i] == '\n') i--;
	}
	return 1;
}

int main() {
	char bytes[13];
	while (1) {
		if(!readBytes(bytes, 1)) break;
		readBytes(bytes + 1, NUM_OPERANDS[ctoi(*bytes)] * 4);
		char* cptr = bytes;
		int opIdx = ctoi(*cptr++);
		const char* operation = OPERATIONS[opIdx];
		printf("%s ", operation);
		const char* operand_prefix = OPERAND_TYPE[ctoi(*cptr) >> 2];
		int operand = extractOperand(cptr);
		cptr += 4;
		printf("%s%d", operand_prefix, operand);
		for (int i = 0; i < NUM_OPERANDS[opIdx] - 1; i++) {
			const char* operand_prefix = OPERAND_TYPE[ctoi(*cptr) >> 2];
			int operand = extractOperand(cptr);
			cptr += 4;
			printf(",%s%d", operand_prefix, operand);
		}
		printf("\n");
	}

}
