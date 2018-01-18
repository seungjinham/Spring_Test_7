package com.iu.board;

import java.util.List;

import com.iu.util.ListData;

public interface BoardService {

	//list
	public List<BoardDTO> selectList(ListData listData) throws Exception;
	
	//one
	public BoardDTO selectOne(int num) throws Exception;
	
	//insert
	public int insert(BoardDTO boardDTO) throws Exception;
	
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int delete(int num) throws Exception;
}
