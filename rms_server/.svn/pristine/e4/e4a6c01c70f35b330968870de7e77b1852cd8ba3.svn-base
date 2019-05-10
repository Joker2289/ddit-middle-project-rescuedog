package kr.or.ddit.rms.user.donation_board;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationBoardServiceImpl extends UnicastRemoteObject implements IDonationBoardService, Serializable {
	IDonationBoardDao dao;

	public DonationBoardServiceImpl() throws RemoteException {
		dao = DonationBoardDaoImpl.getInstance();
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
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.getSearchSpon_log(vo);
	}

	@Override
	public int updateCustom(CustomVO vo) throws RemoteException {
		return dao.updateCustom(vo);
	}

	@Override
	public int insertSpon(SponVO vo) throws RemoteException {
		return dao.insertSpon(vo);
	}

	@Override
	public int updateSpon(SponVO vo) throws RemoteException {
		return dao.updateSpon(vo);
	}

	@Override
	public int deleteSpon(SponVO vo) throws RemoteException {
		return dao.deleteSpon(vo);
	}


	@Override
	public List<SponVO> getSponAll() throws RemoteException {
		return dao.getSponAll();
	}

	@Override
	public List<SponVO> getSearchSponText(SponVO vo) throws RemoteException {
		return dao.getSearchSponText(vo);
	}

	@Override
	public List<CustomVO> getSearchCustom(CustomVO vo) throws RemoteException {
		return dao.getSearchCustom(vo);
	}

	@Override
	public List<CardVO> getCardAll() throws RemoteException {
		return dao.getCardAll();
	}
}


