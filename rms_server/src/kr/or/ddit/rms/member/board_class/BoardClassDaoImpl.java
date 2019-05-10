package kr.or.ddit.rms.member.board_class;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class BoardClassDaoImpl implements BoardClassDao {

	private static BoardClassDaoImpl dao;
	   private SqlMapClient smc;
	   
	   private BoardClassDaoImpl() {
		   smc = XmlConnection.getConnection();
	   }
	   
	   public static BoardClassDaoImpl getInstance() {
	      if(dao==null) {
	         dao=new BoardClassDaoImpl();
	      }
	      return dao;
	   }
	   
	 
	   
	@Override
	public Board_classVO getBoardClass(Board_classVO vo) {
		int cnt = 0;
		Board_classVO temp = null;
		try {
			
			temp= (Board_classVO) smc.queryForObject("board_class.getSearchBoard_class", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public int insertBoardClass(Board_classVO vo) {
		int cnt = 0;
		try {
			
			Object obj = smc.insert("board_class.insertBoardClass", vo);
			if(obj == null) { //성공하면null
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<Board_classVO> getBoardClassAll() {
		List<Board_classVO> list=null;
		try {
			list = smc.queryForList("board_class.getBoard_classAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_classVO> getSearchBoardClass(Board_classVO vo) {
		List<Board_classVO> list=null;
		try {
			list = smc.queryForList("board_class.getSearchBoard_class",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateBoardClass(Board_classVO vo) {
		int num =0;
		try {
			num = smc.update("board_class.updateBoard_class",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	
}
