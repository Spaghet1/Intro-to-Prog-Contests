#include <vector>
#include <numeric>
#include <algorithm>


class Solution {
public:
	using ll = long long;
	
	ll lcm(ll a, ll b) {
		return a / std::gcd(a, b) * b;
	}

	ll calculateWays(const std::vector<int>& coins, ll target, int index, ll prevLCM, bool isSubtract) {
		ll total = 0LL;
		for (int i = index; i < coins.size(); i++) {
			ll nextLCM = lcm(prevLCM, coins[i]);
			if (nextLCM > target) continue;
			ll intersection = target / nextLCM;
			total = isSubtract ? total - intersection : total + intersection;
			total += calculateWays(coins, target, i + 1, nextLCM, !isSubtract);
		}
		return total;
	}

	void pruneMultiples(std::vector<int>& coins) {
		std::ranges::sort(coins);
		for (int i = 0; i < coins.size() - 1; i++) {
			for (int j = i + 1; j < coins.size(); j++) {
				if (coins[j] % coins[i] == 0) coins.erase(coins.begin() + j);
			}
		}
	}
	
	long long findKthSmallest(std::vector<int>& coins, int k) {
		pruneMultiples(coins);
		int minVal = *std::ranges::min_element(coins);
		ll lowerBound = minVal;
		ll upperBound = (ll) k * minVal;
		while (lowerBound < upperBound) {
			ll mid = lowerBound + (upperBound - lowerBound) / 2LL;
			ll currWays = calculateWays(coins, mid, 0, 1LL, false);
			if (currWays >= k) upperBound = mid;
			else lowerBound = mid + 1LL;
		}
		return lowerBound;
	}
};
