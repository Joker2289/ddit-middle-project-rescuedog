package kr.or.ddit.rms.user.mypage.note;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.NoteVO;

public interface INoteService extends Remote{
	List<NoteVO> getNoteAll() throws RemoteException;
	List<NoteVO> getNoteTo(String note_id_to) throws RemoteException;
	int deleteNote(NoteVO vo) throws RemoteException;
	int deleteAllNote(NoteVO vo) throws RemoteException;
	int updateNote(NoteVO vo) throws RemoteException;
	int insertNote(NoteVO vo) throws RemoteException;

}
