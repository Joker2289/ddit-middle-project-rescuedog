package kr.or.ddit.rms.admin.missing_animal_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MissingVO;

public class Missing_animal_DetailAController implements Initializable {

	@FXML
	AnchorPane loadPane;
	@FXML
	AnchorPane servicePane;
	@FXML
	AnchorPane tempPane;

	@FXML
	JFXButton missing_detail_del_btn;
	@FXML
	Label missing_detail_title_lb;
	@FXML
	Label Hide_Lbl;
	@FXML
	JFXButton missing_detail_udt_btn;
	@FXML
	JFXButton missing_detail_back_btn;
	@FXML
	ImageView missing_detail_img_view;
	@FXML
	JFXTextField missing_detail_title_tf;
	@FXML
	JFXTextField missing_detail_name_tf;
	@FXML
	JFXTextField missing_detail_gender_tf;
	@FXML
	JFXTextField missing_detail_kind_tf;
	@FXML
	JFXTextField missing_detail_loc_tf;
	@FXML
	JFXTextField missing_detail_content_tf;
	@FXML
	JFXTextField missing_detail_money_tf;
	@FXML
	JFXTextField missing_detail_tel_tf;
	@FXML
	JFXButton report;

	@FXML
	JFXButton missing_detail_note_btn;
	@FXML
	ImageView note_img;

	public static Stage WritePage;

	MissingVO vo;
	int boardNum;
	protected static String fileName;
	String[] realName = fileName.split(",");

	String[] slice;
	@FXML JFXButton Show_Map_Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Show_Map_Btn.setOnAction(e->modalMap());
		missing_detail_back_btn.setOnAction(e->back());
		
		missing_detail_del_btn.setOnAction(e->delMissing());
		System.out.println(Missing_animal_boardTbAController.log.getAccessibleText());
		String temp = Missing_animal_boardTbAController.log.getAccessibleText();

		missing_detail_loc_tf.setEditable(false);
		missing_detail_title_tf.setEditable(false);
		missing_detail_name_tf.setEditable(false);
		missing_detail_gender_tf.setEditable(false);
		missing_detail_kind_tf.setEditable(false);
		missing_detail_content_tf.setEditable(false);
		missing_detail_money_tf.setEditable(false);
		missing_detail_tel_tf.setEditable(false);

		missing_detail_udt_btn.setVisible(false);

		report.setOnMouseClicked(e -> modalReport());

