#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>
#include <stdlib.h>

/* Function to test:
int isGameOver(struct gameState *state) {
	int i;
	int j;

	//if stack of Province cards is empty, the game ends
	if (state->supplyCount[province] == 0)
	{
		return 1;
	}

	//if three supply pile are at 0, the game ends
	j = 0;
	for (i = 0; i < 25; i++)
	{
		if (state->supplyCount[i] == 0)
		{
			j++;
		}
	}
	if ( j >= 3)
	{
		return 1;
	}

	return 0;
}

*/
int success = 1;

void asserttrue(int statement) {
	if (!statement)
		success = 0;
}

int main() {
	struct gameState state;

	printf("treasure_map: %d\n", treasure_map);	//print value of treasure_map enum

	for (int j = 0; j < treasure_map+1; j++) {	//test all stacks empty
		state.supplyCount[j] = 0;
	}
	int result = isGameOver(&state);
	printf("All stacks empty: result: %d\n", result);
	asserttrue(result == 1);
	
	for (int j = 0; j < treasure_map+1; j++) {	//test only province stack empty
		if (j == province)
			state.supplyCount[j] = 1;
		else
			state.supplyCount[j] = 0;
	}
	result = isGameOver(&state);
	printf("Only province stack empty: result: %d\n", result);
	asserttrue(result == 1);

	for (int j = 0; j < treasure_map+1; j++) {	//test only first two card stacks empty
		if (j < 2)
			state.supplyCount[j] = 0;
		else
			state.supplyCount[j] = 1;
	}
	result = isGameOver(&state);
	printf("Only first two card stacks empty: result: %d\n", result);
	asserttrue(result == 0);

	for (int j = 0; j < treasure_map+1; j++) {	//test only last two card stacks empty
		if (j > treasure_map-2)
			state.supplyCount[j] = 0;
		else
			state.supplyCount[j] = 1;
	}
	result = isGameOver(&state);
	printf("Only last two card stacks empty: result: %d\n", result);
	asserttrue(result == 0);

	for (int j = 0; j < treasure_map+1; j++) {	//test only first three card stacks empty
		if (j < 3)
			state.supplyCount[j] = 0;
		else
			state.supplyCount[j] = 1;
	}
	result = isGameOver(&state);
	printf("Only first three card stacks empty: result: %d\n", result);
	asserttrue(result == 1);

	for (int j = 0; j < treasure_map+1; j++) {	//test only last three card stacks empty
		if (j > treasure_map-3)
			state.supplyCount[j] = 0;
		else
			state.supplyCount[j] = 1;
	}
	result = isGameOver(&state);
	printf("Only last three card stacks empty: result: %d\n", result);
	asserttrue(result == 1);

	for (int j = 0; j < treasure_map+1; j++) {	//test no card stacks empty
		state.supplyCount[j] = 1;
	}
	result = isGameOver(&state);
	printf("No card stacks empty: result: %d\n", result);
	asserttrue(result == 0);

	if (success)
		printf("success!\n");
	else
		printf("failed!\n");

	return 0;
}
