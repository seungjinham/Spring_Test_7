package com.iu.member;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.util.FileSaver;

@Service
public class MemberService {

	@Inject
	private MemberDAO memberDAO;

	public MemberDTO memberIdCheck(String id) throws Exception{
		return memberDAO.memberIdCheck(id);
	}
	
	//========== Join ==========
	public int memberJoin(MemberDTO memberDTO, HttpSession session, MultipartFile file) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		FileSaver fileSaver = new FileSaver();
		String name=fileSaver.saver(file, filePath);
		
		memberDTO.setFname(name);
		memberDTO.setOname(file.getOriginalFilename());
		int result = memberDAO.memberJoin(memberDTO);

		return result;
	}

	//========== Login, View ==========
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberLogin(memberDTO);
	}

	//========== update ==========
	public int memberUpdate(MemberDTO memberDTO,HttpSession session, MultipartFile file) throws Exception{

		if(file != null){
			String filePath = session.getServletContext().getRealPath("resources/upload");

			File f = new File(filePath);
			if(!f.exists()){
				f.mkdirs();
			}

			FileSaver fileSaver = new FileSaver();
			String fileName=fileSaver.saver(file, filePath);

			memberDTO.setFname(fileName);
			memberDTO.setOname(file.getOriginalFilename());
		}else{
			MemberDTO memberDTO2 = (MemberDTO)session.getAttribute("member");
			memberDTO.setFname(memberDTO2.getFname());
			memberDTO.setOname(memberDTO2.getOname());
		}

		return memberDAO.memberUpdate(memberDTO);

	}

	//========== delete ==========
	public int memberDelete(MemberDTO memberDTO, HttpSession session) throws Exception{
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filePath, memberDTO.getFname());
		f.delete();
		
		return memberDAO.memberDelete(memberDTO);
	}
	
}
