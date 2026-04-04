#include <cmath>
#include <string>
#include <iostream>
#include <iomanip>
using namespace std;

int dirToSign(char dir) {
    if (dir == 'S' || dir == 'W') return -1;
    return 1;
}

int main() {
    const double rho = 6875.0 / 2;
    int deg, min, sec;
    char direction[4];
    string line;
    getline(cin, line);
    getline(cin, line);
    getline(cin, line);
    getline(cin, line);
    sscanf(line.c_str(), "%d^%d'%d\" %s%*c", &deg, &min, &sec, direction);
    double phi_ship = (deg + min / 60.0 + sec / 3600.0) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    sscanf(line.c_str(), "and %d^%d'%d\" %s.%*c", &deg, &min, &sec, direction);
    double theta_ship = (deg + min / 60.0 + sec / 3600.0) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    getline(cin, line);
    sscanf(line.c_str(), "%d^%d'%d\" %s%*c", &deg, &min, &sec, direction);
    double phi_ice = (deg + min / 60.0 + sec / 3600.0) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    sscanf(line.c_str(), "and %d^%d'%d\" %s.%*c", &deg, &min, &sec, direction);
    double theta_ice = (deg + min / 60.0 + sec / 3600.0) / 180 * M_PI * dirToSign(*direction);

    double dx = cos(theta_ship) * cos(phi_ship) - cos(theta_ice) * cos(phi_ice);
    double dy = sin(theta_ship) * cos(phi_ship) - sin(theta_ice) * cos(phi_ice);
    double dz = sin(phi_ship) - sin(phi_ice);
    double straight_norm_dist = sqrt(dx*dx + dy*dy + dz*dz);
    double arc_dist = 2.0 * asin(straight_norm_dist / 2.0) * rho;

    /* double dlat = phi_ship - phi_ice;
    double dlon = theta_ship - theta_ice;

    double a = sin(dlat / 2.0) * sin(dlat / 2.0) + cos(phi_ship) * cos(phi_ice) * sin(dlon / 2.0) * sin(dlon / 2.0);
    double arc_dist2 = 2.0 * asin(sqrt(fmin(1.0, a))) * rho; */
    std::cout << std::setprecision(2) << std::fixed;
    cout << "The distance to the iceberg: " << arc_dist <<  " miles." << endl;
    // cout << "(HAVERSINE) The distance to the iceberg: " << arc_dist2 <<  " miles." << endl;

    if (arc_dist < 100.0) cout << "DANGER!" << endl;
    return 0;
}
