package kr.or.ddit.rms.admin.service_center.notice;

import java.util.List;

import kr.or.ddit.rms.vo.NoticeVO;

public interface INoticeAdminDao {
	
	public int insertNotice(NoticeVO vo);
	
	public int updateNotice(NoticeVO vo);

	public int deleteNotice(NoticeVO vo);
	
	public List<NoticeVO> getNoticeAll();
	
	public List<NoticeVO> getSearchNotice(NoticeVO vo);
	
	public List<NoticeVO> getNoticeTextSearch(NoticeVO vo);
	
	
	
	
	
	
	
	



}
