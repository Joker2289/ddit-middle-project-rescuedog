package kr.or.ddit.rms.shelter.volunteer_board;

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
import kr.or.ddit.rms.main.ChangePane;
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
	JFXButton Table_add_btn;
	@FXML
	JFXTextArea Board_Content;

	@FXML
	Pagination Table_search_page;
	private int from, to, itemsForPage;
	@FXML
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
					viewList = Main.vbss.getSerchList(vo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (VolunteerSerch_fd.getText().equals("")) {
					VolunteerSerch_fd.requestFocus();
					errMsg("검색어를 입력해주세요", VolunteerSerch_fd.getText()+"에 대한 검색결과가 없습니다.");

				} else {

					VolunteerBoard_tb.getItems().setAll(viewList);
				}

			}
		});
		Table_add_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage WritePage = new Stage(StageStyle.UTILITY);
				WritePage.initModality(Modality.APPLICATION_MODAL);
				Volunteer_BoardVO insertvo = new Volunteer_BoardVO();
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("Volunteerboard_Register.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Label Writer_lb = (Label) parent.lookup("#Writer_lb");
				Label Up_Vb_CntPeople_ld = (Label) parent.lookup("#Up_Vb_CntPeople_ld");
				Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
				Label Page_No = (Label) parent.lookup("#Page_No");
				TextField Vb_Title_fd = (TextField) parent.lookup("#Vb_Title_fd");
				TextField Vb_sumpeople_fb = (TextField) parent.lookup("#Vb_sumpeople_fb");
				JFXTextArea Board_Content = (JFXTextArea) parent.lookup("#Board_Content");
				JFXButton Register_Btn = (JFXButton) parent.lookup("#Register_Btn");
				JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
				Page_No.setText(insertvo.getVb_num());
				Up_Vb_CntPeople_ld.setText("0");
				LookUp_lb.setText("0");
				Writer_lb.setText(Login_controller.log_s.getShel_id());
				Board_Content.setPromptText("내용을 입력해주세요");
				Vb_Title_fd.setPromptText("제목을 입력하세요");
				Scene scene = new Scene(parent);
				WritePage.setTitle("상세게시판");
				WritePage.setScene(scene);
				WritePage.show();

				Register_Btn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (Vb_Title_fd.getText().isEmpty()) {
							Vb_Title_fd.requestFocus(); // 포커스주기
							errMsg("에러", "제목을 입력해주세요!");
							return;
						}

						if (Board_Content.getText().isEmpty()) {
							Board_Content.requestFocus(); // 포커스주기
							errMsg("에러", "내용을 입력해주세요!");
							return;
						}if (Vb_sumpeople_fb.getText().isEmpty()) {
							Vb_sumpeople_fb.requestFocus(); // 포커스주기
							errMsg("에러", "모집할 인원을 입력해주세요!");
							return;
						}
						// 등록할 정보들을 vo 세팅한 후 insert
						Calendar cal = Calendar.getInstance();
						Volunteer_BoardVO insertvo = new Volunteer_BoardVO();
						insertvo.setVb_num(insertvo.getVb_num());
						insertvo.setVb_title(Vb_Title_fd.getText());
						insertvo.setVb_content(Board_Content.getText());// 임시적인 자료입력 부분
						insertvo.setVb_date(format.format(cal.getTime()));
						insertvo.setShel_id(Login_controller.log_s.getShel_id());// 이부분은 유저 어드민 쉘터 마다 데이터를 불러와 바꿔줘야함 생각중
						insertvo.setVb_cntpeople("0");
						insertvo.setVb_cnt("0");
						insertvo.setVb_sumpeople(Vb_sumpeople_fb.getText());

						// 이부분
						// 신청목록게시판으로 가야하는데..

						try {
							Main.vbss.insertBoard(insertvo);
							VolunteerBoard_tb.getItems().setAll(Main.vbss.getAllBoardList());// 입력후
							dataList.addAll(Main.vbss.getAllBoardList());
							// 업데이트
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						tableSet();
						WritePage.close();

					}
				});
				Board_List_Btn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						WritePage.close();
					}
				});

			}

		});
		VolunteerBoard_tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						Stage WritePage = new Stage(StageStyle.UTILITY);
						WritePage.initModality(Modality.APPLICATION_MODAL);
						Calendar cal = Calendar.getInstance();
						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("Volunteerboard_Registerdetail.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						Label Up_Writer_lb = (Label) parent.lookup("#Up_Writer_lb");
						Label Up_Date_lb = (Label) parent.lookup("#Up_Date_lb");
						Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
						Label Page_No = (Label) parent.lookup("#Page_No");
						Label Up_Vb_CntPeople_ld = (Label) parent.lookup("#Up_Vb_CntPeople_ld");
						TextField Vb_sumpeople_fb = (TextField) parent.lookup("#Vb_sumpeople_fb");
						TextField Up_Vb_Title_fd = (TextField) parent.lookup("#Up_Vb_Title_fd");
						JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
						JFXButton Update_Btn = (JFXButton) parent.lookup("#Update_Btn");
						JFXButton Delete_Btn = (JFXButton) parent.lookup("#Delete_Btn");
						JFXTextArea Up_Content = (JFXTextArea) parent.lookup("#Up_Content");
						// ImageView dog_img = (ImageView)parent.lookup("#dog_img");

						// Image dog = new
						// Image("kr/or/ddit/rms/shelter/volunteer_board/mainpage_icon1.jpeg");
						Volunteer_BoardVO updateVO = new Volunteer_BoardVO();
						// dog_img.setImage(dog);
						count = Integer.parseInt(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cnt())+1;
						Page_No.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						Up_Writer_lb.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id()));
						Up_Vb_Title_fd.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_title()));
						Up_Content.setText((VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_content()));
						Vb_sumpeople_fb.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
						Up_Vb_CntPeople_ld
								.setText(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());
						updateVO.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
						updateVO.setVb_title(Up_Vb_Title_fd.getText());
						updateVO.setVb_content(Up_Content.getText());
						updateVO.setShel_id(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getShel_id());
						updateVO.setVb_cnt(count + "");
						updateVO.setVb_cntpeople(
								VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cntpeople());
						updateVO.setVb_date(format.format(cal.getTime()));
						updateVO.setVb_sumpeople(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_sumpeople());
						int selectItem = VolunteerBoard_tb.getSelectionModel().getSelectedIndex();
						if (!Login_controller.log_s.getShel_id().equals(Up_Writer_lb.getText())) {
							Update_Btn.setVisible(false);
							Delete_Btn.setVisible(false);
							Up_Content.setEditable(false);
						}
						try {
							Main.vbss.updateBoard(updateVO);
							LookUp_lb.setText(Main.vbss.getAllBoardList().get(selectItem).getVb_cnt());

						} catch (RemoteException e1) {
							e1.printStackTrace();
						}

						Scene scene = new Scene(parent);
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
						Update_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								Volunteer_BoardVO updateVO = new Volunteer_BoardVO();
								Calendar cal = Calendar.getInstance();
								updateVO.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
								updateVO.setVb_title(Up_Vb_Title_fd.getText());
								updateVO.setVb_date(format.format(cal.getTime()));
								updateVO.setVb_content(Up_Content.getText());
								updateVO.setVb_cntpeople(Up_Vb_CntPeople_ld.getText());
								updateVO.setVb_sumpeople(Vb_sumpeople_fb.getText());
								updateVO.setShel_id(Login_controller.log_s.getShel_id());
								updateVO.setVb_cnt(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_cnt());
								System.out.println(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
								try {
									Main.vbss.updateBoard(updateVO);
									infoMsg("수정완료", "수정되었습니다.");
									tableSet();
									WritePage.close();
								} catch (RemoteException e) {

									e.printStackTrace();
								}
							}
						});
						Delete_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								Volunteer_BoardVO vo = new Volunteer_BoardVO();
								Volunteer_LogVO logvo = new Volunteer_LogVO();
								vo.setVb_num(VolunteerBoard_tb.getSelectionModel().getSelectedItem().getVb_num());
								vo.setShel_id(Login_controller.log_s.getShel_id());
								String serchLog = null;
							
								List<Volunteer_LogVO> listLog;
								try {
									listLog = Main.vlss.getAllBoardList();
									for (int i = 0; i < listLog.size(); i++) {
										serchLog = listLog.get(i).getVb_num();
										if (vo.getVb_num().equals(serchLog)) {
											errMsg("경고", "신청된 자료가 있습니다.");
											return;
										}
									}

								} catch (RemoteException e1) {
									e1.printStackTrace();
								}

								// 리스트에 로그기록을 담고 번호와 사용자가 이미 있으면 삭제를 취소한다.

								try {

									Main.vbss.deleteBoard(vo);
									infoMsg("등록취소", "등록이 취소되었습니다.");
//									ChangePane cp = Main_page_controller.fxmlLoad.getController(); 모달창은 안돼나보다
//									cp.clearing();
//									ChangePane.changePane(getClass().getResource("Volunteerboard_SelectPage.fxml"));
//									cp.add();
									
									WritePage.close();
									tableSet();
									setTable();
									return;
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
			viewList = (ArrayList<Volunteer_BoardVO>) Main.vbss.getAllBoardList(); // 목록을 전체 가져옴
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
