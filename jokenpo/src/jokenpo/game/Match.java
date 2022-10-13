package jokenpo.game;

import java.util.ArrayList;
import java.util.List;

import jokenpo.game.Game.Results;
import jokenpo.util.ConstantsUtil;

public class Match {
	private Player p_1;

	private Player p_2;

	private List<Game> games;

	private int gameNumber;

	public Match(Player p_1, Player p_2) {
		this.p_1 = p_1;
		this.p_2 = p_2;
		this.gameNumber = 0;
		this.games = new ArrayList<Game>();
	}

	public void play() throws InterruptedException {
		while (gameNumber++ < ConstantsUtil.MAX_GAMES) {
			playSingleGame();
		}

		sendMatchResults();
	}

	private void playSingleGame() throws InterruptedException {
		readMoves();
		sendGameResults();
	}

	private void sendGameResults() {
		Results results = getCurrentGame().getResults();
		if (results == Results.P1_WITHDRAWS) {
			sendWithdraw(getP_2(), getP_1());
		} else if (results == Results.P2_WITHDRAWS) {
			sendWithdraw(getP_1(), getP_2());
		} else if (results == Results.P1_WINS) {
			sendMatchResults(getP_1(), getP_2());
		} else if (results == Results.P2_WINS) {
			sendMatchResults(getP_2(), getP_1());
		} else {
			sendDraw(getP_1(), getP_2());
		}
	}

	private void readMoves() throws InterruptedException {
		MoveThread m_1 = new MoveThread(getP_1(), gameNumber);
		MoveThread m_2 = new MoveThread(getP_2(), gameNumber);
		Thread t_1 = new Thread(m_1);
		Thread t_2 = new Thread(m_2);
		t_1.start();
		t_2.start();
		t_1.join();
		t_2.join();

		addGame(new Game(m_1.getMove(), m_2.getMove()));
	}

	private void sendWithdraw(Player winner, Player loser) {
		winner.getHost().sendMessage(Message.createPlayerWithdraws(loser.getName()));
		winner.increaseScore();
		gameNumber = Integer.MAX_VALUE;
	}

	private void sendMatchResults(Player winner, Player loser) {
		winner.getHost().sendMessage(Message.createPlayerWinsGame());
		loser.getHost().sendMessage(Message.createPlayerLosesGame());
		winner.increaseScore();
	}

	private void sendDraw(Player p_1, Player p_2) {
		Message drawMessage = Message.createDraw();
		p_1.getHost().sendMessage(drawMessage);
		p_2.getHost().sendMessage(drawMessage);
	}

	public void addGame(Game game) {
		this.games.add(game);
	}

	public Game getCurrentGame() {
		return games == null || games.isEmpty() ? null : games.get(games.size() - 1);
	}

	public void increasegameNumber() {
		this.gameNumber++;
	}

	private void sendMatchResults() {
		if (gameNumber < ConstantsUtil.MAX_GAMES) {
			return;
		}

		if (p_1.getScore() == p_2.getScore()) {
			sendPlayerDrawsMatch();
		} else {
			sendPlayerWinsMatch();
		}
	}

	private void sendPlayerDrawsMatch() {
		Message drawMessage = Message.createPlayerDrawsMatch(p_1, p_2);
		p_1.getHost().sendMessage(drawMessage);
		p_2.getHost().sendMessage(drawMessage);
	}

	private void sendPlayerWinsMatch() {
		Message winnerMessage = Message.createPlayerWinsMatch(p_1, p_2);
		Message loserMessage = Message.createPlayerWinsMatch(p_1, p_2);
		if (p_1.getScore() > p_2.getScore()) {
			p_1.getHost().sendMessage(winnerMessage);
			p_2.getHost().sendMessage(loserMessage);
		} else {
			p_1.getHost().sendMessage(loserMessage);
			p_2.getHost().sendMessage(winnerMessage);
		}
	}

	public Player getP_1() {
		return p_1;
	}

	public void setP_1(Player p_1) {
		this.p_1 = p_1;
	}

	public Player getP_2() {
		return p_2;
	}

	public void setP_2(Player p_2) {
		this.p_2 = p_2;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
