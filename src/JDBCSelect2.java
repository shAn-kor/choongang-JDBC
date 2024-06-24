import java.sql.*;
import java.util.Scanner;

public class JDBCSelect2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        직원 테이블과 부서 테이블 left 조인
        직원아이디, 직무아이디, 부서명, 이름 만 출력
        직원아이디를 입력받아서, 이 아이디에 해당하는 데이터만 출력
         */

        // 1. SQL 접속 정보 선언
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로컬 DB 주소
        String uid = "HR";
        String upw = "HR";

        Scanner sc = new Scanner(System.in);
        System.out.println("employee id > ");
        String e_id = sc.next();

        String sql = "SELECT " +
                "E.EMPLOYEE_ID E_ID, " +
                "E.JOB_ID E_JOB_ID, " +
                "D.DEPARTMENT_NAME D_NAME, " +
                "E.FIRST_NAME || E.LAST_NAME NAME " +
                "FROM EMPLOYEES E " +
                "LEFT JOIN DEPARTMENTS D " +
                "ON E.DEPARTMENT_ID = D.DEPARTMENT_ID " +
                "WHERE E.EMPLOYEE_ID = ? ";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection(url, uid, upw);
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, e_id);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            String emp_id = rs.getString("e_id");
            String j_id = rs.getString("e_job_id");
            String d_name = rs.getString("d_name");
            String name = rs.getString("name");
            System.out.println(emp_id+ " " + j_id + " " + d_name + " " + name);
        }
    }
}
