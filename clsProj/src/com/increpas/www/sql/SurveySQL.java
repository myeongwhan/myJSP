package com.increpas.www.sql;

public class SurveySQL {
	public final int SEL_SRV = 1001;
	public final int SEL_RESULT = 1002;
	public final int SEL_ID_CNT = 1003;	// 참여 여부 체크
	
	public final int EDIT_SRV = 2001;	// cnt 개수늘리기(update)
	
	public final int ADD_SRV_ID = 3001;	// surveyIn 테이블 추가
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_SRV:
			buff.append("SELECT "); 
			buff.append("    sno, sino, sibody title, sq, sa1, sa2, sa3, sa4 "); 
			buff.append("FROM "); 
			buff.append("    survey, surveyInfo "); 
			buff.append("WHERE "); 
			buff.append("    s_sino = sino "); 
			buff.append("    AND sysdate BETWEEN sistart AND siend "); 
			break;
		case EDIT_SRV:
			buff.append("UPDATE "); 
			buff.append("    survey "); 
			buff.append("SET "); 
			buff.append("    sack### = sack### + 1 "); 
			// replaceAll로 ###에 맞는 숫자 채워줄거임
			buff.append("WHERE "); 
			buff.append("    sno = ? ");
			break;
		case ADD_SRV_ID:
			buff.append("INSERT INTO "); 
			buff.append("    surveyin(srvno, srv_sino, srvmno) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(srvno)+1, 1000) FROM surveyin), "); 
			buff.append("    ?, "); // 자바에서 int로 바꿀거임
			buff.append("    (SELECT mno FROM member WHERE id = ?) "); 
			buff.append(") ");
			break;
		}
		return buff.toString();
	}
}
