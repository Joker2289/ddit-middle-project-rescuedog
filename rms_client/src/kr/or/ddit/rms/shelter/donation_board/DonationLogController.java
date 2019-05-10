package kr.or.ddit.rms.shelter.donation_board;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class DonationLogController implements Initializable{

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	@FXML TableView<Spon_LogVO> dona_list_tv;
	@FXML TableColumn<Spon_LogVO, String> dona_list_numCol1;
	@FXML TableColumn<Spon_LogVO, String> dona_list_nameCol2;
	@FXML TableColumn<Spon_LogVO, String> dona_list_priceCol3;
	@FXML TableColumn<Spon_LogVO, String> dona_list_dateCol4;
	@FXML Pagination dona_list_paging;
	
	ObservableList<Spon_LogVO> allBoardData,currentBoardData;
	ArrayList<Spon_LogVO> lists;
	private int from, to, itemsForPage;
	int price;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			lists = (ArrayList<Spon_LogVO>) Main.db_s.getSpon_logAll();
			allBoardData = FXCollections.observableArrayList();

			for (int i = 0; i < lists.size(); i++) {
				if (Login_controller.log_s.getShel_id().equals(lists.get(i).getShel_id())) {
					lists.get(i).setSpon_date(lists.get(i).getSpon_date().substring(0, 10));
				}
				allBoardData.add(lists.get(i));
				price += Integer.parseInt(lists.get(i).getPrice());
				System.out.println(price);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		dona_list_numCol1.setCellValueFactory(new PropertyValueFactory<>("spon_num"));
		dona_list_nameCol2.setCellValueFactory(new PropertyValueFactory<>("custom_id"));
		dona_list_priceCol3.setCellValueFactory(new PropertyValueFactory<>("price"));
		dona_list_dateCol4.setCellValueFactory(new PropertyValueFactory<>("spon_date"));
		dona_list_tv.setItems(allBoardData);	
		
		setTable();
		
		
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
