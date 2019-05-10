package kr.or.ddit.rms.admin.goods_mall;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class GoodsMallADaoImpl implements IGoodsMallADao {

	private SqlMapClient smc;

	private static GoodsMallADaoImpl dao;

	private GoodsMallADaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static GoodsMallADaoImpl getInstance() {
		if (dao == null) {
			dao = new GoodsMallADaoImpl();
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
	public String getProd_num() {
		String log = null;

		try {
			log = (String) smc.queryForObject("prod.getProd_num");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public int insertProd(ProdVO vo) {
		int cnt = 0;

		try {
			Object obj = smc.insert("prod.insertProd", vo);

			if (obj == null) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<Buy_LogVO> getBuy_logAll() {
		List<Buy_LogVO> list = null;

		try {

			list = smc.queryForList("buy_log.getBuy_logAll");

		} catch (SQLException e) {
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
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updateRefund_check(Buy_LogVO vo) {
		int cnt = 0;

		try {

			cnt = smc.update("buy_log.updateRefund_check", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) {
		CustomVO temp = null;

		try {
			temp = (CustomVO) smc.queryForObject("custom.getSearchCustom", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return temp;
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

	@Override
	public int deleteProd(ProdVO vo) {
		int cnt = 0;

		try {
			cnt = smc.delete("prod.deleteProd", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<Buy_LogVO> getBetweenSell(Buy_LogVO vo) {
		List<Buy_LogVO> list = null;

		try {

			list = smc.queryForList("buy_log.getBetweenSell", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
