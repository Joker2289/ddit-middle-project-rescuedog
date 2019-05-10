package kr.or.ddit.rms.user.mypage.buylist;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.user.mypage.activeList.Active_controller;
import kr.or.ddit.rms.vo.Buy_LogVO;

public class BuyListController implements Initializable {
	public static Stage dialog;
	public static Buy_LogVO vo;

	@FXML Tab mypage_order_tab1;
	@FXML Pagination mypage_tb_page1;
	@FXML TableView<Buy_LogVO> mypage_order_table;
	@FXML TableColumn<Buy_LogVO, String> tb_buynum_col1;
	@FXML TableColumn<Buy_LogVO, String> tb_date_col2;
	@FXML TableColumn<Buy_LogVO, String> tb_info_col3;
	@FXML TableColumn<Buy_LogVO, String> tb_amount_col4;
	@FXML TableColumn<Buy_LogVO, String> tb_price_col5;
	@FXML DatePicker mypage_date1;
	@FXML JFXButton mypage_search_btn1;
	@FXML JFXButton mypage_point_btn1;
	@FXML DatePicker mypage_date2;
	
	@FXML JFXButton back;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	@FXML
	JFXButton mypage_back_btn;
	
	private int from, to, itemsForPage;
	
	ObservableList<Buy_LogVO> allBuydData, currentBuyData, loginData;
	ArrayList<Buy_LogVO> lists;
	ArrayList<Buy_LogVO> listTo; // custom_id
	ObservableList<Buy_LogVO> pre_date1, post_date1;

	public static int all_price = 0; // 총 금액
	public static int list_size = 0;
	
	public void initialize(URL location, ResourceBundle resources) {
		mypage_back_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(Active_controller.class.getResource("activeList_main.fxml"));
				cp.add();
			}
		});
		
		vo = new Buy_LogVO();
		vo.setCustom_id(Login_controller.log_c.getCustom_id()); 
		dateSearch();
		tableSet();
		
		mypage_point_btn1.setOnAction(e->myPoint());
		
		//주문번호 클릭 시 주문상세보기뜨고 주문 취소 버튼 생성
		mypage_order_table.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				buyDetail();
			}
		});
	}
	
	private void tableSet() {
		// 목록을 전체 가져옴
		allBuydData = FXCollections.observableArrayList();
		try {
			lists = (ArrayList<Buy_LogVO>) Main.buy.getBuy_logAll(); // 목록을 전체 가져옴
			for (int i = 0; i < lists.size(); i++) {
				allBuydData.add(lists.get(i)); // 목록 전체 길이 구하기
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		getbuylist();
		setTable();
	}
	private void setTable() {
		itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
		int totPageCount = loginData.size() % itemsForPage == 0 ? loginData.size() / itemsForPage
				: loginData.size() / itemsForPage + 1; //
		mypage_tb_page1.setPageCount(totPageCount); // 전체 페이지 수 설정
		mypage_tb_page1.setPageFactory(this::createPage); // 페이지 나누기 위한 설정
	}
	
	//로그인한 사람의 구매기록 가져오기
	private void getbuylist() {
		try { // 로그인한 회원의 구매내역가져오기
			listTo = (ArrayList<Buy_LogVO>) Main.buy.getBuyTo(vo.getCustom_id());
			loginData = FXCollections.observableArrayList(listTo);
			if(lists.isEmpty()) {
				return;
			}
			for (int i = 0; i < lists.size(); i++) {
				allBuydData.add(lists.get(i)); //목록 전체 길이 구하기
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		tb_buynum_col1.setCellValueFactory(new PropertyValueFactory<>("buy_num"));
		tb_date_col2.setCellValueFactory(new PropertyValueFactory<>("buy_date"));
		tb_info_col3.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		tb_amount_col4.setCellValueFactory(new PropertyValueFactory<>("buy_quan"));
		tb_price_col5.setCellValueFactory(new PropertyValueFactory<>("all_price"));
		
		mypage_order_table.setItems(loginData);

	}
	//구매 상세 정보 & 구매 취소
	private void buyDetail(){
		
		OrderDetailController.vo = mypage_order_table.getSelectionModel().getSelectedItem();
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("orderDetail.fxml"));
		cp.add();
	}

	//날짜검색
	private void dateSearch() {
	
		Buy_LogVO bvo = new Buy_LogVO();
		bvo.setCustom_id(Login_controller.log_c.getCustom_id());
	      mypage_date1.setOnAction(event -> {
	         if(mypage_date1.getValue()!=null) {
	             LocalDate date = mypage_date1.getValue();
	             String preDate = date.toString().replaceAll("-","/");
	             preDate = preDate.substring(2,preDate.length());
	             bvo.setPre_date(preDate);
	             System.out.println( mypage_date1.getValue());
	         }
	          mypage_date2.setOnAction(event1 -> {
	             if(mypage_date2.getValue()!=null) {
	                LocalDate date1 = mypage_date2.getValue();
	                String postDate = date1.toString().replaceAll("-","/");
	                postDate = postDate.substring(2,postDate.length());
	                bvo.setPost_date(postDate);
	                System.out.println( mypage_date2.getValue());
	             }             
	         mypage_search_btn1.setOnAction(e->{
	             if(mypage_date1.getValue() == null) {
	                alertTest("기준날짜를 선택하세요 ");
	                
	             }else if(mypage_date2.getValue() == null) {
	                alertTest("기준날짜를 선택하세요 ");
	          
	             }else{
	                lists.clear();
	                try {
	                  lists = (ArrayList<Buy_LogVO>) Main.buy.getBetweenProd(bvo);
	                  loginData = FXCollections.observableArrayList(lists);
	                  setTable();
	               } catch (RemoteException e1) {
	                  e1.printStackTrace();
					}

				}
			});
		});

	});
}
	 
	private void myPoint() {
		dialog = new Stage();

		// 새창 띄우기
		dialog = new Stage(StageStyle.UTILITY);

		dialog.initModality(Modality.APPLICATION_MODAL);
		
		dialog.setTitle("적립금");
		Parent parent = null;
		try {
			parent=FXMLLoader.load(getClass().getResource("mypoint.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		
		dialog.setScene(scene);
		dialog.setResizable(false);//크기고정
		dialog.show();
	}
		
//페이징 메서드
	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage; // 전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		mypage_order_table.setItems(getTableViewData(from, to));

		return mypage_order_table;
	}

	private ObservableList<Buy_LogVO> getTableViewData(int from, int to) {

		currentBuyData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = loginData.size();
		for (int i = from; i <= to && i < totSize; i++) {

			currentBuyData.add(loginData.get(i));
			
		}

		return currentBuyData;
	}
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("alert");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();

	}


}