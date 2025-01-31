package wrongcode2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDB implements IDB {
	private String url;
	private String user;
	private String password;
	private Connection connection;

	public PostgreDB(String url, String user, String password, String database) {
		this.url = url + "/" + database;
		this.user = user;
		this.password = password;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}

	@Override
	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}