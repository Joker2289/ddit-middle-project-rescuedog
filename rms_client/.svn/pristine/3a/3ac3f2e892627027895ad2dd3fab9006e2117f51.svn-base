package kr.or.ddit.rms.user.mypage.note;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.vo.NoteVO;

public class NoteMainDetailController implements Initializable{
	public static NoteVO vo; 
	public static Stage dialog;

	@FXML JFXButton layout_logout_btn; 
	@FXML JFXButton layout_mypage_btn;
	@FXML JFXButton layout_memCenter_btn;
	@FXML JFXButton layout_select_btn;
	@FXML Label layout_name_label;
	@FXML JFXButton layout_logo_btn;
	@FXML ImageView layout_logo_img;
	@FXML JFXButton layout_rd_btn;
	@FXML JFXButton layout_protec_btn;
	@FXML JFXButton layout_adopt_btn;
	@FXML JFXButton layout_shop_btn;
	@FXML JFXButton layout_spon_btn;
	@FXML JFXButton layout_vb_btn;
	@FXML JFXButton layout_comu_btn;
	@FXML JFXButton note_main_memModi_btn;
	@FXML JFXButton note_main_activeList_btn;
	@FXML JFXButton note_main_noteList_btn;
	@FXML JFXButton note_main_buyList_btn;
	@FXML Label note_detail_time;
	@FXML Label note_detail_from;
	@FXML JFXTextArea note_detail_txtarea;
	@FXML JFXButton note_detail_sendBtn;
	@FXML JFXButton note_detail_delBtn;
	@FXML JFXButton note_detail_listBtn;
	@FXML JFXTextField note_detail_title_txtfield;
	@FXML JFXTextField to_tf;

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("상세보기 로긴 ID" + vo.getNote_id_to());
		System.out.println("상세보기 내용" + vo.getNote_content());
		
		note_detail_from.setText(vo.getNote_id_from());
		note_detail_time.setText(vo.getNote_date());
		note_detail_title_txtfield.setText(vo.getNote_title());
		note_detail_txtarea.setText(vo.getNote_content());
		
		//답장하기
		note_detail_sendBtn.setOnAction(e -> resend());
		
	}


	
	private void resend() {
		dialog = new Stage();
		
		//새창 띄우기
		dialog = new Stage(StageStyle.UTILITY);
		
		dialog.initModality(Modality.APPLICATION_MODAL);
		//dialog.initOwner();
		dialog.setTitle("쪽지 보내기");
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("note_send.fxml"));
//			to_tf = (JFXTextField) loadPane.lookup("#note_send_to_txtfield");
//			to_tf.setPromptText(vo.getNote_id_from());
		} catch(IOException ee) {
			ee.printStackTrace();
		}
		System.out.println("main" + vo.getNote_id_to()); 
		Scene scene = new Scene(parent);
		
		dialog.setScene(scene);
		dialog.setResizable(false);//크기고정
		dialog.show();
		
	}

	//경고 메서드
	public void alertInfo(String temp){
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFO");
		alertInfo.setContentText(temp);
		alertInfo.showAndWait();
	}
}
