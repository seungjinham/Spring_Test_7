package com.iu.qna;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.ListData;
@Repository
public class QnaDAO implements BoardDAO {
	@Inject
	private SqlSession sqlsession;
	private final String NAMESPACE="QnaMapper.";
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		return sqlsession.selectList(NAMESPACE+"selectList",listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return sqlsession.selectOne(NAMESPACE+"selectOne", num);
	}

	public int num() throws Exception{
		return sqlsession.selectOne(NAMESPACE+"num"); 
	}
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return sqlsession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return sqlsession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return sqlsession.delete(NAMESPACE+"delete", num);
	}
	
	public int replyUpdate(BoardDTO boardDTO) throws Exception{
		return sqlsession.update(NAMESPACE+"replyUpdate",boardDTO);
	}
	
	public int replyInsert(BoardDTO boardDTO) throws Exception{
		return sqlsession.insert(NAMESPACE+"replyInsert", boardDTO);
	}

	@Override
	public int totalCount(ListData listData) throws Exception {
		return sqlsession.selectOne(NAMESPACE+"totalCount",listData);
	}

}
