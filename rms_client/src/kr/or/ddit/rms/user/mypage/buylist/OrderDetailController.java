package kr.or.ddit.rms.user.mypage.buylist;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.user.goods_mall.cart_view.Cart_view_controller;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CommentsVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public class OrderDetailController implements Initializable {
   
   public static Buy_LogVO vo;
   
   @FXML Label mypage_num_label;
   @FXML Label mypage_date_label;
   @FXML Label mypage_name_label;
   @FXML Label mypage_status_label;

   @FXML Label mypage_price_label;//가격
   @FXML Label mypage_quan_label; //수량
   @FXML Label mypage_sum_label; //총 합계
   @FXML Label mypage_sum2_label; //총 합계
   
   @FXML JFXButton mypage_cancel_btn;
   @FXML JFXButton mypage_back_btn;
   
   @FXML Label Hide_Lbl;
   @FXML Label home_name;
   @FXML Label home_addr;
   @FXML Label home_phone;
   ObservableList<CommentsVO> allComData;
   ArrayList<CommentsVO> comLists;
   
   public static List<Buy_LogVO> buyList;
   public static int list_size = 0;
   public static int all_price = 0; //총 금액 저장된 변수 
   public static int all_day_price=0;

   @FXML AnchorPane Order_Main_Pane;
   VBox mainBox;
   public static JFXButton log = null;
   
   public void initialize(URL location, ResourceBundle resources) {

      CustomVO cvo  = new CustomVO(); 
      
      cvo.setCustom_name(Login_controller.log_c.getCustom_name());
      cvo.setCustom_tel(Login_controller.log_c.getCustom_tel());
      cvo.setCustom_addr(Login_controller.log_c.getCustom_addr());
      
      home_name.setText(cvo.getCustom_name());
      home_addr.setText(cvo.getCustom_addr());
      home_phone.setText(cvo.getCustom_tel());
      
      mypage_back_btn.setOnAction(e->{
         ChangePane cp = Main_page_controller.fxmlLoad.getController();
         cp.clearing();
         ChangePane.changePane(getClass().getResource("buylist_view.fxml"));
         cp.add();
      });
      mainBox = new VBox();
      mainBox.setPrefWidth(1036.0);
      
      setVbox();
      Order_Main_Pane.getChildren().add(mainBox);
      
      int quan = Integer.parseInt(vo.getBuy_quan());
      int price = Integer.parseInt(vo.getPrice());
      
      mypage_sum_label.setText(all_price + "");
      mypage_quan_label.setText(list_size + "개");
      
      all_price += price * quan;
      try {
		List<Buy_LogVO> list = Main.buy.getBuyTo(Login_controller.log_c.getCustom_id());
		for (int i = 0; i < list.size(); i++) {
			int quan1 = Integer.parseInt(list.get(i).getBuy_quan());
			int price1 = Integer.parseInt(list.get(i).getPrice());
			all_day_price+= (quan1*price1);
		}
	} catch (RemoteException e1) {
		e1.printStackTrace();
	}
      mypage_num_label.setText(vo.getBuy_num());
      mypage_date_label.setText(vo.getBuy_date());
      mypage_status_label.setText(vo.getRefund_check());
      mypage_name_label.setText(Login_controller.log_c.getCustom_name());
      mypage_price_label.setText(vo.getPrice());
      mypage_quan_label.setText(vo.getBuy_quan());
      mypage_sum_label.setText(all_price+"");
      mypage_sum2_label.setText(all_day_price+"");
      
      String buyNum = vo.getBuy_num();
      String tempBuyDate = vo.getBuy_date().substring(0, 10);
      String buyDate = tempBuyDate.replace("-","/");
   
      //환불완료나 배송완료 label이 있으면 구매취소 버튼 비활성화
      boolean cancel_ok = vo.getRefund_check().equals("환불완료"); 
      boolean order_ok = vo.getRefund_check().equals("배송완료"); 
      
      if(!cancel_ok) {
         mypage_cancel_btn.setOnAction(e->{
            order_cancel(buyDate,buyNum);
      });      
      }else if(cancel_ok) {
         mypage_cancel_btn.setDisable(true);
      }else if(order_ok) {
         mypage_cancel_btn.setDisable(true);
      }
      
   //--------------------------------------- 배송지확인
      
      
   }
   
   private void send_email(Buy_LogVO vo) {
      String host           = "smtp.naver.com";
      final String user      = "sunny42500@naver.com";
      final String password    = "wltjs4188";

      // 수신자 메일 주소
      String to     = Login_controller.log_c.getCustom_email();
      
      // Get the session object
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(user, password);
         }
      });
      
      // 메세지 설정 부분
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      
         //제목작성
         message.setSubject("[공인중견사] 굿즈몰 환불영수증 ");

         int quan = Integer.parseInt(vo.getBuy_quan());
         int price = Integer.parseInt(vo.getPrice());
         //내용작성
         message.setText("구매 번호 : "+vo.getBuy_num()
                     +"\n환불 품목 : " + vo.getProd_name()
                     +"\n가격 : " + vo.getPrice()
                     +"\n수량 : " + vo.getBuy_quan()
                     +"\n차감 포인트 : -" + (quan * price)/20 +"원" 
                     +"\n총 구매 금액 : " + (quan * price) + "원"
                     +"\n\n\n정상적으로 환불 처리 되었습니다"
                     + "\n이용해 주셔서 감사합니다");
         
         //메시지 전송!!
         Transport.send(message);
         System.out.println("환불영수증 전송 완료되었습니다.");
         Hide_Lbl.setText("등록된 email로 환불 영수증 발송");

       } catch (MessagingException ee) {
            ee.printStackTrace();
       } 
   }
   

   //환불취소 
   private void order_cancel(String buyDate, String buyNum) { 
      
      System.out.println(buyNum);
      boolean delcheck = alertConfirm("구매취소를 진행 하시겠습니까?");      
         
       if(delcheck) {
         System.out.println("yes");
      int checkNum = -1;
      try {
           SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
           Date FirstDate = format.parse(buyDate);
            Date SecondDate = new Date();
            
            long calDate =  SecondDate.getTime()- FirstDate.getTime(); 
            
            long calDateDays = calDate / ( 24*60*60*1000); 
            
            calDateDays = Math.abs(calDateDays);
            checkNum = (int) calDateDays;
            System.out.println(checkNum);
            if(checkNum<0) {
                 alertError("잘못된 날짜형식 입니다.");
              }else if(checkNum>=0 && checkNum<=7) {
                 Buy_LogVO rvo = new Buy_LogVO();
                 rvo.setBuy_num(buyNum);
                 mypage_cancel_btn.setDisable(true);
                 try  {
                    List<Buy_LogVO> list = Main.buy.getSearchBuy_log(rvo);
                    rvo = list.get(0);
                    System.out.println(rvo.getBuy_date());
                    rvo.setRefund_check("환불완료");
                    vo.setRefund_check("환불완료");
                    mypage_status_label.setText(vo.getRefund_check());
                  Main.buy.updateBuy_log(rvo);
                  System.out.println(buyNum);
                  mypage_cancel_btn.setDisable(true);
                  send_email(vo);
                  
                 } catch (RemoteException e) {
                  e.printStackTrace();
               }
              }
              else {
                 mypage_cancel_btn.setDisable(true);
                 alertError("구매후 7일이내만 환불이 가능합니다.");
              }
           }
           catch(ParseException e)
           {
              e.printStackTrace();
               // 예외 처리
           }
       }
   }
   private void setVbox() { //구매내역 출력
      mainBox.getChildren().clear();
      try {
         Buy_LogVO vo = new Buy_LogVO();
         vo.setCustom_id(Login_controller.log_c.getCustom_id()); 
         buyList = Main.gm_u.getSearchBuy_log(vo);
         list_size = buyList.size();
         for (int i = 0; i < buyList.size(); i++) {
            ProdVO pvo = new ProdVO();
            pvo.setProd_num(buyList.get(i).getProd_num());
            ProdVO imgTemp = Main.gm_u.getSearchProd(pvo);
            
            String buyNum = buyList.get(i).getBuy_num();
            String prodName = imgTemp.getProd_name();
            String imgName = imgTemp.getProd_img();
            String quan = buyList.get(i).getBuy_quan();
            String price = buyList.get(i).getPrice();
            String tempBuyDate = buyList.get(i).getBuy_date().substring(0, 10);
            String buyDate = tempBuyDate.replace("-","/");
            String state = buyList.get(i).getRefund_check();
            
            HBox H = new HBox();
            H.setPrefWidth(1036.0);
            H.setPrefHeight(50.0);
            H.setAccessibleText(i+"");
            
            //상품 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
            Label buyNum_lbl = new Label();
            buyNum_lbl.setText(buyNum);
            buyNum_lbl.setPrefWidth(77.0);
            buyNum_lbl.setPrefHeight(50.0);
            buyNum_lbl.setAlignment(Pos.CENTER);
            buyNum_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            //상품 이미지 담기 
            Image prod_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+imgName);
            ImageView view = new ImageView();
            view.setImage(prod_image);
            view.setFitWidth(100.0);
            view.setFitHeight(50.0);
            
            //상품명 라벨에 담기 
            Label prod_name_lbl = new Label();
            prod_name_lbl.setText(prodName);
            prod_name_lbl.setPrefWidth(230.0);
            prod_name_lbl.setPrefHeight(50.0);
            prod_name_lbl.setAlignment(Pos.CENTER);
            prod_name_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            HBox imgH = new HBox();
            imgH.setPrefWidth(330.0);
            imgH.setPrefHeight(50.0);
            imgH.setAccessibleText(i+"");
            imgH.getChildren().add(view);
            imgH.getChildren().add(prod_name_lbl);
            
            //수량 라벨에 담기
            Label prod_amount_lbl = new Label();
            prod_amount_lbl.setText(quan);
            prod_amount_lbl.setPrefWidth(71.0);
            prod_amount_lbl.setPrefHeight(50.0);
            prod_amount_lbl.setAlignment(Pos.CENTER);
            prod_amount_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            
            //가격 라벨에 담기
            Label price_lbl = new Label();
            price_lbl.setText(price);
            price_lbl.setPrefWidth(149.0);
            price_lbl.setPrefHeight(50.0);
            price_lbl.setAlignment(Pos.CENTER);
            price_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            //구매일 라벨에 담기
            Label date_lbl = new Label();
            date_lbl.setText(buyDate);
            date_lbl.setPrefWidth(158.0);
            date_lbl.setPrefHeight(50.0);
            date_lbl.setAlignment(Pos.CENTER);
            date_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            //상태 라벨에 담기
            Label state_lbl = new Label();
            state_lbl.setText(state);
            state_lbl.setPrefWidth(90.0);
            state_lbl.setPrefHeight(50.0);
            state_lbl.setAlignment(Pos.CENTER);
            state_lbl.setFont(Font.font(".HCR Dotum", 15));
            
            //HBox 에 출력할 순서대로 담기
            H.getChildren().add(buyNum_lbl);
            H.getChildren().add(imgH);
            H.getChildren().add(prod_amount_lbl);
            H.getChildren().add(price_lbl);
            H.getChildren().add(date_lbl);
            H.getChildren().add(state_lbl);
            
            //라인넣기
            HBox line = new HBox();
            line.setPrefWidth(1036.0);
            line.setPrefHeight(2.0);
            line.setStyle("-fx-background-color: #dadada;");
            
            // VBox 한줄 추가
            mainBox.getChildren().add(H);
            mainBox.getChildren().add(line);
         }
            
         } catch (RemoteException e) {
            e.printStackTrace();
      }
   }
   
   public boolean alertConfirm(String ment) {
      Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

      alertConfirm.setTitle("웹 페이지 메세지");
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

   public void infoMsg(String headerText, String msg) {
      Alert infoAlert = new Alert(AlertType.INFORMATION);
      infoAlert.setTitle("정보 확인");
      infoAlert.setHeaderText(headerText);
      infoAlert.setContentText(msg);
      infoAlert.showAndWait();
   }
   public void alertInfo(String temp){
      Alert alertErorr = new Alert(AlertType.ERROR);
      alertErorr.setTitle("ERROR");
      alertErorr.setContentText(temp);
      alertErorr.showAndWait();
   }
   public void alertError(String temp){
      Alert alertErorr = new Alert(AlertType.ERROR);
      alertErorr.setTitle("ERROR");
      alertErorr.setContentText(temp);
      alertErorr.showAndWait();
   }
}