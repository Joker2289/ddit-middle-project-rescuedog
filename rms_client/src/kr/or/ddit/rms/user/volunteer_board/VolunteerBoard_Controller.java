package kr.or.ddit.rms.user.volunteer_board;

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
import javafx.scene.control.cell.PropertyValueFactory;
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
import kr.or.ddit.rms.user.free_board.Free_board_detailUController;
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
	JFXTextArea Board_Content;

	@FXML
	Pagination Table_search_page;
	private int from, to, itemsForPage;
	@FXML
	ObservableList<Volunteer_BoardVO> allBoardData, currentBoardData;
	TableView<Volunteer_BoardVO> VolunteerLog_tb;
	ObservableList<Volunteer_BoardVO> dataList = FXCollections.observableArrayList();
	ObservableList<Volunteer_BoardVO> dataList2 = FXCollections.observableArrayList();
	List<Volunteer_BoardVO> viewList = FXCollections.observableArrayList();
	List<Volunteer_LogVO> serchList = FXCollections.observableArrayList();
	@FXML
	Label Writer_lb;
	@FXML
	Label Title_lb;
	@FXML
	Label Date_lb;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	int count;
	@FXML TableView<Volunteer_BoardVO> VolunteerBoard_tb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableSet();
		VolunteerSerch_btn.setOnAction(new EventHandler<ActionEvent>() {
			

			@Override // 검색기능 활성화(작성자으로만 검색가능)
			public void handle(ActionEvent event) {
				Volunteer_BoardVO vo = new Volunteer_BoardVO();
				try {
					vo.setShel_id(VolunteerSerch_fd.getText());
					vo.setVb_title(VolunteerSerch_fd.getText());
					System.out.println(VolunteerSerch_fd.getText());

					viewList = Main.vbus.getSerchList(vo);
					System.out.println(viewList.size());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (VolunteerSerch_fd.getText().equals("")) {
					VolunteerSerch_fd.requestFocus();
					errMsg("응없어", "없슴");

				} else {

					VolunteerBoard_tb.getItems().setAll(viewList);
				}

			}
		});

		VolunteerBoard_tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						Stage WritePage = new Stage(StageStyle.UTILITY);
						WritePage.initModality(Modality.APPLICATION_MODAL);
						count++;
						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("Volunteerboard_Registerdetail.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						List<Volunteer_LogVO> listLog;  
						String serchLog;
						Volunteer_LogVO chVO = new Volunteer_LogVO();
						chVO.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						chVO.setCustom_id(Login_controller.log_c.getCustom_id());
						try {
							listLog = Main.vlus.getAllBoard_SerchList(chVO);
							for (int i = 0; i < listLog.size(); i++) {
								System.out.println(listLog.size());
								serchLog = listLog.get(i).getVb_num();
								if (chVO.getVb_num().equals(serchLog)) {
									infoMsg("알림", "신청된 자료가 있습니다.");
									
								}
							}
								

						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						

						Label Writer_lb = (Label) parent.lookup("#Writer_lb");
						Label Title_lb = (Label) parent.lookup("#Title_lb");
						Label Date_lb = (Label) parent.lookup("#Date_lb");
						Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
						Label Page_No = (Label) parent.lookup("#Page_No");
						Label people_lb = (Label) parent.lookup("#people_lb");
						Label sumpeople_lb = (Label) parent.lookup("#sumpeople_lb");
						JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
						JFXButton Register_Btn = (JFXButton) parent.lookup("#Register_Btn");
						JFXTextArea Board_Content = (JFXTextArea) parent.lookup("#Board_Content");
						ImageView dog_img = (ImageView) parent.lookup("#dog_img");
						Writer_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id()));
						Title_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_title()));
						Date_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_date());
						Page_No.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						Board_Content.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_content()));
						people_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());
						sumpeople_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
//						sumpeople_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
						Volunteer_BoardVO updateVO = new Volunteer_BoardVO();// 조회수 증가
						int count = Integer
								.parseInt(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cnt());
						Image dog = new Image("kr/or/ddit/rms/user/volunteer_board/mainpage_icon1.jpeg");
