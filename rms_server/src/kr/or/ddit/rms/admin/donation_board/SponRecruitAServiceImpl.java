package kr.or.ddit.rms.admin.donation_board;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class SponRecruitAServiceImpl extends UnicastRemoteObject implements ISponRecruitAService, Serializable{

	ISponRecruitADao dao;
	
	public SponRecruitAServiceImpl() throws RemoteException {
		dao= SponRecruitADaoImpl.getInstance();
	}
	
	
	@Override
	public List<SponVO> getSponAll() throws RemoteException {
		return dao.getSponAll();
	}

	@Override
	public List<SponVO> getSearchSpon(SponVO vo) throws RemoteException {
		return dao.getSearchSpon(vo);
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
	public int getSpon(String spon_num) throws RemoteException {
		return dao.getSpon(spon_num);
	}


	@Override
	public List<SponVO> getBetweenDate(SponVO vo) throws RemoteException {
		return dao.getBetweenDate(vo);
	}


	@Override
	public List<SponVO> getSearchSponText(SponVO vo) throws RemoteException {
		return dao.getSearchSponText(vo);
	}


	@Override
	public List<Spon_LogVO> getSponlogAll() throws RemoteException {
		return dao.getSponlogAll();
	}


	@Override
	public int deleteSponLogAll(Spon_LogVO vo) throws RemoteException {
		return dao.deleteSponLogAll(vo);
	}

}
