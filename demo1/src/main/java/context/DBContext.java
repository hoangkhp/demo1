package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
  public Connection getConnection() throws Exception {
    String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;hostNameInPackage=*";

    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    return DriverManager.getConnection(url, userID, password);
  }

  private final String serverName = "LAPTOP-CUA-HOAN";
  private final String dbName = "quanlybanhang";
  private final String portNumber = "1433";
  private final String instance = "";
  private final String userID = "sa";
  private final String password = "anhemta123";

  public static void main(String[] args) {
    try{
      System.out.println(new DBContext().getConnection());
    } catch (Exception e) {

    }
  }

}
