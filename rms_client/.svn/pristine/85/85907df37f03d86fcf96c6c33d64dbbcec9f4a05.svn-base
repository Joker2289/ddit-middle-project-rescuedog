package kr.or.ddit.rms.admin.missing_animal_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.user.missing_animal_board.Missing_animal_DetailController;
import kr.or.ddit.rms.user.missing_animal_board.Missing_animal_boardTbController;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.NoteVO;

public class Note_send_Controller implements Initializable {
	public static boolean resend;
	public static int cnt;
	@FXML JFXButton close_btn;
	@FXML JFXButton close_btn1;
	@FXML JFXButton send_btn;
	@FXML JFXTextField idTo_tf;
	@FXML JFXTextField title_tf;
	@FXML JFXTextArea content_ta;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String temp = Missing_animal_boardTbAController.log.getAccessibleText();
		System.out.println(temp);
		String[] tlist = temp.split("/");
		System.out.println(tlist[1]);
		idTo_tf.setText(tlist[1]);
		
		close_btn.setOnAction(e->{
			gotoNoteMain();		
		});
		
		close_btn1.setOnAction(e->{
			gotoNoteMain();
		});
		
		send_btn.setOnAction(e->{
			gotoSend();
		});
	}
	
	private void gotoSend() {
		if(title_tf.getText().isEmpty()) {
			alertError("제목을 입력하세요");
			
		}else if(content_ta.getText().isEmpty()) {
			alertError("내용을 입력하세요");
		}else {
			MemberVO mvo = new MemberVO();
			mvo.setMem_id(idTo_tf.getText());
			List<MemberVO> list = null;
			try {
				list = Main.mems.getSearchMember(mvo);
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(!list.isEmpty()) {
				NoteVO vo = new NoteVO();
				vo.setNote_id_from(Login_controller.log.getMem_id());
				vo.setNote_id_to(idTo_tf.getText());
				vo.setNote_title(title_tf.getText());
				vo.setNote_content(content_ta.getText());
				try {
					Main.ns.insertNote(vo);
					alertInfo("쪽지가 보내졌습니다.");
					gotoNoteMain();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			else {
				alertError("없는 아이디입니다.");
			}
		}
			
	}
	
	private void gotoNoteMain() {
		Missing_animal_DetailAController.WritePage.close();		
	}
	
	public boolean alertConfirm(String ment) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("확인 창");
		alertConfirm.setContentText(ment);
		
		//Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult==ButtonType.OK) {
			return true;
		}else if(confirmResult==ButtonType.CANCEL) {
			return false;
		}
		return false;
	}
	
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("INFO");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	
	public void alertError(String temp){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
}
