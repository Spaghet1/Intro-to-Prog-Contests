#include <stdio.h>
#include <math.h>

int main() {
    int deg, min, sec;
    char direction;
	char buff[256];
	fgets(buff, sizeof(buff), stdin);
	fgets(buff, sizeof(buff), stdin);
	fgets(buff, sizeof(buff), stdin);
	fgets(buff, sizeof(buff), stdin);
   	sscanf(buff, "%d^%d'%d\" %c", &deg, &min, &sec, &direction);
   	double phi_ship = (deg + min / 60.0 + sec / 3600.0) / 180.0 * M_PI * (direction == 'N' ? 1.0 : -1.0);
	fgets(buff, sizeof(buff), stdin);
   	sscanf(buff, "and %d^%d'%d\" %c.", &deg, &min, &sec, &direction);
   	double theta_ship = (deg + min / 60.0 + sec / 3600.0) / 180.0 * M_PI * (direction == 'E' ? 1.0 : -1.0);
	fgets(buff, sizeof(buff), stdin);
	fgets(buff, sizeof(buff), stdin);
   	sscanf(buff, "%d^%d'%d\" %c", &deg, &min, &sec, &direction);
   	double phi_ice = (deg + min / 60.0 + sec / 3600.0) / 180.0 * M_PI * (direction == 'N' ? 1.0 : -1.0);
	fgets(buff, sizeof(buff), stdin);
   	sscanf(buff, "and %d^%d'%d\" %c.", &deg, &min, &sec, &direction);
   	double theta_ice = (deg + min / 60.0 + sec / 3600.0) / 180.0 * M_PI * (direction == 'E' ? 1.0 : -1.0);
	fgets(buff, sizeof(buff), stdin);

	/* cartesian dist + trig method
   	double dx = cos(theta_ship) * cos(phi_ship) - cos(theta_ice) * cos(phi_ice);
   	double dy = sin(theta_ship) * cos(phi_ship) - sin(theta_ice) * cos(phi_ice);
   	double dz = sin(phi_ship) - sin(phi_ice);
   	double straight_norm_dist = sqrt(dx*dx + dy*dy + dz*dz);
   	double arc_dist = asin(straight_norm_dist / 2.0) * 3437.5;
	*/
	
	/* haversine method
   	double dlat = phi_ship - phi_ice;
   	double dlon = theta_ship - theta_ice;

   	double a = sin(dlat / 2.0) * sin(dlat / 2.0) + cos(phi_ship) * cos(phi_ice) * sin(dlon / 2.0) * sin(dlon / 2.0);
	double arc_dist = atan2(sqrt(a), sqrt(1-a)) * 6875.0;
   	// double arc_dist = 2.0 * asin(sqrt(fmin(1.0, a))) * rho;
	*/

	double arc_dist = acos(sin(phi_ship) * sin(phi_ice) + cos(phi_ship) * cos(phi_ice) * cos(theta_ship - theta_ice)) * 3437.5;

	char fdist[16];
	sprintf(fdist, "%.2f", arc_dist);
	printf("The distance to the iceberg: %s miles.\n", fdist);
   	if (fdist[5] == '\0') printf("DANGER!\n");
	return 0;
}
