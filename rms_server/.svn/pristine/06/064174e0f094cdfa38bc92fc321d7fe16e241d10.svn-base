package kr.or.ddit.rms.mainpage.find_id;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class FindIdDaoImpl implements IFindIdDao {

	private static FindIdDaoImpl dao;
	private SqlMapClient smc;

	private FindIdDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static FindIdDaoImpl getInstance() {
		if (dao == null) {
			dao = new FindIdDaoImpl();
		}
		return dao;
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) {
		CustomVO temp = null;

		try {

			temp = (CustomVO) smc.queryForObject("custom.getSearchCustom", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public ShelterVO getSearchShelter(ShelterVO vo) {
		ShelterVO temp = null;

		try {

			temp = (ShelterVO) smc.queryForObject("shelter.getSearchShelter", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

}
