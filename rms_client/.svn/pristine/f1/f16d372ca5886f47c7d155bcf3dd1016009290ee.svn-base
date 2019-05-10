package kr.or.ddit.rms.shelter.adopt_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.Adopt_LogVO;

public class Request_view_controller implements Initializable{

	@FXML AnchorPane Request_List_Pane;
	
	public static VBox mainBox;
	
	public static Stage request_view_dialog;
	
	public static HBox H;
	
	 public static FXMLLoader loader;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainBox = new VBox();
		mainBox.setPrefWidth(960.0);
		
		show_rqList();
	}

	public void show_rqList() {
		mainBox.getChildren().clear();
		Adopt_LogVO vo = new Adopt_LogVO();
		vo.setShel_id(Login_controller.log_s.getShel_id());
		try {
			List<Adopt_LogVO> list = Main.ab_s.getSearchAdopt_log(vo);
			for(int i=0; i<list.size(); i++) {
				H = new HBox();
				H.setPrefWidth(960.0);
				H.setPrefHeight(35.0);
				H.setAccessibleText(i+"");
				String[] request_info = list.get(i).getAdopt_req().split("/");
				
				String rd_Name = request_info[0];
				String custom_Name = request_info[1];
				String custom_Addr = request_info[2];
				String custom_Age = request_info[3];
				String custom_ID = request_info[4];
				String custom_Email = request_info[5];
				String check1 = request_info[6];
				String check2 = request_info[7];
				String img = request_info[8];
				
				String rd_Num = list.get(i).getRd_num();
				String request_Date = list.get(i).getAdopt_date();
				String request_State = list.get(i).getAdopt_check();
				String custom_id = list.get(i).getCustom_id();
				
				//유기견번호
				Label rdNum_lbl = new Label();
				rdNum_lbl.setText(rd_Num);
				rdNum_lbl.setPrefWidth(63.0);
				rdNum_lbl.setPrefHeight(35.0);
				rdNum_lbl.setAlignment(Pos.CENTER);
				rdNum_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(rdNum_lbl);
				
				//유기견 이름
				Label rd_name_lbl = new Label();
				rd_name_lbl.setText(rd_Name);
				rd_name_lbl.setPrefWidth(194.0);
				rd_name_lbl.setPrefHeight(40.0);
				rd_name_lbl.setAlignment(Pos.CENTER);
				rd_name_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(rd_name_lbl);
				
				//신청자ID
				Label custom_ID_lbl = new Label();
				custom_ID_lbl.setText(custom_id);
				custom_ID_lbl.setPrefWidth(160.0);
				custom_ID_lbl.setPrefHeight(40.0);
				custom_ID_lbl.setAlignment(Pos.CENTER);
				custom_ID_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(custom_ID_lbl);
				
				//신청자 이름
				Label custom_Name_lbl = new Label();
				custom_Name_lbl.setText(custom_Name);
				custom_Name_lbl.setPrefWidth(146.0);
				custom_Name_lbl.setPrefHeight(40.0);
				custom_Name_lbl.setAlignment(Pos.CENTER);
				custom_Name_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(custom_Name_lbl);
				
				//신청자 나이
				Label custom_Age_lbl = new Label();
				custom_Age_lbl.setText(custom_Age);
				custom_Age_lbl.setPrefWidth(82.0);
				custom_Age_lbl.setPrefHeight(40.0);
				custom_Age_lbl.setAlignment(Pos.CENTER);
				custom_Age_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(custom_Age_lbl);
				
				//신청날짜
				Label request_Date_lbl = new Label();
				request_Date_lbl.setText(request_Date);
				request_Date_lbl.setPrefWidth(189.0);
				request_Date_lbl.setPrefHeight(40.0);
				request_Date_lbl.setAlignment(Pos.CENTER);
				request_Date_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(request_Date_lbl);
				
				//상태
				Label request_State_lbl = new Label();
				request_State_lbl.setText(request_State);
				request_State_lbl.setPrefWidth(138.0);
				request_State_lbl.setPrefHeight(40.0);
				request_State_lbl.setAlignment(Pos.CENTER);
				request_State_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(request_State_lbl);
				
				//라인넣기
				HBox line = new HBox();
				line.setPrefWidth(960.0);
				line.setPrefHeight(1.0);
				line.setStyle("-fx-background-color: #c2c2c2;");
				
				mainBox.getChildren().add(H);
				mainBox.getChildren().add(line);
				
				H.setAccessibleText(rd_Name + "/" + custom_Name + "/" + custom_Addr + "/" 
						+ custom_Age + "/" + custom_ID + "/" + custom_Email + "/" + check1 + "/"
						+ check2 + "/" + img + "/" + rd_Num + "/" + request_Date + "/" + custom_id + "/" + request_State);
				
				H.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
					H = (HBox) e.getSource();
					
					request_view_dialog = new Stage(StageStyle.UTILITY);
					
					request_view_dialog.initModality(Modality.APPLICATION_MODAL);
					//dialog.initOwner();
					request_view_dialog.setTitle("유기견 상세조회");
					
					Parent parent = null;
					try {
						
						loader = new FXMLLoader(Request_detail_view_controller.class.getResource("request_detail_view.fxml"));
						parent = loader.load();
						Request_detail_view_controller cont = loader.getController();
						cont.newset(this);
						
						//	쓰지마
//						parent = FXMLLoader.load(getClass().getResource("request_detail_view.fxml"));
					} catch(IOException ee) {
						ee.printStackTrace();
					}
					
					Scene scene = new Scene(parent);
					
					request_view_dialog.setScene(scene);
					request_view_dialog.setResizable(false);//크기고정
					request_view_dialog.show();
					
				});
				
				
			}
			//Request_List_Pane 비어있지 않을때 지워주기
			if(!Request_List_Pane.getChildren().isEmpty()) {
				Request_List_Pane.getChildren().clear();
			}
			
			Request_List_Pane.getChildren().add(mainBox);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
