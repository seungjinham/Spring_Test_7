package com.iu.qna;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	@Inject
	private FileDAO fileDAO;
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount=qnaDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return qnaDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile [] file, HttpSession session) throws Exception {
		
		qnaDAO.insert(boardDTO);
		
		FileSaver fs = new FileSaver();
		String filePath=session.getServletContext().getRealPath("resources/upload");
		
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		List<String> names= fs.saver(file, filePath);
		
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
	public int update(BoardDTO boardDTO, MultipartFile [] file, HttpSession session) throws Exception {
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> ar = fileDAO.selectList(num);		
		int result = qnaDAO.delete(num); 
		result = fileDAO.delete(num);
		
		for(FileDTO fileDTO : ar){
			try {
				File file = new File(filePath, fileDTO.getFname());
				file.delete();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
		
	public int replyInsert(BoardDTO boardDTO) throws Exception{
		qnaDAO.replyUpdate(boardDTO);
		return qnaDAO.replyInsert(boardDTO);
	}

}
