#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <limits.h>
#include <stdlib.h>

/* Function to test:
int updateCoins(int player, struct gameState *state, int bonus)
{
	int i;

	//reset coin count
	state->coins = 0;

	//add coins for each Treasure card in player's hand
	for (i = 0; i < state->handCount[player]; i++)
	{
		if (state->hand[player][i] == copper)
		{
			state->coins += 1;
		}
		else if (state->hand[player][i] == silver)
		{
			state->coins += 2;
		}
		else if (state->hand[player][i] == gold)
		{
			state->coins += 3;
		}	
	}	

	//add bonus
	state->coins += bonus;

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
	for (int player = 0; player < MAX_PLAYERS; player++) {	//do testing for as many players as possible
		state.handCount[player] = 10;	//make hands size 10
		for (int i = 0; i < 10; i++) {			//test hand full of adventurers
			state.hand[player][i] = adventurer;
		}
		updateCoins(player, &state, 0);	//without bonus
		printf("Player %d, no coin cards, no bonus: state.coins = %d\n", player, state.coins);
		asserttrue(state.coins == 0);
		updateCoins(player, &state, 3);	//with bonus
		printf("Player %d, no coin cards, bonus 3: state.coins = %d\n", player, state.coins);
		asserttrue(state.coins == 3);
		
		for (int i = 0; i < 10; i++) {	//test one of each coin
			if (i == 0)
				state.hand[player][i] = gold;
			else if (i == 1)
				state.hand[player][i] = silver;
			else if (i == 9)
				state.hand[player][i] = copper;
			else
				state.hand[player][i] = steward;
		}
		updateCoins(player, &state, 0);	//without bonus
		printf("Player %d, one of each coin, no bonus: state.coins = %d\n", player, state.coins);
		asserttrue(state.coins == 6);
		updateCoins(player, &state, 4);	//with bonus
		printf("Player %d, one of each coin, bonus 4: state.coins = %d\n", player, state.coins);
		asserttrue(state.coins == 10);
	}

	if (success)
		printf("success!\n");
	else
		printf("failed!\n");
	
	return 0;
}
