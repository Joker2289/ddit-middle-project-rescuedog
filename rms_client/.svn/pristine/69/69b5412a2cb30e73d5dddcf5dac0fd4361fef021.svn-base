package kr.or.ddit.rms.user.mypage.note;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.NoteVO;

public class NoteController implements Initializable{
	public static NoteVO vo;
	public static Stage dialog;


	@FXML CheckBox note_main_cb1;
	@FXML CheckBox note_main_cb2;
	@FXML CheckBox note_main_cb3;
	@FXML CheckBox note_main_cb4;
	@FXML CheckBox note_main_cb5;
	@FXML CheckBox note_main_cb6;
	@FXML CheckBox note_main_cb7;
	@FXML MenuBar vote_sel;
	@FXML Pagination note_main_paging;
	@FXML Button note_main_sel_btn;
	@FXML Button note_main_selAll_btn;
	@FXML Button note_main_send_btn;
	@FXML JFXButton note_main_memModi_btn;
	@FXML JFXButton note_main_activeList_btn;
	@FXML JFXButton note_main_buyList_btn;
	@FXML JFXButton note_main_noteList_btn;
	@FXML TableView<NoteVO> note_main_tableView;
	@FXML TableColumn<NoteVO, String> note_main_from_col1;
	@FXML TableColumn<NoteVO, String> note_main_title_col2;
	@FXML TableColumn<NoteVO, String> note_main_date_col3;
	@FXML TableColumn<NoteVO, String> note_main_cnt_col4;
	@FXML Label note_main_read;
	@FXML Label note_main_notread;
	
	private int from, to, itemsForPage;
	ObservableList<NoteVO> allNoteData, currentBoardData, loginData;
	ArrayList<NoteVO> listTo;
	ArrayList<Boolean> checkValue;
	ArrayList<NoteVO> lists;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	@FXML AnchorPane tempPane;
	@FXML Button back;
	@FXML Button detail_delBtn;

	int notReadCnt = 0;
	int readCnt = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vo = new NoteVO();
		vo.setNote_id_to(Login_controller.log_c.getCustom_id());
		System.out.println("loginID" + vo.getNote_id_to());
		System.out.println(" 내용 " + vo.getNote_content());
		
		//테이블 설정
		noteTableSet();
		
		//선택삭제 버튼 이벤트
		note_main_sel_btn.setOnAction(e -> selectDel());
		
		//쪽지보내기 버튼 이벤트 
		note_main_send_btn.setOnAction(e -> sendNote());
		
		//상세보기 이벤트
		note_main_tableView.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				gotoNoteDetail();
			}
		});
		
		//읽음, 읽지 않음 카운트 로직
		for(int i = 0; i < listTo.size(); i++) {
			if(listTo.get(i).getNote_cnt().equals("N")) {
				notReadCnt++;
			}
		}
		readCnt = listTo.size() - notReadCnt;
		
		note_main_notread.setText(notReadCnt+"");
		note_main_read.setText(readCnt+"");
		
		
		System.out.println(note_main_cnt_col4.getCellData(0));
