package kr.or.ddit.rms.mainpage.sign_up;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.CustomVO;

public class Signup_Page_Controller implements Initializable {
	
	@FXML private ToggleGroup choice;

	@FXML private JFXRadioButton Signup_User_Btn;

	@FXML private JFXRadioButton Signup_Shel_Btn;

	@FXML private Button Signup_Confirm_Btn;

	@FXML private Button Signup_Cancel_Btn;

	public static CustomVO customVo;
	
	public static Stage Signup_Page_view;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		choice = new ToggleGroup();

		Signup_User_Btn.setToggleGroup(choice);

        Signup_Shel_Btn.setToggleGroup(choice);
		
		Signup_Confirm_Btn.setOnAction(e -> {
			
			if (Signup_User_Btn.isSelected()) {
				
				// 1. Stage객체 생성
				Signup_Page_view = new Stage();

				// 2. 모달창 여부 설정
				Signup_Page_view.initModality(Modality.APPLICATION_MODAL);

				// 3. 부모창 지정
				//dialog.initOwner(new Signup_Page_Main().StageHome);

				Signup_Page_view.setTitle("일반회원");
				
				// 4. 자식창에 나타날 컨테이너 객체 생성
				Parent parent = null;

				try {										
					parent = FXMLLoader.load(getClass().getResource("signup_user.fxml"));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				// 5. Scene객체 생성해서 컨테이너 객체 추가하기
				Scene scene = new Scene(parent);			
				
				// 6. Stage객체에 Scene객체 추가
				Signup_Page_view.setScene(scene);
				Signup_Page_view.setResizable(false); // 크기 고정
				Signup_Page_view.show();
				
				Login_controller.dialog.close();
			}

			else if (Signup_Shel_Btn.isSelected()) {
				// 1. Stage객체 생성
				Signup_Page_view = new Stage();

				// 2. 모달창 여부 설정
				Signup_Page_view.initModality(Modality.APPLICATION_MODAL);

				// 3. 부모창 지정
				//dialog.initOwner(new Signup_Page_Main().StageHome);

				Signup_Page_view.setTitle("보호기관");

				// 4. 자식창에 나타날 컨테이너 객체 생성
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("signup_shel.fxml"));

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				// 5. Scene객체 생성해서 컨테이너 객체 추가하기
				Scene scene = new Scene(parent);

				// 6. Stage객체에 Scene객체 추가
				Signup_Page_view.setScene(scene);
				Signup_Page_view.setResizable(false); // 크기 고정
				Signup_Page_view.show();
				
				Login_controller.dialog.close();
			}
			
			
		});
		
		Signup_Cancel_Btn.setOnAction(e->{
			Login_controller.dialog.close();
		});
		
//	//취소버튼
//	Signup_Cancel_Btn.setOnAction(event->{
//		//취소시 화면 꺼짐
//		Button btnCancel = (Button) parent.lookup("#Signup_Cancel_Btn");
//		btnCancel.setOnAction(event->{
//			dialog.close();
//		});
//	});
		

	}

	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();

	}
}
