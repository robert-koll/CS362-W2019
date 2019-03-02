#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>
#include <stdlib.h>

//unit test for adventurer card
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
			state.deck[player][i] = smithy;
	}
	for (int i = 0; i < state.handCount[player]; i++) {
		state.hand[player][i] = smithy;
	}
	int result = cardEffect(adventurer, 0, 0, 0, &state, handPos, &bonus);
	printf("Adventurer effect completed (returned %d). Deck:\n", result);
	asserttrue(state.deckCount[player] == 8);
	for (int i = 0; i < state.deckCount[player]; i++) {
		printf("\tCard %d: %d\n", i, state.deck[player][i]);
		asserttrue(state.deck[player][i] == smithy);
	}
	printf("\nPlayer %d's hand:\n", player);
	for (int i = 0; i < state.handCount[player]; i++) {
		printf("\tCard %d: %d\n", i, state.hand[player][i]);
	}

}


