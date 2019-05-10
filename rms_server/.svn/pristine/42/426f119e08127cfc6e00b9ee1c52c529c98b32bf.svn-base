package kr.or.ddit.rms.user.goods_mall;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class GoodsMallUDaoImpl implements IGoodsMallUDao {

	private SqlMapClient smc;

	private static GoodsMallUDaoImpl dao;

	private GoodsMallUDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static GoodsMallUDaoImpl getInstance() {
		if (dao == null) {
			dao = new GoodsMallUDaoImpl();
		}
		return dao;
	}

	@Override
	public List<ProdVO> getProdAll() {
		List<ProdVO> list = null;

		try {
			list = smc.queryForList("prod.getProdAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ProdVO getSearchProd(ProdVO vo) {
		ProdVO temp = null;

		try {
			temp = (ProdVO) smc.queryForObject("prod.getSearchProd", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public List<CartVO> getSearchCart(CartVO vo) {
		List<CartVO> list = null;

		try {
			list = smc.queryForList("cart.getSearchCart", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) {
		List<Buy_LogVO> list = null;

		try {
			list = smc.queryForList("buy_log.getSearchBuy_log", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteCart(String prod_num) {
		int cnt = 0;

		try {
			cnt = smc.delete("cart.deleteCart", prod_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int insertCart(CartVO vo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("cart.insertCart", vo);

			if (obj == null) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int updateCart(CartVO vo) {
		int cnt = 0;

		try {

			cnt = smc.update("cart.updateCart", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;

	}

	@Override
	public int deleteAllCart(String custom_id) {
		int cnt = 0;

		try {

			cnt = smc.update("cart.deleteAllCart", custom_id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int insertBuy_log(Buy_LogVO vo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("buy_log.insertBuy_log", vo);

			if (obj == null) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<CardVO> getCardAll() {
		List<CardVO> list = null;

		try {

			list = smc.queryForList("card.getCardAll");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String getBuy_num() {
		String log = null;

		try {
			log = (String) smc.queryForObject("buy_log.getBuy_num");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public int updateBuy_log(Buy_LogVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("buy_log.updateBuy_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<CustomVO> getSearchCustom(CustomVO vo) {
		List<CustomVO> list = null;

		try {

			list = smc.queryForList("custom.getSearchCustom", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updatePoint(CustomVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("custom.updatePoint", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<ProdVO> getLikeProd(ProdVO vo) {
		List<ProdVO> list = null;

		try {

			list = smc.queryForList("prod.getLikeProd", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
