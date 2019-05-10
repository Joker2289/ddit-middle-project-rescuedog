package kr.or.ddit.rms.member.comments;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.CommentsVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class CommentsDaoImpl implements CommentsDao {

	private static CommentsDaoImpl dao;

	private SqlMapClient smc;

	private CommentsDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static CommentsDaoImpl getInstance() {
		if (dao == null) {
			dao = new CommentsDaoImpl();
		}
		return dao;
	}

	@Override
	public List<CommentsVO> getCommentsAll() {
		List<CommentsVO> list = null;
		try {
			list = smc.queryForList("comments.getCommentsAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CommentsVO> getSearchComments(CommentsVO vo) {
		List<CommentsVO> list = null;
		try {
			list = smc.queryForList("comments.getSearchComments", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertComments(CommentsVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("comments.insertComments", vo);
			if (obj == null) {
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updateComments(CommentsVO vo) {
		int num = 0;
		try {
			num = smc.update("comments.updateComments", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteComments(CommentsVO vo) {
		int num = 0;
		try {
			num = smc.delete("comments.deleteComments", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
