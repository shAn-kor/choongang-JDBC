import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDelete {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. sql 접속 정보 선언, DB마다 정의 되어 있음
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로컬 DB 주소
        String uid = "HR";
        String upw = "HR";

        Scanner sc = new Scanner(System.in);
        System.out.print("id > ");
        String id = sc.next();

        String sql = "DELETE FROM MEMBER WHERE ID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        Class.forName("oracle.jdbc.OracleDriver");

        conn = DriverManager.getConnection(url, uid, upw);
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("failed");
        }

        conn.close();
        pstmt.close();
    }
}
