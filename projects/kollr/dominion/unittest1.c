#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>

/* Function to test:
int compare(const void* a, const void* b) {
	if (*(int*)a > *(int*)b)
	return 1;
	if (*(int*)a < *(int*)b)
	return -1;
	return 0;
	}
*/
int success = 1;

void asserttrue(int statement) {
	if (!statement)
		success = 0;
}

int main() {
	int test[6] = {0, -1, INT_MAX, INT_MIN, 500, -500};	//create array of test values

	for (int i = 0; i < 6; i++) {	//iterate through all combinations of test values
		for (int j = 0; j < 6; j++) {
			int result = compare(&(test[i]), &(test[j]));	//call function under test
			if (test[i] > test[j])
				asserttrue(result == 1);
			else if (test[i] == test[j])
				asserttrue(result == 0);
			else
				asserttrue(result == -1);
			printf("a: %d, b: %d, result: %d\n", test[i], test[j], result);	//print result
		}
	}
	if (success)
		printf("success!\n");
	else
		printf("failed!\n");
	return 0;
}
