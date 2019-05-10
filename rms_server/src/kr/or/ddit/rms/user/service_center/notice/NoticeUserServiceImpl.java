package kr.or.ddit.rms.user.service_center.notice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.NoticeVO;

public class NoticeUserServiceImpl extends UnicastRemoteObject implements INoticeUserService {

	INoticeUserDao dao;

	public NoticeUserServiceImpl() throws RemoteException {
		dao = NoticeUserDaoImpl.getInstance();
	}

	/*
	 * @Override public int insertNotice(NoticeVO vo) throws RemoteException {
	 * return dao.insertNotice(vo); }
	 * 
	 * @Override public int updateNotice(NoticeVO vo) throws RemoteException {
	 * return dao.updateNotice(vo); }
	 * 
	 * @Override public int deleteNotice(NoticeVO vo) throws RemoteException {
	 * return dao.deleteNotice(vo); }
	 */

	@Override
	public List<NoticeVO> getNoticeAll() throws RemoteException {
		return dao.getNoticeAll();
	}

	@Override
	public List<NoticeVO> getSearchNotice(NoticeVO vo) throws RemoteException {
		return dao.getSearchNotice(vo);
	}

	@Override
	public List<NoticeVO> getNoticeTextSearch(NoticeVO vo) throws RemoteException {
		return dao.getNoticeTextSearch(vo);
	}

}
