package uwb;
import java.io.IOException;
import java.net.*;

public class Server {
	protected int SERVICE_PORT = 54321;
	protected ServerSocket serverSocket = null;

	public Server() {
		serverInit();
	}

	protected void serverInit() {
		try {
			serverSocket = new ServerSocket(SERVICE_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean run() throws IOException {
		if (serverSocket == null)
			return false;

		while (true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client Connected : " + clientSocket);
			Client client = new Client(clientSocket);
			client.start();
		}
	}
}
