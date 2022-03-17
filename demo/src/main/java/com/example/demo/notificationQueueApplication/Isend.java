package NotificationQueueApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import com.example.demo.database.DefaultConnection;

public interface Isend {

	public Boolean Send(int id, ArrayList<String> placeHolders) throws SQLException;

	public DefaultConnection DB = new DefaultConnection() {
	};

	public default String PreparationToSend(String body, ArrayList<String> placeHolders) {
		int currentPlace = 0;
		while (body.contains("?")) {
			body = body.replaceFirst("?", placeHolders.get(currentPlace++));
		}
		return body;
	}
}
