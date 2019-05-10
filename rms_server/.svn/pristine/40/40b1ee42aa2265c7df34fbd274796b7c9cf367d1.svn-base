package kr.or.ddit.rms.shelter.donation_board.recruit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class SponRecruitSDaoImpl implements ISponRecruitSDao{
	private SqlMapClient smc;

	public SponRecruitSDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	@Override
	public List<SponVO> getSponAll() {
		List<SponVO> list = new ArrayList<SponVO>();
		try {
			list = smc.queryForList("spon.getSponAll");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public List<SponVO> getSearchSpon(SponVO vo) {
		List<SponVO> list = new ArrayList<SponVO>();
		try {
			list = smc.queryForList("spon.getSearchSpon",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public int insertSpon(SponVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("spon.insertSpon",vo);
			if (obj == null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 		return cnt;
	}

	@Override
	public int updateSpon(SponVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("spon.updateSpon", vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteSpon(SponVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("spon.deleteSpon", vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int getSpon(String spon_num) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("spon.getSpon", spon_num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SponVO> getBetweenDate(SponVO vo) {
		List<SponVO> list = new ArrayList<SponVO>();
		try {
			list = smc.queryForList("spon.getBetweenDate",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public List<SponVO> getSearchSponText(SponVO vo) {
		List<SponVO> list = new ArrayList<SponVO>();
		try {
			list = smc.queryForList("spon.getSearchSponText",vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public String getShelterName(String shel_name) {
		String name = null;
		try {
			name = (String) smc.queryForObject("shelter.getShelterName",shel_name);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return name;
	}

}
