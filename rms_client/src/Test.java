import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.rms.mainpage.login.ILoginService;
import kr.or.ddit.rms.member.board_class.BoardClassService;
import kr.or.ddit.rms.user.mypage.updatecustom.IUpdateCustomService;
import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;

public class Test {

	public static void main(String[] args) throws RemoteException {
		
		Registry reg;
		BoardClassService bcs = null;
		ILoginService ms = null;
		IUpdateCustomService cus = null;
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 4444);
			bcs = (BoardClassService) reg.lookup("server");
			ms = (ILoginService) reg.lookup("member");
			cus = (IUpdateCustomService) reg.lookup("custom"); 
			

		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		Board_classVO vo = new Board_classVO();
		vo.setBc_num("1");
		Board_classVO temp = bcs.getBoardClass(vo);
		System.out.println(temp.getBc_num());
		System.out.println(temp.getBc_name());
		
		
		
		//vo.setBc_num("2");
		//vo.setBc_name("源??룄?쁽");
		
//		int d = clientInf.insertBoard(vo);
//		System.out.println(d);
		
		
		MemberVO memvo = new MemberVO();
		
		memvo.setMem_id("jk1");
		memvo.setMem_pw("1234");
		
		MemberVO mem_temp = ms.getSearchMember(memvo);
		
		//mem_temp.getMem_author();
		
		System.out.println(mem_temp.getMem_author());
		
		
		//회원정보수정
		CustomVO customvo = new CustomVO();
	    customvo.setCustom_id("2");
	    customvo.setCustom_name("2");
	    customvo.setCustom_email("3");
	    customvo.setCustom_birth("3");
	    customvo.setCustom_tel("3");
	    //customvo.setCustom_pw("3");

	    int temp1 = cus.updateCustom(customvo);
		
	    System.out.println(temp1);
		
		
		
	}
}
