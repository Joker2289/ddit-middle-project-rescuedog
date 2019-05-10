package kr.or.ddit.rms.shelter.donation_board.recruit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.SponVO;

public interface ISponRecruitSService extends Remote{

	public List<SponVO> getSponAll() throws RemoteException;
	public List<SponVO> getSearchSpon(SponVO vo) throws RemoteException;
	public List<SponVO> getBetweenDate(SponVO vo) throws RemoteException;
	public List<SponVO> getSearchSponText(SponVO vo) throws RemoteException;
	public int insertSpon(SponVO vo) throws RemoteException;
	public int updateSpon(SponVO vo) throws RemoteException;
	public int deleteSpon(SponVO vo) throws RemoteException;
	public int getSpon(String spon_num) throws RemoteException;
	public String getShelterName(String shel_name) throws RemoteException;
}
