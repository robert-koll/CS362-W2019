#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>
#include <stdlib.h>

/* Function to test:
int* kingdomCards(int k1, int k2, int k3, int k4, int k5, int k6, int k7,
		int k8, int k9, int k10) {
	int* k = malloc(10 * sizeof(int));
	k[0] = k1;
	k[1] = k2;
	k[2] = k3;
	k[3] = k4;
	k[4] = k5;
	k[5] = k6;
	k[6] = k7;
	k[7] = k8;
	k[8] = k9;
	k[9] = k10;
	return k;
}

*/
int success = 1;

void asserttrue(int statement) {
	if (!statement)
		success = 0;
}

int main() {
	int test[2] = {INT_MIN, INT_MAX};
	int* result = malloc(10 * sizeof(int));

	for (int i = 0; i < 2; i++) {
		printf("k[*]: %d\n", test[i]);
		result = kingdomCards(test[i], test[i], test[i], test[i], test[i], test[i], test[i], test[i], test[i], test[i]);
		for (int j = 0; j < 10; j++) {
			printf("\tresult[%d]: %d\n", j, result[j]);
			asserttrue(result[j] == test[i]);
		}
	}
	if (success)
		printf("success!\n");
	else
		printf("failed!\n");
	return 0;
}
