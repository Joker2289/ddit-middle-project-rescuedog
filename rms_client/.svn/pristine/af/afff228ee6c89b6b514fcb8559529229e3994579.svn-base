package kr.or.ddit.rms.question;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;

public class QuestionController implements Initializable{

	@FXML JFXTextField question_tf;
	@FXML JFXButton question_btn;
	@FXML JFXButton close_btn;
	@FXML ImageView admin_iv;
	@FXML ImageView user_iv;
	@FXML JFXTextArea user_tf;
	@FXML JFXTextArea admin_tf;
	@FXML AnchorPane textPane;
	@FXML ScrollPane main_scroll;
	
	Double[] lastX= {114.0,55.0,362.0,14.0}; //유저 텍스트 관리자 텍스트 유저 아이콘 관리자 아이콘
	Double[] lastY = {33.0,102.0,70.0,140.0};
	int msgCnt = 0;
	ArrayList<JFXTextArea> userList = new ArrayList<>();
	ArrayList<JFXTextArea> adminList = new ArrayList<>();
	
	boolean check=false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		question_tf.setOnAction(e->{
			returnAns();
			
		});
		question_btn.setOnAction(e->returnAns());
		close_btn.setOnAction(e->{
			Main_page_controller.WritePage.close();
		});
	}

	private void addItem() {
		lastY[0] += 140.0;
		lastY[1] += 140.0;
		lastY[2] += 140.0;
		lastY[3] += 140.0;
		JFXTextArea user_tf = new JFXTextArea();
		user_tf.setLayoutX(lastX[0]);
		user_tf.setLayoutY(lastY[0]);
		user_tf.setPrefHeight(50.0);
		user_tf.setPrefWidth(242.0);
		user_tf.setId("user_tf"+Integer.toString(msgCnt));
		user_tf.setStyle("-fx-background-color:#f0d9da");
		user_tf.setEditable(false);
		userList.add(user_tf);
		
		
		JFXTextArea admin_tf = new JFXTextArea();
		admin_tf.setLayoutX(lastX[1]);
		admin_tf.setLayoutY(lastY[1]);
		admin_tf.setId("admin_tf"+Integer.toString(msgCnt));
		admin_tf.setPrefHeight(50.0);
		admin_tf.setPrefWidth(237.0);
		admin_tf.setStyle("-fx-background-color:#b1bed5");
		
		admin_tf.setEditable(false);
		adminList.add(admin_tf);
		
		FontAwesomeIcon user_icon= new FontAwesomeIcon();
		user_icon.setLayoutX(lastX[2]);
		user_icon.setLayoutY(lastY[2]);
		user_icon.setIconName("USER");
		user_icon.setSize("3em");
		user_icon.setFill(Paint.valueOf("fdb44b"));
		
		FontAwesomeIcon admin_icon= new FontAwesomeIcon();
		admin_icon.setLayoutX(lastX[3]);
		admin_icon.setLayoutY(lastY[3]);
		admin_icon.setIconName("ANDROID");
		admin_icon.setSize("3em");
		admin_icon.setFill(Paint.valueOf("74b49b"));
		
		textPane.getChildren().add(user_tf);
		textPane.getChildren().add(user_icon);
		textPane.getChildren().add(admin_tf);
		textPane.getChildren().add(admin_icon);
		main_scroll.setVvalue(700.0);
		
		
	}
private void returnAns() {
		String ans = comeResponse(question_tf.getText());
		if(!check) {
			user_tf.setText(question_tf.getText());
			admin_tf.setText(ans);
			admin_tf.setEditable(false);
			user_tf.setEditable(false);
			check=true;
		}else {
			userList.get(userList.size()-1).setText(question_tf.getText());
			adminList.get(adminList.size()-1).setText(ans);
			userList.get(userList.size()-1).setEditable(false);
			adminList.get(adminList.size()-1).setEditable(false);
			adminList.get(adminList.size()-1).requestFocus();
		}
		question_tf.clear();
		msgCnt++;
		addItem();
	}

	private String comeResponse(String question) {
		try {
			ArrayList<String> ansAl = Main.qs.getAnswer(question);
			String ans= new String();
			for (int i = 0; i < ansAl.size(); i++) {
				ans+=ansAl.get(i);
			}
			if(ans.length()==0) {
				return "등록되지 않은 답변입니다.";
			}else {
				return ans;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
