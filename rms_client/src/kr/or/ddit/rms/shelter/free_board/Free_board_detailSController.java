package kr.or.ddit.rms.shelter.free_board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.webkit.WebPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CommentsVO;
import kr.or.ddit.rms.vo.NoteVO;

public class Free_board_detailSController implements Initializable{
	public static Board_detailVO vo; // 목록화면에서 선택했을 때 선택한 값을 저장하기 위한 vo
	@FXML JFXTextField freeboard_title_tf;
	@FXML JFXTextField freeboard_id_tf;
	@FXML ImageView Layout_logo_note_img;
	@FXML ImageView freeboard_content_iv;
	@FXML Label freeboard_upd_date_label;
	@FXML Label freeboard_cnt_label;
	@FXML WebView freeboard_content_wv;
	@FXML JFXTextField freeboard_comment_name_textfield;
	@FXML JFXTextArea freeboard_comment_add_ta;
	@FXML JFXButton freeboard_comment_add_btn;
	@FXML JFXButton freeboard_upd_btn;
	@FXML JFXButton freeboard_del_btn;
	@FXML JFXButton freeboard_gotolist_btn;
	@FXML JFXButton report_btn;
	@FXML TableView<CommentsVO> comment_table;
	@FXML TableColumn<CommentsVO,String> comment_table_col1;
	@FXML TableColumn<CommentsVO,String> comment_table_col2;
	@FXML TableColumn<CommentsVO,String> comment_table_col3;
	@FXML TableColumn<CommentsVO,String> comment_table_col4;
	@FXML TableColumn<CommentsVO,String> comment_table_col5;
	
	ObservableList<CommentsVO> allComData;
	ArrayList<CommentsVO> comLists;
	@FXML AnchorPane forRemove_vb;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Layout_logo_note_img.setOnMouseClicked(e->sendNote());
		freeboard_title_tf.setText(vo.getTitle());
		freeboard_id_tf.setText(vo.getCustom_id());
		freeboard_upd_date_label.setText(vo.getUpd_date());
		freeboard_cnt_label.setText(vo.getBoard_cnt());
		contentLoad();
		freeboard_title_tf.setEditable(false);
		freeboard_id_tf.setEditable(false);
		freeboard_upd_btn.setOnAction(e->modalupd());
		report_btn.setOnAction(e->modalReport());
		
		if(!vo.getCustom_id().equals(Login_controller.log_s.getShel_id())) {
			freeboard_upd_btn.setDisable(true);
			freeboard_del_btn.setDisable(true);
		}
		freeboard_comment_name_textfield.setText(Login_controller.log_s.getShel_name());
		freeboard_comment_name_textfield.setDisable(true);
		
		commentTableSet();
		imgSet();
		
