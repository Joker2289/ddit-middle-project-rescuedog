package kr.or.ddit.rms.member.comments;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.CommentsVO;

public class CommentsServiceImpl extends UnicastRemoteObject implements CommentsService {
	CommentsDao dao;

	public CommentsServiceImpl() throws RemoteException {
		dao = CommentsDaoImpl.getInstance();
	}

	@Override
	public List<CommentsVO> getCommentsAll() throws RemoteException {
		return dao.getCommentsAll();
	}

	@Override
	public List<CommentsVO> getSearchComments(CommentsVO vo) throws RemoteException {
		return dao.getSearchComments(vo);
	}

	@Override
	public int insertComments(CommentsVO vo) throws RemoteException {
		return dao.insertComments(vo);
	}

	@Override
	public int updateComments(CommentsVO vo) throws RemoteException {
		return dao.updateComments(vo);
	}

	@Override
	public int deleteComments(CommentsVO vo) throws RemoteException {
		return dao.deleteComments(vo);
	}

}
