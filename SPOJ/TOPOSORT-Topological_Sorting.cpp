#include <iostream>
#include <vector>
#include <queue>
#include <functional>
using namespace std;

int main() {
    int tasks, lines;
    cin >> tasks >> lines;
    vector<vector<int>> graph(tasks + 1);
    vector<int> inDegrees(tasks + 1, 0);
    inDegrees[0] = INT32_MAX;
    for (int i = 0; i < lines; i++) {
        int task1, task2;
        cin >> task1 >> task2;
        graph[task1].push_back(task2);
        inDegrees[task2]++;
    }
    priority_queue<int, vector<int>, greater<int>> pq;
    vector<int> ordering;
    for (int node = 1; node <= tasks; node++) {
        if (inDegrees[node] == 0) pq.push(node);
    }
    while (!pq.empty()) {
        int node = pq.top();
        pq.pop();
        for (int toNode : graph[node]) {
            inDegrees[toNode]--;
            if (inDegrees[toNode] == 0) pq.push(toNode);
        }
        ordering.push_back(node);
    }
    if (ordering.size() == tasks) {
        for (int i = 0; i < tasks - 1; i++) {
            cout << ordering[i] << " ";
        }
        cout << ordering[ordering.size() - 1] << endl;
    }
    else {
        cout << "Sandro fails." << endl;
    }
}