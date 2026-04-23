package com.kh.delivery.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.common.Template;
import com.kh.delivery.dao.MemberDao;
import com.kh.delivery.dto.DeliMemberDto;


public class MemberService {
	SqlSession session = new Template().getSqlConnection(); 
	MemberDao memberDao = new MemberDao();
	
	public int signUp(DeliMemberDto memberDto) {
		int result =0;
			try {
				result = memberDao.signUp(session, memberDto);
				} catch(org.apache.ibatis.exceptions.PersistenceException e) { // db에서 유니크 제약, 중복 집어넣을 시 뱉는 exception
					System.out.println("중복된 ID입니다, 다른 ID를 입력해주세요."); 
				} catch(Exception e) {
					System.out.println("잘못된 형식의 ID입니다, 다른 ID를 입력해주세요.");
				} 
		if(result >0) {
			session.commit();
		}
			session.close();
		return result;
	}
	
	public DeliMemberDto selectLogin(DeliMemberDto memberDto) {
		DeliMemberDto LoginResult = null;
		try {
			LoginResult = memberDao.selectLogin(session, memberDto);
			}catch(Exception e) {
			System.out.println("잘못된 접근입니다.");
			return null;
		}
		return LoginResult;
	}

}
