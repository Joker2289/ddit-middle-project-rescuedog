package kr.or.ddit.rms.main;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.media.Lee;
import kr.or.ddit.rms.vo.MissingVO;
import kr.or.ddit.rms.vo.NoticeVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

import com.jfoenix.controls.JFXTabPane;

public class MainItemsController implements Initializable{

	@FXML Label main_name_label;		//삭제
	@FXML ImageView main_name_img;		//삭제
	@FXML Label layout_name_label11;	//삭제
	@FXML Label main_note_label;		//삭제
	@FXML JFXTextArea main_info_ta;		//삭제
	@FXML MediaView main_mediaview;
	@FXML JFXButton main_media_btn;
	@FXML JFXTabPane RD_View;			//유기견 목록 보여줄 화면
	@FXML JFXTabPane MD_View;			//실종동물 목록 보여줄 화면
	@FXML AnchorPane Notice_View;		//공지사항 목록 보여줄 화면
	@FXML AnchorPane Thread_Btn;
	
	boolean mediaFlag;
	public static MediaPlayer player;
	
	public static TabPane tappane = null;
	
	//쓰레드
	private static boolean thread_flag;
	static int current_index;
	static Thread[] arr_thread = new Thread[1];
	
	static int Size;
	
	//쓰레드
	private static boolean thread_flag2;
	static int current_index2;
	static Thread[] arr_thread2 = new Thread[1];

	static int Size2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//tappane = new TabPane();
		
		//쓰레드 수식
//		if(arr_thread[0] != null) {
//	       arr_thread[0].interrupt();
//	    }
//	    System.out.println("thread_flag : " + thread_flag);
//	    RD_View.getSelectionModel().select(0);
//	
//	    Thread1 thread = new Thread1();
//	    thread.setDaemon(true);
//	    thread.start();
//	    arr_thread[0] = thread;
//	    
//	    //쓰레드 수식
//  		if(arr_thread2[0] != null) {
//  	       arr_thread2[0].interrupt();
//  	    }
//  	    System.out.println("thread_flag : " + thread_flag2);
//  	    MD_View.getSelectionModel().select(0);
//  	
//  	    Thread2 thread2 = new Thread2();
//  	    thread2.setDaemon(true);
//  	    thread2.start();
//  	    arr_thread2[0] = thread2;

		
		show_RDList();
		show_MDList();
		show_Noice();
		
