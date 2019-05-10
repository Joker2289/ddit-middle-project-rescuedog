package kr.or.ddit.rms.user.donation_board;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DonationBoard_ChoiceController implements Initializable {
	
	@FXML private ToggleGroup choice;
	
	@FXML private JFXRadioButton card_Btn;
	@FXML private JFXRadioButton point_Btn;
	@FXML private Button Confirm_Btn;
	@FXML private Button Cancel_Btn;
	
	public static Stage choice_madal;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choice = new ToggleGroup();

		card_Btn.setToggleGroup(choice);

		point_Btn.setToggleGroup(choice);
		
		Confirm_Btn.setOnAction(e -> {
			
			if (card_Btn.isSelected()) {
				
				// 1. Stage객체 생성
				choice_madal = new Stage();

				// 2. 모달창 여부 설정
				choice_madal.initModality(Modality.APPLICATION_MODAL);

				// 3. 부모창 지정
				//dialog.initOwner(new Signup_Page_Main().StageHome);

				choice_madal.setTitle("카드후원");
				
				// 4. 자식창에 나타날 컨테이너 객체 생성
				Parent parent = null;

				try {										
					parent = FXMLLoader.load(getClass().getResource("payment_card.fxml"));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				// 5. Scene객체 생성해서 컨테이너 객체 추가하기
				Scene scene = new Scene(parent);			
				
				// 6. Stage객체에 Scene객체 추가
				choice_madal.setScene(scene);
				choice_madal.setResizable(false); // 크기 고정
				choice_madal.show();
				
				DonationBoard_DetailController.donation_choice_modal.close();
			}

			else if (point_Btn.isSelected()) {
				// 1. Stage객체 생성
				choice_madal = new Stage();

				// 2. 모달창 여부 설정
				choice_madal.initModality(Modality.APPLICATION_MODAL);

				// 3. 부모창 지정
				//dialog.initOwner(new Signup_Page_Main().StageHome);

				choice_madal.setTitle("포인트후원");

				// 4. 자식창에 나타날 컨테이너 객체 생성
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("payment_point.fxml"));

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				// 5. Scene객체 생성해서 컨테이너 객체 추가하기
				Scene scene = new Scene(parent);

				// 6. Stage객체에 Scene객체 추가
				choice_madal.setScene(scene);
				choice_madal.setResizable(false); // 크기 고정
				choice_madal.show();
				
				DonationBoard_DetailController.donation_choice_modal.close();
			}
			
			
		});
		
		Cancel_Btn.setOnAction(e->{
			DonationBoard_DetailController.donation_choice_modal.close();
		});
	
		
	}

}
