package kr.or.ddit.rms.user.mypage.updatecustom;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.stage.Stage;
import kr.or.ddit.rms.user.mypage.break_away.IBreak_AwayService;
import kr.or.ddit.rms.vo.CustomVO;

public class UdpateMain extends Application {
	
	public static Stage StageHome;
	public static Registry reg;
	public static IUpdateCustomService cus = null;
	public static IBreak_AwayService ba = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
/*		StageHome = primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("usermodify.fxml"));
		Parent root = loader.load();
			
			Scene scene = new Scene(root);
			StageHome.setTitle("회원정보수정");
			StageHome.setScene(scene);
			StageHome.show();
			*/
	
	}
	
	
	public static void main(String[] args) throws Exception {
		
		try {
			
			reg = LocateRegistry.getRegistry("localhost", 1);
			cus = (IUpdateCustomService) reg.lookup("custom"); 
			ba = (IBreak_AwayService) reg.lookup("ba"); 

		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		//회원정보수정
		CustomVO customvo = new CustomVO();
	    customvo.setCustom_id("1");
	    customvo.setCustom_name("6");
	    customvo.setCustom_email("53");
	    customvo.setCustom_birth("4");
	    customvo.setCustom_tel("6");
	    customvo.setCustom_point("6");

	    int temp1 = cus.updateCustom(customvo);
		
	    System.out.println(temp1);
	 

//		launch(args);
		
		
	}

	
	
}
