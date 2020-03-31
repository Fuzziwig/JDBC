import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Department {
    private int DEPTNO;
    private String DNAME;
    private String LOC;

    private Connection con;

    public Department(int DEPTNO, String DNAME, String LOC) {
        this.DEPTNO = DEPTNO;
        this.DNAME = DNAME;
        this.LOC = LOC;
    }

    public Department() {
    }

    public Set getSetOfDepartments(){
        Set<Department> depSet = new HashSet<Department>();
        DatabaseConnectionManager servercon = new DatabaseConnectionManager();
        Statement myStatement;
        try {
            con = servercon.getConnection();
            myStatement = con.createStatement();
            String getAllDeps = "select * from dept";
            ResultSet allDeps = myStatement.executeQuery(getAllDeps);
            while(allDeps.next()){
                depSet.add(new Department(allDeps.getInt("deptno"),
                        allDeps.getString("dname"),
                        allDeps.getString("loc")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return depSet;
    }

    public static void main(String[] args) {
        Set<String> depSet = new HashSet<String>();
        Department D = new Department();
        depSet = D.getSetOfDepartments();
        System.out.println(depSet.toString());
    }

}
