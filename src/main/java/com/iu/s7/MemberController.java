package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String join(MemberDTO memberDTO) throws Exception{
		memberService.insert(memberDTO);		
		
		return "redirect:../";
	}
	
	//========== IdCheck ==========
	@RequestMapping(value="memberIdCheck", method=RequestMethod.POST)
	public void idCheck() throws Exception{
		
	}


	
	
	
	//========== Login ==========
	@RequestMapping(value="memberLogin", method=RequestMethod.GET)
	public void login() throws Exception{
		
	}

	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView login(HttpSession session, String id, String pw) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPw(pw);

		memberDTO=memberService.selectOne(memberDTO);		
		
		if(memberDTO != null){
			session.setAttribute("member", memberDTO);
			mv.addObject("message", "Login Success");
			mv.addObject("path", "../");
		}else{
			mv.addObject("message", "Login Fail");
			mv.addObject("path", "./memberLogin");
		}
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
	public void view(HttpSession session, Model model) throws Exception{
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		model.addAttribute("view", memberDTO);
	}
	
	
	

	//========== update ==========
	@RequestMapping(value="memberUpdate", method=RequestMethod.GET)
	public void update(HttpSession session, Model model) throws Exception{
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		model.addAttribute("view", memberDTO);
	}
	
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public ModelAndView update(MemberDTO memberDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.update(memberDTO);
		
		if(result>0){
			mv.addObject("message", "Update Success");
		}else {
			mv.addObject("message", "Update Fail");
		}
		
		mv.addObject("path", "./memberView");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	
	
	
	//========== delete ==========
	@RequestMapping(value="memberDelete")
	public String delete(HttpSession session) throws Exception{
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		memberService.delete(memberDTO.getId());
		session.invalidate();
		
		return "redirect:../";
	}
		
}
