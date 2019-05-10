package kr.or.ddit.rms.shelter.adopt_board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.RescuedogVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class Add_RD_controller implements Initializable {

	@FXML Label Hide_Lbl;				//숨김 라벨
	
	
	@FXML JFXButton Goto_RDList_Btn;	//목록 버튼
	
	@FXML JFXTextField RD_Name;			//유기견명
	@FXML JFXTextArea RD_Info;			//유기견 정보
	
	@FXML JFXComboBox Kind_Select;		//견종 선택 박스
	
	@FXML JFXCheckBox M_Check;			//수컷 체크
	@FXML JFXCheckBox F_Check;			//암컷 체크
	
	@FXML JFXTextField Path_Img_Txt;	//이미지 경로 
	@FXML JFXButton Find_Img_Btn;		//파일첨부 버튼
	@FXML ImageView RD_Img_Iv;			//이미지 뷰
	
	@FXML JFXButton Add_RD_Btn;			//등록 버튼
	
	String rd_num = ""; //유기견 등록 번호
	String fileFormat;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path_Img_Txt.setDisable(true);
		
		if(M_Check.isSelected() == true) {
			F_Check.setSelected(true);
			System.out.println("M");
		} else if(F_Check.isSelected() == true) {
			System.out.println("F");
			M_Check.setSelected(true);
		}
		
		//콤보박스 설정
		Kind_Select.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
		    @Override
		    public ListCell<String> call(ListView<String> list) {
		        return new ListCell<String>() {
		            @Override
		            public void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item != null) {
		                    setText(item);
		                    setAlignment(Pos.CENTER);
		                    setPadding(new Insets(3, 3, 3, 0));
		                }
		            }
		        };
		    }
		});
		
		try {
			//rd_num MAX값 가져오기
			String num = Main.ab_s.getRd_num();
			if(num!=null) {
				rd_num = (Integer.parseInt(num)+1)+"";
			}else {
				rd_num="1";
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String[] dog_kind = {"골든리트리버","닥스훈트", "포메라니안", "말티즈", "시츄", "치와와", "프렌치불독", "시바견", 
				"진돗개", "핏불", "셰퍼트", "보스턴테리어", "도베르만", "래브라도리트리버", "로트바일러", "슈나우저",
				"푸들", "비숑프리제", "비글", "보더콜리", "웰시코기", "똥개"};
		
		for(int i=0; i<dog_kind.length; i++) {
			Kind_Select.getItems().add(dog_kind[i]);
		}
		
		//파일 찾기 버튼
		Find_Img_Btn.setOnAction(e->{
			fileFormat=fileAdd();	
		});
		
		//등록 버튼 클릭
		Add_RD_Btn.setOnAction(e->{
			add_RD();
		});
		
		
		//체크박스 왔다갔다
		M_Check.setOnAction(e->{
			if(F_Check.isSelected() == true) {
				F_Check.setSelected(false);
			}
		});
		
		F_Check.setOnAction(e->{
			if(M_Check.isSelected() == true) {
				M_Check.setSelected(false);
			}
		});
		
		
		Goto_RDList_Btn.setOnAction(e->{
			ChangePane cp = Main_page_controller.fxmlLoad.getController();
			cp.clearing();
			ChangePane.changePane(getClass().getResource("adopt_view.fxml"));
			cp.add();
		});
		
		
	}
	
	private void add_RD() {
		
		if(RD_Name.getText().isEmpty() == true) {
			Hide_Lbl.setText("유기견 명을 입력 해주세요");
			RD_Name.requestFocus();
			return;
		} else if(Kind_Select.getValue() == null) {
			Hide_Lbl.setText("견종을 선택해 주세요");
			Kind_Select.requestFocus();
			return;
		} else if(!M_Check.isSelected() && !F_Check.isSelected()) {
			Hide_Lbl.setText("성별을 선택해 주세요");
			M_Check.requestFocus();
			return;
		} else if(RD_Info.getText().isEmpty() == true) {
			Hide_Lbl.setText("추가정보를 입력 해주세요");
			RD_Info.requestFocus();
			return;
		} else if(Path_Img_Txt.getText().isEmpty() == true) {
			Hide_Lbl.setText("사진을 첨부해 주세요");
			return;
		}
		
		System.out.println(Kind_Select.getValue().toString());
		System.out.println(M_Check.getText());
		System.out.println(Login_controller.log_s.getShel_id());
		
		
		RescuedogVO rdvo = new RescuedogVO();
		rdvo.setRd_num(rd_num);
		rdvo.setRd_name(RD_Name.getText());
		rdvo.setRd_kind(Kind_Select.getValue().toString());
		rdvo.setRd_check("입양 전");

		if(M_Check.isSelected()) {
			rdvo.setRd_gender("M");
		} else if(F_Check.isSelected()) {
			rdvo.setRd_gender("F");
		}
		rdvo.setShel_id(Login_controller.log_s.getShel_id());
		rdvo.setRd_info(RD_Info.getText());
		rdvo.setRd_img("rd"+rd_num+fileFormat);
		
		
		try {
			int tmp = Main.ab_s.insertRescuedog(rdvo);
			System.out.println(tmp);
			if(tmp == 1) {
				alertTest("등록 완료");
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("adopt_view.fxml"));
				cp.add();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(rdvo.getRd_num());
		System.out.println(rdvo.getRd_name());
		System.out.println(rdvo.getRd_kind());
		System.out.println(rdvo.getRd_check());
		System.out.println(rdvo.getRd_gender());
		System.out.println(rdvo.getRd_info());
		System.out.println(rdvo.getRd_img());
	}
	
	private String fileAdd() {
		Stage stage = (Stage) Main.StageHome.getScene().getWindow();
		FileChooser fileChooser =new FileChooser();
		fileChooser.setTitle("Image select");
		
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		
		if(!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		
		File filePath = fileChooser.showOpenDialog(stage);
			BufferedImage getFile;
			try {
				
				getFile = ImageIO.read(filePath);
				Image img = SwingFXUtils.toFXImage(getFile, null);
				RD_Img_Iv.setImage(img);
				String fileName = filePath.getName();
				int FileLen = fileName.length();
				System.out.println(fileName);	//파일이름
				File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\"+
											"rd"+rd_num+fileName.substring(FileLen-4, FileLen)); //저장파일 이름및 경로 지정
				System.out.println(fileName.substring(FileLen-3, FileLen));
				ImageIO.write(getFile, fileName.substring(FileLen-3, FileLen), downloadFile);
				String userFilePath = filePath.getAbsolutePath();
				System.out.println(userFilePath); //절대경로
				//System.out.println(downloadFile.getName());
//				fileName = downloadFile.getName();
				Path_Img_Txt.setText(userFilePath);
				return fileName.substring(FileLen-4, FileLen);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("검색");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
