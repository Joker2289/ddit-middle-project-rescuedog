package kr.or.ddit.rms.admin.goods_mall;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public class GoodsMallAServiceImpl extends UnicastRemoteObject implements IGoodsMallAService {

	IGoodsMallADao dao;
	
	public GoodsMallAServiceImpl() throws RemoteException {
		dao = GoodsMallADaoImpl.getInstance();
	}
	
	@Override
	public List<ProdVO> getProdAll() throws RemoteException {
		return dao.getProdAll();
	}

	@Override
	public ProdVO getSearchProd(ProdVO vo) throws RemoteException {
		return dao.getSearchProd(vo);
	}

	@Override
	public List<CartVO> getSearchCart(CartVO vo) throws RemoteException {
		return dao.getSearchCart(vo);
	}

	@Override
	public int deleteCart(String prod_num) throws RemoteException {
		return dao.deleteCart(prod_num);
	}

	@Override
	public int insertCart(CartVO vo) throws RemoteException {
		return dao.insertCart(vo);
	}

	@Override
	public int updateCart(CartVO vo) throws RemoteException {
		return dao.updateCart(vo);
	}

	@Override
	public int deleteAllCart(String custom_id) throws RemoteException {
		return dao.deleteAllCart(custom_id);
	}

	@Override
	public int insertBuy_log(Buy_LogVO vo) throws RemoteException {
		return dao.insertBuy_log(vo);
	}

	@Override
	public List<CardVO> getCardAll() throws RemoteException {
		return dao.getCardAll();
	}

	@Override
	public String getBuy_num() throws RemoteException {
		return dao.getBuy_num();
	}

	@Override
	public String getProd_num() throws RemoteException {
		return dao.getProd_num();
	}

	@Override
	public int insertProd(ProdVO vo) throws RemoteException {
		return dao.insertProd(vo);
	}

	@Override
	public List<Buy_LogVO> getBuy_logAll() throws RemoteException {
		return dao.getBuy_logAll();
	}

	@Override
	public List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) throws RemoteException {
		return dao.getSearchBuy_log(vo);
	}

	@Override
	public int updateRefund_check(Buy_LogVO vo) throws RemoteException {
		return dao.updateRefund_check(vo);
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) throws RemoteException {
		return dao.getSearchCustom(vo);
	}

	@Override
	public List<ProdVO> getLikeProd(ProdVO vo) throws RemoteException {
		return dao.getLikeProd(vo);
	}

	@Override
	public int deleteProd(ProdVO vo) throws RemoteException {
		return dao.deleteProd(vo);
	}

	@Override
	public List<Buy_LogVO> getBetweenSell(Buy_LogVO vo) throws RemoteException {
		return dao.getBetweenSell(vo);
	}
	
	

}