//						dog_img.setImage(dog);
						count++;
						updateVO.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						updateVO.setVb_title(Title_lb.getText());
						updateVO.setVb_date(Date_lb.getText());
						updateVO.setVb_content(Board_Content.getText());
						updateVO.setShel_id(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id());
						updateVO.setVb_cnt(count + "");
						updateVO.setVb_cntpeople(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());
						updateVO.setVb_sumpeople(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
						int selectItem = VolunteerBoard_tb.getSelectionModel().getSelectedIndex();

						try {
							Main.vbss.updateBoard(updateVO);
							LookUp_lb.setText(Main.vbss.getAllBoardList().get(selectItem).getVb_cnt());
							// LookUp_lb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cnt());
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}

				

						Scene scene = new Scene(parent);
						scene.getStylesheets().add(getClass().getResource("app.css").toString());
						WritePage.setTitle("게시판");
						WritePage.setScene(scene);
						WritePage.show();

						Board_List_Btn.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								tableSet();
								WritePage.close();
							}

						});

						Register_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
//								ChangePane cp = Main_page_controller.fxmlLoad.getController();
//								cp.clearing();
//								ChangePane.changePane(getClass().getResource("Volunteerboard_SelectPage.fxml"));
//								cp.add();

								Calendar cal = Calendar.getInstance();
								Volunteer_BoardVO updateVO = new Volunteer_BoardVO();
								Volunteer_BoardVO updateVO2 = new Volunteer_BoardVO();
								Volunteer_LogVO insertvo = new Volunteer_LogVO();
								String serchName;
								String serchNum;
								System.out.println(VolunteerBoard_tb.getSelectionModel().getSelectedItems());
								insertvo.setVb_num(Page_No.getText());
								insertvo.setCustom_id(Login_controller.log_c.getCustom_id());
								insertvo.setVl_date(format.format(cal.getTime()));
								insertvo.setVl_check("승인대기중");
								insertvo.setVl_title(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_title());
								System.out.println(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_title()+"@@@@@@@@@@@@@@@@@@@");
								int peoplecount = Integer.parseInt(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople())+1;
								updateVO.setVb_cntpeople(String.valueOf(peoplecount));
								
								updateVO.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
								updateVO.setVb_title(Title_lb.getText());
								System.out.println(Title_lb.getText()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
								updateVO.setVb_date(Date_lb.getText());
								updateVO.setVb_content(Board_Content.getText());
								updateVO.setShel_id(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id());
								updateVO.setVb_cnt(LookUp_lb.getText());
								updateVO.setVb_sumpeople(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
								
		
								try {
									Main.vbus.updateVolunteer_board(updateVO);
									serchList = Main.vlus.getAllBoardList();
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
								for (int i = 0; i < serchList.size(); i++) {
									serchName = serchList.get(i).getCustom_id();
									serchNum = serchList.get(i).getVb_num();

									if (Login_controller.log_c.getCustom_id().equals(serchName)
											&& insertvo.getVb_num().equals(serchNum)) {
								
										errMsg("에러", "이미 신청되었습니다.");
										int peoplecount2 = Integer.parseInt(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());
										updateVO2.setVb_cntpeople(String.valueOf(peoplecount2));
										
										updateVO2.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
										updateVO2.setVb_title(Title_lb.getText());
										System.out.println(Title_lb.getText()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
										updateVO2.setVb_date(Date_lb.getText());
										updateVO2.setVb_content(Board_Content.getText());
										updateVO2.setShel_id(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id());
										updateVO2.setVb_cnt(LookUp_lb.getText());
										updateVO2.setVb_sumpeople(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
										
										try {
											Main.vbus.updateVolunteer_board(updateVO2);
										} catch (RemoteException e1) {
											e1.printStackTrace();
										}
										return;
										
									} else {

									}
								}
								

								try {
									Main.vlus.insertVolunteer_log(insertvo);
								
									
									infoMsg("신청완료", "신청완료");
									WritePage.close();
									tableSet();
									setTable();
									
								} catch (RemoteException e) {
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
