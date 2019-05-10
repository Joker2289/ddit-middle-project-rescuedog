package kr.or.ddit.rms.member.note;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.NoteVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class Note_viewController implements Initializable{
	@FXML AnchorPane viewPane;
	@FXML JFXButton note_allDel_btn;
	@FXML JFXButton note_selDel_btn;
	@FXML JFXButton close_btn;
	@FXML JFXButton note_write_btn;
	
	public static int list_size = 0;
	public static List<NoteVO> noteList;
	public static ArrayList<HBox> hboxs;
	
	
	
	VBox mainBox;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mainBox = new VBox();
		mainBox.setPrefWidth(1036.0);
		
		setNote();
		viewPane.getChildren().add(mainBox);
		
		note_allDel_btn.setOnAction(e->{
			allDel();
		});
		note_selDel_btn.setOnAction(e->{
			selDel();
		});
		close_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main_page_controller.WritePage.close();
			}
		});
		note_write_btn.setOnAction(e->{
			ChangeNoteScene.ChangeView(Note_viewController.class, "note_send_view.fxml",true,Main_page_controller.WritePage);
		});
	}

	private void selDel() {
		
		ArrayList<Integer> checks = new ArrayList<>();
		for (int i = 0; i < hboxs.size(); i++) {
			JFXCheckBox box = (JFXCheckBox) hboxs.get(i).getChildren().get(0);
			if(box.isSelected()) {
				checks.add(i);
			}
			
		}
		if(alertConfirm(checks.size()+" 개 선택하셨습니다. 삭제하시겠습니까?")) {
			for (int i = 0; i < checks.size(); i++) {
				try {
					Main.ns.deleteNote(noteList.get(checks.get(i)));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			setNote();
		}
		
	}

	private void allDel() {
		if(alertConfirm("전부삭제하시겠습니까?")) {
			try {
				NoteVO vo = new NoteVO();
				vo.setNote_id_to(Login_controller.log.getMem_id());
				int cnt = Main.ns.deleteAllNote(vo);
				if(cnt>0) {
					alertInfo(cnt+"건 삭제 완료");
				}else {
					alertError("쪽지가 없습니다.");
				}
				viewPane.getChildren().clear();
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private void setNote() {
		switch(Login_controller.log.getMem_author()) {
		
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
		}
			if(mainBox!=null) {
				
				mainBox.getChildren().clear();
			}
			try {
				List<NoteVO> nlist = Main.ns.getNoteTo(Login_controller.log.getMem_id());
				noteList = nlist;
				list_size =nlist.size();
				hboxs =new ArrayList<>();
				for (int i = 0; i < nlist.size(); i++) {
					
					String title = nlist.get(i).getNote_title();
					String idFrom = nlist.get(i).getNote_id_from();
					String recieveDate = nlist.get(i).getNote_date();
					String readCheck = nlist.get(i).getNote_cnt();
					
					HBox H = new HBox();
					H.setPrefWidth(1036.0);
					H.setPrefHeight(60.0);
					H.setAccessibleText(i+"");
					
					
					//체크박스 생성
					JFXCheckBox checkBox = new JFXCheckBox();
					checkBox.setPrefWidth(44.0);
					checkBox.setPrefHeight(60.0);
					checkBox.setAccessibleText(i+"");
					checkBox.setAlignment(Pos.CENTER);
					checkBox.setCheckedColor(Color.valueOf("#547FBE"));		
					
					//제목라벨
					Label note_title_lbl = new Label();
					note_title_lbl.setText(title);
					note_title_lbl.setPrefWidth(344.0);
					note_title_lbl.setPrefHeight(60.0);
					note_title_lbl.setAlignment(Pos.CENTER);
					note_title_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 18));
					note_title_lbl.setOnMouseClicked(e->{
						
						if(e.getClickCount()==2) {
							int num = Integer.parseInt(checkBox.getAccessibleText());
							gotoDetail(num);
						}
					});
					
					
					//보낸이 라벨
					Label note_idFrom_lbl = new Label();
					note_idFrom_lbl.setText(idFrom);
					note_idFrom_lbl.setPrefWidth(127.0);
					note_idFrom_lbl.setPrefHeight(60.0);
					note_idFrom_lbl.setAlignment(Pos.CENTER);
					note_idFrom_lbl.setAccessibleText(idFrom);
					note_idFrom_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 18));
					
					//받은날짜 라벨
					Label rdfindDate_lbl = new Label();
					rdfindDate_lbl.setText(recieveDate.substring(0, 9));
					rdfindDate_lbl.setPrefWidth(172.0);
					rdfindDate_lbl.setPrefHeight(60.0);
					rdfindDate_lbl.setAlignment(Pos.CENTER);
					rdfindDate_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 18));
					
					
					
					
					H.getChildren().add(checkBox);
					H.getChildren().add(note_title_lbl);
					H.getChildren().add(note_idFrom_lbl);
					H.getChildren().add(rdfindDate_lbl);
					
					
					FontAwesomeIcon CheckIcon = null;
					Label readCheck_lbl = null;
					if(readCheck.equals("Y")) {
						CheckIcon = new FontAwesomeIcon();
						CheckIcon.setIconName("CHECK");
						CheckIcon.setSize("2em");
					
					
						readCheck_lbl = new Label();
						readCheck_lbl.setGraphic(CheckIcon);
						readCheck_lbl.setPrefWidth(76.0);
						readCheck_lbl.setPrefHeight(60.0);
						readCheck_lbl.setAlignment(Pos.CENTER);
						
						H.getChildren().add(readCheck_lbl);
					}
					
					
					//라인넣기
					HBox line = new HBox();
					line.setPrefWidth(763.0);
					line.setPrefHeight(1.0);
					line.setStyle("-fx-background-color: #c2c2c2;");
					
					
					hboxs.add(H);
					
					mainBox.getChildren().add(H);
					mainBox.getChildren().add(line);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	
	private void gotoDetail(int i) {
		Note_detail_viewController.cnt=i;
		NoteVO vo = new NoteVO();
		vo=noteList.get(i);
		vo.setNote_cnt("Y");
		try {
			Main.ns.updateNote(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ChangeNoteScene.ChangeView(Note_viewController.class, "note_detail_view.fxml",true,Main_page_controller.WritePage);
		
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
