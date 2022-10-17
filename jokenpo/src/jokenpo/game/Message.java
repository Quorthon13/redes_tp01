package jokenpo.game;

public class Message {
	public static enum MessageType {
		REQUEST_NAME, //
		REQUEST_MOVE, //

		MOVE, //

		PLAYER_WITHDRAWS, //

		PLAYER_WINS_GAME, //
		PLAYER_LOSES_GAME, //
		PLAYER_DRAWS_GAME, //

		PLAYER_WINS_MATCH, //
		PLAYER_LOSES_MATCH, //
		PLAYER_DRAWS_MATCH, //
	}

	private MessageType type;
	private String content;

	public Message(MessageType type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public static Message createPlayerDrawsMatch(Player p_1, Player p_2) {
		return new Message(MessageType.PLAYER_WINS_MATCH,
		        "\n" + getDelimiter() + "Você empatou a partida.\n" + getFinalResult(p_1, p_2));
	}

	public static Message createPlayerWinsMatch(Player p_1, Player p_2) {
		return new Message(MessageType.PLAYER_WINS_MATCH,
		        "\n" + getDelimiter() + "Você ganhou a partida!\n" + getFinalResult(p_1, p_2));
	}

	public static Message createPlayerLosesMatch(Player p_1, Player p_2) {
		return new Message(MessageType.PLAYER_WINS_MATCH,
		        "\n" + getDelimiter() + "Você perdeu a partida.\n" + getFinalResult(p_1, p_2));
	}

	private static String getFinalResult(Player p_1, Player p_2) {
		return "Resultado final: " + p_1.getName() + " [" + p_1.getScore() + "] x [" + p_2.getScore() + "] "
		        + p_2.getName();
	}

	public static Message createName(String name) {
		return new Message(null, name);
	}

	public static Message createRequestMove(int matchNumber, String name) {
		return new Message(MessageType.REQUEST_MOVE, getDelimiter() + "Partida " + matchNumber + "\n" + name
		        + ", digite \"pedra\", \"papel\" ou \"tesoura\", ou \"sair\" para sair: ");
	}

	public static Message createPlayerWithdraws(String name) {
		return new Message(MessageType.PLAYER_WITHDRAWS, "\n\nVitória! " + name + " abandonou o jogo.");
	}

	public static Message createPlayerWinsGame(Match match) {
		return new Message(MessageType.PLAYER_WINS_GAME, getGameOverview(match) + "\nVocê ganhou!");
	}

	public static Message createPlayerLosesGame(Match match) {
		return new Message(MessageType.PLAYER_LOSES_GAME, getGameOverview(match) + "\nVocê perdeu.");
	}

	public static Message createDraw(Match match) {
		return new Message(MessageType.PLAYER_DRAWS_GAME, getGameOverview(match) + "\nEmpate.");
	}

	private static String getGameOverview(Match match) {
		return match.getP_1().getName() + " " + match.getCurrentGame().toString() + " " + match.getP_2().getName();
	}

	private static String getDelimiter() {
		return "\n--------------------------------------------------------------------\n";
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
