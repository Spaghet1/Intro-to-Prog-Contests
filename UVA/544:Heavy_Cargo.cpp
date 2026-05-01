#include <queue>
#include <iostream>
#include <string>
#include <unordered_map>
#include <unordered_set>
using namespace std;


struct Road {
	int city1;
	int city2;
	int capacity;
	Road(int city1, int city2, int capacity): city1(city1), city2(city2), capacity(capacity) {}
};


struct Comparator {
	bool operator()(const Road& a, const Road& b) const {
		return a.capacity < b.capacity;
	}
};

int dfs(const vector<vector<int>>& mst, const int currCity, const int destination, int minWeight) {
	if (currCity == destination) return minWeight;
	for (int cityCap : mst[currCity]) {
		int cap = cityCap % 10001;
		int path = dfs(mst, cityCap / 10001, destination, cap < minWeight ? cap : minWeight);
		if (path != -1) return path;
	}
	return -1;
}

int main() {
	int cities, lines;
	int scenario = 1;
	while (true) {
		cin >> cities >> lines;
		if (cities == 0) break;
		vector<vector<int>> graph(cities, vector<int>(cities, 0));
		unordered_map<string, int> cityToIndex;
		int currIndex = 0;
		string city1, city2;
		for (int i = 0; i < lines; i++) {
			int capacity;
			cin >> city1 >> city2 >> capacity;
			if (cityToIndex.find(city1) == cityToIndex.end()) cityToIndex.emplace(city1, currIndex++);
			if (cityToIndex.find(city2) == cityToIndex.end()) cityToIndex.emplace(city2, currIndex++);
			graph[cityToIndex[city1]][cityToIndex[city2]] = capacity;
			graph[cityToIndex[city2]][cityToIndex[city1]] = capacity;
		}
		cin >> city1 >> city2;
		priority_queue<Road, vector<Road>, Comparator> pq;
		unordered_set<int> visited;
		visited.insert(cityToIndex[city1]);
		vector<vector<int>> mst(cities);
		for (int i = 0; i < cities; i++) {
			int capacity = graph[cityToIndex[city1]][i];
			if (capacity > 0) pq.emplace(cityToIndex[city1], i, capacity);
		}
		while (!pq.empty()) {
			Road road = pq.top();
			pq.pop();
			if (visited.find(road.city2) != visited.end()) continue;
			visited.insert(road.city2);
			mst[road.city1].push_back(road.city2 * 10001 + road.capacity);
			for (int i = 0; i < cities; i++) {
				int capacity = graph[road.city2][i];
				if (capacity > 0 && visited.find(i) == visited.end()) pq.emplace(road.city2, i, capacity);
			}
		}
		cout << "Scenario #" << scenario++ << endl;
		cout << dfs(mst, cityToIndex[city1], cityToIndex[city2], INT32_MAX) << " tons\n" << endl;
	}
	return 0;
}
