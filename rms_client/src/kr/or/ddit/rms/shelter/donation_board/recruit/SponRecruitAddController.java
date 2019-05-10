package kr.or.ddit.rms.shelter.donation_board.recruit;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.SponVO;

public class SponRecruitAddController implements Initializable{

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	@FXML HTMLEditor dona_content_ta;
	@FXML JFXTextField dona_price_tf;
	@FXML JFXTextField dona_title_tf;
	@FXML JFXButton dona_add_btn;
	@FXML JFXButton dona_cancel_btn;
	@FXML DatePicker dona_start_date;
	@FXML DatePicker dona_end_date;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dona_add_btn.setOnAction(e->addIncruit());
		dona_cancel_btn.setOnAction(e->back());
	}
	
	private void back() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("recruit_list.fxml"));
		cp.add();
	}

	protected void addIncruit() {
		
		if(dona_title_tf.getText().isEmpty() == true) {
			alertError("제목을 입력해주세요");
		}else if(dona_price_tf.getText().isEmpty() == true) {
			alertError("목표금액을 입력해 주세요");
		}else if(dona_start_date.getValue() == null) {
			alertError("시작 날짜를 입력해 주세요");
		}else if(dona_end_date.getValue() == null) {
			alertError("종료날짜를 입력해 주세요");
		}else if(dona_content_ta.getHtmlText().isEmpty() == true) {
			alertError("내용을 입력해 주세요");
		}
		
		SponVO vo = new SponVO();
//		LocalDate sval = dona_start_date.getValue();
//		LocalDate eval = dona_end_date.getValue();
//		
//		String sdate = sval.toString();
//		String edate = eval.toString();
		
		vo.setShel_id(Login_controller.log_s.getShel_id());
		vo.setUpd_date(dona_start_date.getValue().toString());
		vo.setLast_date(dona_end_date.getValue().toString());
		vo.setMoney(dona_price_tf.getText());
		vo.setTitle(dona_title_tf.getText());
		vo.setContent(dona_content_ta.getHtmlText().replaceAll("<[^>]*>",""));
		
		try {
			int cnt = Main.sr_s.insertSpon(vo);
			if(cnt > 0) {
				alertInfo("등록성공");
				back();
			}else {
				alertError("등록실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
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
