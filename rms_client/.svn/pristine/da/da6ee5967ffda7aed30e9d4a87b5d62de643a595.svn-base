package kr.or.ddit.rms.user.adopt_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import org.omg.CORBA.Request;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.Adopt_LogVO;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class Adopt_request_controller implements Initializable{

	@FXML AnchorPane detailPane;
	
	@FXML JFXButton Adopt_Request_Btn;
	@FXML JFXButton Adopt_Close_Btn;
	
	@FXML JFXTextField Request_Name;
	@FXML JFXTextField Request_Addr;
	@FXML JFXTextField Request_Email;
	
	@FXML JFXCheckBox Request_Y1;
	@FXML JFXCheckBox Request_N1;
	@FXML JFXCheckBox Request_N2;
	@FXML JFXCheckBox Request_Y2;
	
	@FXML JFXTextField Request_Age;
	@FXML JFXTextField Request_ID1;
	@FXML JFXPasswordField Request_ID2;
	@FXML JFXCheckBox Request_Agree;
	
	
	String img;
	String rd_num;
	String rd_name;
	String rd_kind;
	String rd_gender;
	String rd_finddate;
	String Shel_name;
	String rd_info;
	String rd_check;
	
	String info;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		img = Adopt_detail_viewController.adopt[0];
		rd_num = Adopt_detail_viewController.adopt[1];
		rd_name = Adopt_detail_viewController.adopt[2];
		rd_kind = Adopt_detail_viewController.adopt[3];
		rd_gender = Adopt_detail_viewController.adopt[4];
		rd_finddate = Adopt_detail_viewController.adopt[5];
		Shel_name = Adopt_detail_viewController.adopt[6];
		rd_info = Adopt_detail_viewController.adopt[7];
		rd_check = Adopt_detail_viewController.adopt[8];
		
		System.out.println(Login_controller.log_c.getCustom_name());
		Request_Name.setText(Login_controller.log_c.getCustom_name());
		Request_Addr.setText(Login_controller.log_c.getCustom_addr());
		Request_Email.setText(Login_controller.log_c.getCustom_email());
		
		
		
		Adopt_Close_Btn.setOnAction(e->{
			Adopt_viewController.adopt_view_dialog.close();
		});
		
		
		Adopt_Request_Btn.setOnAction(e->{
			request_adopt();
			Adopt_viewController.adopt_view_dialog.close();
			
		});
		
		
		Request_Y1.setOnAction(e->{
			if(Request_N1.isSelected() == true) {
				Request_N1.setSelected(false);
			}
		});
		
		Request_N1.setOnAction(e->{
			if(Request_Y1.isSelected() == true) {
				Request_Y1.setSelected(false);
			}
		});
		
		Request_Y2.setOnAction(e->{
			if(Request_N2.isSelected() == true) {
				Request_N2.setSelected(false);
			}
		});
		
		Request_N2.setOnAction(e->{
			if(Request_Y2.isSelected() == true) {
				Request_Y2.setSelected(false);
			}
		});
		
		
	}

	private void request_adopt() {
		
		if(Request_Age.getText().isEmpty()) {
			alertTest("나이를 입력해주세요");
			Request_Age.requestFocus();
			return;
		}else if(Request_ID1.getText().isEmpty() || Request_ID2.getText().isEmpty()) {
			alertTest("주민등록 번호를 입력해주세요");
			Request_ID1.requestFocus();
			return;
		}else if(!Request_Y1.isSelected() && !Request_N1.isSelected()) {
			alertTest("동거 애견 여부를 체크해주세요");
			Request_Y1.requestFocus();
			return;
		}else if(!Request_Y2.isSelected() && !Request_N2.isSelected()) {
			alertTest("가족 동의 여부를 체크해주세요");
			Request_Y2.requestFocus();
			return;
		}else if(!Request_Agree.isSelected()) {
			alertTest("동의 여부를 체크해주세요");
			Request_Agree.requestFocus();
			return;
		}
		
		String check1;
		String check2;
		if(Request_Y1.isSelected()) {
			check1 = "Y";
		} else {
			check1 = "N";
		}
		
		if(Request_Y2.isSelected()) {
			check2 = "Y";
		} else {
			check2 = "N";
		}
		
		
		info = rd_name + "/" + Request_Name.getText() + "/" + Request_Addr.getText() + "/" + Request_Age.getText()
			+ "/" + Request_ID1.getText()+Request_ID2.getText() + "/" + Request_Email.getText() 
			+ "/" + check1 + "/" + check2 + "/" + img;
		
		Adopt_LogVO avo = new Adopt_LogVO();
		avo.setCustom_id(Login_controller.log_c.getCustom_id());
		avo.setRd_num(rd_num);
		avo.setAdopt_check("승인 대기중");
		avo.setAdopt_req(info);
		
		//보호기관 아이디 셋팅
		String[] temp = Adopt_viewController.log_img.getAccessibleText().split("/");
		avo.setShel_id(temp[9]);
		
		try {
			int tmp = Main.ab_u.insertAdopt_log(avo);
			
			if(tmp == 1) {
				alertTest("입양 신청 완료");
				Adopt_viewController.adopt_view_dialog.close();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("입양 신청");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
