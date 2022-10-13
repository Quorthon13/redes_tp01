package jokenpo.game;

import jokenpo.util.ConstantsUtil.Move;

public class MoveThread extends Thread {
	private Move move;
	private Player player;
	private int matchNumber;

	public MoveThread(Player player, int matchNumber) {
		super();
		this.player = player;
		this.matchNumber = matchNumber;
		move = null;
	}

	@Override
	public void run() {
		player.getHost().sendMessage(Message.createRequestMove(matchNumber, player.getName()));
		String receivedString = player.getHost().readMessage().getContent();

		switch (receivedString) {
		case "pedra":
			move = Move.ROCK;
			break;
		case "papel":
			move = Move.PAPER;
			break;
		case "tesoura":
			move = Move.SCISSORS;
			break;
		}

	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

}