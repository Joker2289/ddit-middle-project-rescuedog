package kr.or.ddit.rms.shelter.adopt_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class S_adopt_view_controller implements Initializable {

	@FXML
	AnchorPane Adopt_Main_Pane;
	@FXML
	JFXComboBox Search_Comb;
	@FXML
	JFXTextField Search_Txt;
	@FXML
	JFXButton Search_Btn;
	@FXML
	Label Hide_Lbl;

	VBox mainBox;

	public static Stage adopt_view_dialog;
	public static ImageView log_img = null;
	public static int list_size = 0;
	public static List<Buy_LogVO> buyList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainBox = new VBox();
		mainBox.setPrefWidth(978.0);

		List<RescuedogVO> rdlist;
		try {
			rdlist = Main.ab_u.getRescuedogAll();
			show_rdList(rdlist);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		String[] search_item = { "유기견 이름", "보호기관명", "입양상태", "견종" };

		for (int i = 0; i < search_item.length; i++) {
			Search_Comb.getItems().add(search_item[i]);
		}

		// 검색버튼 클릭
		Search_Btn.setOnAction(e -> {
			if (Search_Comb.getSelectionModel().isEmpty()) {
				alertTest("검색조건을 설정해 주세요");
				return;
			}

			switch (Search_Comb.getValue().toString()) {
			case "유기견 이름":
				RescuedogVO rd_name = new RescuedogVO();
				rd_name.setRd_name(Search_Txt.getText());
				try {
					List<RescuedogVO> list = Main.ab_u.getSearchRescuedog(rd_name);
					show_rdList(list);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				break;

			case "보호기관명":
				ShelterVO svo = new ShelterVO();
				svo.setShel_name(Search_Txt.getText());
				try {
					List<ShelterVO> shelList = Main.ab_u.getSearchShelter(svo);
					if (shelList.size() != 0) {
						String shel_id = shelList.get(0).getShel_id();
						RescuedogVO shel_name = new RescuedogVO();
						shel_name.setShel_id(shel_id);
						List<RescuedogVO> list = Main.ab_u.getSearchRescuedog(shel_name);
						show_rdList(list);
					}

					if (shelList.size() == 0) {
						Adopt_Main_Pane.getChildren().clear();
						Hide_Lbl.setText("검색된 목록이 없습니다");
						VBox v = new VBox();
						v.getChildren().add(Hide_Lbl);
						v.setPrefWidth(978);
						v.setPrefHeight(400);
						v.setAlignment(Pos.CENTER);
						Adopt_Main_Pane.getChildren().add(v);
						return;
					}

				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
				break;

			case "입양상태":
				RescuedogVO state = new RescuedogVO();
				state.setRd_check(Search_Txt.getText());
				try {
					List<RescuedogVO> list = Main.ab_u.getSearchRescuedog(state);
					show_rdList(list);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				break;

			case "견종":
				RescuedogVO rd_kind = new RescuedogVO();
				rd_kind.setRd_kind(Search_Txt.getText());
				try {
					List<RescuedogVO> list = Main.ab_u.getSearchRescuedog(rd_kind);
					show_rdList(list);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				break;
			default:
				break;
			}

		});
	}

	private void show_rdList(List<RescuedogVO> list) {
		mainBox.getChildren().clear();

		try {
			if (list.size() == 0) {
				Adopt_Main_Pane.getChildren().clear();
				Hide_Lbl.setText("검색된 목록이 없습니다");
				VBox v = new VBox();
				v.getChildren().add(Hide_Lbl);
				v.setPrefWidth(978);
				v.setPrefHeight(400);
				v.setAlignment(Pos.CENTER);
				Adopt_Main_Pane.getChildren().add(v);
				return;
			}

			for (int i = 0; i < list.size(); i++) {
				ShelterVO svo = new ShelterVO();
				svo.setShel_id(list.get(i).getShel_id());
				List<ShelterVO> slist = Main.ab_s.getSearchShelter(svo);

				String rd_num = list.get(i).getRd_num(); // 유기견번호
				String rd_img = list.get(i).getRd_img(); // 사진
				String rd_name = list.get(i).getRd_name(); // 유기견 이름
				String rd_kind = list.get(i).getRd_kind(); // 유기견 종류
				String rd_gender = list.get(i).getRd_gender(); // 유기견 성별
				String shel_name = slist.get(0).getShel_name(); // 보호기관 이름
				String find_date = list.get(i).getRd_finddate().substring(0, 10); // 등록날짜
				String rd_check = list.get(i).getRd_check(); // 입양상태
				String shel_loc = slist.get(0).getShel_loc(); // 보호기관 위치
				String rd_info = list.get(i).getRd_info(); // 유기견 정보

				HBox H = new HBox();
				H.setPrefWidth(978.0);
				H.setPrefHeight(150.0);
				H.setAccessibleText(i + "");

				// 유기견 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
				Label rdNum_lbl = new Label();
				rdNum_lbl.setText(rd_num);
				rdNum_lbl.setPrefWidth(61.0);
				rdNum_lbl.setPrefHeight(150.0);
				rdNum_lbl.setAlignment(Pos.CENTER);
				rdNum_lbl.setFont(Font.font(".System", 20));
				H.getChildren().add(rdNum_lbl);

				// 유기견 이미지
				Image rd_image = null;
				if (rd_img != null) {
					rd_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\" + rd_img);
					// rd_image = new Image("kr/or/ddit/rms/shelter/adopt_board/rdImg/"+rd_img);
				}
				ImageView view = new ImageView();
				view.setImage(rd_image);
				view.setFitWidth(150.0);
				view.setFitHeight(150.0);
				view.setAccessibleText(rd_img + "/" + rd_num + "/" + rd_name + "/" + rd_kind + "/" + rd_gender + "/"
						+ find_date + "/" + shel_name + "/" + rd_info + "/" + rd_check);
				H.getChildren().add(view);

				// 유기견 이름
				Label rd_name_lbl = new Label();
				rd_name_lbl.setText("     이름 : " + rd_name);
				rd_name_lbl.setPrefWidth(250.0);
				rd_name_lbl.setPrefHeight(50.0);
				rd_name_lbl.setFont(Font.font(".System", 15));

				// 유기견 종
				Label rd_kind_lbl = new Label();
				rd_kind_lbl.setText("     견종 : " + rd_kind);
				rd_kind_lbl.setPrefWidth(250.0);
				rd_kind_lbl.setPrefHeight(50.0);
				rd_kind_lbl.setFont(Font.font(".System", 15));

				// 유기견 성별
				Label rd_gender_lbl = new Label();
				rd_gender_lbl.setText("     성별 : " + rd_gender);
				rd_gender_lbl.setPrefWidth(250.0);
				rd_gender_lbl.setPrefHeight(50.0);
				rd_gender_lbl.setFont(Font.font(".System", 15));

				// 유기견 정보
				VBox v = new VBox();
				v.setPrefWidth(250.0);
				v.setPrefHeight(150.0);
				v.setAlignment(Pos.CENTER);
				v.getChildren().add(rd_name_lbl);
				v.getChildren().add(rd_kind_lbl);
				v.getChildren().add(rd_gender_lbl);
				H.getChildren().add(v);

				// 보호소이름 라벨
				Label shel_name_lbl = new Label();
				shel_name_lbl.setText(shel_name);
				shel_name_lbl.setPrefWidth(180.0);
				shel_name_lbl.setPrefHeight(150.0);
				shel_name_lbl.setAlignment(Pos.CENTER);
				shel_name_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(shel_name_lbl);

				// 등록날짜 라벨
				Label find_date_lbl = new Label();
				find_date_lbl.setText(find_date);
				find_date_lbl.setPrefWidth(189.0);
				find_date_lbl.setPrefHeight(150.0);
				find_date_lbl.setAlignment(Pos.CENTER);
				find_date_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(find_date_lbl);

				// 입양상태 라벨
				Label rd_check_lbl = new Label();
				rd_check_lbl.setText(rd_check);
				rd_check_lbl.setPrefWidth(138.0);
				rd_check_lbl.setPrefHeight(150.0);
				rd_check_lbl.setAlignment(Pos.CENTER);
				rd_check_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(rd_check_lbl);

				// 라인넣기
				HBox line = new HBox();
				line.setPrefWidth(978.0);
				line.setPrefHeight(1.0);
				line.setStyle("-fx-background-color: #c2c2c2;");

				mainBox.getChildren().add(H);
				mainBox.getChildren().add(line);

				view.setOnMouseClicked(e -> {
					log_img = (ImageView) e.getSource();
					System.out.println(log_img.getAccessibleText());
					// 새창 띄우기
					adopt_view_dialog = new Stage(StageStyle.UTILITY);

					adopt_view_dialog.initModality(Modality.APPLICATION_MODAL);
					// dialog.initOwner();
					adopt_view_dialog.setTitle("유기견 상세조회");

					Parent parent = null;
					try {
						log_img = (ImageView) e.getSource();

						parent = FXMLLoader.load(getClass().getResource("adopt_detail_view.fxml"));
					} catch (IOException ee) {
						ee.printStackTrace();
					}

					Scene scene = new Scene(parent);

					adopt_view_dialog.setScene(scene);
					adopt_view_dialog.setResizable(false);// 크기고정
					adopt_view_dialog.show();
				});
			}
			if (!Adopt_Main_Pane.getChildren().isEmpty()) {
				Adopt_Main_Pane.getChildren().clear();
			}

			Adopt_Main_Pane.getChildren().add(mainBox);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("검색");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
