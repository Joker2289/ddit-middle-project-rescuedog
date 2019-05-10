package kr.or.ddit.rms.member.board_class;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Board_classVO;

public class BoardClassServiceImpl extends UnicastRemoteObject implements BoardClassService {
	
	BoardClassDao dao;
	public BoardClassServiceImpl() throws RemoteException {
		dao = BoardClassDaoImpl.getInstance();
	}
	
	@Override
	public Board_classVO getBoardClass(Board_classVO vo) throws RemoteException{
		return dao.getBoardClass(vo);
	}

	@Override
	public int insertBoardClass(Board_classVO vo) throws RemoteException {
		return dao.insertBoardClass(vo);
	}

	@Override
	public List<Board_classVO> getBoardClassAll() throws RemoteException {
		return dao.getBoardClassAll();
	}

	@Override
	public List<Board_classVO> getSearchBoardClass(Board_classVO vo) throws RemoteException {
		return dao.getSearchBoardClass(vo);
	}

	@Override
	public int updateBoardClass(Board_classVO vo) throws RemoteException {
		return dao.updateBoardClass(vo);
	}
	

}
