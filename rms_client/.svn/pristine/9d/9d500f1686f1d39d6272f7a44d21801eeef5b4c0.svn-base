package kr.or.ddit.rms.user.mypage.activeList;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationListController implements Initializable{

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	@FXML TableView<Spon_LogVO> dona_list_tv;
	@FXML TableColumn<Spon_LogVO, String> dona_list_numCol1;
	@FXML TableColumn<Spon_LogVO, String> dona_list_nameCol2;
	@FXML TableColumn<Spon_LogVO, String> dona_list_priceCol3;
	@FXML TableColumn<Spon_LogVO, String> dona_list_dateCol4;
	@FXML Pagination dona_list_paging;
	@FXML JFXButton mypage_back_btn;
	
	ObservableList<Spon_LogVO> allBoardData,currentBoardData;
	ArrayList<Spon_LogVO> lists;
	private int from, to, itemsForPage;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mypage_back_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("activeList_main.fxml"));
				cp.add();
			}
		});
		System.out.println("상세보기 화면 이동");
		listSet();
		
		dona_list_numCol1.setCellValueFactory(new PropertyValueFactory<>("spon_num"));
		dona_list_nameCol2.setCellValueFactory(new PropertyValueFactory<>("shel_name"));
		dona_list_priceCol3.setCellValueFactory(new PropertyValueFactory<>("price"));
		dona_list_dateCol4.setCellValueFactory(new PropertyValueFactory<>("spon_date"));
		dona_list_tv.setItems(allBoardData);	
		
		setTable();
	}

	private void listSet() {
		Spon_LogVO vo = new Spon_LogVO();//로그인 한 아이디 담을 vo
		Spon_LogVO tvo = new Spon_LogVO();//로그인 + 보호기관 담은 vo
		
		try {
			vo.setCustom_id(Login_controller.log_c.getCustom_id());
			List<ShelterVO> slist = Main.al.getSpon_log_shelname(vo);
			System.out.println(slist.size());
			lists = (ArrayList<Spon_LogVO>) Main.al.getSearchSpon_log(tvo);
			
			allBoardData = FXCollections.observableArrayList();
			
			for(int i = 0; i < lists.size(); i++) {
				for (int j = 0; j < slist.size(); j++) {
					if(slist.get(j).getShel_id().equals(lists.get(i).getShel_id())&&
							lists.get(i).getCustom_id().equals(Login_controller.log_c.getCustom_id())) {
						lists.get(i).setShel_name(slist.get(j).getShel_name());
						String date = lists.get(i).getSpon_date().substring(0, 10);
						lists.get(i).setSpon_date(date);
						allBoardData.add(lists.get(i));
					}
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	private void setTable() {
		itemsForPage = 5;
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage : allBoardData.size() / itemsForPage + 1;
		dona_list_paging.setPageCount(totPageCount);
		dona_list_paging.setPageFactory(this::createPage); // 페이지 나누기 위한 설정
		
		dona_list_tv.setItems(allBoardData);
		
	}
	
	private Node createPage(int pageIndex){
		
		from = pageIndex * itemsForPage; //전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		dona_list_tv.setItems(getTableViewData(from, to));
		
		return dona_list_tv;
	}
	
	private ObservableList<Spon_LogVO> getTableViewData(int from, int to){
		
		currentBoardData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = allBoardData.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentBoardData.add(allBoardData.get(i));
		}
		
		return currentBoardData;
	}
	

}
