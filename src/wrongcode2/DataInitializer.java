package wrongcode2;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DataInitializer {
	public static void initializeDatabase(Connection connection) {
		try {
			if (connection == null) {
				System.err.println("Database connection is null. Check your connection setup.");
				return;
			}
			URL resourceUrl = DataInitializer.class.getClassLoader().getResource("resources/init.sql");
			if (resourceUrl == null) {
				throw new IllegalArgumentException("Resource 'init.sql' not found in the classpath.");
			}

			String sql = new String(Files.readAllBytes(Paths.get(resourceUrl.toURI())));

			try (Statement stmt = connection.createStatement()) {
				stmt.execute(sql);
				System.out.println("Database initialized successfully.");
			}
		} catch (Exception e) {
			System.err.println("Failed to initialize database: " + e.getMessage());
			e.printStackTrace();
		}
	}
}