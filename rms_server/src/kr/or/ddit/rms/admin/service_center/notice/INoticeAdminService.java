package kr.or.ddit.rms.admin.service_center.notice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.NoticeVO;

public interface INoticeAdminService extends Remote {
	
	public int insertNotice(NoticeVO vo) throws RemoteException;
	
	public int updateNotice(NoticeVO vo) throws RemoteException;
	
	public int deleteNotice(NoticeVO vo) throws RemoteException;
	
	public List<NoticeVO> getNoticeAll() throws RemoteException;
	
	public List<NoticeVO> getSearchNotice(NoticeVO vo) throws RemoteException;
	
	public List<NoticeVO> getNoticeTextSearch(NoticeVO vo) throws RemoteException;
	

}
