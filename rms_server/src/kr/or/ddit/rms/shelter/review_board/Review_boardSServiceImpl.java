package kr.or.ddit.rms.shelter.review_board;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;

public class Review_boardSServiceImpl extends UnicastRemoteObject implements Review_boardSService, Serializable {
	Review_boardSDao dao;

	public Review_boardSServiceImpl() throws RemoteException {
		dao = Review_boardSDaoImpl.getInstance();
	}

	@Override
	public List<Board_detailVO> getBoard_detailAll() throws RemoteException {
		return dao.getBoard_detailAll();
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) throws RemoteException {
		return dao.getSearchBoard_detail(vo);
	}

	@Override
	public List<Board_detailVO> getReview_detailTextSearch(Board_detailVO vo) throws RemoteException {
		return dao.getReview_detailTextSearch(vo);
	}

	@Override
	public int insertBoard_detail(Board_detailVO vo) throws RemoteException {
		return dao.insertBoard_detail(vo);
	}

	@Override
	public int updateBoard_detail(Board_detailVO vo) throws RemoteException {
		return dao.updateBoard_detail(vo);
	}

	@Override
	public int deleteBoard_detail(Board_detailVO vo) throws RemoteException {
		return dao.deleteBoard_detail(vo);
	}
}
