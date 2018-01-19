package com.iu.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.ListData;

public class NoticeDAO implements BoardDAO {
	
	private SqlSession sqlSession;
	private final String NAMESPACE="NoticeMapper.";
	
	public NoticeDAO(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		return sqlSession.selectList(NAMESPACE+"selectList",listData);
		}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"selectOne",num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"insert",boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"update",boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return sqlSession.insert(NAMESPACE+"delete",num);
	}

	@Override
	public int totalCount(ListData listData) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", listData);
	}

}
