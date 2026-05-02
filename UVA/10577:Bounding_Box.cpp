#include <iostream>
#include <cmath>
#include <iomanip>
#include <cfloat>
using namespace std;

struct PointVector {
    double x;
    double y;
    PointVector(): x(0), y(0) {};
    PointVector(double x, double y): x(x), y(y) {};
    PointVector(double r, double angle, const PointVector& append) {
        x = append.x + r * cos(angle);
        y = append.y + r * sin(angle);
    };

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

inline double cross(const PointVector& a, const PointVector& b) {
    return a.x * b.y - a.y * b.x;
}

PointVector intersection(const PointVector& A, const PointVector& B, const PointVector& C, const PointVector& D) {
    PointVector AB = B - A;
    PointVector CD = D - C;
    PointVector AC = C - A;
    double totalCross = cross(AB, CD);
    double partialCross = cross(AC, CD);
    return A + AB * (partialCross / totalCross);
}

int main() {
    int n;
    int numPolygon = 1;
    while (true) {
        cin >> n;
        if (n == 0) break;
        PointVector p1, p2, p3;
        cin >> p1.x >> p1.y >> p2.x >> p2.y >> p3.x >> p3.y;
        PointVector mid1 = p1 + (p2 - p1) * 0.5;
        PointVector mid2 = p1 + (p3 - p1) * 0.5;
        PointVector perpenBisec1 = mid1 + PointVector(-(p2 - p1).y, (p2 - p1).x);
        PointVector perpenBisec2 = mid2 + PointVector(-(p3 - p1).y, (p3 - p1).x);
        PointVector center = intersection(mid1, perpenBisec1, mid2, perpenBisec2);
        const double interAngle = 2.0 * M_PI / n;
        const double radius = hypot((p1 - center).x, (p1 - center).y);
        double currAngle = atan2((p1 - center).y, (p1 - center).x);
        double xmin = DBL_MAX, ymin = DBL_MAX;
        double xmax = -DBL_MAX, ymax = -DBL_MAX;
        for (int i = 0; i < n; i++) {
            PointVector vertex = PointVector(radius, currAngle, center);
            xmin = min(vertex.x, xmin);
            xmax = max(vertex.x, xmax);
            ymin = min(vertex.y, ymin);
            ymax = max(vertex.y, ymax);
            currAngle += interAngle;
        }
        cout << "Polygon " << numPolygon++ << ": " << fixed << setprecision(3) << (xmax - xmin) * (ymax - ymin) << endl;
    }
}