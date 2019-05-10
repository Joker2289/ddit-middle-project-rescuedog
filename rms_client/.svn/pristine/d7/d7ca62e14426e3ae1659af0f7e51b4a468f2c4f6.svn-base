package kr.or.ddit.rms.admin.service_center.notice;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.NoticeVO;

public class Notice_Admin_Upd_Controller implements Initializable {
	
	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	
	@FXML JFXTextField notice_title_txtF;
	@FXML JFXTextField notice_writer_txtF; 
	@FXML JFXTextArea notice_content_txtA;
	@FXML JFXButton cancel_btn;
	@FXML JFXButton add_btn;
	
	static NoticeVO vo=new NoticeVO();
	@FXML JFXButton notice_back_btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		notice_writer_txtF.setText(Login_controller.log_a.getAdmin_id());
		notice_title_txtF.setText(vo.getTitle());
		notice_content_txtA.setText(vo.getContent());

		
		cancel_btn.setOnAction(e->{
			Notice_Admin_Detail_Controller.WritePage.close();
		});
	}

	//글 수정하고 등록
	protected void updNotice() {
		NoticeVO newVO = new NoticeVO();

		newVO.setNotice_num(vo.getNotice_num());
		System.out.println(vo.getNotice_num());
		newVO.setTitle(notice_title_txtF.getText());
		newVO.setAdmin_id(notice_writer_txtF.getText());
		newVO.setContent(notice_content_txtA.getText());
		vo.setContent(notice_content_txtA.getText());

		try {
			int cnt = Main.n_a.updateNotice(newVO);
			if (cnt > 0) {
				alertInfo("글 수정이 완료되었습니다.");
				Notice_Admin_Detail_Controller.WritePage.close();
			} else {
				alertError("글 수정 권한이 없습니다.");
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