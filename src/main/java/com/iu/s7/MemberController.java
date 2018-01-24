package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {
	@Inject
	private MemberService memberService;

	//========== Join ==========
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public void join() throws Exception{
	}
	
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public String join(MemberDTO memberDTO,HttpSession session, MultipartFile file) throws Exception{
		memberService.memberJoin(memberDTO,session,file);		
		
		return "redirect:../";
	}
	
	//========== IdCheck ==========
	@RequestMapping(value="memberIdCheck", method=RequestMethod.POST)
	public ModelAndView idCheck(String id) throws Exception{
		MemberDTO memberDTO = memberService.memberIdCheck(id);
		
		int result = 1;
		
		if(memberDTO != null){
			result = 0;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("common/fileResult");
		
		return mv;
	}	
	
	
	//========== Login ==========
	@RequestMapping(value="memberLogin", method=RequestMethod.GET)
	public void login() throws Exception{
		
	}

	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView login(MemberDTO memberDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();

		memberDTO=memberService.memberLogin(memberDTO);		

		String message = "Login Fail";
		String path = "./memberLogin";
		
		
		if(memberDTO != null){
			session.setAttribute("member", memberDTO);
			message = "Login Success";
			path = "../";
		}
		
		mv.addObject("message", message);
		mv.addObject("path", path);
		mv.setViewName("common/result");
		
		return mv;
	}
	
	
	
	
	
	//========== Logout ==========
	@RequestMapping(value="memberLogout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:../";
	}

	
	
	
	//========== View ==========
	@RequestMapping(value="memberView")
	public void view() throws Exception{
	}
	
	

	//========== update ==========
	@RequestMapping(value="memberUpdate", method=RequestMethod.GET)
	public void update() throws Exception{
	}
	
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public ModelAndView update(MemberDTO memberDTO,HttpSession session, MultipartFile file) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("fname : "+memberDTO.getFname());
		System.out.println("oname : "+memberDTO.getOname());
		int result = memberService.memberUpdate(memberDTO,session,file);
		
		if(result>0){
			session.setAttribute("member", memberDTO);
			mv.addObject("message", "Update Success");
		}else {
			mv.addObject("message", "Update Fail");
		}
		
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	
	
	
	//========== delete ==========
	@RequestMapping(value="memberDelete")
	public String delete(HttpSession session, RedirectAttributes rd) throws Exception{
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		int result=memberService.memberDelete(memberDTO,session);
		String message = "Delete Fail";
		
		if(result>0){
			message = "Success";
			session.invalidate();
		}
		
		rd.addFlashAttribute("message", message);
		return "home";
	}
		
}
