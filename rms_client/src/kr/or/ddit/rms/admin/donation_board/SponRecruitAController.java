package kr.or.ddit.rms.admin.donation_board;

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
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.SponVO;

public class SponRecruitAController implements Initializable{

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	
	@FXML Pagination Table_paging;
	@FXML JFXButton Table_add_btn;
	@FXML JFXTextField Table_search_tf;
	@FXML JFXButton Table_search_btn;
	@FXML TableView<SponVO> recuruit_table;
	@FXML TableColumn<SponVO, String> recuruit_numCol1;
	@FXML TableColumn<SponVO, String> recuruit_titleCol2;
	@FXML TableColumn<SponVO, String> recuruit_nameCol3;
	@FXML TableColumn<SponVO, String> recuruit_sdateCol4;
	@FXML TableColumn<SponVO, String> recuruit_edateCol5;
	@FXML TableColumn<SponVO, String> recuruit_priceCol6;
	
	@FXML
	Button back;
	public static FXMLLoader loader;
	private int from, to, itemsForPage;
	ObservableList<SponVO> allBoardData, currentBoardData;
	ArrayList<SponVO> lists;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
		recuruit_table.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				gotorecruitDetail();
			}
		});
		
		Table_add_btn.setVisible(false);
		
		Table_search_btn.setOnAction(e -> search());
//		Table_add_btn.setOnAction(e -> addEvent());
	}
	
	private void search() {
		// 검색 값이 없을 경우
		if (Table_search_tf.getText().isEmpty()) {
			alertError("값을 입력하세요");
			return;
		}
		
		// 검색 값이 있을 경우
		if (!Table_search_tf.getText().isEmpty()) {
			String txt = Table_search_tf.getText();
			SponVO vo = new SponVO();
			ShelterVO svo = new ShelterVO();
			svo.setShel_name(txt);
			try {
				String name = Main.sr_s.getShelterName(txt);
				svo.setShel_name(name);
				ShelterVO resVO = Main.fis.getSearchShelter(svo);
				
				
				if(resVO!=null) {
					vo.setShel_id(resVO.getShel_id());	// 보호기관 이름으로 들어가있어서 검색값으로 보호기관 이름을 검색하는 쿼리문 작성해서 검색하면됩니다.
					System.out.println(resVO.getShel_id());
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
				return;
			}
			vo.setTitle(txt);
			lists.clear();
			allBoardData.clear();
			allBoardData = FXCollections.observableArrayList();
			try {
				lists =  (ArrayList<SponVO>) Main.sr_s.getSearchSponText(vo);
				System.out.println(lists.size());
				for (int i = 0; i < lists.size(); i++) {
					System.out.println(lists.get(i).getShel_id());
					svo=new ShelterVO();
					svo.setShel_id(lists.get(i).getShel_id());
					ShelterVO name = (ShelterVO) Main.fis.getSearchShelter(svo);
					lists.get(i).setShel_name(name.getShel_name());
					allBoardData.add(lists.get(i));
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			setTable();
		}
		
	}

	/*private void addEvent() {
		try {
			loadPane=FXMLLoader.load(getClass().getResource("spon_recruit.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		servicePane.getChildren().remove(tempPane);
		servicePane.getChildren().add(loadPane);
		
	}*/

	private void gotorecruitDetail() {
		SponRecruitDetailAController.vo = recuruit_table.getSelectionModel().getSelectedItem();
		
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("spon_recruitDetailA.fxml"));
		cp.add();
		
	}


	private void init() {
		ShelterVO svo = new ShelterVO();
		allBoardData = FXCollections.observableArrayList();
		try {
			lists = (ArrayList<SponVO>) Main.sr_a.getSponAll();
			for (int i = 0; i < lists.size(); i++) {
				svo.setShel_id(lists.get(i).getShel_id());
				ShelterVO name = null;
				try {
					name = (ShelterVO) Main.fis.getSearchShelter(svo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				lists.get(i).setShel_name(name.getShel_name());
				lists.get(i).setUpd_date(lists.get(i).getUpd_date().substring(0, 10));
				lists.get(i).setLast_date(lists.get(i).getLast_date().substring(0, 10));
				allBoardData.add(lists.get(i));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

		recuruit_numCol1.setCellValueFactory(new PropertyValueFactory<>("spon_num"));
		recuruit_titleCol2.setCellValueFactory(new PropertyValueFactory<>("title"));
		recuruit_nameCol3.setCellValueFactory(new PropertyValueFactory<>("shel_name"));
		recuruit_sdateCol4.setCellValueFactory(new PropertyValueFactory<>("upd_date"));
		recuruit_edateCol5.setCellValueFactory(new PropertyValueFactory<>("last_date"));
		recuruit_priceCol6.setCellValueFactory(new PropertyValueFactory<>("money"));

		setTable();
	}
	
	private void setTable() {
		itemsForPage = 5;
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1;
		Table_paging.setPageCount(totPageCount);
		Table_paging.setPageFactory(this::createPage);

		recuruit_table.setItems(allBoardData);
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage; // 전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		recuruit_table.setItems(getTableViewData(from, to));

		return recuruit_table;
	}

	private ObservableList<SponVO> getTableViewData(int from, int to) {

		currentBoardData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = allBoardData.size();
		for (int i = from; i <= to && i < totSize; i++) {

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
