package uwb;
import java.io.IOException;

public class Main {
	public static void main(String args[]) {
		Server server = null;
		Triangulation trianulation = null;
		try {
			if (server == null) {
				server = new Server();
				trianulation = new Triangulation();
			}
			server.run();
			trianulation.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
