package kr.or.ddit.rms.user.mypage.buylist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CustomVO;

public class BuyListServiceImpl extends UnicastRemoteObject implements IBuyListService {

	IBuyListDao dao;

	public BuyListServiceImpl() throws RemoteException {
		dao = new BuyListDaoImpl();
	}

	@Override
	public List<Buy_LogVO> getBuy_logAll() throws RemoteException {
		return dao.getBuy_logAll();
	}

	@Override
	public int updateBuy_log(Buy_LogVO vo) throws RemoteException {
		return dao.updateBuy_log(vo);
	}

	@Override
	public int deleteBuy_log(String vo) throws RemoteException {
		return dao.deleteBuy_log(vo);
	}

	@Override
	public List<Buy_LogVO> getBuyTo(String custom_id) throws RemoteException {
		return dao.getBuyTo(custom_id);
	}

	@Override
	public List<CustomVO> getCustomAll() throws RemoteException {
		return dao.getCustomAll();
	}

	@Override
	public List<Buy_LogVO> getBetweenProd(Buy_LogVO vo) throws RemoteException {
		return dao.getBetweenProd(vo);
	}

	@Override
	public List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) throws RemoteException {
		return dao.getSearchBuy_log(vo);
	}

}