		freeboard_del_btn.setOnAction(e->{
			boolean delcheck = alertConfirm("삭제 하시겠습니까?");
			if(delcheck) {
				try {
					Main.fb_s.deleteBoard_detail(Free_board_detailSController.vo);
					ChangePane cp = Main_page_controller.fxmlLoad.getController();
					cp.clearing();
					ChangePane.changePane(getClass().getResource("free_board_table.fxml"));
					cp.add();
				} catch (RemoteException e1) {
					alertInfo("삭제되지 않았습니다.");
				}
			}else {
				alertInfo("삭제되지 않았습니다.");
			}
			
		});
		freeboard_gotolist_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("free_board_table.fxml"));
				cp.add();
			}
		});
	}
	
	private void contentLoad() {
		WebEngine webEngine = freeboard_content_wv.getEngine();
		webEngine.loadContent(vo.getContent());

		try {
			Field field = webEngine.getClass().getDeclaredField("page");
			field.setAccessible(true);
			com.sun.webkit.WebPage page = (WebPage) field.get(webEngine);
			SwingUtilities.invokeLater(() -> {
			page.setBackgroundColor(new java.awt.Color(0, 0, 0, 0).getRGB());
			});
			

			} catch ( Exception e ) {
			e.printStackTrace();
			}
	}

	private void imgSet() {
		File loadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Freeboard\\"+vo.getBc_num()+
				","+vo.getBoard_num()+vo.getBoard_img());
		try {
			BufferedImage bufferedImg = ImageIO.read(loadFile);
			Image img = SwingFXUtils.toFXImage(bufferedImg, null);
			freeboard_content_iv.setImage(img);
		} catch (IOException e) {
			forRemove_vb.getChildren().remove(freeboard_content_iv);
			System.out.println("공유폴더에 파일 없음");
		}
		
	}
	
	private void sendNote() {
		Stage WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("note_send.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFXButton cancel = (JFXButton) parent.lookup("#note_send_cancelBtn");
		JFXButton send = (JFXButton) parent.lookup("#note_send_sendBtn");
		JFXTextField note_send_to_txtfield  = (JFXTextField) parent.lookup("#note_send_to_txtfield");
		JFXTextField note_send_title_txtField  = (JFXTextField) parent.lookup("#note_send_title_txtField");
		JFXTextArea note_send_textarea = (JFXTextArea) parent.lookup("#note_send_textarea");
		note_send_title_txtField.setPromptText("쪽지 제목");
		note_send_textarea.setPromptText("쪽지 내용");
		note_send_to_txtfield.setText(vo.getCustom_id());
		note_send_to_txtfield.setDisable(true);
		
		Scene scene = new Scene(parent);
		WritePage.setTitle("게시판");
		WritePage.setScene(scene);
		WritePage.show();
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WritePage.close();
			}
		});
		send.setOnAction(e->{
			
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
			}else if(vo.getCustom_id().equals(Login_controller.log_s.getShel_id())){
				alertError("자신에게 보낼수 없습니다.");
				WritePage.close();
			}else {
				NoteVO vo1 = new NoteVO();
				vo1.setNote_cnt("N");
				vo1.setNote_date(null);
				vo1.setNote_id_from(Login_controller.log_s.getShel_id()); // 받는 사람에 보내는 사람(로그인한) id 넣기
				vo1.setNote_id_to(note_send_to_txtfield.getText());
				vo1.setNote_title(note_send_title_txtField.getText());
				vo1.setNote_content(note_send_textarea.getText());

				int cnt = 0;
				try {
					cnt = Main.ns.insertNote(vo1);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(cnt > 0) {
					alertInfo("쪽지 보내기 완료");
					WritePage.close();
				}else {
					alertError("쪽지 보내기 실패");
				}
			}
		});
	}
	
	private void commentTableSet() {
		allComData = FXCollections.observableArrayList();
		try {
			CommentsVO temp =new CommentsVO();
			temp.setBc_num(vo.getBc_num());
			temp.setBoard_num(vo.getBoard_num());
			
			comLists = (ArrayList<CommentsVO>) Main.cs.getSearchComments(temp); // 목록을 전체 가져옴
			for (int i = 0; i < comLists.size(); i++) {
				allComData.add(comLists.get(i)); //목록 전체 길이 구하기
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		freeboard_comment_add_btn.setOnAction(e->addCom());
		comment_table_col1.setCellValueFactory(new PropertyValueFactory<>("comment_num"));
		comment_table_col2.setCellValueFactory(new PropertyValueFactory<>("board_num"));
		comment_table_col3.setCellValueFactory(new PropertyValueFactory<>("custom_id"));
		comment_table_col4.setCellValueFactory(new PropertyValueFactory<>("ins_date"));
		comment_table_col5.setCellValueFactory(new PropertyValueFactory<>("comment_content")); //컬럼 설정
		comment_table.setItems(allComData);		
	}
	
	private void addCom() {
		if(freeboard_comment_add_ta.getText().length()==0||freeboard_comment_add_ta.getText().length()<5) {
			alertError("값을 입력하거나 짧습니다.");
		}else {
			CommentsVO temp =new CommentsVO();
			temp.setComment_num(vo.getCom_idx());
			System.out.println(vo.getCom_idx());
			vo.setCom_idx((Integer.parseInt(vo.getCom_idx())+1)+"");
			try {
				Main.fb_u.updateBoard_detail(vo);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			temp.setBc_num(vo.getBc_num());
			temp.setBoard_num(vo.getBoard_num());
			temp.setComment_content(freeboard_comment_add_ta.getText());
			temp.setCustom_id(Login_controller.log_s.getShel_id());
			try {
				Main.cs.insertComments(temp);
				freeboard_comment_add_ta.clear();
				alertInfo("댓글 등록이 성공적으로 되었습니다!");
				commentTableSet();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void modalReport() {
		Stage WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("reportPage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFXButton cancel = (JFXButton) parent.lookup("#report_cancel_btn");
		JFXButton ok = (JFXButton) parent.lookup("#report_ok_btn");
		JFXTextField idFrom = (JFXTextField) parent.lookup("#report_IdFrom_tf");
		JFXTextField idTo = (JFXTextField) parent.lookup("#report_IdTo_tf");
		JFXTextArea content = (JFXTextArea) parent.lookup("#report_reason_ta");
		idTo.setText(this.freeboard_id_tf.getText());
		idFrom.setText(Login_controller.log_s.getShel_id());
		idTo.setDisable(true);
		idFrom.setDisable(true);
		
		Scene scene = new Scene(parent);
		WritePage.setTitle("게시판");
		WritePage.setScene(scene);
		WritePage.show();
		ok.setOnAction(e->{
			boolean check = alertConfirm("신고하시겠습니까? (허위 신고시 제제)");
			if(check) {
				try {
					BlacklistVO bvo = new BlacklistVO();
					bvo.setMem_id(this.freeboard_id_tf.getText());
					bvo.setReason(content.getText());
					Main.bs.insertBlacklist(bvo);
					alertInfo("신고 완료");
				} catch (RemoteException e1) {
					alertError("DB 에러");
				}
			}else {
				alertInfo("취소하셨습니다.");
			}
			WritePage.close();
		});
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WritePage.close();
			}
		});
	}
	
	private void modalupd() {
		Stage WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("free_board_detail_upd.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFXButton cancel = (JFXButton) parent.lookup("#freeboard_cancel_btn");
		JFXButton ok = (JFXButton) parent.lookup("#freeboard_ok_btn");
		JFXTextField title = (JFXTextField) parent.lookup("#freeboard_title_tf");
		JFXTextField id = (JFXTextField) parent.lookup("#freeboard_customId_tf");
		JFXTextArea content = (JFXTextArea) parent.lookup("#freeboard_content_ta");
		title.setText(this.freeboard_title_tf.getText());
		id.setText(this.freeboard_id_tf.getText());
		content.setText(vo.getContent());
		id.setDisable(true);
		ok.setOnAction(e->updCheck(title,content,WritePage));
		
		Scene scene = new Scene(parent);
		WritePage.setTitle("게시판");
		WritePage.setScene(scene);
		WritePage.show();
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WritePage.close();
			}
		});
	}
	
	private void updCheck(JFXTextField title, JFXTextArea content, Stage writePage) {
		if(title.getText().isEmpty()) {
			alertError("제목이 비었습니다.");
		}else if(content.getText().isEmpty()) {
			alertError("내용이 비었습니다.");
		}else {
			Board_detailVO uvo = vo;
			uvo.setContent(content.getText());
			uvo.setTitle(title.getText());
			try {
				int cnt = Main.fb_u.updateBoard_detail(uvo);
				if(cnt>0) {
					alertInfo("수정 완료");
					vo=uvo;
					freeboard_title_tf.setText(vo.getTitle());
					contentLoad();
				}else {
					alertError("수정 실패");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			writePage.close();
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
	
	public void alertTest(){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText("ERROR 창 입니다");
		alertErorr.showAndWait();
	}
	public void alertError(String temp){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	
	
}
