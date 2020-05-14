package com.increpas.www.dao;

import java.sql.*;
import com.increpas.www.DB.*;
import com.increpas.www.sql.memberSQL;

public class memberDAO {
	CLSDBCP db;
	memberSQL mSQL;

	public memberDAO() {
		db = new CLSDBCP();
		mSQL = new memberSQL();
	}
	
	// 로그인 DB처리
	public int getCnt(String id, String pw) {
		int cnt = 0;
		
		Connection con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		
		PreparedStatement pstmt = db.getPstmt(con, sql);
		ResultSet rs = null;
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt("cnt");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

}
