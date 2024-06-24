import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class JDBCUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. sql 접속 정보 선언, DB마다 정의 되어 있음
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로컬 DB 주소
        String uid = "HR";
        String upw = "HR";

        Scanner sc = new Scanner(System.in);
        System.out.print("id > ");
        String id = sc.next();
        System.out.print("pw > ");
        String pw = sc.next();
        System.out.print("age > ");
        int age = sc.nextInt();
        System.out.print("email > ");
        String email = sc.next();

        String sql = "UPDATE MEMBER SET AGE = ?, PW = ?, EMAIL = ? WHERE ID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        Class.forName("oracle.jdbc.OracleDriver");

        conn = DriverManager.getConnection(url, uid, upw);
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, age);
        pstmt.setString(2, pw);
        pstmt.setString(3, email);
        pstmt.setString(4, id);

        int result = pstmt.executeUpdate();

        if (result == 0) {
            System.out.println("failed");
        }

        conn.close();
        pstmt.close();
    }
}
