import java.sql.*;
import java.util.Scanner;

public class JDBCSelect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 나이가 10 이상인 데이터 조회

        // 1. SQL 접속 정보 선언
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로컬 DB 주소
        String uid = "HR";
        String upw = "HR";

        Scanner sc = new Scanner(System.in);
        System.out.println("age > ");
        int age = sc.nextInt();

        String sql = "SELECT * FROM MEMBER WHERE AGE >= ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        // select 는 ResultSet 객체 필요
        ResultSet rs = null; // select 결과를 조회해서 처리할 ResultSet 객체 필요

        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection(url, uid, upw);
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, age);

        rs = pstmt.executeQuery(); // select는 executeQurey() 사용

        // rs에 저장된 데이터 1행 씩 처리하는 구문
        while (rs.next()) { // 다음이 있는지 확인해서, 다음이 있으면 true
            // 1 행에 대한 프로그램 처리
            // getString(), getInt(), getDate(), getTimestamp() 등을 이용해 데이터 읽는다.
            String id = rs.getString("id"); // 대소문자 구분 없음
            int ages = rs.getInt("age");
            String pw = rs.getString("pw");
            String email = rs.getNString("email");
            System.out.println(id + " " + pw + " " + ages + " " + email);
        }
    }
}
