package kr.or.ddit.rms.shelter.mypage.break_away;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class Break_AwayDaoImpl implements IBreak_AawayDao{
	
	private static Break_AwayDaoImpl dao;
	private SqlMapClient smc;
	
	 private Break_AwayDaoImpl() {
		 smc = XmlConnection.getConnection();
	   }
	   
	   public static Break_AwayDaoImpl getInstance() {
	      if(dao==null) {
	         dao=new Break_AwayDaoImpl();
	      }
	      return dao;
	   }

	   @Override
		public int deleteMember(String id) {
			int cnt = 0;
			try {
				cnt = smc.delete("member.deleteMember", id);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return cnt;
		}


	@Override
	public List<MemberVO> getMemberAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			list = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
}

