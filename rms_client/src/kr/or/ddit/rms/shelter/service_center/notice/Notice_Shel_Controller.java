package kr.or.ddit.rms.shelter.service_center.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.NoticeVO;

public class Notice_Shel_Controller implements Initializable {
	
	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane; //main에 연결할 때 
	
	@FXML JFXTextField search_txtF;
	@FXML JFXButton search_btn;
	@FXML TableView<NoticeVO> notice_table;
	@FXML TableColumn<NoticeVO,String> notice_board_col1;
	@FXML TableColumn<NoticeVO,String> notice_board_col2;
	@FXML TableColumn<NoticeVO,String> notice_board_col3;
	@FXML TableColumn<NoticeVO,String> notice_board_col4;
	@FXML Pagination table_paging;
	@FXML Button back;
	
	 private int from, to, itemsForPage;
	 ObservableList<NoticeVO> allBoardData,currentBoardData;
	 ArrayList<NoticeVO> lists;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		search_btn.toFront();
		// 초기화면
		init();

		notice_table.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				gotoNoticeDetail();
			}
		});

		search_btn.setOnAction(e -> {
			NoticeSearch();
		});
	}

	private void init() {
		allBoardData = FXCollections.observableArrayList();
	      try {
	         lists = (ArrayList<NoticeVO>) Main.n_s.getNoticeAll();
	         for(int i = 0; i < lists.size(); i++) {
	            allBoardData.add(lists.get(i));
	         }
	      } catch (RemoteException e) {
	         e.printStackTrace();
	      }
	      
	      notice_board_col1.setCellValueFactory(new PropertyValueFactory<>("notice_num")); //글번호
	      notice_board_col2.setCellValueFactory(new PropertyValueFactory<>("title")); //글제목 
	      notice_board_col3.setCellValueFactory(new PropertyValueFactory<>("admin_id")); //작성자ID
	      notice_board_col4.setCellValueFactory(new PropertyValueFactory<>("write_date")); //작성날짜
	      
	      setTable();
		
	}
	

	//검색
	private void NoticeSearch() {
		String txt = search_txtF.getText();
		NoticeVO vo = new NoticeVO();
		
		vo.setNotice_num(txt); //글번호로검색
		vo.setTitle(txt); //제목으로검색
		//vo.setContent(txt); //내용으로검색 //CLOB오류확인필요

		// 검색 값이 있을 경우
		if (!search_txtF.getText().isEmpty()) {
			lists.clear();
			allBoardData.clear();
			allBoardData = FXCollections.observableArrayList();
			try {
				lists = (ArrayList<NoticeVO>) Main.n_s.getNoticeTextSearch(vo);
				for (int i = 0; i < lists.size(); i++) {
					allBoardData.add(lists.get(i));
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			setTable();
		}

		// 검색 값이 없을 경우
		if (search_txtF.getText().isEmpty()) {
			alertError("검색 결과가 없습니다.");
			init();
		}

	}

	private void gotoNoticeDetail() {
		Notice_Shel_Detail_Controller.vo = notice_table.getSelectionModel().getSelectedItem();
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("notice_detail.fxml"));
		cp.add();

	}
		
	
	private void setTable() {
		itemsForPage = 5;
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1;
		table_paging.setPageCount(totPageCount);
		table_paging.setPageFactory(this::createPage);
		
		notice_table.setItems(allBoardData);
	}

	private Node createPage(int pageIndex){
	      
	      from = pageIndex * itemsForPage; //전체 페이지수 * 페이지당 목록개수
	      to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
	      notice_table.setItems(getTableViewData(from, to));
	      
	      return notice_table;
	   }
	   
	   private ObservableList<NoticeVO> getTableViewData(int from, int to){
	      
	      currentBoardData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
	      int totSize = allBoardData.size();
	      for(int i = from; i <= to && i <totSize; i++){
	      
	         currentBoardData.add(allBoardData.get(i));
	      }
	      
	      return currentBoardData;
	   }
	   
	   public void alertError(String temp){
			Alert alertErorr = new Alert(AlertType.ERROR);
			alertErorr.setTitle("ERROR");
			alertErorr.setContentText(temp);
			alertErorr.showAndWait();
		}

	}

