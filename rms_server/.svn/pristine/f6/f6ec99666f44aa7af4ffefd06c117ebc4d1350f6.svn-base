package kr.or.ddit.rms.member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;

public interface MemberService extends Remote{
	List<MemberVO> getMemberAll()throws RemoteException;
	List<MemberVO> getSearchMember(MemberVO vo)throws RemoteException;
	int insertMember(MemberVO vo)throws RemoteException;
	int updateMember(MemberVO vo)throws RemoteException;
	int deleteMember(MemberVO vo)throws RemoteException;
}
