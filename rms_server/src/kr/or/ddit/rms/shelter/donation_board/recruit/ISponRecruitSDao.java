package kr.or.ddit.rms.shelter.donation_board.recruit;

import java.util.List;

import kr.or.ddit.rms.vo.SponVO;

public interface ISponRecruitSDao {

	public List<SponVO> getSponAll();
	public List<SponVO> getSearchSpon(SponVO vo);
	public List<SponVO> getBetweenDate(SponVO vo);
	public List<SponVO> getSearchSponText(SponVO vo);
	public int insertSpon(SponVO vo);
	public int updateSpon(SponVO vo);
	public int deleteSpon(SponVO vo);
	public int getSpon(String spon_num);
	public String getShelterName(String shel_name);
}
