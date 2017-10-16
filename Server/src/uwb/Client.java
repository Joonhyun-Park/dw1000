package uwb;
import java.net.Socket;

public class Client extends ClientHandler {
	Database db = null;
	int id;
	public Client(Socket clientsocket) {
		super(clientsocket);
	}

	public void run() {
		db = new Database();
		while (isConnected()) {
			String message = read();
			
			if (message == null) {
				continue;
			}
			String[] data = message.split(" ");
			shared.Object.distanceList.put(Integer.parseInt(data[0]), Double.parseDouble(getHexToDec(data[1])));
			//db.updateLocation(1, tag.x, tag.y);
			System.out.println(data[0]+" "+getHexToDec(data[1]));
		}
	}
	
	private String getHexToDec(String hex) {
		long v = Long.parseLong(hex, 16);
		return String.valueOf(v);
	}
}
