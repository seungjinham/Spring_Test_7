package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Inject
	private SqlSession session;
	private final String NAMESPACE="MemberMapper.";
	
	public MemberDTO memberIdCheck(String id) throws Exception {
		return session.selectOne(NAMESPACE+"memberIdCheck", id);
	}
	
	//========== Join ==========
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		return session.insert(NAMESPACE+"memberJoin", memberDTO);
	}

	//========== Login, View ==========
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		return session.selectOne(NAMESPACE+"memberLogin", memberDTO);
	}

	//========== update ==========
	public int memberUpdate(MemberDTO memberDTO) throws Exception{
		return session.update(NAMESPACE+"memberUpdate", memberDTO);
	}

	//========== delete ==========
	public int memberDelete(MemberDTO memberDTO) throws Exception{
		return session.delete(NAMESPACE+"memberDelete", memberDTO);
	}

}
