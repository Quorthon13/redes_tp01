package jokenpo.game;

import jokenpo.util.ConstantsUtil.Move;

public class Game {

	public static enum Results {
		P1_WINS, P2_WINS, DRAW, P1_WITHDRAWS, P2_WITHDRAWS
	}

	private Move move_1;
	private Move move_2;

	public Game(Move move_1, Move move_2) {
		this.move_1 = move_1;
		this.move_2 = move_2;
	}

	public Move getMove_1() {
		return move_1;
	}

	public void setMove_1(Move move_1) {
		this.move_1 = move_1;
	}

	public Move getMove_2() {
		return move_2;
	}

	public void setMove_2(Move move_2) {
		this.move_2 = move_2;
	}

	public Results getResults() {
		if (move_1 == null) {
			return Results.P1_WITHDRAWS;
		} else if (move_2 == null) {
			return Results.P2_WITHDRAWS;
		} else if ((move_1 == Move.SCISSORS && move_2 == Move.PAPER) //
		        || (move_1 == Move.PAPER && move_2 == Move.ROCK) //
		        || (move_1 == Move.ROCK && move_2 == Move.SCISSORS)) {
			return Results.P1_WINS;
		} else if ((move_2 == Move.SCISSORS && move_1 == Move.PAPER) //
		        || (move_2 == Move.PAPER && move_1 == Move.ROCK) //
		        || (move_2 == Move.ROCK && move_1 == Move.SCISSORS)) {
			return Results.P2_WINS;
		} else {
			return Results.DRAW;
		}
	}

	// [Rock] x [Scissors]
	@Override
	public String toString() {
		return "[" + moveToString(move_1) + "] x [" + moveToString(move_2) + "]";
	}

	private String moveToString(Move move) {
		switch (move) {
		case ROCK:
			return "Pedra";
		case PAPER:
			return "Papel";
		case SCISSORS:
			return "Tesoura";
		}

		return "";
	}

}
