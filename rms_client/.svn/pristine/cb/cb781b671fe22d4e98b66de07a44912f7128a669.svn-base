package kr.or.ddit.rms.user.donation_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.Spon_LogVO;
public class DonationBoard_CardController  implements Initializable{

   @FXML JFXCheckBox check_all;
   @FXML JFXCheckBox check_1;
   @FXML JFXCheckBox check_2;
   @FXML JFXComboBox<String> card_choice;
   @FXML JFXTextField cardnum_1;
   @FXML JFXTextField cardnum_2;
   @FXML JFXTextField cardnum_3;
   @FXML JFXTextField cardnum_4;
   @FXML JFXTextField cvcnum;
   @FXML JFXTextField donation_price;
   @FXML JFXButton card_confirm_btn;
   @FXML JFXButton card_cancel_btn;
   
   ArrayList<CardVO> lists;
   
	public void initialize(URL location, ResourceBundle resources) {

		// 확인버튼
		card_confirm_btn.setOnAction(e -> {
			card_donation();
		});

		// 취소버튼
		card_cancel_btn.setOnAction(e -> {
			DonationBoard_ChoiceController.choice_madal.close();
		});

		try {
			lists = (ArrayList<CardVO>) Main.db_u.getCardAll();
			for (int i = 0; i < lists.size(); i++) {
				card_choice.getItems().addAll(lists.get(i).getCard_name());
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		card_choice.setValue(card_choice.getItems().get(0)); // 카드기본값설정
	}

	
	
   //체크박스 전체체크
	public void ch_all() {
		if (check_all.isSelected()) {
			check_1.setSelected(true);
			check_2.setSelected(true);
		} else {
			check_1.setSelected(false);
			check_2.setSelected(false);
		}
	}

   //카드 후원
	private void card_donation() {
		if (!(cardnum_1.getText().isEmpty() || cardnum_2.getText().isEmpty() || cardnum_3.getText().isEmpty()
				|| cardnum_4.getText().isEmpty() || cvcnum.getText().isEmpty())) {

			if (check_1.isSelected() || check_2.isSelected()) {

				if (!donation_price.getText().isEmpty()) {

					Spon_LogVO sVO = new Spon_LogVO();

					sVO.setShel_id(DonationBoard_DetailController.vo.getShel_id());
					sVO.setCustom_id(Login_controller.log_c.getCustom_id()); // 회원아이디
					sVO.setPrice(donation_price.getText());
					sVO.setSpon_num(DonationBoard_DetailController.vo.getSpon_num());

					try {
						int cnt = Main.db_u.insertSpon_log(sVO);
						if (cnt > 0) {
							alertInfo("후원해 주셔서 감사합니다.");
							DonationBoard_DetailController dbdc = ChangePane.loader.getController();
							dbdc.ShowDetail();
							DonationBoard_ChoiceController.choice_madal.close();
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}

				} else {
					alertError("금액을 입력해주세요.");
				}
			} else {
				alertError("동의 항목을 모두 체크해주세요.");
			}
		} else {
			alertError("빈칸없이 입력해주세요.");
		}
	}
   
   public void alertInfo(String temp){
      Alert alertInfo = new Alert(AlertType.INFORMATION);
      alertInfo.setTitle("INFO");
      alertInfo.setContentText(temp);
      alertInfo.showAndWait();
   }
   
   public void alertError(String temp){
      Alert alertError = new Alert(AlertType.ERROR);
      alertError.setTitle("ERROR");
      alertError.setContentText(temp);
      alertError.showAndWait();
   }
}