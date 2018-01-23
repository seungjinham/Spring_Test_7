package com.iu.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Inject
	private MemberDAO memberDAO;

	//========== Join ==========
	public int insert(MemberDTO memberDTO) throws Exception{
		return memberDAO.insert(memberDTO);
	}

	//========== Login, View ==========
	public MemberDTO selectOne(MemberDTO memberDTO) throws Exception{
		return memberDAO.selectOne(memberDTO);
	}

	//========== update ==========
	public int update(MemberDTO memberDTO) throws Exception{
		return memberDAO.update(memberDTO);
	}

	//========== delete ==========
	public int delete(String id) throws Exception{
		return memberDAO.delete(id);
	}
	
}