		Media media = new Media(Lee.class.getResource("mainAd.mp4").toString());
		player = new MediaPlayer(media);
		main_mediaview.setMediaPlayer(player);
		main_media_btn.setOnAction(e -> {
			mediaFlag = !mediaFlag;
			if (!mediaFlag) {
				player.play();
			} else {
				player.stop();
				player.seek(player.getStartTime());
			}

		});
		player.setAutoPlay(true);
		
		
		
//		Thread_Btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
//			thread.interrupt();
//			thread2.interrupt();
//		});
	}


	private void show_Noice() {
		VBox V = new VBox();
		V.setPrefWidth(518);
		
		
		try {
			List<NoticeVO> noticeList = Main.n_a.getNoticeAll();
			for(int i=0; i<noticeList.size(); i++) {
				HBox H = new HBox();
				H.setPrefWidth(518);
				H.setPrefHeight(25);
				H.setStyle("-fx-background-color: #eceff1");
				
				Label notice_num_Lbl = new Label();
				notice_num_Lbl.setText(noticeList.get(i).getNotice_num());
				notice_num_Lbl.setPrefWidth(44);
				notice_num_Lbl.setPrefHeight(25);
				notice_num_Lbl.setAlignment(Pos.CENTER);
				notice_num_Lbl.setFont(Font.font("HCR Dotum", 13));
				H.getChildren().add(notice_num_Lbl);
				
				Label notice_title_Lbl = new Label();
				notice_title_Lbl.setText(noticeList.get(i).getTitle());
				notice_title_Lbl.setPrefWidth(282);
				notice_title_Lbl.setPrefHeight(25);
				notice_title_Lbl.setAlignment(Pos.CENTER);
				notice_title_Lbl.setFont(Font.font("HCR Dotum", 13));
				H.getChildren().add(notice_title_Lbl);
				
				Label notice_date_Lbl = new Label();
				notice_date_Lbl.setText(noticeList.get(i).getWrite_date().substring(0, 13));
				notice_date_Lbl.setPrefWidth(115);
				notice_date_Lbl.setPrefHeight(25);
				notice_date_Lbl.setAlignment(Pos.CENTER);
				notice_date_Lbl.setFont(Font.font("HCR Dotum", 13));
				H.getChildren().add(notice_date_Lbl);
				
				Label notice_writer_Lbl = new Label();
				notice_writer_Lbl.setText(noticeList.get(i).getAdmin_id());
				notice_writer_Lbl.setPrefWidth(71);
				notice_writer_Lbl.setPrefHeight(25);
				notice_writer_Lbl.setAlignment(Pos.CENTER);
				notice_writer_Lbl.setFont(Font.font("HCR Dotum", 13));
				H.getChildren().add(notice_writer_Lbl);
				
				HBox line = new HBox();
				line.setPrefHeight(1);
				line.setPrefWidth(518);
				line.setStyle("-fx-background-color: #c2c2c2");
				
				V.getChildren().add(H);
				V.getChildren().add(line);
			}
			Notice_View.getChildren().add(V);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}


	private void show_MDList() {
		RescuedogVO rvo = new RescuedogVO();
		rvo.setRd_check("입양 전");
		
		try {
			List<MissingVO> msList = Main.ma_u.getmissingAll();
			
			Size2 = msList.size();
			for(int i=0; i<msList.size(); i++) {
				
				String[] split = msList.get(i).getContent().split("/");
				String kind = split[2];
				String money = split[4];
				
				
				Tab tab = new Tab(msList.get(i).getBoard_num());
				
				tab.setStyle("-fx-background-color: #FFFFFF");
				//RD_View.getTabs().add(tab);
				VBox V = new VBox();
				V.setPrefWidth(286);
				V.setPrefHeight(336);		
				V.setAlignment(Pos.CENTER);
				V.setStyle("-fx-background-color: #FFFFFF");
				
				//유기견 사진 저장 //수정해야함
//				Image image = new Image("kr/or/ddit/rms/shelter/adopt_board/rdImg/"+msList.get(i).getImg());
				Image image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\"+
							msList.get(i).getBoard_num()+","+msList.get(i).getMem_id()+","+msList.get(i).getImg());
				ImageView view = new ImageView();
				view.setImage(image);
				view.setFitWidth(260);
				view.setFitHeight(170);
				
				HBox H = new HBox();
				H.setPrefWidth(300);
				H.setPrefHeight(200);
				H.setAlignment(Pos.CENTER);
	//			H.setStyle("-fx-background-color: #FED9CA");
				H.getChildren().add(view);
				V.getChildren().add(H);
				
				
				//글제목
				Label Title_Lbl = new Label();
				Title_Lbl.setText(msList.get(i).getTitle());
				Title_Lbl.setPrefWidth(240);
				Title_Lbl.setPrefHeight(10);
				Title_Lbl.setAlignment(Pos.CENTER);
				V.getChildren().add(Title_Lbl);
				Title_Lbl.setFont(Font.font("HCR Dotum", 13));
				
			/*	//위치
				Label Locate_Lbl = new Label();
				Locate_Lbl.setText(msList.get(i).getMiss_loc());
				Locate_Lbl.setPrefWidth(240);
				Locate_Lbl.setPrefHeight(10);
				Locate_Lbl.setAlignment(Pos.CENTER);
				V.getChildren().add(Locate_Lbl);*/
				
				
			/*	//견종
				Label kind_Lbl = new Label();
				kind_Lbl.setText(kind);
				kind_Lbl.setPrefWidth(240);
				kind_Lbl.setPrefHeight(10);
				kind_Lbl.setAlignment(Pos.CENTER);
				V.getChildren().add(kind_Lbl);
				
				//견종
				Label money_Lbl = new Label();
				money_Lbl.setText(money);
				money_Lbl.setPrefWidth(240);
				money_Lbl.setPrefHeight(10);
				money_Lbl.setAlignment(Pos.CENTER);
				V.getChildren().add(money_Lbl);*/
				
			
				
				tab.setContent(V);
				MD_View.getTabs().add(tab);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}


	private void show_RDList() {
		RescuedogVO rvo = new RescuedogVO();
		rvo.setRd_check("입양 전");
		
		try {
			List<RescuedogVO> sList = Main.ab_s.getSearchRescuedog(rvo);
			
			Size = sList.size();
			for(int i=0; i<sList.size(); i++) {
				Tab tab = new Tab(sList.get(i).getRd_num());
				tab.setStyle("-fx-background-color: #FFFFFF");
				
				//RD_View.getTabs().add(tab);
				VBox V = new VBox();
		//		V.setSpacing(2.0);
				V.setPrefWidth(290);
				V.setPrefHeight(360);		
				V.setAlignment(Pos.CENTER);
				V.setStyle("-fx-background-color: #FFFFFF");
				
				//유기견 사진 저장
				Image image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Rescuedog\\"+sList.get(i).getRd_img());
//				Image image = new Image("kr/or/ddit/rms/shelter/adopt_board/rdImg/"+sList.get(i).getRd_img());
				ImageView view = new ImageView();
				view.setImage(image);
				view.setFitWidth(255);
				view.setFitHeight(165);
				
				HBox H = new HBox();
				H.setPrefWidth(240);
				H.setPrefHeight(200);
				H.setAlignment(Pos.CENTER);
	//			H.setStyle("-fx-background-color: #FED9CA");
				H.getChildren().add(view);
				V.getChildren().add(H);
				
				
				//등록번호 저장
				/*HBox numH = new HBox();
				numH.setPrefWidth(240);
				numH.setPrefHeight(10);
				
				Label rd_num_Lbl = new Label();
				rd_num_Lbl.setText("등록 번호 : ");
				rd_num_Lbl.setPrefWidth(120);
				rd_num_Lbl.setPrefHeight(10);
				rd_num_Lbl.setAlignment(Pos.CENTER_RIGHT);
				numH.getChildren().add(rd_num_Lbl);
				
				Label rd_num_Lbl2 = new Label();
				rd_num_Lbl2.setText("   " + sList.get(i).getRd_num());
				rd_num_Lbl2.setPrefWidth(120);
				rd_num_Lbl2.setPrefHeight(10);
				rd_num_Lbl2.setAlignment(Pos.CENTER_LEFT);
				numH.getChildren().add(rd_num_Lbl2);
				
				V.getChildren().add(numH);
				*/
				
				//유기견 이름 저장
				HBox nameH = new HBox();
				nameH.setPrefWidth(240);
				nameH.setPrefHeight(10);
				
				Label rd_name_Lbl = new Label();
				rd_name_Lbl.setText("등록 이름 : ");
				rd_name_Lbl.setPrefWidth(120);
				rd_name_Lbl.setPrefHeight(10);
				rd_name_Lbl.setAlignment(Pos.CENTER_RIGHT);
				rd_name_Lbl.setFont(Font.font("HCR Dotum", 13));
				nameH.getChildren().add(rd_name_Lbl);
				
				Label rd_name_Lbl2 = new Label();
				rd_name_Lbl2.setText("   " + sList.get(i).getRd_name());
				rd_name_Lbl2.setPrefWidth(120);
				rd_name_Lbl2.setPrefHeight(10);
				rd_name_Lbl2.setAlignment(Pos.CENTER_LEFT);
				rd_name_Lbl2.setFont(Font.font("HCR Dotum", 13));
				nameH.getChildren().add(rd_name_Lbl2);
				
				V.getChildren().add(nameH);
				
				
			/*	//유기견 견종 저장
				HBox kindH = new HBox();
				kindH.setPrefWidth(240);
				kindH.setPrefHeight(10);
				
				Label rd_kind_Lbl = new Label();
				rd_kind_Lbl.setText("등록 견종 : ");
				rd_kind_Lbl.setPrefWidth(120);
				rd_kind_Lbl.setPrefHeight(10);
				rd_kind_Lbl.setAlignment(Pos.CENTER_RIGHT);
				kindH.getChildren().add(rd_kind_Lbl);
				
				Label rd_kind_Lbl2 = new Label();
				rd_kind_Lbl2.setText("   " + sList.get(i).getRd_kind());
				rd_kind_Lbl2.setPrefWidth(120);
				rd_kind_Lbl2.setPrefHeight(10);
				rd_kind_Lbl2.setAlignment(Pos.CENTER_LEFT);
				kindH.getChildren().add(rd_kind_Lbl2);
				
				V.getChildren().add(kindH);*/
				
				//보호기관 이름 가져오기
			/*	String shel_id = sList.get(i).getShel_id();
				ShelterVO shelVO = new ShelterVO();
				List<ShelterVO> shel_list = Main.ab_s.getSearchShelter(shelVO);
				String shel_name = shel_list.get(0).getShel_name();
				
				
				//보호기관 이름 저장
				HBox shelH = new HBox();
				V.setSpacing(7.0);
				shelH.setPrefWidth(240);
				shelH.setPrefHeight(10);
				
				Label shel_name_lbl = new Label();
				shel_name_lbl.setText("보호 기관 : ");
				shel_name_lbl.setPrefWidth(120);
				shel_name_lbl.setPrefHeight(10);
				shel_name_lbl.setAlignment(Pos.CENTER_RIGHT);
				shelH.getChildren().add(shel_name_lbl);
				
				Label shel_name_lbl2 = new Label();
				shel_name_lbl2.setText("   " + shel_name);
				shel_name_lbl2.setPrefWidth(120);
				shel_name_lbl2.setPrefHeight(10);
				shel_name_lbl2.setAlignment(Pos.CENTER_LEFT);
				shelH.getChildren().add(shel_name_lbl2);
				
				V.getChildren().add(shelH);*/
				tab.setContent(V);
				RD_View.getTabs().add(tab);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	class Thread1 extends Thread {
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {

               // System.out.println("인터럽트" + this.isInterrupted());
               if (Thread.interrupted()) { // interrupt()메서드가 호출되면 true
                  System.out.println("인스턴스용 isInterrupted()");
                  break;
               }
               Thread.sleep(2500);
               current_index = RD_View.getSelectionModel().getSelectedIndex();
               if (current_index == Size-1) {
            	   RD_View.getSelectionModel().select(0);
               } else {
            	   RD_View.getSelectionModel().select(current_index + 1);
               }
               // System.out.println(current_index + 1);
            }
            arr_thread[0] = this;
         } catch (Exception e) {
        	 
         }
         System.out.println("자원 정리 중...");
         System.out.println("실행종료.");
      }
   }

	
	class Thread2 extends Thread {
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {

               // System.out.println("인터럽트" + this.isInterrupted());
               if (Thread.interrupted()) { // interrupt()메서드가 호출되면 true
                  System.out.println("인스턴스용 isInterrupted()");
                  break;
               }
               Thread.sleep(2500);
               current_index2 = MD_View.getSelectionModel().getSelectedIndex();
               if (current_index2 == Size2-1) {
            	   MD_View.getSelectionModel().select(0);
               } else {
            	   MD_View.getSelectionModel().select(current_index2 + 1);
               }
               // System.out.println(current_index + 1);
            }
			arr_thread2[0] = this;
         } catch (Exception e) {
        	 
         }
         System.out.println("자원 정리 중...");
         System.out.println("실행종료.");
      }
   }
}
