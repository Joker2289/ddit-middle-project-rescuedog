package kr.or.ddit.rms.admin.free_board;

import java.rmi.RemoteException;
import java.util.ArrayList;

import kr.or.ddit.rms.vo.Board_detailVO;

public class lee {
	public static void main(String[] args) {
		try {
			Free_boardService ser = new Free_boardServiceImpl();
			Board_detailVO vo = new Board_detailVO();
			vo.setCustom_id("1");
			ArrayList<Board_detailVO> list =(ArrayList<Board_detailVO>) ser.getSearchBoard_detail(vo);
			System.out.println(list.get(0).getContent());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
