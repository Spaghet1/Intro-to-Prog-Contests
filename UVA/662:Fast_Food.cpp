#include <iostream>
#include <vector>

struct Cell {
    int weight;
    int parentSplit;
};

int main() {
    int n, k;
    int chainCount = 1;
    while (true) {
        std::cin >> n >> k;
        if (!n && !k) break;
        std::vector<int> restaurants(n);
        for (int i = 0; i < n; i++) {
            std::cin >> restaurants[i];
        }
        std::vector<std::vector<int>> medianCosts(n, std::vector<int>(n, 0));
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                const int median = (i + j) / 2;
                for (int l = i; l <= j; l++) {
                    medianCosts[i][j] += std::abs(restaurants[l] - restaurants[median]);
                }
            }
        }
        std::vector<std::vector<Cell>> dp(k, std::vector<Cell>(n));
        for (int i = 0; i < n; i++) {
            dp[0][i] = {medianCosts[0][i], -1};
        }
        for (int i = 0; i < k; i++) {
            dp[i][0] = {0, -1};
        }
        for (int i = 1; i < k; i++) {
            for (int j = 1; j < n; j++) {
                int minParent = 0;
                int minWeight = 1e9;
                for (int l = 0; l < j; l++) {
                    if (dp[i - 1][l].weight + medianCosts[l + 1][j] < minWeight) {
                        minWeight = dp[i - 1][l].weight + medianCosts[l + 1][j];
                        minParent = l;
                    }
                }
                dp[i][j] = {minWeight, minParent};
            }
        }
        std::vector<int> stack;
        int currIndex = n - 1;
        for (int i = k - 1; i >= 0; i--) {
            stack.push_back(currIndex);
            currIndex = dp[i][currIndex].parentSplit;
        }
        int prev = 0;
        std::cout << "Chain " << chainCount++ << std::endl;
        for (int i = 1; i <= k; i++) {
            int median = (prev + stack.back()) / 2;
            if (prev == stack.back()) std::cout << "Depot " << i << " at restaurant " << prev + 1 << " serves restaurant " << prev + 1 << std::endl;
            else std::cout << "Depot " << i << " at restaurant " << median + 1 << " serves restaurants " << prev + 1 << " to " << stack.back() + 1 << std::endl;
            prev = stack.back() + 1;
            stack.pop_back();
        }
        std::cout << "Total distance sum = " << dp[k - 1][n - 1].weight << std::endl;
        std::cout << std::endl;
    }
    return 0;
}
