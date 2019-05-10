package kr.or.ddit.rms.user.adopt_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.member.note.Note_viewController;
import kr.or.ddit.rms.vo.Adopt_LogVO;

public class Adopt_detail_viewController implements Initializable{
	
	@FXML AnchorPane detailPane;	//메인판
	
	@FXML FontAwesomeIcon Icon_M;	//수컷 아이콘
	@FXML FontAwesomeIcon Icon_F;	//암컷 아이콘
	
	@FXML Label RD_Name;			//이름 라벨
	@FXML Label RD_Kind;			//견종 라벨
	@FXML Label RD_Num;				//등록번호 라벨
	@FXML Label RD_Date;			//등록 날짜
	@FXML Label RD_Shel;			//보호 기
	
	@FXML JFXTextArea RD_Info;		//특이사항
	
	@FXML Label Hide_Lbl;			//숨김라벨
	@FXML ImageView RD_Img;			//유기견 사진

	@FXML JFXButton Adopt_Request_Btn;	//입양 신청 버튼
	
	@FXML JFXButton Goto_Adopt;			//목록 버튼
	
	public static String[] adopt;
	public static String rd_num;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adopt = Adopt_viewController.log_img.getAccessibleText().split("/");
		rd_num = adopt[1];
		
		Adopt_Request_Btn.setOnAction(e->{
			ChangeScene.ChangeView(getClass(), "adopt_request.fxml", true, Adopt_viewController.adopt_view_dialog);
		});
		
		
		
		show_info();
		
		
		Goto_Adopt.setOnAction(e->{
			Adopt_viewController.adopt_view_dialog.close();
		});
	}
	
	private void show_info() {
		
		//이미지 세팅
//		Image rd_image = new Image("image/resquedog/"+adopt[0]);
		Image rd_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\"+adopt[0]);
		RD_Img.setImage(rd_image);
		
		RD_Name.setText(adopt[2]);
		
		RD_Kind.setText(adopt[3]);
		
		RD_Num.setText(adopt[1]);
		
		RD_Date.setText(adopt[5]);
		
		RD_Shel.setText(adopt[6]);
		
		RD_Info.setText(adopt[7]);
		
		if(adopt[4].equals("F")){
			Icon_F.setVisible(true);
			Icon_M.setVisible(false);
		}else {
			Icon_F.setVisible(false);
			Icon_M.setVisible(true);
		}
		
		if(adopt[8].equals("입양 전")) {
			Hide_Lbl.setVisible(false);
			Adopt_Request_Btn.setVisible(true);
		} else {
			Hide_Lbl.setVisible(true);
			Adopt_Request_Btn.setVisible(false);
		}
		
	}
	
//	
	
	private void checkAdopt(String rd_num) {
		boolean check = alertConfirm("입양 신청하시겠습니까?");
		if(check) {
			try {
				Adopt_LogVO vo = new Adopt_LogVO();
				vo.setRd_num(rd_num);
				vo.setAdopt_check("n");
				vo.setCustom_id(Login_controller.log_c.getCustom_id());
				if(Main.ab_u.insertAdopt_log(vo)>0) {
					alertInfo("신청이 성공적으로 되었습니다.");
				}else {
					alertError("신청 실패");
				}
				Adopt_viewController.adopt_view_dialog.close();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
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
