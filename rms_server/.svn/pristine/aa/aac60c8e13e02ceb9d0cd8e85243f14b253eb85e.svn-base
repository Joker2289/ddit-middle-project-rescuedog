package kr.or.ddit.rms.user.missing_animal_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.MissingVO;

public interface IMissing_animalService extends Remote{
	List<MissingVO> getmissingAll() throws RemoteException;
	List<MissingVO> getSearchmissing(MissingVO vo) throws RemoteException;
	List<MissingVO> getmissingTextSearch(MissingVO vo) throws RemoteException;
	int getmissing(MissingVO vo) throws RemoteException;
	int insertmissing(MissingVO vo) throws RemoteException;
	int updatemissing(MissingVO vo) throws RemoteException;
	int deletemissing(MissingVO vo) throws RemoteException;
	int updatemissing_free(MissingVO vo) throws RemoteException;
	String getSeq() throws RemoteException;

}
