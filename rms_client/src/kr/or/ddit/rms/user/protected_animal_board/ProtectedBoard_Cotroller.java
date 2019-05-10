package kr.or.ddit.rms.user.protected_animal_board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.ProdVO;
import kr.or.ddit.rms.vo.Protected_boardVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class ProtectedBoard_Cotroller implements Initializable {

	int report_Count = 0;
	@FXML
	AnchorPane Protected_Pane;
	ObservableList<Protected_boardVO> dataList = FXCollections.observableArrayList();
	List<Protected_boardVO> viewList = FXCollections.observableArrayList();
	public static ImageView log_img = null;
	public static JFXButton log_btn = null;
	public static Stage protected_dialog;
	public static String board_num;
	public static String fileName;
	public static String duplfileName;
	@FXML
	JFXButton Register_Btn;
	@FXML
	JFXButton ProtectedSerch_btn;
	@FXML
	JFXTextField ProtectedSerch_fd;
	@FXML
	AnchorPane addPane;
	@FXML
	AnchorPane loadPane;
	@FXML
	AnchorPane tempPane;
	@FXML
	AnchorPane servicePane;
	@FXML
	ScrollPane scPane;
	@FXML
	HBox pr_box;
	@FXML
	HBox pr_registerBox;
	public static Label writer_lb;
	public static Stage WritePage;
	Image img;
	VBox main_box;
	@FXML
	Label inviserble_lb;
	@FXML
	Label top_lb;
	@FXML
	Label top_lb2;
	@FXML
	JFXComboBox Search_Comb;
	ImageView Pr_ImgView;
	int imgcount = 0;
	
	// public ImageView note_img = new ImageView();
	int count;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			viewList = Main.pbus.getAllBoardList();
			setVbox(viewList);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
String[] search_item = {"작성자명", "특징", "견종","발견위치"};
		
		for(int i=0; i<search_item.length; i++) {
			Search_Comb.getItems().add(search_item[i]);
		}
		
		//검색버튼 클릭
		ProtectedSerch_btn.setOnAction(e->{
			if(Search_Comb.getSelectionModel().isEmpty()) {
				viewList.get(0).getKind();
				infoMsg("에러", "검색설정을 해주세요");
				return;
			}
			
			switch(Search_Comb.getValue().toString()) {
//				case "유기견 이름":
//					Protected_boardVO vo = new Protected_boardVO();
//					vo.setName(ProtectedSerch_fd.getText());
//					try {
//						List<Protected_boardVO> searchList = Main.pbus.getprotectedTextSearch(vo);
//						if (searchList.size() == 0) {
//							Protected_Pane.getChildren().clear();
//							System.out.println("검색없음");
//							inviserble_lb.setText("검색된 결과가 없습니다.");
//							VBox v = new VBox();
//						v.getChildren().add(inviserble_lb);
//							v.setPrefWidth(1100);
//							v.setPrefHeight(524);
//							v.setAlignment(Pos.CENTER);
//							Protected_Pane.getChildren().add(v);
//							return;
//						}
//						viewList = null;
//						setVbox(searchList);
//
//					} catch (RemoteException e1) {
//						e1.printStackTrace();
//					}
//					break;
					
				case "작성자명":
					Protected_boardVO Membervo = new Protected_boardVO();
					Membervo.setMem_id(ProtectedSerch_fd.getText());
					try {
						List<Protected_boardVO> searchList = Main.pbus.getprotectedTextSearch(Membervo);
						if (searchList.size() == 0) {
							Protected_Pane.getChildren().clear();
							System.out.println("검색없음");
							inviserble_lb.setText("검색된 결과가 없습니다.");
							VBox v = new VBox();
						v.getChildren().add(inviserble_lb);
							v.setPrefWidth(1100);
							v.setPrefHeight(524);
							v.setAlignment(Pos.CENTER);
							Protected_Pane.getChildren().add(v);
							return;
						}
						viewList = null;
						setVbox(searchList);

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "특징":
					Protected_boardVO Characteristicvo = new Protected_boardVO();
					Characteristicvo.setCharacteristic(ProtectedSerch_fd.getText());
					try {
						List<Protected_boardVO> searchList = Main.pbus.getprotectedTextSearch(Characteristicvo);
						if (searchList.size() == 0) {
							Protected_Pane.getChildren().clear();
							System.out.println("검색없음");
							inviserble_lb.setText("검색된 결과가 없습니다.");
							VBox v = new VBox();
						v.getChildren().add(inviserble_lb);
							v.setPrefWidth(1100);
							v.setPrefHeight(524);
							v.setAlignment(Pos.CENTER);
							Protected_Pane.getChildren().add(v);
							return;
						}
						viewList = null;
						setVbox(searchList);

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				case "견종":
					Protected_boardVO Kindvo = new Protected_boardVO();
					Kindvo.setKind(ProtectedSerch_fd.getText());
					try {
						List<Protected_boardVO> searchList = Main.pbus.getprotectedTextSearch(Kindvo);
						if (searchList.size() == 0) {
							Protected_Pane.getChildren().clear();
							System.out.println("검색없음");
							inviserble_lb.setText("검색된 결과가 없습니다.");
							VBox v = new VBox();
						v.getChildren().add(inviserble_lb);
							v.setPrefWidth(1100);
							v.setPrefHeight(524);
							v.setAlignment(Pos.CENTER);
							Protected_Pane.getChildren().add(v);
							return;
						}
						viewList = null;
						setVbox(searchList);

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "발견위치":
					Protected_boardVO Findvo = new Protected_boardVO();
					Findvo.setFind_loc(ProtectedSerch_fd.getText());
					try {
						List<Protected_boardVO> searchList = Main.pbus.getprotectedTextSearch(Findvo);
						if (searchList.size() == 0) {
							Protected_Pane.getChildren().clear();
							System.out.println("검색없음");
							inviserble_lb.setText("검색된 결과가 없습니다.");
							VBox v = new VBox();
						v.getChildren().add(inviserble_lb);
							v.setPrefWidth(1100);
							v.setPrefHeight(524);
							v.setAlignment(Pos.CENTER);
							Protected_Pane.getChildren().add(v);
							return;
						}
						viewList = null;
						setVbox(searchList);

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				default:
					break;
			}
			
		});
		
		
	}



	private void setVbox(List<Protected_boardVO> viewList) {

		Protected_boardVO vo = new Protected_boardVO();
		// vo.setMem_id(login_controller.log_c.getCustom_id());

		// 저장값
//		try {
//			viewList.addAll(Main.pbus.getAllBoardList());
//			System.out.println(viewList.size());
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		main_box = new VBox();
		main_box.setPrefWidth(1036.0);
		for (int i = 0; i < viewList.size(); i++) {
			ImageView view;
			String Protected_Num = viewList.get(i).getBoard_num();
			vo.setBoard_num(Protected_Num);

			HBox H = new HBox();
			H.setPrefWidth(1036.0);
			H.setPrefHeight(100.0);
			H.setAccessibleText(i + "");
			H.setAlignment(Pos.CENTER);

			Protected_boardVO pvo = viewList.get(i);
//			view = new ImageView();
//			File file = new File("C:\\Users\\shrtm\\OneDrive\\바탕 화면\\testpath" + viewList.get(i).getImg());
//			try {
//				BufferedImage bufferedImg = ImageIO.read(file);
//				img = SwingFXUtils.toFXImage(bufferedImg, null);
//			} catch (IOException e) {
//				System.out.println("공유폴더에 파일 없음");
//			}
			view = new ImageView();
			File file = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\"+viewList.get(i).getImg());
			try {
				BufferedImage bufferedImg = ImageIO.read(file);
				img = SwingFXUtils.toFXImage(bufferedImg, null);
			} catch (IOException e) {
				System.out.println("공유폴더에 파일 없음");
			}
//			Image Protected_image = new Image("kr/or/ddit/rms/user/protected_animal_board/protected_img/" + viewList.get(i).getImg());
//			Image Protected_image = new Image("\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\"+bc_num+viewList.get(i).getImg());
//			InputStream is = new BufferedInputStream(
//		            new FileInputStream("\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\"+bc_num+viewList.get(i).getImg()));

			view.setImage(img);
			view.setFitWidth(170.0);
			view.setFitHeight(110.0);
			view.setAccessibleText(viewList.get(i).getImg());
			H.getChildren().add(view);

			Label Protected_Titel = new Label();
			Protected_Titel.setText("     특징  :" + pvo.getCharacteristic());
			Protected_Titel.setPrefWidth(290.0);
			Protected_Titel.setPrefHeight(30.0);
			Protected_Titel.setFont(Font.font("HCR Dotum", 15));
			Protected_Titel.setAlignment(Pos.CENTER_LEFT);

			Label kind_lb = new Label();
			kind_lb.setText("     견종  :" + viewList.get(i).getKind());
			kind_lb.setPrefWidth(290.0);
			kind_lb.setPrefHeight(30.0);
			kind_lb.setFont(Font.font("HCR Dotum", 15));
			kind_lb.setAlignment(Pos.CENTER_LEFT);

			Label gender_lb = new Label();
			gender_lb.setText("     성별  :" + viewList.get(i).getGender());
			gender_lb.setPrefWidth(290.0);
			gender_lb.setPrefHeight(30.0);
			gender_lb.setFont(Font.font("HCR Dotum", 15));
			gender_lb.setAlignment(Pos.CENTER_LEFT);

			VBox v = new VBox();
			v.setPrefWidth(360.0);
			v.setPrefHeight(100.0);
			v.setAlignment(Pos.CENTER);
			v.getChildren().add(Protected_Titel);
			v.getChildren().add(kind_lb);
			v.getChildren().add(gender_lb);
			H.getChildren().add(v);

			Label Protected_Date = new Label();
			Protected_Date.setText(pvo.getUpd_date().substring(0, 10));
			Protected_Date.setPrefWidth(260.0);
			Protected_Date.setPrefHeight(100.0);
			Protected_Date.setAlignment(Pos.CENTER_LEFT);
			Protected_Date.setFont(Font.font("HCR Dotum", 15));

			// System.out.println(viewList.get(i).getCustom_id());
			Label Protected_Writer_lb = new Label();
			Protected_Writer_lb.setText(pvo.getMem_id());
			Protected_Writer_lb.setPrefWidth(250);
			Protected_Writer_lb.setPrefHeight(100.0);
			Protected_Writer_lb.setAlignment(Pos.CENTER_LEFT);
			Protected_Writer_lb.setFont(Font.font("HCR Dotum", 15));

			Label Protected_Lookup_lb = new Label();
			Protected_Lookup_lb.setText(pvo.getFind_loc());
			Protected_Lookup_lb.setPrefWidth(180.0);
			Protected_Lookup_lb.setPrefHeight(100.0);
			Protected_Lookup_lb.setAlignment(Pos.CENTER_LEFT);
			Protected_Lookup_lb.setFont(Font.font("HCR Dotum", 15));

			HBox line = new HBox();
			line.setPrefWidth(1036.0);
			line.setPrefHeight(1.0);
			line.setStyle("-fx-background-color: #dadada;");

			H.getChildren().add(Protected_Writer_lb);
			H.getChildren().add(Protected_Lookup_lb);
			H.getChildren().add(Protected_Date);
			main_box.getChildren().add(H);
			main_box.getChildren().add(line);
			Protected_Pane.getChildren().setAll(main_box);

			view.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> { // 수정 화면
				viewEvent(e, img, pvo);
			});
		}

	}

	private void viewEvent(MouseEvent e, Image protected_image, Protected_boardVO protected_boardVO) {
		System.out.println(protected_image);
		log_img = (ImageView) e.getSource();
		String[] imgString = log_img.accessibleTextProperty().get().split("/");
		try {
			loadPane = FXMLLoader.load(getClass().getResource("Protected_Detail.fxml"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		ProtectedSerch_btn.setVisible(false);
		Search_Comb.setVisible(false);
		ProtectedSerch_fd.setVisible(false);
		top_lb2.setVisible(false);
		top_lb.setVisible(false);
		pr_box.setVisible(false);
		pr_registerBox.setVisible(false);
		scPane.setVisible(false);
		FontAwesomeIcon camera_icon = (FontAwesomeIcon) loadPane.lookup("#camera_icon");
		JFXTextField Title_Fb = (JFXTextField) loadPane.lookup("#Title_Fb");
		JFXTextField Sex_Fb = (JFXTextField) loadPane.lookup("#Sex_Fb");
		JFXTextField Find_Fb = (JFXTextField) loadPane.lookup("#Find_Fb");
		JFXTextField Kind_Fb = (JFXTextField) loadPane.lookup("#Kind_Fb");
		JFXTextField Characteristic_Fb = (JFXTextField) loadPane.lookup("#Characteristic_Fb");
		JFXTextField Tel_Fb = (JFXTextField) loadPane.lookup("#Tel_Fb");
		JFXButton Update_Btn = (JFXButton) loadPane.lookup("#Update_Btn");
		JFXButton Delete_Btn = (JFXButton) loadPane.lookup("#Delete_Btn");
		JFXButton File_Path_Btn = (JFXButton) loadPane.lookup("#File_Path_Btn");
		JFXButton report_Btn = (JFXButton) loadPane.lookup("#report_Btn");
		JFXButton BoardList_Btn = (JFXButton) loadPane.lookup("#BoardList_Btn");
		Pr_ImgView = (ImageView) loadPane.lookup("#Pr_ImgView");
		ImageView noteimg = (ImageView) loadPane.lookup("#noteimg");
		writer_lb = (Label) loadPane.lookup("#writer_lb");

		Image notimg = new Image("kr/or/ddit/rms/user/protected_animal_board/note.png");

//		Image nmage = new Image("file:///C:/Users/shrtm/OneDrive/바탕 화면/testpath" + imgString[0]);
		Image nmage = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\"+imgString[0]);

		noteimg.setImage(notimg);
		Pr_ImgView.setImage(nmage);
		Title_Fb.setText(protected_boardVO.getTitle());
		Sex_Fb.setText(protected_boardVO.getGender());
		Find_Fb.setText(protected_boardVO.getFind_loc());
		Kind_Fb.setText(protected_boardVO.getKind());
		Characteristic_Fb.setText(protected_boardVO.getCharacteristic());
		writer_lb.setText(protected_boardVO.getMem_id());
		System.out.println(protected_boardVO.getMem_id() + "@@@@@");
		Tel_Fb.setText(protected_boardVO.getTel());

		Protected_Pane.getChildren().removeAll(main_box);
		tempPane.getChildren().setAll(loadPane);
		if (!writer_lb.getText().equals(Login_controller.log_c.getCustom_id())) {
			Update_Btn.setVisible(false);
			Delete_Btn.setVisible(false);
			File_Path_Btn.setVisible(false);
			Sex_Fb.setEditable(false);
			Find_Fb.setEditable(false);
			Kind_Fb.setEditable(false);
			Characteristic_Fb.setEditable(false);
			Title_Fb.setEditable(false);
			Tel_Fb.setEditable(false);
			camera_icon.setVisible(false);

		}
		;

		Update_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Protected_boardVO UpdateVO = new Protected_boardVO();

				if (Title_Fb.getText().isEmpty() == true) {
					errMsg("경고", "제목을 입력하세요");
					return;
				} else if (Find_Fb.getText().isEmpty() == true) {
					errMsg("경고", "찾은위치를 입력하세요");
					return;
				} else if (Characteristic_Fb.getText().isEmpty() == true) {
					errMsg("경고", "특징을 입력하세요");
					return;
				} else if (fileName == null) {
					errMsg("경고", "사진을 수정하세요");
					return;

				}
				Pattern chkTel = Pattern.compile("^[0-9_]{10,11}");
				Matcher Telcheck = chkTel.matcher(Tel_Fb.getText());
				String tel = Tel_Fb.getText();
				if (!Telcheck.matches()) {
					Tel_Fb.requestFocus();
					errMsg("경 고 ", "숫자만 입력하세요");
					return;
				}

				UpdateVO.setBoard_num(protected_boardVO.getBoard_num());
				UpdateVO.setMem_id(Login_controller.log_c.getCustom_id());
				UpdateVO.setTitle(Title_Fb.getText());
				UpdateVO.setGender(Sex_Fb.getText());
				UpdateVO.setKind(Kind_Fb.getText());
				UpdateVO.setFind_loc(Find_Fb.getText());
				UpdateVO.setCharacteristic(Characteristic_Fb.getText());
				UpdateVO.setTel(tel);

				if (fileName == null) {
					fileName = nmage.toString();
					UpdateVO.setImg(fileName);
				} else {
					UpdateVO.setImg(fileName);
				}
				fileName = null;
//				else {
//					UpdateVO.setImg(fileName);
//				}
				UpdateVO.setUpd_date(protected_boardVO.getUpd_date());
				UpdateVO.setMem_id(protected_boardVO.getMem_id());

				try {
					Main.pbus.updateBoard(UpdateVO);
					infoMsg("수정완료", "수정");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					loadPane = FXMLLoader
							.load(IProtectedBoardUserService.class.getResource("protectedBoard_View.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tempPane.getChildren().clear();
				servicePane.getChildren().add(loadPane);
//				setVbox();

			}
		});
		Delete_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Protected_boardVO deleteVO = new Protected_boardVO();
				deleteVO.setBoard_num(protected_boardVO.getBoard_num());
				deleteVO.setMem_id(Login_controller.log_c.getCustom_id());

				try {
					Main.pbus.deleteBoard(deleteVO);
					infoMsg("삭제가 되었습니다.", "삭제완료");

				} catch (RemoteException e) {

				}
				try {
					loadPane = FXMLLoader
							.load(IProtectedBoardUserService.class.getResource("protectedBoard_View.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tempPane.getChildren().clear();
				servicePane.getChildren().add(loadPane);
//				setVbox();
			}
		});
		noteimg.addEventHandler(MouseEvent.MOUSE_CLICKED, (e2) -> {
			WritePage = new Stage(StageStyle.UTILITY);
			WritePage.initModality(Modality.APPLICATION_MODAL);

			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("Protected_Note_Send_Viiew.fxml"));
			} catch (IOException e3) {
				e3.printStackTrace();
			}

			Scene scene = new Scene(parent);
			WritePage.setTitle("쪽지 보내기");
			WritePage.setScene(scene);
			WritePage.show();

		});
		File_Path_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileAdd();

			}
		});
		BoardList_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					loadPane = FXMLLoader
							.load(IProtectedBoardUserService.class.getResource("protectedBoard_View.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tempPane.getChildren().clear();
				servicePane.getChildren().add(loadPane);

			}
		});
		report_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				WritePage = new Stage(StageStyle.UTILITY);
				WritePage.initModality(Modality.APPLICATION_MODAL);

				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("reportPage.fxml"));
				} catch (IOException e3) {
					e3.printStackTrace();

				}

				JFXButton cancel = (JFXButton) parent.lookup("#report_cancel_btn");
				JFXButton ok = (JFXButton) parent.lookup("#report_ok_btn");
				JFXTextField idFrom = (JFXTextField) parent.lookup("#report_IdFrom_tf");
				JFXTextField idTo = (JFXTextField) parent.lookup("#report_IdTo_tf");
				JFXTextArea content = (JFXTextArea) parent.lookup("#report_reason_ta");
				idTo.setText(writer_lb.getText());
				idFrom.setText(Login_controller.log_c.getCustom_id());
				idTo.setDisable(true);
				idFrom.setDisable(true);

				Scene scene = new Scene(parent);
				WritePage.setTitle("신고하기");
				WritePage.setScene(scene);
				WritePage.show();

				ok.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						boolean check = alertConfirm("신고하시겠습니까? (허위 신고시 제제)");
						if (check) {
							try {
								BlacklistVO bvo = new BlacklistVO();
								bvo.setMem_id(writer_lb.getText());
								bvo.setReason(content.getText());
								Main.bs.insertBlacklist(bvo);
								infoMsg("신고 완료", "신고완료");
							} catch (RemoteException e1) {
								errMsg("에러", "Error");
							}
						} else {
							infoMsg("취소하셨습니다.", "취소");
						}

						WritePage.close();

					}

				});
				cancel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						WritePage.close();

					}
				});

			}
		});

	}

	private void fileAdd() {
		imgcount++;
		Stage stage = (Stage) Main.StageHome.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Image select");

		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);

		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}

		File filePath = fileChooser.showOpenDialog(stage);
		BufferedImage getFile;
		try {
//			bc_num = Main.pbus.getAllBoardList().get(0).getBoard_num();
			getFile = ImageIO.read(filePath);
			Image img = SwingFXUtils.toFXImage(getFile, null);
			Pr_ImgView.setImage(img);

			fileName = filePath.getName();
			System.out.println(fileName);
			int FileLen = fileName.length();
//			File downloadFile = new File("src\\kr\\or\\ddit\\rms\\user\\protected_animal_board\\protected_img"+"1"+ "2"+ fileName);
			File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\" + fileName);
//			File downloadFile = new File("C:\\Users\\shrtm\\OneDrive\\바탕 화면\\testpath"+fileName);
			ImageIO.write(getFile, fileName.substring(FileLen - 3, FileLen), downloadFile);

		} catch (IOException e) {
		}
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

}