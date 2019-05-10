package kr.or.ddit.rms.admin.review_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.vo.Board_detailVO;

public class Review_boardTbController implements Initializable {

	@FXML
	JFXComboBox Table_search_cb;
	@FXML
	JFXTextField Table_search_tf;
	@FXML
	JFXButton Table_search_btn;
	@FXML
	TableView<Board_detailVO> review_table;
	@FXML
	TableColumn<Board_detailVO, String> Table_review_col1;
	@FXML
	TableColumn<Board_detailVO, String> Table_review_col2;
	@FXML
	TableColumn<Board_detailVO, String> Table_review_col3;
	@FXML
	TableColumn<Board_detailVO, String> Table_review_col4;
	@FXML
	TableColumn<Board_detailVO, String> Table_review_col5;
	@FXML
	AnchorPane servicePane;
	@FXML
	AnchorPane loadPane;
	@FXML
	AnchorPane tempPane;

	@FXML
	JFXButton back;
	@FXML
	Pagination Table_search_page;
	@FXML
	JFXButton Table_add_btn;
	private int from, to, itemsForPage;

	ObservableList<String> comlist = FXCollections.observableArrayList("제목", "작성자");
	ObservableList<Board_detailVO> allBoardData, currentBoardData;
	ArrayList<Board_detailVO> lists;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Table_add_btn.toFront();
		Table_search_btn.toFront();
		Table_search_cb.setItems(comlist);
		System.out.println("후기");
		tableSet();

		Table_search_tf.setPromptText("작성자 입력");
		review_table.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				gotoreviewDetail();
			}
		});
		Table_add_btn.setOnAction(e -> gotoreviewAdd());
		Table_search_btn.setOnAction(e -> findMember());

	}

	private void tableSet() {
		allBoardData = FXCollections.observableArrayList();
		try {
			lists = (ArrayList<Board_detailVO>) Main.rb_a.getBoard_detailAll(); // 목록을 전체 가져옴
			if (lists.isEmpty()) {
				return;
			}
			for (int i = 0; i < lists.size(); i++) {
				allBoardData.add(lists.get(i)); // 목록 전체 길이 구하기
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		Table_review_col1.setCellValueFactory(new PropertyValueFactory<>("board_num"));
		Table_review_col2.setCellValueFactory(new PropertyValueFactory<>("title"));
		Table_review_col3.setCellValueFactory(new PropertyValueFactory<>("custom_id"));
		Table_review_col4.setCellValueFactory(new PropertyValueFactory<>("upd_date"));
		Table_review_col5.setCellValueFactory(new PropertyValueFactory<>("board_cnt")); // 컬럼 설정

		setTable();
	}

	private void setTable() {
		itemsForPage = 9; // 한페이지 보여줄 항목 수 설정
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1; //
		Table_search_page.setPageCount(totPageCount); // 전체 페이지 수 설정

		Table_search_page.setPageFactory(this::createPage); // 페이지 나누기 위한 설정

		review_table.setItems(allBoardData);
	}

	private void findMember() {
		Board_detailVO vo = new Board_detailVO();
		vo.setCustom_id(Table_search_tf.getText());
		if (Table_search_cb.getSelectionModel().isEmpty()) {
			alertInfo("분류를 선택하세요");
			return;
		} else if (Table_search_tf.getText().length() == 0) {
			alertInfo("검색한 값이 없습니다.");
			lists.clear();
			allBoardData.clear();
			allBoardData = FXCollections.observableArrayList();
			try {
				lists = (ArrayList<Board_detailVO>) Main.rb_a.getSearchBoard_detail(vo);
				for (int i = 0; i < lists.size(); i++) {
					allBoardData.add(lists.get(i)); // 목록 전체 길이 구하기
				}
				tableSet();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			Board_detailVO tempVO = new Board_detailVO();
			tempVO.setBc_num("2");
			switch (Table_search_cb.getSelectionModel().getSelectedItem().toString()) {

			case "제목":
				try {
					tempVO.setTitle(Table_search_tf.getText());
					ArrayList<Board_detailVO> searchList = (ArrayList<Board_detailVO>) Main.rb_a
							.getReview_detailTextSearch(tempVO);
					if (searchList.isEmpty()) {
						alertInfo("정보가 없습니다.");
					} else {
						allBoardData.clear();
						allBoardData.addAll(searchList);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "작성자":
				tempVO.setCustom_id(Table_search_tf.getText());
				try {
					ArrayList<Board_detailVO> searchList = (ArrayList<Board_detailVO>) Main.rb_a
							.getReview_detailTextSearch(tempVO);
					if (searchList.isEmpty()) {
						alertInfo("정보가 없습니다.");
					} else {
						allBoardData.clear();
						allBoardData.addAll(searchList);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		setTable();
	}

	private void gotoreviewAdd() {
		if (lists.isEmpty()) {
			Review_board_addController.bc_num = "3";
		} else {
			try {
				lists = (ArrayList<Board_detailVO>) Main.rb_a.getBoard_detailAll(); // 목록을 전체 가져옴
				Board_classVO tempBn = new Board_classVO();
				if (!lists.isEmpty()) {
					tempBn.setBc_num(lists.get(0).getBc_num());
				} else {
					tempBn.setBc_num("1");
				}
				ArrayList<Board_classVO> tempBns = (ArrayList<Board_classVO>) Main.bcs.getSearchBoardClass(tempBn);
				Review_board_addController.board_num = tempBns.get(0).getBc_idx();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			Review_board_addController.bc_num = review_table.getItems().get(0).getBc_num();
		}

		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("review_board_add.fxml"));
		cp.add();
	}

	private void gotoreviewDetail() {
		Review_board_detailController.vo = review_table.getSelectionModel().getSelectedItem();

		Board_detailVO tempVO = Review_board_detailController.vo;
		int cntPlus = Integer.parseInt(tempVO.getBoard_cnt()) + 1;
		tempVO.setBoard_cnt(cntPlus + "");

		Review_board_detailController.vo = tempVO;
		try {
			Main.rb_a.updateBoard_detail(tempVO);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("review_board_detail.fxml"));
		cp.add();
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage; // 전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		review_table.setItems(getTableViewData(from, to));

		return review_table;
	}

	private ObservableList<Board_detailVO> getTableViewData(int from, int to) {

		currentBoardData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = allBoardData.size();
		for (int i = from; i <= to && i < totSize; i++) {

			currentBoardData.add(allBoardData.get(i));
		}

		return currentBoardData;
	}

	public boolean alertConfirm(String ment) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

		alertConfirm.setTitle("확인 창");
		alertConfirm.setContentText(ment);

		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();

		if (confirmResult == ButtonType.OK) {
			return true;
		} else if (confirmResult == ButtonType.CANCEL) {
			return false;
		}
		return false;
	}

	public void alertTest() {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText("ERROR 창 입니다");
		alertErorr.showAndWait();
	}

	public void alertInfo(String temp) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

}
