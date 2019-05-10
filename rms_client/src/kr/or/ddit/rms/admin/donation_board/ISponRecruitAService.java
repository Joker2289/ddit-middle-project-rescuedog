package kr.or.ddit.rms.admin.donation_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface ISponRecruitAService extends Remote{
	public List<SponVO> getSponAll() throws RemoteException;
	public List<Spon_LogVO> getSponlogAll() throws RemoteException;
	public List<SponVO> getSearchSpon(SponVO vo) throws RemoteException;
	public List<SponVO> getBetweenDate(SponVO vo) throws RemoteException;
	public List<SponVO> getSearchSponText(SponVO vo) throws RemoteException;
	public int insertSpon(SponVO vo) throws RemoteException;
	public int updateSpon(SponVO vo) throws RemoteException;
	public int deleteSpon(SponVO vo) throws RemoteException;
	public int deleteSponLogAll(Spon_LogVO vo) throws RemoteException;
	public int getSpon(String spon_num) throws RemoteException;
}
