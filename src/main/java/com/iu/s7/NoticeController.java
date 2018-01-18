package com.iu.s7;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeSerivce;
import com.iu.util.FileSaver;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	@Inject
	private NoticeSerivce noticeSerivce;
	
	@RequestMapping(value="noticeList")
	public ModelAndView selectList(ListData listData) throws Exception{
//		System.out.println("search : " +listData.getSearch());
//		System.out.println("kind : "+ listData.getKind());
//		System.out.println("curpage : "+ listData.getCurPage());
		
		ModelAndView mv = new ModelAndView();
		
		List<BoardDTO> ar = noticeSerivce.selectList(listData);
		
		mv.addObject("list", ar);
		mv.addObject("page",listData);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String insert(Model model) throws Exception{
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String insert(NoticeDTO noticeDTO, RedirectAttributes re,MultipartFile f[], HttpSession session) throws Exception{
		//Service class로 session, file들을 넘겨줌
		String filePath=session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath);
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		FileSaver fs = new FileSaver();
		
		for(MultipartFile f1:f){
			String s=fs.save1(f1, filePath);
			System.out.println(s);
		}
		
		int result=noticeSerivce.insert(noticeDTO);
		String message="Write Fail";
		if(result>0){
			message="Write Success";
		}
		
		re.addFlashAttribute("message", message);
		return "redirect:./noticeList";
	}
}
