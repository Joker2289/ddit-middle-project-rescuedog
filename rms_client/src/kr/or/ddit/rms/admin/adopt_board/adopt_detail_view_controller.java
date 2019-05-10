package kr.or.ddit.rms.admin.adopt_board;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class adopt_detail_view_controller implements Initializable{

	@FXML AnchorPane detailPane;	//메인판
	
	@FXML FontAwesomeIcon Icon_M;	//수컷 아이콘
	@FXML FontAwesomeIcon Icon_F;	//암컷 아이콘
	
	
	@FXML Label RD_Name;			//이름 라벨
	@FXML Label RD_Kind;			//견종 라벨
	@FXML Label RD_Num;				//등록번호 라벨
	@FXML Label RD_Date;			//등록 날짜
	@FXML Label RD_Shel;			//보호 기관
	
	@FXML JFXTextArea RD_Info;		//특이사항
	
	@FXML Label Hide_Lbl;			//숨김라벨
	@FXML ImageView RD_Img;			//유기견 사진

	@FXML JFXButton Goto_Adopt;		//닫기버튼
	@FXML JFXButton Adopt_Request_Btn;	//입양 신청 버튼
	
	String[] RD_log;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RD_log = adopt_view_controller.log_img.getAccessibleText().split("/");
		
		show_info();
		
		Adopt_Request_Btn.setVisible(false);
		Goto_Adopt.setOnAction(e->{
			adopt_view_controller.adopt_view_dialog.close();
		});
	}
	
	private void show_info() {
		
		//이미지 세팅
//		Image rd_image = new Image("kr/or/ddit/rms/shelter/adopt_board/rdImg/"+RD_log[0]);
		Image rd_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\"+RD_log[0]);
		RD_Img.setImage(rd_image);
		
		RD_Name.setText(RD_log[2]);
		
		RD_Kind.setText(RD_log[3]);
		
		RD_Num.setText(RD_log[1]);
		
		RD_Date.setText(RD_log[5]);
		
		RD_Shel.setText(RD_log[6]);
		
		RD_Info.setText(RD_log[7]);
		
		if(RD_log[4].equals("F")){
			Icon_F.setVisible(true);
			Icon_M.setVisible(false);
		}else {
			Icon_F.setVisible(false);
			Icon_M.setVisible(true);
		}
		
		if(RD_log[8].equals("입양 전")) {
			Hide_Lbl.setVisible(false);
			Adopt_Request_Btn.setVisible(true);
		} else {
			Hide_Lbl.setVisible(true);
			Adopt_Request_Btn.setVisible(false);
		}
		
		
	}
}
