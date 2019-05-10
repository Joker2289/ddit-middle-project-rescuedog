package kr.or.ddit.rms.user.mypage.activeList;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.user.volunteer_board.VolunteerBoard_Controller;
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class VolunteerLog_Controller implements Initializable {
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
	JFXButton VolunteerBoardPg_btn;
	@FXML
	JFXButton Volunteer_RegisterPg_btn;
	@FXML
	JFXButton Log_Register_Btn;
	@FXML
	JFXButton Table_add_btn;
	@FXML
	JFXTextField Log_Title_fd;
	@FXML
	JFXTextField VolunteerSerch_fd;

	@FXML
	JFXTextArea Log_Contetn_ct;
	@FXML
	JFXButton Log_Cancle_Btn;
	@FXML
	JFXButton VolunteerlogSerch_Btn;

	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_No;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Title;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Writer;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Date;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Check;
	@FXML
	Pagination Table_search_page;
	@FXML
	TableView<Volunteer_LogVO> VolunteerLog_tb;
	
	@FXML JFXButton mypage_back_btn;
	
	ObservableList<Volunteer_LogVO> allBoardData, currentBoardData;
	private int from, to, itemsForPage;
	ObservableList<Volunteer_LogVO> dataList = FXCollections.observableArrayList();
	ObservableList<Volunteer_LogVO> dataList2 = FXCollections.observableArrayList();
	ObservableList<Volunteer_BoardVO> BoarddataList = FXCollections.observableArrayList();
	List<Volunteer_LogVO> viewList = FXCollections.observableArrayList();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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
		 tableSet();
		VolunteerlogSerch_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override // 검색기능 활성화(작성자으로만 검색가능)
			public void handle(ActionEvent event) {
				Volunteer_LogVO vo = new Volunteer_LogVO();
				vo.setVl_title(VolunteerSerch_fd.getText());
				try {
					

					viewList = Main.vlus.volunteerlog_serch(vo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (VolunteerSerch_fd.getText().equals("")) {
					VolunteerSerch_fd.requestFocus();
					errMsg("에러.", "검색결과가 없습니다.");

				} else {

					VolunteerLog_tb.getItems().setAll(viewList);
				}

			}
		});
		VolunteerLog_tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						System.out.println(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
						Stage WritePage = new Stage(StageStyle.UTILITY);
						WritePage.initModality(Modality.APPLICATION_MODAL);

						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("VolunteerLog_Registerdetail.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						Label Writer_lb = (Label) parent.lookup("#Writer_lb");
						Label Writer_lb2 = (Label) parent.lookup("#Writer_lb2");
						Label Date_lb = (Label) parent.lookup("#Date_lb");
						Label Page_No = (Label) parent.lookup("#Page_No");
						Label Title_lb = (Label) parent.lookup("#Title_lb");
						Label sumpeople_lb = (Label) parent.lookup("#sumpeople_lb");
						Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
						Label people_lb = (Label) parent.lookup("#people_lb");
						JFXButton Cancle_Btn = (JFXButton) parent.lookup("#Cancle_Btn");
						JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
						JFXTextArea Board_Content = (JFXTextArea) parent.lookup("#Board_Content");
						// // No_lbWriter_lb
						Page_No.setText((VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num()));
						Writer_lb.setText((VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id()));
						Date_lb.setText(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_date());

						Volunteer_LogVO selectItem = VolunteerLog_tb.getSelectionModel().getSelectedItem();
						Volunteer_BoardVO vbVO = new Volunteer_BoardVO();
						vbVO.setVb_num(selectItem.getVb_num());
						try {
							List<Volunteer_BoardVO> list = Main.vbus.getAllBoard_SerchList(vbVO);

							Title_lb.setText(list.get(0).getVb_title());
							sumpeople_lb.setText(list.get(0).getVb_sumpeople());
							Writer_lb2.setText(list.get(0).getShel_id());
							Board_Content.setText(list.get(0).getVb_content());
							LookUp_lb.setText(list.get(0).getVb_cnt());
							people_lb.setText(list.get(0).getVb_cntpeople());
							Board_Content.setEditable(false);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						if (!Writer_lb.getText().equals(Login_controller.log_c.getCustom_id())) {
							Cancle_Btn.setVisible(false);
							Board_Content.setEditable(false);

						}
						;

						Cancle_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								

								Volunteer_LogVO vo = new Volunteer_LogVO();
								Volunteer_BoardVO vbo = new Volunteer_BoardVO();
								vo.setVb_num(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
								vo.setCustom_id(Login_controller.log_c.getCustom_id());
								if (people_lb.getText()==null) {
									people_lb.setText("0");
									vbo.setVb_cntpeople(people_lb.getText());

								} else {
									int peoplecount = Integer.parseInt(people_lb.getText()) - 1;
									vbo.setVb_cntpeople(peoplecount + "");

								}
								vbo.setVb_num(selectItem.getVb_num());
								vbo.setVb_title(Title_lb.getText());
								vbo.setVb_content(Board_Content.getText());
								vbo.setShel_id(Writer_lb2.getText());
								vbo.setVb_sumpeople(sumpeople_lb.getText());
								vbo.setVb_date(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_date());
								vbo.setVb_cnt(LookUp_lb.getText());
								System.out.println(vbo.getVb_num());

								try {
									// List<Volunteer_BoardVO> list =Main.vbus.getAllBoard_SerchList(vbo);
									Main.vbus.updateVolunteer_board(vbo);

								} catch (Exception e1) {
									e1.printStackTrace();
								}

								try {

									Main.vlus.deleteVolunteer_log(vo);
									infoMsg("신청취소", "신청이 취소되었습니다.");
									VolunteerLog_tb.getItems().setAll(viewList);
									WritePage.close();
									tableSet();
									return;
								} catch (RemoteException e) {
									e.printStackTrace();
								}

							}
						});

						Board_List_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// VolunteerLog_tb.getItems().setAll(viewList);
//								tableSet();
								WritePage.close();
							}
						});

						Scene scene = new Scene(parent);
						WritePage.setTitle("게시판");
						WritePage.setScene(scene);
						WritePage.show();
					}
				}
			}

		});

	}// 이니셜 라이저블 끝

	private void tableSet() {
		allBoardData = FXCollections.observableArrayList();
		Volunteer_LogVO serchVO = new Volunteer_LogVO();
		serchVO.setCustom_id(Login_controller.log_c.getCustom_id());
		try {
			viewList = (ArrayList<Volunteer_LogVO>) Main.vlus.getAllBoard_SerchList(serchVO); // 목록을 전체 가져옴
			 // 목록 전체 길이 구하기
				if(viewList.isEmpty()) {
					return;
				}
					for (int i = 0; i < viewList.size(); i++) {
						allBoardData.add(viewList.get(i));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}VolunteerLog_No.setCellValueFactory(new PropertyValueFactory<>("vb_num"));
		VolunteerLog_Writer.setCellValueFactory(new PropertyValueFactory<>("custom_id"));
		VolunteerLog_Date.setCellValueFactory(new PropertyValueFactory<>("vl_date"));
		VolunteerLog_Check.setCellValueFactory(new PropertyValueFactory<>("vl_check"));
		VolunteerLog_Title.setCellValueFactory(new PropertyValueFactory<>("vl_title"));
		setTable();
	}

	private ObservableList<Volunteer_LogVO> getTableViewData(int from, int to) {

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
		VolunteerLog_tb.setItems(getTableViewData(from, to));

		return VolunteerLog_tb;
	}

	private void setTable() {
		itemsForPage = 9; // 한페이지 보여줄 항목 수 설정
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1; //
		Table_search_page.setPageCount(totPageCount); // 전체 페이지 수 설정

		Table_search_page.setPageFactory(this::createPage); // 페이지 나누기 위한 설정

		VolunteerLog_tb.setItems(allBoardData);
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
