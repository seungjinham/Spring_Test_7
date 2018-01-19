package com.iu.notice;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

public class NoticeSerivce implements BoardService {

	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile [] file, HttpSession session) throws Exception {
		
		//mybatis에서 쿼리를 실행하기 전 num값을 먼저 넣어놓고 그 다음에 쿼리를 실행
		noticeDAO.insert(boardDTO);
		
		FileSaver fileSaver = new FileSaver();
		String filePath = session.getServletContext().getRealPath("resources/upload");
		
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		List<String> names=fileSaver.saver(file, filePath);
		
		for(int i=0; i<names.size(); i++){
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());
			
			fileDAO.insert(fileDTO);			
		}

		return 1;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return noticeDAO.delete(num);
	}

}
