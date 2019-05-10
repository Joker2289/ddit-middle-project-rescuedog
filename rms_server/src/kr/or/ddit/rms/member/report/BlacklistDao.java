package kr.or.ddit.rms.member.report;

import java.util.List;

import kr.or.ddit.rms.vo.BlacklistVO;

public interface BlacklistDao {
	List<BlacklistVO> getBlacklistAll();
	List<BlacklistVO> getSearchBlacklist(BlacklistVO vo);
	int insertBlacklist(BlacklistVO vo);
	int updateBlacklist(BlacklistVO vo);
	int deleteBlacklist(BlacklistVO vo);
}
