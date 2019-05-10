package kr.or.ddit.rms.xmls;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class XmlConnection {
	
	private static SqlMapClient smc;  // SqlMapClient객체 변수 선언
	
	public static SqlMapClient getConnection(){
		
		if(smc == null) {
			
			Reader rd;
			try {
				rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close();
			} catch (IOException e) {
				System.out.println("SqlMapClient객체 생성 실패!!");
				e.printStackTrace();
			}
		}
		
		return smc;
	}
	
}
