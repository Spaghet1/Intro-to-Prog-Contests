#include <iostream>
#include <iomanip>
#include <vector>
#include <cmath>

double plugIn(const std::vector<double>& poly, const double x) {
    double total = 0.0;
    for (int i = 0; i < poly.size(); i++) {
        total += poly[i] * std::pow(x, i);
    }
    return total;
}

int main() {
    int n;
    int caseNum = 1;
    while (std::cin >> n) {
        std::vector<double> poly(n + 1);
        for (int i = 0; i <= n; i++) std::cin >> poly[i];
        double xLow, xHigh, inc;
        std::cin >> xLow >> xHigh >> inc;
        std::vector<double> integral((n + 1) * 2, 0.0);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                integral[i + j] += poly[i] * poly[j];
            }
        }
        for (int i = 2 * n + 1; i > 0; i--) {
            integral[i] = integral[i - 1] * M_PI / (i * 1.0);
        }
        integral[0] = 0.0;
        double totalVol = plugIn(integral, xHigh) - plugIn(integral, xLow);
        std::cout << std::fixed << std::setprecision(2) << "Case " << caseNum++ << ": " << totalVol << std::endl;
        if (totalVol < inc) {
            std::cout << "insufficient volume" << std::endl;
            continue;
        }
        double target = inc;
        int marker = 0;
        while (marker < 8 && target < totalVol) {
            double low = xLow;
            double high = xHigh;
            while (high - low > 1e-6) {
                double mid = (high + low) / 2.0;
                double value = plugIn(integral, mid) - plugIn(integral, xLow);
                if (value < target) low = mid;
                else if (value > target) high = mid;
                else break;
            }
            if (marker != 0) std::cout << ' ';
            std::cout << high - xLow;
            target += inc;
            marker++;
        }
        std::cout << std::endl;
    }
}