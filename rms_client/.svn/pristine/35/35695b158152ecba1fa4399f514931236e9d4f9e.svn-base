package kr.or.ddit.rms.mainpage.find_pw;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface IFindPwService extends Remote {
	
	CustomVO getSearchCustom(CustomVO vo) throws RemoteException; 
	
	ShelterVO getSearchShelter(ShelterVO vo) throws RemoteException;
	
	MemberVO getSearchMember(MemberVO vo) throws RemoteException;
	
	int updateMember(MemberVO vo) throws RemoteException;
}
