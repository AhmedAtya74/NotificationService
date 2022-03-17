package NotificationQueueApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Mail implements Isend {

	@Override
	public Boolean Send(int id, ArrayList<String> placeHolders) throws SQLException {
		Connection connection = DB.connectToDatabase();
		Statement statement = (Statement) connection.createStatement();
		String selectQuery = "SELECT * FROM notificationtemplate WHERE id = " + id;
		ResultSet result = statement.executeQuery(selectQuery);
		if (!result.next()) {
			return false;
		}
		int rand = (int) (Math.random() % 2); // according to requirments 
		String body = PreparationToSend(result.getString("body"), placeHolders);
		String insertQuery = "INSERT INTO mail values(" + "\"" + body + "\"" + ",\"" + result.getString("subject")
				+ "\",\"" + id + "\",\"" + result.getString("language") + "\"," + "\""
				+ placeHolders.get(placeHolders.size() - 1) + "\"," + "\"" + placeHolders.get(0) + "\"," + rand + ')';

		statement.executeUpdate(insertQuery);

		return rand > 0;
	}

}
