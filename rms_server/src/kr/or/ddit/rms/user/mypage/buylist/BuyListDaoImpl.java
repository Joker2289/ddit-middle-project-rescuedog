package kr.or.ddit.rms.user.mypage.buylist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class BuyListDaoImpl implements IBuyListDao {

	private SqlMapClient smc;
	private static BuyListDaoImpl dao;

	public BuyListDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static BuyListDaoImpl getInstance() {
		if (dao == null) {
			dao = new BuyListDaoImpl();
		}
		return dao;
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
	public int updateBuy_log(Buy_LogVO vo) {
		int num = 0;
		try {
			num = smc.update("buy_log.updateBuy_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteBuy_log(String vo) {
		int num = 0;
		try {
			num = smc.delete("buy_log.deleteBuy_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Buy_LogVO> getBuyTo(String custom_id) {
		List<Buy_LogVO> listTo = new ArrayList<Buy_LogVO>();
		try {
			listTo = smc.queryForList("buy_log.getBuyTo", custom_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTo;
	}

	@Override
	public List<CustomVO> getCustomAll() {

		List<CustomVO> list = null;
		try {
			list = smc.queryForList("custom.getCustomAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Buy_LogVO> getBetweenProd(Buy_LogVO vo) {
		List<Buy_LogVO> list = null;
		try {
			list = smc.queryForList("buy_log.getBetweenProd", vo);
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

}
