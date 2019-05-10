package kr.or.ddit.rms.user.mypage.note;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.NoteVO;

public class NoteServiceImpl extends UnicastRemoteObject implements INoteService, Serializable {
	INoteDao dao;

	public NoteServiceImpl() throws RemoteException {
		dao = NoteDaoImpl.getInstance();
	}

	@Override
	public List<NoteVO> getNoteAll() throws RemoteException {
		return dao.getNoteAll();
	}

	@Override
	public List<NoteVO> getNoteTo(String note_id_to) throws RemoteException {
		return dao.getNoteTo(note_id_to);
	}

	@Override
	public int deleteNote(NoteVO vo) throws RemoteException {
		return dao.deleteNote(vo);
	}

	@Override
	public int deleteAllNote(NoteVO vo) throws RemoteException {
		return dao.deleteAllNote(vo);
	}

	@Override
	public int updateNote(NoteVO vo) throws RemoteException {
		return dao.updateNote(vo);
	}

	@Override
	public int insertNote(NoteVO vo) throws RemoteException {
		return dao.insertNote(vo);
	}
}
