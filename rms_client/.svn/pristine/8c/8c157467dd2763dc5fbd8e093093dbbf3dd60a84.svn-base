package kr.or.ddit.rms.mainpage.login;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.rms.vo.AdminVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface ILoginService extends Remote {
	
	MemberVO getSearchMember(MemberVO vo) throws RemoteException; 
	
	CustomVO getSearchCustom(CustomVO vo) throws RemoteException; 
	
	ShelterVO getSearchShelter(ShelterVO vo) throws RemoteException;
	
	AdminVO getSearchAdmin(AdminVO vo) throws RemoteException;
}
