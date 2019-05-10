package kr.or.ddit.rms.shelter.donation_board.recruit;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.SponVO;

public class SponRecruitSServiceImpl extends UnicastRemoteObject implements ISponRecruitSService, Serializable{

	ISponRecruitSDao dao;
	
	public SponRecruitSServiceImpl() throws RemoteException {
		dao= new SponRecruitSDaoImpl();
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
	public String getShelterName(String shel_name) throws RemoteException {
		return dao.getShelterName(shel_name);
	}

}
