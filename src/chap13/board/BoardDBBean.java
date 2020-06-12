package chap13.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	private BoardDBBean() {}
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	public String doA() {
		return "연결";
	}
	
	public void insertArticle(BoardDateBean dataBean) {
		System.out.println(dataBean);
		Connection conn = null; //DB연결하는 객체
		PreparedStatement pstmt = null; //sql연결하는데 insert select update delete 를 연결
		
		//jar파일 관리하는 회사 maven 와 gradle
		
		//jar파일 연결되어이는지 확인 -- Class.forname
		//connection 객체 연결 -- Drivermanager.getConnection();
		//preparestatemenmt 생성 -- conn.prepareStratement();
		// 실행
		try {
			//mysql 3306포트
			//oracle 1521 포트
			//sqlserver 1433 포트 를 일반적으로 사용
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.200:1433;database=uxu_20200611", "sa", "8765432!");
//			System.out.println("디비 연결 성공");
			pstmt = conn.prepareStatement("INSERT INTO [dbo].[board]" + 
					"           ([num]" + 
					"           ,[writer]" + 
					"           ,[email]" + 
					"           ,[subject]" + 
					"           ,[passwd]" + 
					"           ,[reg_date]" + 
					"           ,[readcount]" + 
					"           ,[ref]" + 
					"           ,[re_step]" + 
					"           ,[re_level]" + 
					"           ,[content]" + 
					"           ,[ip])" + 
					"     VALUES" + 
					"           ( (select max(num)+1 as num from board)" + 
					"           , ? " + 
					"           , ? " + 
					"           , ? " + 
					"           , ? " + 
					"           ,getdate()" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           , ? " + 
					"           ,'192.168.0.198')");
			
			pstmt.setString(1,  dataBean.getWriter());
			pstmt.setString(2,  dataBean.getEmail());
			pstmt.setString(3,  dataBean.getSubject());
			pstmt.setString(4,  dataBean.getPasswd());
			pstmt.setString(5,  dataBean.getContent());
			
			pstmt.executeUpdate(); //f5번과 같은 효과, 데이터베이스 실행과 같은 말임
			System.out.println("insert 성공");			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertDetaiLArticle(String name, String subject, String email, String content, String password ) {
		System.out.println("subject = "+subject);
		System.out.println("name = "+name);
		System.out.println("email = "+email);
		System.out.println("content = "+content);
		System.out.println("password = "+password);
	}
}
