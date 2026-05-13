#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>
using namespace std;

struct WeightUnique {
    int weight;
    bool isUnique;
};

void dfs(vector<vector<int>>& tree, int curr, vector<WeightUnique>& in, vector<WeightUnique>& out) {
    in[curr].weight = 1;
    in[curr].isUnique = true;
    out[curr].weight = 0;
    out[curr].isUnique = true;
    for (int child : tree[curr]) {
        dfs(tree, child, in, out);

        if (out[child].isUnique == false) in[curr].isUnique = false;
        in[curr].weight += out[child].weight;

        if (in[child].weight == out[child].weight) out[curr].isUnique = false;
        if (in[child].weight > out[child].weight) {
            if (in[child].isUnique == false) out[curr].isUnique = false;
            out[curr].weight += in[child].weight;
        }
        else {
            if (out[child].isUnique == false) out[curr].isUnique = false;
            out[curr].weight += out[child].weight;
        }
    }
}

int main() {
    while (true) {
        int n;
        cin >> n;
        if (!n) break;
        unordered_map<string, int> nameToIndex;
        vector<vector<int>> tree(n);
        string boss;
        string employee;
        cin >> boss;
        nameToIndex[boss] = 0;
        int ID = 1;
        for (int i = 1; i < n; i++) {
            cin >> employee >> boss;
            if (nameToIndex.find(employee) == nameToIndex.end()) {
                nameToIndex[employee] = ID++;
            }
            if (nameToIndex.find(boss) == nameToIndex.end()) {
                nameToIndex[boss] = ID++;
            }
            tree[nameToIndex[boss]].push_back(nameToIndex[employee]);
        }
        vector<WeightUnique> in(n);
        vector<WeightUnique> out(n);
        dfs(tree, 0, in, out);
        bool isUniqueMIS = in[0].isUnique;
        int maxPeople = in[0].weight;
        if (in[0].weight < out[0].weight) {
            maxPeople = out[0].weight;
            isUniqueMIS = out[0].isUnique;
        }
        else if (in[0].weight == out[0].weight) {
            isUniqueMIS = false;
        }

        cout << maxPeople << " " << (isUniqueMIS ? "Yes" : "No") << endl;
    }
    return 0;
}