//		if(note_main_cnt_col4.getCellData(0).equals("0")) {
//			note_main_cnt_col4.set
//		}
//		
		
		//페이징
		allNoteData = FXCollections.observableArrayList();
		try {
			lists = (ArrayList<NoteVO>) Main.ns.getNoteAll(); // 목록을 전체 가져옴
			for (int i = 0; i < lists.size(); i++) {
				allNoteData.add(lists.get(i)); //목록 전체 길이 구하기
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		itemsForPage = 5; // 한페이지 보여줄 항목 수 설정
		int totPageCount = loginData.size()%itemsForPage == 0 ? loginData.size()/itemsForPage : loginData.size()/itemsForPage + 1; //
		note_main_paging.setPageCount(totPageCount); // 전체 페이지 수 설정
		note_main_paging.setPageFactory(this::createPage); // 페이지 나누기 위한 설정

	
	}
	
	private void noteTableSet() {
		try {
		//로그인한 ID의 쪽지 가져오기
		listTo = (ArrayList<NoteVO>) Main.ns.getNoteTo(vo.getNote_id_to());
		loginData = FXCollections.observableArrayList(listTo);

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		//테이블뷰 컬럼 연결
		note_main_from_col1.setCellValueFactory(new PropertyValueFactory<>("note_id_from"));
		note_main_title_col2.setCellValueFactory(new PropertyValueFactory<>("note_title"));
		note_main_date_col3.setCellValueFactory(new PropertyValueFactory<>("note_date"));
		note_main_cnt_col4.setCellValueFactory(new PropertyValueFactory<>("note_cnt"));
		
		//컬럼에 값 넣기
		note_main_tableView.setItems(loginData);
			
		}

	//쪽지보내기 메서드
	private void sendNote() {
		dialog = new Stage();
		
		//새창 띄우기
		dialog = new Stage(StageStyle.UTILITY);
		
		dialog.initModality(Modality.APPLICATION_MODAL);
		//dialog.initOwner();
		dialog.setTitle("쪽지 보내기");
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("note_send.fxml"));
		} catch(IOException ee) {
			ee.printStackTrace();
		}
		System.out.println("main" + vo.getNote_id_to()); 
		Scene scene = new Scene(parent);
		
		dialog.setScene(scene);
		dialog.setResizable(false);//크기고정
		dialog.show();
	}

	//상세보기 메서드
	private void gotoNoteDetail() {
		System.out.println("상세보기");
		NoteMainDetailController.vo = note_main_tableView.getSelectionModel().getSelectedItem();
		
		//상세보기 클릭시 읽은 쪽지로 변경
		NoteVO vo = note_main_tableView.getSelectionModel().getSelectedItem();
		if(vo.getNote_cnt().equals("N")) {
			vo.setNote_cnt("Y");
			vo.setNote_content(vo.getNote_content());
			vo.setNote_date(vo.getNote_date());
			vo.setNote_id_from(vo.getNote_id_from());
			vo.setNote_id_to(vo.getNote_id_to());
			vo.setNote_title(vo.getNote_title());
			
			try {
				int c = Main.ns.updateNote(vo);
				System.out.println(c);
				System.out.println("change" + vo.getNote_cnt());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			note_main_tableView.refresh();
			//읽음으로 바뀔 때 읽지않은 쪽지 개수 차감, 읽은 쪽지 증가;
			notReadCnt--;
			readCnt++;
			
			
			note_main_notread.setText(notReadCnt+"");
			note_main_read.setText(readCnt+"");
			
		}
		
		tempPane.setVisible(false);
		tempPane.setDisable(true);
		try {
			loadPane=FXMLLoader.load(getClass().getResource("note_main_detail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		servicePane.getChildren().add(loadPane);
		back=null;
		back = (JFXButton) loadPane.lookup("#note_detail_listBtn");
		detail_delBtn = (JFXButton) loadPane.lookup("#note_detail_delBtn");
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadPane.getChildren().clear();
				servicePane.getChildren().remove(loadPane);
				tempPane.setVisible(true);
				tempPane.setDisable(false);
			}
		});
		
		//상세보기 삭제 이벤트
		detail_delBtn.setOnAction(e -> {
			System.out.println("상세보기 삭제 버튼");
			try {
				NoteVO vo1 = new NoteVO();
				vo1.setNote_id_to(vo.getNote_id_to());
				vo1.setNote_id_from(vo.getNote_id_from());
				vo1.setNote_date(vo.getNote_date());
				Main.ns.deleteNote(vo1);
				alertInfo("삭제 되었습니다.");
//				dialog.close(); 
//				
				loadPane.getChildren().clear();
//				servicePane.getChildren().clear();
				servicePane.getChildren().remove(loadPane);
		        
				loadPane.getChildren().add(tempPane);
		      
				servicePane.getChildren().add(tempPane);
		        loadPane.getChildren().add(servicePane);
//				gotoNoteDetail();
				
			} catch (RemoteException ee) {
				ee.printStackTrace();
			}
		});
		
	}

	//선택삭제 버튼 메서드
	private void selectDel() {
		
		//check true / false 값 가져오기
		checkValue = new ArrayList<>();
		
		checkValue.add(note_main_cb1.isSelected());
		checkValue.add(note_main_cb2.isSelected());
		checkValue.add(note_main_cb3.isSelected());
		checkValue.add(note_main_cb4.isSelected());
		checkValue.add(note_main_cb5.isSelected());
		checkValue.add(note_main_cb6.isSelected());
		checkValue.add(note_main_cb7.isSelected());

		for(int i = 0; i < loginData.size(); i++) {
			if(checkValue.get(i) == true) {
				NoteVO vo = new NoteVO();
				vo.setNote_id_to(loginData.get(i).getNote_id_to());
				vo.setNote_id_from(loginData.get(i).getNote_id_from());
				vo.setNote_date(loginData.get(i).getNote_date());
				
				try {
					int cnt = Main.ns.deleteNote(vo);
					System.out.println(cnt + " 개가 삭제되었습니다.");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//페이징 메서드
	private Node createPage(int pageIndex){
		
		from = pageIndex * itemsForPage; //전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		note_main_tableView.setItems(getTableViewData(from, to));
		
		return note_main_tableView;
	}
	
	//페이징 메서드
	private ObservableList<NoteVO> getTableViewData(int from, int to){
		
		currentBoardData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = loginData.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentBoardData.add(loginData.get(i));
		}
		return currentBoardData;
	}
	
	//경고 메서드
	public void alertErorr(String temp){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	//경고 메서드
	public void alertInfo(String temp){
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFO");
		alertInfo.setContentText(temp);
		alertInfo.showAndWait();
	}
	
	
	
		
	
}

