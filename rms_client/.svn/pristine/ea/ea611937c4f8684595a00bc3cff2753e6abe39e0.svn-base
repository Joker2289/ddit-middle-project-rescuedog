package kr.or.ddit.rms.user.donation_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationBoard_DetailController implements Initializable {

	public static Stage donation_view;
	public static Stage card;
	public static Stage point;

	public static SponVO vo;
	public static Stage donation_choice_modal;

	@FXML
	private ToggleGroup choice;

	@FXML
	AnchorPane servicePane;
	@FXML
	AnchorPane tempPane;
	@FXML
	Label donate_title_lb;
	@FXML
	JFXTextField donate_title_tf;
	@FXML
	JFXTextArea donate_content_ta;
	@FXML
	Label donate_shelId_lb;
	@FXML
	JFXButton donate_btn;
	@FXML
	JFXButton donate_list_btn;
	@FXML
	Label donate_sdate_lb;
	@FXML
	Label donate_goal_lb;
	@FXML
	Label donate_goal_lb1;
	@FXML
	Label donate_date_lb1;
	@FXML
	Label donate_edate_lb;
	@FXML
	Label rateLb;
	@FXML
	ProgressBar price_rate;
	@FXML
	JFXButton cancel_btn;
	@FXML
	ImageView Layout_logo_note_img;
	@FXML
	JFXButton report_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		donate_goal_lb1.setText(vo.getMoney()+"원");
		donate_title_tf.setEditable(false);
		donate_content_ta.setEditable(false);

		// 상세글 값 불러오기
		ShowDetail();

		// 후원하기버튼
		donate_btn.setOnAction(e -> {
			choice_modal();
		});

		donate_list_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("donation_list.fxml"));
				cp.add();
			}
		});

	}

	private void choice_modal() {
		donation_choice_modal = new Stage(StageStyle.UTILITY);

		donation_choice_modal.initModality(Modality.APPLICATION_MODAL);
		// dialog.initOwner();
		donation_choice_modal.setTitle("후원방법");

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("payment_choice.fxml"));
		} catch (IOException ee) {
			ee.printStackTrace();
		}

		Scene scene = new Scene(parent);

		donation_choice_modal.setScene(scene);
		donation_choice_modal.setResizable(false);// 크기고정
		donation_choice_modal.show();
	}

	protected void ShowDetail() {
		donate_title_tf.setText(vo.getTitle());
		donate_shelId_lb.setText(vo.getShel_id());
		donate_content_ta.setText(vo.getContent());
		donate_sdate_lb.setText(vo.getUpd_date().substring(0, 10));
		donate_edate_lb.setText(vo.getLast_date().substring(0, 10));

		// 달성률
		float rate = 0f;
		ArrayList<Spon_LogVO> slist = new ArrayList<>();
		try {
			Spon_LogVO svo = new Spon_LogVO();
			svo.setSpon_num(vo.getSpon_num());
			float sum = 0;
			slist = (ArrayList<Spon_LogVO>) Main.db_u.getSearchSpon_log(svo);
			for (int i = 0; i < slist.size(); i++) {
				sum += Float.parseFloat(slist.get(i).getPrice());
			}
			System.out.println(sum);
			donate_goal_lb.setText((int)sum + " 원");
			rate = sum / Float.parseFloat((vo.getMoney()));
			price_rate.setProgress(rate);
			rateLb.setText(Math.round(rate * 100) + " %");

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}