import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnectionManager {

    private String url = "";
    private String user = "";
    private String password  ="";

    public DatabaseConnectionManager() {
        this.getProperties();
    }

    private void getProperties (){
        try (InputStream input = new FileInputStream("C:\\Users\\Jakob\\IdeaProjects\\JDBC-1\\src\\main\\resources\\mysettings.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            this.url = prop.getProperty("db.url");
            this.user = prop.getProperty("db.user");
            this.password = prop.getProperty("db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private Connection mincon;

    public Connection getConnection(){
        try {
            mincon = DriverManager.getConnection(this.url, this.user, this.password);
            return mincon;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }


/*    public static void main(String[] args) {
        try {
            Connection forbindelsetilmysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_company", "root", "81c3b080dad537de7e10e0987a4bf52e");
            Statement myStatement = forbindelsetilmysql.createStatement();
            String getAllDepartments = "select * from dept";
            ResultSet alleDepartments = myStatement.executeQuery(getAllDepartments);
            while(alleDepartments.next()){
                System.out.println(alleDepartments.getInt("deptno"));
            }
            System.out.println(alleDepartments.getMetaData());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnectionManager d = new DatabaseConnectionManager();
        d.getProperties();

    }


*/

}

