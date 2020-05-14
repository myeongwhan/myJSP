package com.increpas.www.sql;

public class memberSQL {
	public final int SEL_LOGIN = 111;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LOGIN:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			buff.append("	and pw = ? ");
			break;
		}
		return buff.toString();
	}
}
