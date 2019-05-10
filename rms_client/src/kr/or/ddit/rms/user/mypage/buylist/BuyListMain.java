package kr.or.ddit.rms.user.mypage.buylist;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuyListMain extends Application {
	
	public static Stage StageHome;
	public static Registry reg;
	public static IBuyListService buy = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageHome = primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("buylist_view.fxml"));
		Parent root = loader.load();
			
			Scene scene = new Scene(root);
			StageHome.setTitle("회원정보수정");
			StageHome.setScene(scene);
			StageHome.show();
	
	}
	
	
	public static void main(String[] args) throws Exception {
		
		try {
			
			reg = LocateRegistry.getRegistry("localhost", 1120);

			buy = (IBuyListService) reg.lookup("buy");
			
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}


		launch(args);
		
		
	}

	
	
}
