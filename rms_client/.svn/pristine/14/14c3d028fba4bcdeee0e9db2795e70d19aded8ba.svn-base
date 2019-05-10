package kr.or.ddit.rms.admin.member_management;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.MemberVO;

public class Member_managementController implements Initializable {
	@FXML JFXTextField search_tf;
	@FXML JFXButton search_btn;
	@FXML TableView<MemberVO> mem_tv;
	@FXML TableColumn<MemberVO,String> mem_tv_col1;
	@FXML TableColumn<MemberVO,String> mem_tv_col2;
	@FXML TableColumn<MemberVO,String> mem_tv_col3;
	@FXML JFXButton gotoblack_btn;
	@FXML JFXButton ref_btn;
	@FXML Pagination black_pg;
	@FXML JFXButton memDel_btn;
	@FXML JFXButton reportDel_btn;
	@FXML TableView<BlacklistVO> blacklist_tv;
	@FXML TableColumn<BlacklistVO,String> black_tv_col1;
	@FXML TableColumn<BlacklistVO,String> black_tv_col2;
	@FXML TableColumn<BlacklistVO,String> black_tv_col3;
	
	ObservableList<MemberVO> allMemList,curMemList ;
	ObservableList<BlacklistVO> allBlList, curBlList;
	
	ArrayList<MemberVO> memAList;
	ArrayList<BlacklistVO> blAList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemSet();
		
		gotoblack_btn.setOnAction(e->gotoblack());
		memDel_btn.setOnAction(e->memDel());
		reportDel_btn.setOnAction(e->reportDel());
		
	}

	private void itemSet() {
		try {
			memAList=(ArrayList<MemberVO>) Main.mems.getMemberAll();
			allMemList= FXCollections.observableArrayList(memAList);
			blAList = (ArrayList<BlacklistVO>) Main.bs.getBlacklistAll();
			allBlList =  FXCollections.observableArrayList(blAList);
			setTable();
			mem_tv.setItems(allMemList);
			blacklist_tv.setItems(allBlList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void gotoblack() {
		BlacklistVO gotoB = new BlacklistVO();
		gotoB.setMem_id(mem_tv.getSelectionModel().getSelectedItem().getMem_id());
		gotoB.setReason("관리자");
		try {
			Main.bs.insertBlacklist(gotoB);
			alertInfo("블랙리스트 등록 성공");
			itemSet();
			setTable();
		} catch (RemoteException e) {
			System.out.println("안들어감 ㅠ");
		}
		
	}

	private void reportDel() {
		try {
			BlacklistVO vo = new BlacklistVO();
			vo.setMem_id(blacklist_tv.getSelectionModel().getSelectedItem().getMem_id());
			vo.setAdd_date(blacklist_tv.getSelectionModel().getSelectedItem().getAdd_date());
			Main.bs.deleteBlacklist(vo);
			alertInfo("삭제 됨");
			itemSet();
			setTable();
			
		} catch (RemoteException e) {
			System.out.println("게시글 삭제 실패");
		}
	}

	private void memDel() {
		MemberVO vo = new MemberVO();
		vo.setMem_id(blacklist_tv.getSelectionModel().getSelectedItem().getMem_id());
		boolean flag = alertConfirm("정말 삭제하십니까?");
		if(flag) {
			try {
				Main.mems.deleteMember(vo);
				itemSet();
				setTable();
			} catch (RemoteException e) {
				System.out.println("멤버삭제 실패");
			}
		}
	}

	private void setTable() {
		mem_tv_col1.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		mem_tv_col2.setCellValueFactory(new PropertyValueFactory<>("mem_author"));
		mem_tv_col3.setCellValueFactory(new PropertyValueFactory<>("mem_report"));
		
		
		black_tv_col1.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		black_tv_col2.setCellValueFactory(new PropertyValueFactory<>("reason"));
		black_tv_col3.setCellValueFactory(new PropertyValueFactory<>("add_date"));
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
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("정보");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
}
