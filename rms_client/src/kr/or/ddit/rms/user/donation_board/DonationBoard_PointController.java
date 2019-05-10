package kr.or.ddit.rms.user.donation_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationBoard_PointController implements Initializable {
	
	public static Spon_LogVO Svo;
	
	@FXML JFXTextField donation_point_txtF;
	@FXML Label my_point_lable;
	@FXML JFXButton point_donation_btn;
	@FXML JFXButton point_cancel_btn;
	ArrayList<CustomVO> lists;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Svo = new Spon_LogVO();
		
		Svo.setShel_id(DonationBoard_DetailController.vo.getShel_id()); // 글작성한 보호기관 아이디
		Svo.setCustom_id(Login_controller.log_c.getCustom_id()); // 회원아이디
		Svo.setPrice(donation_point_txtF.getText()); //기재한 금액
		Svo.setSpon_num(DonationBoard_DetailController.vo.getSpon_num());
		System.out.println(Svo.getSpon_num());
		CustomVO vo = new CustomVO();
		vo.setCustom_id(Login_controller.log_c.getCustom_id());
		CustomVO resVO;
		try {
			resVO = Main.fis.getSearchCustom(vo);
			my_point_lable.setText(resVO.getCustom_point());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		//후원
		point_donation_btn.setOnAction(e-> point_donation());
		
		//취소
		point_cancel_btn.setOnAction(e -> {
			DonationBoard_ChoiceController.choice_madal.close();
		});
	}

	//후원버튼
	private void point_donation() {
		if (!donation_point_txtF.getText().isEmpty()) {

			Spon_LogVO sVO = new Spon_LogVO();
			sVO.setShel_id(DonationBoard_DetailController.vo.getShel_id()); // 글작성한 보호기관 아이디
			sVO.setCustom_id(Login_controller.log_c.getCustom_id()); // 회원아이디
			sVO.setPrice(donation_point_txtF.getText());
			sVO.setSpon_num(this.Svo.getSpon_num());
			 
			CustomVO cuVO =new CustomVO();
			cuVO.setCustom_id(Login_controller.log_c.getCustom_id());
			
			try {
				int cnt = Main.db_u.insertSpon_log(sVO);
				lists = (ArrayList<CustomVO>) Main.db_u.getSearchCustom(cuVO);		
				CustomVO cVO =new CustomVO();
				cVO=lists.get(0);
				int Point = Integer.parseInt(lists.get(0).getCustom_point());
				int setPoint = Integer.parseInt(donation_point_txtF.getText());
				cVO.setCustom_point(Point-setPoint+"");
				int cnt2= Main.db_u.updateCustom(cVO);
				
				if (cnt2 > 0 && cnt>0) {
					Login_controller.log_c.setCustom_point(Point-setPoint+"");
					alertInfo("후원해 주셔서 감사합니다.");
					DonationBoard_DetailController dbdc = ChangePane.loader.getController();
					dbdc.ShowDetail();
					DonationBoard_ChoiceController.choice_madal.close();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			alertError("금액을 입력해주세요.");
		}
		
	}
	
	
	public void alertInfo(String temp){
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFO");
		alertInfo.setContentText(temp);
		alertInfo.showAndWait();
	}
	
	public void alertError(String temp){
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("ERROR");
		alertError.setContentText(temp);
		alertError.showAndWait();
	}

}
