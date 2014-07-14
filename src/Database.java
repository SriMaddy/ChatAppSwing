import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String serverUsername, serverPassword;
	private String clientUsername, clientPassword;
	private String message = "";

	public Database() {
		initial();
	}

	void initial() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			connection = DriverManager.getConnection("jdbc:odbc:chatsrc");
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
	}

	void close() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
	}

	public int registerServer(String username, String password) {
		initial();
		int result = 0;
		if (connection != null) {
			try {
				serverUsername = username;
				serverPassword = password;
				statement = connection
						.prepareStatement("insert into ServerRegisterTable values(?,?);");
				statement.setString(1, serverUsername);
				statement.setString(2, serverPassword);
				result = statement.executeUpdate();
			} catch (SQLException e) {
			} finally {
				close();
			}

		}
		return result;
	}

	public int registerClient(String username, String password) {
		initial();
		int result = 0;
		if (connection != null) {
			try {
				clientUsername = username;
				clientPassword = password;
				statement = connection
						.prepareStatement("insert into ClientRegisterTable values(?,?);");
				statement.setString(1, clientUsername);
				statement.setString(2, clientPassword);
				result = statement.executeUpdate();
			} catch (SQLException e) {
			} finally {
				close();
			}

		}
		return result;
	}

	public boolean checkServerCredentials(String username, String password) {
		try {
			initial();
			statement = connection
					.prepareStatement("select * from ServerRegisterTable where username=? AND password=?;");
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				serverUsername = resultSet.getString("username");
				serverPassword = resultSet.getString("password");
			}
			if (serverUsername.equals(username)
					&& serverPassword.equals(password)) {
				return true;
			}
		} catch (SQLException e) {
		} finally {
			close();
		}
		return false;
	}

	public boolean checkClientCredentials(String username, String password) {
		try {
			initial();
			statement = connection
					.prepareStatement("select * from ClientRegisterTable where username=? AND password=?;");
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clientUsername = resultSet.getString("username");
				clientPassword = resultSet.getString("password");
			}
			if (clientUsername.equals(username)
					&& clientPassword.equals(password)) {
				return true;
			}
		} catch (SQLException e) {
		} finally {
			close();
		}
		return false;
	}

	public int storeMessage(String msg, String label) {
		initial();
		int result = 0;
		if (connection != null) {
			try {
				message = label + ": " + msg + "/";
				statement = connection
						.prepareStatement("insert into MessageTable values(?);");
				statement.setString(1, message);
				result = statement.executeUpdate();
				return result;
			} catch (SQLException e) {
			} finally {
				close();
			}
		}
		return -1;
	}

	public String retriveMessage() {
		String copyMessage = "";
		initial();
		if (connection != null) {
			try {
				statement = connection
						.prepareStatement("select * from MessageTable;");
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					copyMessage += resultSet.getString("message");
				}
			} catch (SQLException e) {
			} finally {
				close();
			}
			String[] msgArray = copyMessage.split("/");
			for (String msg : msgArray) {
				message = (message + msg + "\n");
			}
		}
		return message;
	}

}
