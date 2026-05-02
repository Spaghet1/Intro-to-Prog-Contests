#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

struct PointVector {
	double x;
	double y;
	PointVector operator+(const PointVector& other) const {
		return {x + other.x, y + other.y};
	}
	PointVector operator-(const PointVector& other) const {
		return {x - other.x, y - other.y};
	}
	PointVector operator*(const double scalar) const {
		return {x * scalar, y * scalar};
	}
};

double cross(const PointVector& a, const PointVector& b) {
	return a.x * b.y - a.y * b.x;
}

int main() {
	int lines;
	cin >> lines;
	PointVector points[4];
	cout << "INTERSECTING LINES OUTPUT" << endl;
	for (int i = 0; i < lines; i++) {
		for (int j = 0; j < 4; j++) {
			cin >> points[j].x >> points[j].y;
		}
		PointVector	AB = points[1] - points[0];
		PointVector AC = points[2] - points[0];
		PointVector	CD = points[3] - points[2];
		double totalCross = cross(AB, CD);
		double partialCross = cross(AC, CD);
		if (fabs(totalCross) < 1e-8) {
			if (fabs(partialCross) < 1e-8) cout << "LINE" << endl;
			else cout << "NONE" << endl;
		} else {
			PointVector intersection =  AB * (partialCross / totalCross) + points[0];
			cout << "POINT " << fixed << setprecision(2) << intersection.x << " " << intersection.y << endl;
		}
	}
	cout << "END OF OUTPUT" << endl;
	return 0;
}
