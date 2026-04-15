#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>
using namespace std;

void dfs(vector<vector<int>>& tree, int curr, vector<vector<int>>& inOrOut, vector<vector<bool>>& ambigInOrOut) {
    for (int i : tree[curr]) {
        dfs(tree, i, inOrOut, ambigInOrOut);
        inOrOut[0][curr] += inOrOut[1][i];
        if (ambigInOrOut[1][i]) ambigInOrOut[0][curr] = true;
        if (inOrOut[0][i] > inOrOut[1][i]) {
            inOrOut[1][curr] += inOrOut[0][i];
            ambigInOrOut[1][curr] = ambigInOrOut[1][curr] || ambigInOrOut[0][i];
        }
        else if (inOrOut[0][i] < inOrOut[1][i]) {
            inOrOut[1][curr] += inOrOut[1][i];
            ambigInOrOut[1][curr] = ambigInOrOut[1][curr] || ambigInOrOut[1][i];
        }
        else {
            inOrOut[1][curr] += inOrOut[0][i];
            ambigInOrOut[1][curr] = true;
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
        string name;
        cin >> name;
        nameToIndex[name] = 0;
        for (int i = 1; i < n; i++) {
            cin >> name;
            nameToIndex[name] = i;
            cin >> name;
            tree[nameToIndex[name]].push_back(i);
        }
        vector<vector<int>> inOrOut( {vector<int>(n, 1), vector<int>(n, 0)});
        vector<vector<bool>> ambigInOrOut(2, vector<bool>(n, false));
        dfs(tree, 0, inOrOut, ambigInOrOut);
        bool in = inOrOut[0][0] > inOrOut[1][0];
        bool ambig = in ? ambigInOrOut[0][0] : ambigInOrOut[1][0];
        if (inOrOut[0][0] == inOrOut[1][0]) ambig = true;
        cout << (in ? inOrOut[0][0] : inOrOut[1][0]) << " " << (ambig ? "No" : "Yes") << endl;
    }
    return 0;
}
