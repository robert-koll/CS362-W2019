#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>
#include <stdlib.h>

//unit test for smithy card
int success = 1;

void asserttrue(int statement) {
	if (!statement)
		success = 0;
}

void test(int i1, int i2) {
	struct gameState state;
	int bonus = 0;
	int handPos = 5;
	state.whoseTurn = 0;
	int player = state.whoseTurn;
	state.handCount[player] = 10;
	state.deckCount[player] = 10;
	state.numPlayers = MAX_PLAYERS;
	for (int i = 0; i < state.handCount[player]; i++) {
		if (i == i1 || i == i2)
			state.deck[player][i] = gold;
		else
			state.deck[player][i] = adventurer;
	}
	for (int i = 0; i < state.handCount[player]; i++) {
		state.hand[player][i] = adventurer;
	}
	int result = cardEffect(smithy, 0, 0, 0, &state, handPos, &bonus);
	printf("Smithy effect completed (returned %d). Deck:\n", result);
	asserttrue(state.deckCount[player] == 7);
	for (int i = 0; i < state.deckCount[player]; i++) {
		printf("\tCard %d: %d\n", i, state.deck[player][i]);
		asserttrue(state.deck[player][i] == adventurer);
	}
	printf("\nPlayer %d's hand:\n", player);
	for (int i = 0; i < state.handCount[player]; i++) {
		printf("\tCard %d: %d\n", i, state.hand[player][i]);
	}

}


int main() {
	printf("\ngold at 0 and 1:\n");
	test(0, 1);
	printf("\ngold at 1 and 2: \n");
	test(1, 2);
	printf("\ngold at 5 and 9: \n");
	test(5, 9);
	printf("\ngold at 8 and 9: \n");
	test(8, 9);

	if (success)
		printf("success!\n");
	else
		printf("failed!\n");

	return 0;
}
