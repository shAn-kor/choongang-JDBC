import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCInsert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        1. 프로그램과 DATABASE 연결
        2. 오라클에서 제공해주는 DB 연결 API
        3. lib 폴더에 ojdbc11.jar 을 넣고, 우클릭 -> build path

        JDBC 는 자동 커밋된다.
         */

        // 1. sql 접속 정보 선언, DB마다 정의 되어 있음
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로컬 DB 주소
        String uid = "HR";
        String upw = "HR";

        Scanner sc = new Scanner(System.in);
        System.out.println("id > ");
        String id = sc.next();
        System.out.println("pw > ");
        String pw = sc.next();
        System.out.println("age > ");
        int age = sc.nextInt();
        System.out.println("email > ");
        String email = sc.next();

        // 실행시킬 SQL
        String sql = "INSERT INTO MEMBER(ID, PW, AGE, EMAIL) VALUES(?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        // 2. JDBC 드라이버 호출
        // java.sql 패키지를 사용하며 내부 클래스들이 전부 throws로 처리되어 있다.
        Class.forName("oracle.jdbc.OracleDriver");

        // 3. SQL 연결 위한 Connection 객체 생성
        conn = DriverManager.getConnection(url, uid, upw);

        // 4. sql 쿼리 구문 실행을 위한 Statement 객체 생성
        // sql의 ?를 1부터 순서대로 채워준다. (setString(), setInt(), setDate(), setTimestemp())
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, pw);
        pstmt.setInt(3, age);
        pstmt.setString(4, email);

        // 5. sql 실행 - insert, update, delete 는 executeUpdate() 로 실행
        int result = pstmt.executeUpdate(); // 적용된 로우행의 개수 반환

        if (result > 0) {
            System.out.println("succeed");
        } else {
            System.out.println("failed");
        }

        conn.close();
        pstmt.close();
    }
}
