package jokenpo.client;

import java.util.Scanner;

import jokenpo.game.Message;
import jokenpo.game.Message.MessageType;
import jokenpo.util.ConstantsUtil;

public class Client extends Host {
	Scanner scanner = new Scanner(System.in);

	public Client() {
		super.start();

		while (processServerMessage())
			;

		scanner.close();
		close();
	}

	// continuará rodando até retornar false
	private boolean processServerMessage() {
		Message message = super.readMessage();
		switch (message.getType()) {
		case REQUEST_NAME:
			requestAndSendName(message);
			break;
		case REQUEST_MOVE:
			if (!requestAndSendMove(message)) {
				return false;
			}
			break;
		case PLAYER_WINS_GAME:
		case PLAYER_LOSES_GAME:
		case PLAYER_DRAWS_GAME:
			showGameResult(message);
			break;
		case PLAYER_WINS_MATCH:
		case PLAYER_LOSES_MATCH:
		case PLAYER_DRAWS_MATCH:
			showMatchResult(message);
			return false;
		case PLAYER_WITHDRAWS:
			showPlayerWithdraws(message);
			return false;
		default:
			break;
		}
		return true;
	}

	private void showMatchResult(Message message) {
		printMessage(message);
	}

	private void showGameResult(Message message) {
		printMessage(message);
	}

	private void showPlayerWithdraws(Message message) {
		printMessage(message);
	}

	private void requestAndSendName(Message message) {
		printMessage(message);

		String name = scanner.nextLine();
		sendMessage(Message.createName(name));

		System.out.println("Aguardando um novo oponente...");
	}

	// retorna false quando usuário digita "sair"
	private boolean requestAndSendMove(Message message) {
		printMessage(message);

		String entrada = getUserInput();
		sendMessage(new Message(MessageType.MOVE, entrada));
		if (entrada.equals("sair"))
			return false;

		System.out.println("Aguardando outro oponente...");
		return true;
	}

	private String getUserInput() {
		String entrada;
		while (true) {
			entrada = scanner.nextLine();
			if (verifyInput(entrada))
				break;
			else
				System.out.println("Entrada inválida.");
		}
		return entrada;
	}

	private boolean verifyInput(String entrada) {
		return ConstantsUtil.VALID_MOVES.contains(entrada.toLowerCase());
	}

	private void printMessage(Message message) {
		System.out.println(message.getContent());
	}

}