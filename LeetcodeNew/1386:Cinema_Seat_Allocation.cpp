#include <vector>
#include <algorithm>
#include <iostream>

class Solution {
public:
	int maxNumberOfFamilies(int n, std::vector<std::vector<int>> reservedSeats) {
		struct {
			bool operator()(const std::vector<int>& seat1, const std::vector<int>& seat2) {
				if (seat1.front() == seat2.front()) return seat1.back() < seat2.back();
				return seat1.front() < seat2.front();
			}
		} comparator;
		std::ranges::sort(reservedSeats, comparator);
		int rowIndex = 0; 
		int total = 2 * n;
		while (rowIndex < reservedSeats.size()) {
			int currRow = reservedSeats[rowIndex][0];
			bool s2 = true;
			bool s4 = true;
			bool s6 = true;
			while (rowIndex < reservedSeats.size() && reservedSeats[rowIndex][0] == currRow) {
				if (reservedSeats[rowIndex][1] == 2 || reservedSeats[rowIndex][1] == 3) s2 = false;
				if (reservedSeats[rowIndex][1] == 4 || reservedSeats[rowIndex][1] == 5) {
					s2 = false;
					s4 = false;
				}
				if (reservedSeats[rowIndex][1] == 6 || reservedSeats[rowIndex][1] == 7) {
					s4 = false;
					s6 = false;
				}
				if (reservedSeats[rowIndex][1] == 8 || reservedSeats[rowIndex][1] == 9) s6 = false;
                rowIndex++;
			}
			if (s2 && s6);
			else if (s2 || s4 || s6) total--;
			else total -= 2;
		}
		return total;
	}
};

int main() {
	std::vector<std::vector<int>> v = {
    {1, 2},
    {1, 3},
    {1, 8},
    {2, 6},
    {3, 1},
    {3, 10}
	};
	std::cout << Solution{}.maxNumberOfFamilies(3, v) << std::endl;
}
