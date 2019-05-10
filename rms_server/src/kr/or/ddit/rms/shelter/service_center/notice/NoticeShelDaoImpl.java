package kr.or.ddit.rms.shelter.service_center.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.NoticeVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class NoticeShelDaoImpl implements INoticeShelDao {

	private static NoticeShelDaoImpl dao;

	private SqlMapClient smc;

	private NoticeShelDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static NoticeShelDaoImpl getInstance() {
		if (dao == null) {
			dao = new NoticeShelDaoImpl();
		}
		return dao;
	}

	/*
	 * @Override public int insertNotice(NoticeVO vo) { int cnt = 0; try {
	 * 
	 * Object obj = smc.insert("notice.insertNotice", vo); if (obj == null) { cnt =
	 * 1; } } catch (SQLException e) { e.printStackTrace(); } return cnt; }
	 * 
	 * @Override public int updateNotice(NoticeVO vo) { int cnt = 0; try { cnt =
	 * smc.update("notice.updateNotice", vo); } catch (SQLException e) {
	 * e.printStackTrace(); } return cnt; }
	 * 
	 * @Override public int deleteNotice(NoticeVO vo) { int cnt = 0; try { cnt =
	 * smc.delete("notice.deleteNotice", vo); } catch (SQLException e) {
	 * e.printStackTrace(); } return cnt; }
	 */

	@Override
	public List<NoticeVO> getNoticeAll() {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			list = smc.queryForList("notice.getNoticeAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<NoticeVO> getSearchNotice(NoticeVO vo) {
		List<NoticeVO> list = null;
		try {
			list = smc.queryForList("notice.getSearchNotice", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<NoticeVO> getNoticeTextSearch(NoticeVO vo) {
		List<NoticeVO> list = null;
		try {
			list = smc.queryForList("notice.getNoticeTextSearch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
