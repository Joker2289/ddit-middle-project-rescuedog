package kr.or.ddit.rms.admin.volunteer_board;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.user.volunteer_log.VolunteerLog_Controller;
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public class VolunteerBoard_Controller implements Initializable {
	@FXML
	JFXButton Layout_logout_btn;
	@FXML
	JFXButton Layout_mypage_btn;
	@FXML
	JFXButton Layout_memCenter_btn;
	@FXML
	JFXButton Layout_select_btn;
	@FXML
	Label layout_name_label;
	@FXML
	JFXButton layout_logo_btn;
	@FXML
	ImageView Layout_logo_img;
	@FXML
	JFXButton Layout_rd_btn;
	@FXML
	JFXButton Layout_protect_btn;
	@FXML
	JFXButton Layout_adopt_btn;
	@FXML
	JFXButton Layout_shop_btn;
	@FXML
	JFXButton Layout_spon_btn;
	@FXML
	JFXButton Layout_vb_btn;
	@FXML
	JFXButton Layout_comu_btn;
	@FXML
	JFXButton VolunteerBoard_btn;
	@FXML
	JFXButton Volunteer_Register_btn;
	@FXML
	JFXButton VolunteerSerch_btn;
	@FXML
	JFXButton Board_List_Btn;
	@FXML
	JFXButton Register_Btn;
	@FXML
	TableColumn<Volunteer_BoardVO, String> VolunteerBoard_No;
	@FXML
	TableColumn<Volunteer_BoardVO, String> VolunteerBoard_Title;
	@FXML
	TableColumn<Volunteer_BoardVO, String> VolunteerBoard_Writer;
	@FXML
	TableColumn<Volunteer_BoardVO, String> VolunteerBoard_Date;
	@FXML
	TableColumn<Volunteer_BoardVO, String> VolunteerBoard_Lookup;
	@FXML
	JFXTextField VolunteerSerch_fd;
	@FXML
	JFXButton RegisterV_Btn;
	@FXML
	JFXTextArea Board_Content;

	@FXML
	Pagination Table_search_page;
	private int from, to, itemsForPage;
	ObservableList<Volunteer_BoardVO> allBoardData, currentBoardData;
	@FXML
	TableView<Volunteer_BoardVO> VolunteerBoard_tb;
	TableView<Volunteer_BoardVO> VolunteerLog_tb;
	ObservableList<Volunteer_BoardVO> dataList = FXCollections.observableArrayList();
	ObservableList<Volunteer_BoardVO> dataList2 = FXCollections.observableArrayList();
	static ObservableList<Volunteer_BoardVO> AlldataList = FXCollections.observableArrayList();
	List<Volunteer_BoardVO> viewList = FXCollections.observableArrayList();
	@FXML
	Label Writer_lb;
	@FXML
	Label Title_lb;
	@FXML
	Label Date_lb;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
int count;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableSet();

	
	

		 VolunteerSerch_btn.setOnAction(new EventHandler<ActionEvent>() {
		
		 @Override // 검색기능 추가
		 public void handle(ActionEvent event) {
		 Volunteer_BoardVO vo = new Volunteer_BoardVO();
		 try {
		 vo.setShel_id(VolunteerSerch_fd.getText());
		 vo.setVb_title(VolunteerSerch_fd.getText());
		 System.out.println(VolunteerSerch_fd.getText());
		
		 viewList = Main.vbas.getAllBoard_SerchList(vo);
		 System.out.println(viewList.size());
		 } catch (RemoteException e) {
		 e.printStackTrace();
		 }
		 if (VolunteerSerch_fd.getText().equals("")) {
		 VolunteerSerch_fd.requestFocus();
		 errMsg("에러", "검색결과 없습니다.");
		
		 } else {
		
		 VolunteerBoard_tb.getItems().setAll(viewList);
		 }
		
		 }
		 });
		
		VolunteerBoard_tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				count++;
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) { 
						Stage WritePage = new Stage(StageStyle.UTILITY);
						WritePage.initModality(Modality.APPLICATION_MODAL);

						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("Volunteerboard_Registerdetail.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						Label Up_Writer_lb = (Label) parent.lookup("#Up_Writer_lb");
						Label Date_lb = (Label) parent.lookup("#Date_lb");
						Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
						Label sumpeople_lb = (Label) parent.lookup("#sumpeople_lb");
						JFXTextField Up_Vb_Title_fd = (JFXTextField) parent.lookup("#Up_Vb_Title_fd");
						Label Up_Vb_CntPeople_lb = (Label) parent.lookup("#Up_Vb_CntPeople_lb");
						Label Page_No = (Label) parent.lookup("#Page_No");
						JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
						JFXButton Delete_Btn = (JFXButton) parent.lookup("#Delete_Btn");
						JFXTextArea Up_Content = (JFXTextArea) parent.lookup("#Up_Content");
						Page_No.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						sumpeople_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople()));
						LookUp_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cnt()));
						Up_Writer_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id()));
						Up_Vb_Title_fd.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_title()));
						Date_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_date());
						Up_Content.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_content()));
						Up_Vb_CntPeople_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());

						Scene scene = new Scene(parent);
						WritePage.setTitle("게시판");
						WritePage.setScene(scene);
						WritePage.show();

						Board_List_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								WritePage.close();
							}
						});
				
						Delete_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								Volunteer_BoardVO vo = new Volunteer_BoardVO();
								vo.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
								String serchLog;
								List<Volunteer_LogVO> listLog;
								try {
									listLog = Main.vlss.getAllBoardList();
									for (int i = 0; i < listLog.size(); i++) {
										serchLog = listLog.get(i).getVb_num();
										if (vo.getVb_num().equals(serchLog)) {
											errMsg("경고", "등록된 자료가 있습니다.");
											return;
										}
									}

								} catch (RemoteException e1) {
									e1.printStackTrace();
								}


									
									try {

										Main.vbas.deleteBoard(vo);
										infoMsg("게시글이 삭제되었습니다.", "게시글 삭제");
										VolunteerBoard_tb.getItems().setAll(viewList);
										WritePage.close();
										return;
									} catch (RemoteException e) {
										System.out.println("관련된 게시글이 남아있습니다.");
										e.printStackTrace();
									}
							
								
								
							}
						});



					}
				}
			}
		});

	}// 이니셜 라이저블 끝
	


	private void tableSet() {
		allBoardData = FXCollections.observableArrayList();
		try {
			viewList = (ArrayList<Volunteer_BoardVO>) Main.vbus.getAllBoardList(); // 목록을 전체 가져옴
			 // 목록 전체 길이 구하기
				if(viewList.isEmpty()) {
					return;
				}
					for (int i = 0; i < viewList.size(); i++) {
						allBoardData.add(viewList.get(i));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}VolunteerBoard_No.setCellValueFactory(new PropertyValueFactory<>("vb_num"));
		VolunteerBoard_Title.setCellValueFactory(new PropertyValueFactory<>("vb_title"));
		VolunteerBoard_Writer.setCellValueFactory(new PropertyValueFactory<>("shel_id"));
		VolunteerBoard_Date.setCellValueFactory(new PropertyValueFactory<>("vb_date"));
		VolunteerBoard_Lookup.setCellValueFactory(new PropertyValueFactory<>("vb_cnt"));
		setTable();
	}

	private ObservableList<Volunteer_BoardVO> getTableViewData(int from, int to) {

		currentBoardData= FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = allBoardData.size();
		for (int i = from; i <= to && i < totSize; i++) {

			currentBoardData.add(allBoardData.get(i));
		}

		return currentBoardData;
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage; // 전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		VolunteerBoard_tb.setItems(getTableViewData(from, to));

		return VolunteerBoard_tb;
	}

	private void setTable() {
		itemsForPage = 9; // 한페이지 보여줄 항목 수 설정
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1; //
		Table_search_page.setPageCount(totPageCount); // 전체 페이지 수 설정

		Table_search_page.setPageFactory(this::createPage); // 페이지 나누기 위한 설정

		VolunteerBoard_tb.setItems(allBoardData);
	}

	public void errMsg(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	// 확인메세지창
	public void infoMsg(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