		// 상세보기에 값 보여주기
		slice = temp.split("/");
		CustomVO svo = new CustomVO();
		svo.setCustom_id(slice[1]);
		try {
			CustomVO telVO = Main.fis.getSearchCustom(svo);
			String tel = telVO.getCustom_tel();
			missing_detail_loc_tf.setText(slice[2]);
			missing_detail_title_tf.setText(slice[3]);
			Image missing_img = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\" + slice[4]); // 붙여넣기 할 때
			// 인식
			missing_detail_img_view.setImage(missing_img);
			missing_detail_name_tf.setText(slice[5]);
			missing_detail_gender_tf.setText(slice[6]);
			missing_detail_kind_tf.setText(slice[7]);
			missing_detail_content_tf.setText(slice[8]);
			missing_detail_money_tf.setText(slice[9]);
			missing_detail_tel_tf.setText(tel);

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		// 버튼 활성화
		if (Login_controller.log_a.getAdmin_id().equals(slice[1])) {
			missing_detail_del_btn.setVisible(true);
		}

		// 조회수 증가
		cntUp();

		missing_detail_del_btn.setOnAction(e -> delMissing());

		// 쪽지보내기
		Image note = new Image("kr/or/ddit/rms/admin/missing_animal_board/note.png");
		note_img.setImage(note);
		note_img.addEventHandler(MouseEvent.MOUSE_CLICKED, (e2) -> {
			WritePage = new Stage(StageStyle.UTILITY);
			WritePage.initModality(Modality.APPLICATION_MODAL);

			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("note_send_view.fxml"));
			} catch (IOException e3) {
				e3.printStackTrace();
			}

			Scene scene = new Scene(parent);
			WritePage.setTitle("쪽지 보내기");
			WritePage.setScene(scene);
			WritePage.show();

		});

	}
	
	private void modalMap() {		
		vo =new MissingVO();
		vo.setBoard_num(slice[0]);
		List<MissingVO> list = null;
		try {
			
			list =Main.ma_u.getSearchmissing(vo);
			System.out.println(list.size());
			System.out.println(list.size());
			if(list.get(0).getMiss_loc_img()==null) {
				alertError("지도 사진이 없습니다.");
				return;
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		ShowMapAController.miss_loc_img=list.get(0).getMiss_loc_img();
		WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("show_map.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		WritePage.setTitle("지도 보기");
		WritePage.setScene(scene);
		WritePage.show();
	}

	private void cntUp() {
		MissingVO vo = new MissingVO();
		MissingVO tvo = new MissingVO();
		vo.setBoard_num(slice[0]);
		try {
			ArrayList<MissingVO> tlist = (ArrayList<MissingVO>) Main.ma_m.getSearchmissing(vo);
			String com_idx = tlist.get(0).getCom_idx();
			com_idx = (Integer.parseInt(com_idx) + 1) + "";

			tvo.setBoard_num(slice[0]);
			tvo.setCom_idx(com_idx);
			tvo.setTitle(missing_detail_title_tf.getText());
			tvo.setContent(missing_detail_name_tf.getText() + "/" + missing_detail_gender_tf.getText() + "/"
					+ missing_detail_kind_tf.getText() + "/" + missing_detail_content_tf.getText() + "/"
					+ missing_detail_money_tf.getText());
			tvo.setMiss_loc(missing_detail_loc_tf.getText());
			tvo.setMem_id(tlist.get(0).getMem_id());
			tvo.setImg(realName[2]);
			tvo.setMiss_loc_img(tlist.get(0).getMiss_loc_img());
			Main.ma_m.updatemissing_free(tvo);

			// System.out.println(com_idx);

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

	}// 조회수 증가 end
	
	private void back() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("missingTable_A.fxml"));
		cp.add();
	}

	private void delMissing() {
		MissingVO vo = new MissingVO();
		vo.setBoard_num(slice[0]);
		// vo.setMem_id(login_controller.log_c.getCustom_id());
		vo.setMem_id(slice[1]);

		try {
			int cnt = Main.ma_m.deletemissing(vo);
			if (cnt > 0) {
				alertInfo("삭제완료");
				back();
			} else {
				alertError("삭제실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void modalReport() {
		Stage WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("reportPage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFXButton cancel = (JFXButton) parent.lookup("#report_cancel_btn");
		JFXButton ok = (JFXButton) parent.lookup("#report_ok_btn");
		JFXTextField idFrom = (JFXTextField) parent.lookup("#report_IdFrom_tf");
		JFXTextField idTo = (JFXTextField) parent.lookup("#report_IdTo_tf");
		JFXTextArea content = (JFXTextArea) parent.lookup("#report_reason_ta");
		idTo.setText(this.slice[1]);
		idFrom.setText(Login_controller.log_a.getAdmin_id());
		idTo.setDisable(true);
		idFrom.setDisable(true);

		Scene scene = new Scene(parent);
		WritePage.setTitle("실종동물");
		WritePage.setScene(scene);
		WritePage.show();
		ok.setOnAction(e -> {
			boolean check = alertConfirm("신고하시겠습니까? (허위 신고시 제제)");
			if (check) {
				try {
					BlacklistVO bvo = new BlacklistVO();
					bvo.setMem_id(this.slice[1]);
					bvo.setReason(content.getText());
					Main.bs.insertBlacklist(bvo);
					alertInfo("신고 완료");
				} catch (RemoteException e1) {
					alertError("DB 에러");
				}
			} else {
				alertInfo("취소하셨습니다.");
			}
			WritePage.close();
		});
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WritePage.close();
			}
		});
	}

	public boolean alertConfirm(String ment) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

		alertConfirm.setTitle("확인 창");
		alertConfirm.setContentText(ment);

		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();

		if (confirmResult == ButtonType.OK) {
			return true;
		} else if (confirmResult == ButtonType.CANCEL) {
			return false;
		}
		return false;
	}

	public void alertInfo(String temp) {
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFO");
		alertInfo.setContentText(temp);
		alertInfo.showAndWait();
	}

	public void alertError(String temp) {
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("ERROR");
		alertError.setContentText(temp);
		alertError.showAndWait();
	}

}
