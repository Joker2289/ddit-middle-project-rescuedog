package kr.or.ddit.rms.user.mypage.note;

import java.rmi.RemoteException;
import java.util.ArrayList;

import kr.or.ddit.rms.vo.NoteVO;

public class sss {
	public static void main(String[] args) throws RemoteException {
		INoteService service = new NoteServiceImpl();
		ArrayList<NoteVO> list = (ArrayList<NoteVO>) service.getNoteAll();
		System.out.println(list.get(0).getNote_title());
//		
//		NoteVO vo = new NoteVO();
//		vo.setNote_id_to("2");
//		ArrayList<NoteVO> listTo = (ArrayList<NoteVO>) service.getNoteTo("2");
//		System.out.println(listTo.get(0).getNote_content());
		
//	    int delList = service.deleteNote("1");
//		System.out.println(delList);
		
		NoteVO vo = new NoteVO();
		
		 vo.setNote_id_to("6");
		 vo.setNote_title("2");
		 vo.setNote_content("2");
		 vo.setNote_date(null);
		 vo.setNote_cnt("1");
		 vo.setNote_id_from("7");
		 
		 int update = service.updateNote(vo);
		
//		NoteVO vo = new NoteVO();
//		vo.setNote_cnt("0");
//		vo.setNote_content("13425");
//		vo.setNote_date(null);
//		vo.setNote_id_from("7");
//		vo.setNote_id_to("9");
//		vo.setNote_title("gjfk");
//		
//		System.out.println(service.insertNote(vo));
		

	}
}
