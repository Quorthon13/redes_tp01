package jokenpo.util;

import java.util.Arrays;
import java.util.List;

public class ConstantsUtil {
	public static final String URL = "localhost";
	public static final Integer PORT = 5069;
	public static final Integer MAX_GAMES = 5;
	public static final List<String> VALID_MOVES = Arrays.asList("pedra", "papel", "tesoura", "sair");

	public static enum Move {
		ROCK, PAPER, SCISSORS;
	}
}
