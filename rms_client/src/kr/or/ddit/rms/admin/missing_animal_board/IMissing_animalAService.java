package kr.or.ddit.rms.admin.missing_animal_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.MissingVO;

public interface IMissing_animalAService extends Remote {

	public List<MissingVO> getmissingAll() throws RemoteException;

	public List<MissingVO> getSearchmissing(MissingVO vo) throws RemoteException;

	public List<MissingVO> getmissingTextSearch(MissingVO vo) throws RemoteException;

	public int getmissing(MissingVO vo) throws RemoteException;

	public int insertmissing(MissingVO vo) throws RemoteException;

	public int updatemissing(MissingVO vo) throws RemoteException;

	public int deletemissing(MissingVO vo) throws RemoteException;

	public int updatemissing_free(MissingVO vo) throws RemoteException;

}
