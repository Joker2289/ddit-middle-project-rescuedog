package kr.or.ddit.rms.shelter.mypage.break_away;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;


public interface IBreak_AwaySService extends Remote{

	public int deleteMember(String vo) throws RemoteException;

	public List<MemberVO> getMemberAll() throws RemoteException;
}


