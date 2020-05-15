package com.increpas.www.dao;

/**
 * 이 클래스는 설문조사 데이터베이스 작업을 처리할 클래스
 * @author	이명환
 * @since	2020.05.15
 * @version	v.1.0.0
 *
 */

import java.sql.*;
import java.util.*;
import com.increpas.www.DB.*;
import com.increpas.www.vo.*;
import com.increpas.www.sql.*;
public class SurveyDAO {
	CLSDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	SurveySQL sSQL;
	
	public SurveyDAO() {
		db = new CLSDBCP();
		sSQL = new SurveySQL();
	}
	
	// 설문조사 문항 정보를 가져오는 전담처리 함수
	// (하나로 가져올 땐 vo만, 여러 개는 list<vo>로)
	public ArrayList<SurveyVO> getPaper(){
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		// 커넥션
		con = db.getCon();
		// sql
		String sql = sSQL.getSQL(sSQL.SEL_SRV);
		// stmt
		stmt = db.getStmt(con);
		try {
			// 질의명령 보내고 결과받기
			rs = stmt.executeQuery(sql);
			// 데이터 꺼내고 vo에 담기
			while(rs.next()) {
				// 반복할 때마다 매번 vo가 만들어져야됨
				// while안에 넣기
				SurveyVO sVO = new SurveyVO();
				sVO.setSino(rs.getInt("sino"));
				sVO.setTitle(rs.getString("title"));
				sVO.setSno(rs.getInt("sno"));
				sVO.setSq(rs.getString("sq"));
				sVO.setSa1(rs.getString("sa1"));
				sVO.setSa2(rs.getString("sa2"));
				sVO.setSa3(rs.getString("sa3"));
				sVO.setSa4(rs.getString("sa4"));
				
				// 리스트에 담기
				list.add(sVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		// 리스트 내보내기
		return list;
	}
	
	// 설문보기 체크 서밋 db작업 전담처리 함수
	public int updateCount(HashMap<Integer, String> map) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = sSQL.getSQL(sSQL.EDIT_SRV);
		// 질의명령 수정
		Set keys = map.keySet();
		ArrayList<Integer> kList = new ArrayList<Integer>(keys);
		
		for(int no : kList) {
			String tmp = sql;
			tmp.replaceAll("###", map.get(no));
			// pstmt
			pstmt = db.getPstmt(con, tmp);
			try {
				// 질의명령 완성
				pstmt.setInt(1, no);
				// 질의명령 보내고 결과받고
				cnt += pstmt.executeUpdate();	// 변경 행 수 체크 누적
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(pstmt);
			}
		}
		db.close(con);
		// 결과 내보내고
		return cnt;
	}
	
	// 설문참여기록 데이터베이스 작업 전담처리 함수
	public int addRecord(int sino, String id) {	// sino==>(문자열로 받아서 숫자로 바꿀거임)
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = sSQL.getSQL(sSQL.ADD_SRV_ID);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성
			pstmt.setInt(1, sino);
			pstmt.setString(2, id);
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 결과 내보내고
		return cnt;
	}
	
	
}
