package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Inject
	private SqlSession session;
	private final String NAMESPACE="MemberMapper.";
	
	//========== Join ==========
	public int insert(MemberDTO memberDTO) throws Exception{
		return session.insert(NAMESPACE+"insert", memberDTO);
	}

	//========== Login, View ==========
	public MemberDTO selectOne(MemberDTO memberDTO) throws Exception{
		return session.selectOne(NAMESPACE+"selectOne", memberDTO);
	}

	//========== update ==========
	public int update(MemberDTO memberDTO) throws Exception{
		return session.update(NAMESPACE+"update", memberDTO);
	}

	//========== delete ==========
	public int delete(String id) throws Exception{
		return session.delete(NAMESPACE+"delete", id);
	}

}
