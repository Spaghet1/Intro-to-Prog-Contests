#include <iostream>
using namespace std;

int main() {
    int n;
    int num;
    int total = 0;
    cin >> n;
    for (int i = 1; i < n; i++) {
        cin >> num;
        total ^= num;
        total ^= i;
    }
    cout << (total ^ n) << endl;
}