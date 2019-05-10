package kr.or.ddit.rms.user.adopt_board;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.Main;

public class ChangeScene {
	
	public static void ChangeView(Class getC, String fxml, boolean blur, Stage s) { // 화면전환을 위한 메서드
		Parent window1 = null; 
	    try {
	        window1 = FXMLLoader.load(getC.getResource(fxml)); //getC로 클래스를 받아와서 fxml받아온 값을 화면에 입력
	        Stage newStage;
	        Scene newScene = new Scene(window1);
	        if(blur) {
	        	window1.requestFocus();
	        }
	        
	        newStage = s;
	        newStage.setScene(newScene);
	        
	       newStage.show();
	       //BoardMain.StageHome.getScene().getRoot().lookup("VBox").requestFocus();
	        
	    } catch (IOException e2) {
	        e2.printStackTrace();
	    }
	}
}

