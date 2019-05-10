package kr.or.ddit.rms.admin.service_center.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.NoticeVO;

public class Notice_Admin_Detail_Controller implements Initializable {

	public static NoticeVO vo;

	@FXML
	AnchorPane servicePane;
	@FXML
	AnchorPane tempPane;
	@FXML
	AnchorPane loadPane;

	@FXML
	JFXTextField notice_newtitle_txtF;
	@FXML
	JFXTextField notice_id_txtF;
	@FXML
	Label notice_num;
	@FXML
	Label notice_add_date_label;
	@FXML
	JFXTextArea notice_content_txtA;
	@FXML
	JFXButton notice_gotolist_btn;
	@FXML
	JFXButton notice_del_btn;
	@FXML
	JFXButton notice_upd_btn;
	@FXML
	Button back;
	public static Stage WritePage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 관리자는 수정이 가능하지만 수정버튼 눌렀을때만 수정가능
		notice_newtitle_txtF.setEditable(false);
		notice_id_txtF.setEditable(false);
		notice_content_txtA.setEditable(false);

		// 값 불러오기
		notice_newtitle_txtF.setText(vo.getTitle()); // 글제목
		notice_num.setText(vo.getNotice_num()); // 글번호
		notice_add_date_label.setText(vo.getWrite_date()); // 글작성일
		notice_id_txtF.setText(vo.getAdmin_id()); // 글작성한관리자ID
		notice_content_txtA.setText(vo.getContent()); // 글내용

		// 수정
		notice_upd_btn.setOnAction(e -> {
			NoticeUpd();
		});

		// 삭제
		notice_del_btn.setOnAction(e -> {
			NoticeDel();
		});

		// 목록으로 돌아가기
		notice_gotolist_btn.setOnAction(e -> {
			gotoNoticelist();
		});
	}

	// 목록으로 돌아가기
	private void gotoNoticelist() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("notice_table.fxml"));
		cp.add();
	}

	// 수정
	private void NoticeUpd() {
		Notice_Admin_Upd_Controller.vo=vo;
		WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);
		Parent parent = null;
		FXMLLoader load = null;
		try {
			load = new FXMLLoader(getClass().getResource("notice_upd.fxml"));
			parent = load.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Notice_Admin_Upd_Controller nauc = load.getController();
		JFXButton add_btn = (JFXButton) parent.lookup("#add_btn");
		add_btn.setOnAction(e -> {
			nauc.updNotice();
			notice_content_txtA.setText(Notice_Admin_Upd_Controller.vo.getContent());
		});
		Scene scene = new Scene(parent);
		WritePage.setTitle("게시판");
		WritePage.setScene(scene);
		WritePage.show();

	}

	// 삭제
	private void NoticeDel() {
		// Notice_Admin_Controller nac = new Notice_Admin_Controller();
		if (vo.getAdmin_id().equals(notice_id_txtF.getText())) {
			try {
				Main.n_a.deleteNotice(vo);
				alertInfo("글이 삭제되었습니다.");
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("notice_table.fxml"));
				cp.add();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			alertError("삭제 권한이 없습니다.");
		}
	}

	public void alertError(String temp) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

	public void alertInfo(String temp) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

}
