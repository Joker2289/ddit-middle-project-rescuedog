package kr.or.ddit.rms.member.comments;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.CommentsVO;

public interface CommentsService extends Remote {
	List<CommentsVO> getCommentsAll() throws RemoteException;
	List<CommentsVO> getSearchComments(CommentsVO vo) throws RemoteException;
	int insertComments(CommentsVO vo) throws RemoteException;
	int updateComments(CommentsVO vo) throws RemoteException;
	int deleteComments(CommentsVO vo) throws RemoteException;
}
