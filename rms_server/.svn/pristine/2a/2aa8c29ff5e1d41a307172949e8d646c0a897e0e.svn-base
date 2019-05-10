package kr.or.ddit.rms.shelter.donation_board;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationBoardSServiceImpl extends UnicastRemoteObject implements IDonationBoardSService, Serializable {
	IDonationBoardSDao dao;

	public DonationBoardSServiceImpl() throws RemoteException {
		dao = DonationBoardSDaoImpl.getInstance();
	}

	@Override
	public int insertSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.insertSpon_log(vo);
	}

	@Override
	public int updateSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.updateSpon_log(vo);
	}

	@Override
	public int deleteSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.deleteSpon_log(vo);
	}

	@Override
	public List<Spon_LogVO> getSpon_logAll() throws RemoteException {
		return dao.getSpon_logAll();
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
	public List<Board_detailVO> getSearchBoard_detail_or(Board_detailVO vo) throws RemoteException {
		return dao.getSearchBoard_detail_or(vo);
	}

	@Override
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.getSearchSpon_log(vo);
	}

	@Override
	public int insertBoard(Board_detailVO vo) throws RemoteException {
		return dao.insertBoard(vo);
	}

	@Override
	public int updateBoard(Board_detailVO vo) throws RemoteException {
		return dao.updateBoard(vo);
	}

	@Override
	public int deleteBoard(Board_detailVO vo) throws RemoteException {
		return dao.deleteBoard(vo);
	}

}
