package jokenpo.game;

import jokenpo.client.Host;

public class Player {
	private String name;
	private int score;
	private Host host;

	public Player(String name, Host host) {
		this.name = name;
		this.host = host;
		this.score = 0;
	}

	public void increaseScore() {
		score++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

}
