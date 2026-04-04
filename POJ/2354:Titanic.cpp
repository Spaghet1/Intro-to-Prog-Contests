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
    double phi_ship = (deg + min / 60.0 + sec / 3600) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    sscanf(line.c_str(), "and %d^%d'%d\" %s.%*c", &deg, &min, &sec, direction);
    double theta_ship = (deg + min / 60.0 + sec / 3600) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    getline(cin, line);
    sscanf(line.c_str(), "%d^%d'%d\" %s%*c", &deg, &min, &sec, direction);
    double phi_ice = (deg + min / 60.0 + sec / 3600) / 180 * M_PI * dirToSign(*direction);
    getline(cin, line);
    sscanf(line.c_str(), "and %d^%d'%d\" %s.%*c", &deg, &min, &sec, direction);
    double theta_ice = (deg + min / 60.0 + sec / 3600) / 180 * M_PI * dirToSign(*direction);

    /* double straight_norm_dist = sqrt(
        pow(cos(theta_ship) * cos(phi_ship) - cos(theta_ice) * cos(phi_ice), 2) +
        pow(sin(theta_ship) * cos(phi_ship) - sin(theta_ice) * cos(phi_ice), 2) +
        pow(sin(phi_ship) - sin(phi_ice), 2) );
    double arc_dist = 2 * asin(straight_norm_dist / 2) * rho; */
    double dphi = phi_ice - phi_ship;
    double dtheta = theta_ice - theta_ship;
    double a = pow(sin(dphi/2), 2) + cos(phi_ship)*cos(phi_ice)*pow(sin(dtheta/2), 2);
    double c = 2 * atan2(sqrt(a), sqrt(1-a));
    double arc_dist = rho * c;
    std::cout << std::setprecision(2) << std::fixed;
    cout << "The distance to the iceberg: " << arc_dist <<  " miles." << endl;
    if (arc_dist < 100.0) cout << "DANGER!" << endl;
    return 0;
}
