package com.kh.delivery.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.dto.DeliMemberDto;


public class MemberDao {
	
	public int signUp(SqlSession session, DeliMemberDto memberDto) {
		return session.insert("member-mapper.signUp", memberDto);
	}
	
	public DeliMemberDto selectLogin(SqlSession session, DeliMemberDto memberDto) { // 입력한 값과 db상 뽑은 값을 서비스단에서 체크
		return session.selectOne("member-mapper.selectLogin", memberDto);
		}

}
