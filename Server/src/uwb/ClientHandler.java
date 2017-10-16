package uwb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	protected Socket clientSocket = null;

	protected BufferedReader reader = null;
	protected BufferedWriter writer = null;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			this.clientSocket = null;
		}
	}

	public boolean isConnected() {
		return (clientSocket.isConnected()) && (!clientSocket.isClosed());
	}

	public boolean write(String message) {
		if (writer == null) {
			return false;
		}
		try {
			writer.write(message + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		return true;
	}

	public String read()
	{
		String res = null;
		if(reader==null){
			return null;
		}
		try {
			if(reader.ready()){
				res = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("ClientHandler::read::error");
			e.printStackTrace();
			close();
		}
		return res;
	}

	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
