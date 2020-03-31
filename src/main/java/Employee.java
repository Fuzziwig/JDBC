import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class Employee {
    private int EMPNO;
    private String ENAME;
    private String JOB;
    private int MGR;
    private Date HIREDATE;
    private int SAL;
    private int COMM;
    private  int DEPTNO;

    private Connection con;

    public Employee(int EMPNO, String ENAME, String JOB, int MGR, Date HIREDATE, int SAL, int COMM, int DEPTNO) {
        this.EMPNO = EMPNO;
        this.ENAME = ENAME;
        this.JOB = JOB;
        this.MGR = MGR;
        this.HIREDATE = HIREDATE;
        this.SAL = SAL;
        this.COMM = COMM;
        this.DEPTNO = DEPTNO;
    }

    public Employee() {
    }

    public Map getAllEmployees(){
    Map<Integer, Employee> empMap = new TreeMap<Integer, Employee>();
    DatabaseConnectionManager servercon = new DatabaseConnectionManager();
    Statement myStatement;
    try {
    con = servercon.getConnection();
    myStatement = con.createStatement();
    String getAllEmps = "select * from emp";
    ResultSet allEmployees = myStatement.executeQuery(getAllEmps);
    int tmpkey = 0;
    while(allEmployees.next()){
        tmpkey = allEmployees.getInt("empno");
        System.out.println(allEmployees.getString("ename"));
        if (!empMap.containsKey(tmpkey)){
            empMap.put(tmpkey, new Employee(tmpkey,
                    allEmployees.getString("ename"),
                    allEmployees.getString("job"),
                    allEmployees.getInt("mgr"),
                    allEmployees.getDate("hiredate"),
                    allEmployees.getInt("sal"),
                    allEmployees.getInt("comm"),
                    allEmployees.getInt("deptno")));
        }
    }
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
    return empMap;
}

    public static void main(String[] args) {
        Map<Integer, String> empMap = new TreeMap<Integer, String>();
        Employee E = new Employee();
        empMap = E.getAllEmployees();
        System.out.println(empMap.toString());

    }

}
