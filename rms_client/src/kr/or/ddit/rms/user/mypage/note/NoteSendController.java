package kr.or.ddit.rms.user.mypage.note;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.vo.NoteVO;

public class NoteSendController implements Initializable{
	NoteVO vo = NoteController.vo;
	
	@FXML JFXTextField note_send_to_txtfield; 
	@FXML JFXTextField note_send_title_txtField; 
	@FXML JFXTextArea note_send_textarea; 
	@FXML JFXButton note_send_sendBtn; 
	@FXML JFXButton note_send_cancleBtn; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("send" + NoteController.vo.getNote_id_to());
		
		//힌트 
		note_send_to_txtfield.setPromptText("받는사람 ID");
		note_send_title_txtField.setPromptText("제목");
		note_send_textarea.setPromptText("500자 이내로 입력");
		
		//보내기 버튼 이벤트
		note_send_sendBtn.setOnAction(e -> sendCheck());
		//취소 버튼 이벤트
		note_send_cancleBtn.setOnAction(e -> {
			NoteController.dialog.close();
		});
	}

	//보내기 버튼 메서드
	private void sendCheck() {
		System.out.println("쪽지보내기 버튼");
		System.out.println(vo.getNote_id_to());
		try {
			if (note_send_to_txtfield.getText().isEmpty()) { // 받는 사람이 비어있을때
				alertError("받는 사람을 입력하세요.");
				note_send_to_txtfield.requestFocus();
				return;
			} else if (note_send_title_txtField.getText().isEmpty()) { // 제목이 비어있을때
				alertError("제목을 입력하세요.");
				note_send_title_txtField.requestFocus();
			} else if (note_send_textarea.getText().isEmpty()) { // 내용이 비어있을때
				alertError("내용을 입력하세요.");
				note_send_textarea.requestFocus();
			} else {
				NoteVO vo1 = new NoteVO();
				vo1.setNote_cnt("N");
				vo1.setNote_date(null);
				vo1.setNote_id_from(vo.getNote_id_to()); // 받는 사람에 보내는 사람(로그인한) id 넣기
				vo1.setNote_id_to(note_send_to_txtfield.getText());
				vo1.setNote_title(note_send_title_txtField.getText());
				vo1.setNote_content(note_send_textarea.getText());

				int cnt = Main.ns.insertNote(vo1);
				
				if(cnt > 0) {
					alertInfo("쪽지 보내기 완료");
					NoteController.dialog.close();
				}else {
					alertError("쪽지 보내기 실패");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		alertErorr.setTitle("INFO");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

}
