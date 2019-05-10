package kr.or.ddit.rms.admin.donation_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class SponRecruitDetailAController implements Initializable{
	public static SponVO vo;
	
	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	@FXML JFXTextArea dona_content_ta;
	@FXML JFXTextField dona_price_tf;
	@FXML JFXTextField dona_title_tf;
	@FXML JFXTextField dona_login_tf;
	@FXML JFXButton dona_upd_btn;
	@FXML JFXButton dona_list_btn;
	@FXML JFXButton dona_del_btn;
	@FXML DatePicker dona_start_date;
	@FXML DatePicker dona_end_date;
	@FXML ProgressBar price_rate;
	@FXML Label rateLb;
	
	String sname;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ShowDetail();
		
		dona_del_btn.setOnAction(e->{
			delRecruit();
		});
		dona_list_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("recruit_listA.fxml"));
				cp.add();
			}
		});
		dona_upd_btn.setVisible(false);
//		dona_upd_btn.setOnAction(e->updRecruit());
	}

	protected void delRecruit() {
		Spon_LogVO svo = new Spon_LogVO();
		svo.setSpon_num(vo.getSpon_num());
		try {
			Main.sr_a.deleteSponLogAll(svo);
		} catch (RemoteException e1) {
			
		}
		SponVO tvo = new SponVO();
		tvo.setSpon_num(vo.getSpon_num());
		try {
			int cnt = Main.sr_s.deleteSpon(tvo);
			if(cnt > 0) {
				alertInfo("삭제완료");
				back();
			}else {
				alertError("삭제실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected void updRecruit() {

		if(dona_start_date.getValue() == null) {
			alertError("시작 날짜를 입력하세요");
		}else if(dona_end_date.getValue() == null) {
			alertError("종료 날짜를 입력하세요");
		}
		
		LocalDate sval = dona_start_date.getValue();
		LocalDate eval = dona_end_date.getValue();
		
		String sdate = sval.toString();
		String edate = eval.toString();
		
		SponVO tvo = new SponVO();
		tvo.setSpon_num(vo.getSpon_num());
		tvo.setShel_id(vo.getShel_id());
		tvo.setUpd_date(sdate);
		tvo.setLast_date(edate);
		tvo.setMoney(dona_price_tf.getText());
		tvo.setContent(dona_content_ta.getText());
		tvo.setTitle(dona_title_tf.getText());
		
		try {
			int cnt = Main.sr_s.updateSpon(tvo);
			if(cnt > 0) {
				alertInfo("수정완료");
				vo=tvo;
				back();
			}else {
				alertError("수정실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	private void back() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("recruit_listA.fxml"));
		cp.add();
	}

	/*private void updRecruit() {

		LocalDate sval = dona_start_date.getValue();
		LocalDate eval = dona_end_date.getValue();
		
		String sdate = sval.toString();
		String edate = eval.toString();
		
		SponVO tvo = new SponVO();
		tvo.setSpon_num(vo.getSpon_num());
		tvo.setShel_id(login_controller.log_s.getShel_id());
		tvo.setUpd_date(sdate);
		tvo.setLast_date(edate);
		tvo.setMoney(dona_price_tf.getText());
		tvo.setContent(dona_content_ta.getText());
		tvo.setTitle(dona_title_tf.getText());
		
		try {
			int cnt = Main.sr_s.updateSpon(tvo);
			if(cnt > 0) {
				alertInfo("수정완료");
			}else {
				alertError("수정실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}*/
	


	private void ShowDetail() {
		ShelterVO svo1 = new ShelterVO();
		svo1.setShel_id(vo.getShel_id());
		String shel_loc="";
		try {
			ShelterVO list = (ShelterVO) Main.fis.getSearchShelter(svo1);
			shel_loc=list.getShel_loc();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		dona_login_tf.setDisable(true);
		dona_title_tf.setText(vo.getTitle());
		dona_login_tf.setText(vo.getShel_id());
		
		dona_price_tf.setText(vo.getMoney());
		dona_content_ta.setText(vo.getContent());
		
		String sdate = vo.getUpd_date().substring(0, 10);
		dona_start_date.setValue(LocalDate.parse(sdate));
		
		String gdate = vo.getLast_date().substring(0, 10);
		dona_end_date.setValue(LocalDate.parse(gdate));
		
		float rate = 0f;
		ArrayList<Spon_LogVO> slist = new ArrayList<>();
		try {
			Spon_LogVO svo = new Spon_LogVO();
	    	  svo.setSpon_num(vo.getSpon_num());
	         float sum = 0;
	         slist = (ArrayList<Spon_LogVO>) Main.db_u.getSearchSpon_log(svo);
	         for(int i = 0; i < slist.size(); i++) {
	            if(vo.getShel_id().equals(slist.get(i).getShel_id())) {
	               sum += Float.parseFloat(slist.get(i).getPrice());
	            }
	         }
	         System.out.println(sum);
	         rate = sum / Float.parseFloat((vo.getMoney()));
	         price_rate.setProgress(rate);
	         rateLb.setText(Math.round(rate * 100) + " %");
	         
	      } catch (RemoteException e) {
	         e.printStackTrace();
	      }	
		shelCheck();
	}
	
	private void shelCheck() {
		dona_title_tf.setEditable(false);
		dona_login_tf.setDisable(true);
		dona_price_tf.setEditable(false);
		dona_content_ta.setEditable(false);
		dona_upd_btn.setDisable(true);
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
