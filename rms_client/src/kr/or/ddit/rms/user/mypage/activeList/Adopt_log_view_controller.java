package kr.or.ddit.rms.user.mypage.activeList;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Adopt_log_view_controller implements Initializable {

	@FXML
	AnchorPane Log_View_Pane;
	@FXML
	Label Hide_Label;
	@FXML
	JFXButton mypage_back_btn;

	VBox mainBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mypage_back_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("activeList_main.fxml"));
				cp.add();
			}
		});
		mainBox = new VBox();
		mainBox.setPrefWidth(954.0);

		show_adopt_log();
	}

	private void show_adopt_log() {
		mainBox.getChildren().clear();

		try {
			Adopt_LogVO avo = new Adopt_LogVO();
			String custom_id = Login_controller.log_c.getCustom_id();
			avo.setCustom_id(custom_id);
			List<Adopt_LogVO> log_list = Main.al.getSearchAdopt_log(avo);

			for (int i = 0; i < log_list.size(); i++) {

				// 유기견 정보 가져오기
				RescuedogVO rdvo = new RescuedogVO();
				rdvo.setRd_num(log_list.get(i).getRd_num());
				List<RescuedogVO> rd_list = Main.al.getSearchRescuedog(rdvo);

				// 쉘터 정보 가져오기
				ShelterVO svo = new ShelterVO();
				svo.setShel_id(log_list.get(i).getShel_id());
				List<ShelterVO> shel_list = Main.al.getSearchShelter(svo);

				String rd_num = log_list.get(i).getRd_num();
				String rd_img = rd_list.get(0).getRd_img();
				String rd_name = rd_list.get(0).getRd_name();
				String rd_kind = rd_list.get(0).getRd_kind();
				String rd_gender = rd_list.get(0).getRd_gender();
				String shel_name = shel_list.get(0).getShel_name();
				String req_date = log_list.get(i).getAdopt_date();
				String req_check = log_list.get(i).getAdopt_check();

				HBox H = new HBox();
				H.setPrefWidth(954.0);
				H.setPrefHeight(150.0);

				if (i % 2 == 0) {
					H.setStyle("-fx-background-color: #E9E9E9");
				}

				// 유기견 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
				Label rdNum_lbl = new Label();
				rdNum_lbl.setText(rd_num);
				rdNum_lbl.setPrefWidth(52.0);
				rdNum_lbl.setPrefHeight(150.0);
				rdNum_lbl.setAlignment(Pos.CENTER);
				rdNum_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));
				H.getChildren().add(rdNum_lbl);

				// 유기견 이미지
				Image rd_image = null;
				if (rd_img != null) {
					rd_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\" + rd_img);
				}
				ImageView view = new ImageView();
				view.setImage(rd_image);
				view.setFitWidth(150.0);
				view.setFitHeight(150.0);
				// view.setAccessibleText(rd_img+"/"+rd_num+"/"+rd_name+"/"+
				// rd_kind+"/"+rd_gender+"/"+
				// find_date+"/"+ shel_name+"/"+
				// rd_info+"/"+rd_check +"/"+ rdlist.get(i).getShel_id());
				H.getChildren().add(view);

				// 유기견 이름
				Label rd_name_lbl = new Label();
				rd_name_lbl.setText("     이름 : " + rd_name);
				rd_name_lbl.setPrefWidth(238.0);
				rd_name_lbl.setPrefHeight(50.0);
				rd_name_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));

				// 유기견 종
				Label rd_kind_lbl = new Label();
				rd_kind_lbl.setText("     견종 : " + rd_kind);
				rd_kind_lbl.setPrefWidth(238.0);
				rd_kind_lbl.setPrefHeight(50.0);
				rd_kind_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));

				// 유기견 성별
				Label rd_gender_lbl = new Label();
				rd_gender_lbl.setText("     성별 : " + rd_gender);
				rd_gender_lbl.setPrefWidth(238.0);
				rd_gender_lbl.setPrefHeight(50.0);
				rd_gender_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));

				// 유기견 정보
				VBox v = new VBox();
				v.setPrefWidth(240.0);
				v.setPrefHeight(150.0);
				v.setAlignment(Pos.CENTER);
				v.getChildren().add(rd_name_lbl);
				v.getChildren().add(rd_kind_lbl);
				v.getChildren().add(rd_gender_lbl);
				H.getChildren().add(v);

				// 보호소이름 라벨
				Label shel_name_lbl = new Label();
				shel_name_lbl.setText(shel_name);
				shel_name_lbl.setPrefWidth(156.0);
				shel_name_lbl.setPrefHeight(150.0);
				shel_name_lbl.setAlignment(Pos.CENTER);
				shel_name_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));
				H.getChildren().add(shel_name_lbl);

				// 신청 날짜
				Label find_date_lbl = new Label();
				find_date_lbl.setText(req_date.substring(0, 10));
				find_date_lbl.setPrefWidth(161.0);
				find_date_lbl.setPrefHeight(150.0);
				find_date_lbl.setAlignment(Pos.CENTER);
				find_date_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));
				H.getChildren().add(find_date_lbl);

				// 신청 날짜
				Label req_check_lbl = new Label();
				req_check_lbl.setText(req_check);
				req_check_lbl.setPrefWidth(121.0);
				req_check_lbl.setPrefHeight(150.0);
				req_check_lbl.setAlignment(Pos.CENTER);
				req_check_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 13));
				H.getChildren().add(req_check_lbl);

				FontAwesomeIcon icon = new FontAwesomeIcon();
				icon.setIconName("CLOSE");
				icon.setSize("2em");

				if (log_list.get(i).getAdopt_check().equals("승인 대기중")) {
					// 버튼 추가
					JFXButton Btn = new JFXButton();
					Btn.setPrefWidth(68.0);
					Btn.setPrefHeight(150.0);
					Btn.setGraphic(icon);
					Btn.setAlignment(Pos.CENTER);
					H.getChildren().add(Btn);

					Btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

						Adopt_LogVO avo2 = new Adopt_LogVO();
						avo2.setCustom_id(Login_controller.log_c.getCustom_id());
						avo2.setRd_num(rd_num);
						try {
							Main.al.deleteAdopt_logThis(avo2);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						alertTest(rd_name + " 의 입양 신청 취소");

						show_adopt_log();
					});
				}

				// H.addEventHandler(MouseEvent.MOUSE_ENTERED, g->{
				// H.setStyle(H.getStyle().replace("background-color:transparent;",
				// "background-color:#ddd3d3;"));
				// });
				//
				// H.addEventHandler(MouseEvent.MOUSE_EXITED, g->{
				// H.setStyle(H.getStyle().replace("background-color:#ddd3d3;",
				// "background-color:transparent;"));
				// });
				// 라인넣기
				HBox Hline = new HBox();
				Hline.setPrefWidth(954.0);
				Hline.setPrefHeight(4.0);
				Hline.setStyle("-fx-background-color: #dadada;");

				mainBox.getChildren().add(H);
				mainBox.getChildren().add(Hline);
			}

			if (!Log_View_Pane.getChildren().isEmpty()) {
				Log_View_Pane.getChildren().clear();
			}
			Log_View_Pane.getChildren().add(mainBox);

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("INFO");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
