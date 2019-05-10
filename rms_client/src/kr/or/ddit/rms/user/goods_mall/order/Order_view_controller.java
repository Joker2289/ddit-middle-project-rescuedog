package kr.or.ddit.rms.user.goods_mall.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.user.goods_mall.cart_view.Cart_view_controller;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public class Order_view_controller implements Initializable{
	@FXML AnchorPane Order_Main_Pane;

	@FXML Label Order_Amount_Lbl;		//총수량 라벨	
	@FXML JFXButton Order_Pay_Btn;		//결제 버튼
	@FXML Label Order_Message_Lbl;		//경고메시지 라벨
	
	@FXML Label Order_Name_Lbl;			//구매자 이름 라벨
	@FXML Label Order_Email_Lbl;		//구매자 이메일 라벨
	@FXML Label Order_Tel_Lbl;			//구매자 전화번호 라벨
	
	@FXML JFXTextField Order_Name_Txt;		//배송정보 이름 라벨
	@FXML JFXTextField Order_Addr_Txt;		//배송정보 주소 라벨
	@FXML JFXTextField Order_Tel_Txt;		//배송정보 연락처 라벨
	@FXML JFXTextField Order_Request_Txt;	//배송정보 요청사항 라벨
	
	@FXML Label Order_Price_Lbl2;			//결제정보 총금액 라벨
	@FXML Label Order_Point_Lbl;			//결제정보 포인트 라벨
	@FXML JFXComboBox Order_Selcard_Comb;	//결제정보 카드사 콤보박스
	@FXML JFXTextField Order_Card1_Txt;		//결제정보 카드번호1 텍스트
	@FXML JFXTextField Order_Card2_Txt;		//결제정보 카드번호2 텍스트
	@FXML JFXTextField Order_Card3_Txt;		//결제정보 카드번호3 텍스트
	@FXML JFXTextField Order_Card4_Txt;		//결제정보 카드번호4 텍스트
	@FXML JFXTextField Order_CVC_Txt;		//결제정보 CVC번호 텍스트
	@FXML JFXButton Close_Btn;				//창닫기 버튼
	
	@FXML Label Order_Price_Lbl;			//총금액 라벨
//	cart_view_controller cvc;
	
	int point = 0;
	int my_point = 0;
	private Cart_view_controller cvc;

	
	public void setcon(Cart_view_controller cvc) {
		this.cvc = cvc; 
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//창닫기 버튼 클릭
		Close_Btn.setOnAction(e->{
			Cart_view_controller.cart_view_dialog.close();
		});
		
		//구매 종류
		Order_Amount_Lbl.setText("("+Cart_view_controller.list_size+" 개)");
		
		//구매자정보 세팅
		Order_Name_Lbl.setText(Login_controller.log_c.getCustom_name());
		Order_Email_Lbl.setText(Login_controller.log_c.getCustom_email());
		Order_Tel_Lbl.setText(Login_controller.log_c.getCustom_tel());
		
		//배송정보 세팅
		Order_Name_Txt.setText(Login_controller.log_c.getCustom_name());
		Order_Addr_Txt.setText(Login_controller.log_c.getCustom_addr());
		Order_Tel_Txt.setText(Login_controller.log_c.getCustom_tel());
		Order_Request_Txt.setPromptText("요청사항을 입력주세요");
		
		//결제정보 세팅
		Order_Price_Lbl2.setText(Cart_view_controller.all_price+"");
		Order_Point_Lbl.setText((Cart_view_controller.all_price/20)+"");
		Order_Price_Lbl.setText(Cart_view_controller.all_price+"");
		
		//콤보박스에 카드사 세팅
		try {
			List<CardVO> list = Main.gm_u.getCardAll();
			for(int i=0; i<list.size(); i++) {
				Order_Selcard_Comb.getItems().add(list.get(i).getCard_name());
			}
		} catch (RemoteException e2) {
			
		}
		
	
		
		//결제 버튼 클릭
		Order_Pay_Btn.setOnAction(e->{
			if(Order_Card1_Txt.getText().isEmpty() || Order_Card2_Txt.getText().isEmpty()
					|| Order_Card3_Txt.getText().isEmpty() || Order_Card4_Txt.getText().isEmpty()
					|| Order_CVC_Txt.getText().isEmpty()) {
				
					alertError("결제정보 입력 란이 비었습니다");
				return;
			}
			payment();
			
			//결제창 닫아주기
			cvc.setVbox();
			//cvc
			//민규방법
			//cvc = main_page_controller.fxmlLoad.getController();
			//cvc.
			Cart_view_controller.cart_view_dialog.close();
			
			
			alertTest("결제 완료!! \n- 총 구매금액 : " + Order_Price_Lbl.getText() + "\n- MyPoint : " + my_point +"   ( + " + point + ")" + "\n- 결제 후 포인트 : " + (my_point+point) );
		});
		
	}//initialize 끝
	
	//결제메서드 
	void payment() {
		try {
			
			for(int i=0; i<Cart_view_controller.cart_list.size(); i++) {
				String prod_num = Cart_view_controller.cart_list.get(i).getProd_num();  //상품번호
				
				ProdVO pvo = new ProdVO();
				pvo.setProd_num(prod_num);
				ProdVO rpvo = Main.gm_u.getSearchProd(pvo);
				
				String prod_name = rpvo.getProd_name();									//상품명
				String custom_id = Login_controller.log_c.getCustom_id();				//회원ID
				String refund_check = "배송준비중";										//환불여부
				String cnt = Cart_view_controller.cart_list.get(i).getCart_cnt(); 		//수량
				String card_name = (String) Order_Selcard_Comb.getValue();													//카드사번호
				String price = rpvo.getPrice();											//가격
				String buy_num = Main.gm_u.getBuy_num();								
				
				if(buy_num==null) {
					buy_num = "0";
				}
				
				Buy_LogVO bvo = new Buy_LogVO();
				bvo.setBuy_num((Integer.parseInt(buy_num)+1)+"");
				bvo.setBuy_quan(cnt);
				bvo.setProd_name(prod_name);
				bvo.setPrice(price);
				bvo.setRefund_check(refund_check);
				bvo.setCard_name(card_name);
				bvo.setProd_num(prod_num);
				bvo.setCustom_id(custom_id);
				
				//구매기록 DB 추가
				Main.gm_u.insertBuy_log(bvo);
				
				
				
			}
			//로그인한 회원의 포인트 정보 가져오기
			CustomVO cvo = new CustomVO();
			cvo.setCustom_id(Login_controller.log_c.getCustom_id());
			List<CustomVO> sc = Main.gm_u.getSearchCustom(cvo);
			my_point = Integer.parseInt(sc.get(0).getCustom_point());
			
			//포인트 적립
			CustomVO vo = new CustomVO();
			vo.setCustom_id(Login_controller.log_c.getCustom_id());
			vo.setCustom_point((Cart_view_controller.all_price/20 + my_point) +"");
			Main.gm_u.updatePoint(vo);
			System.out.println(Cart_view_controller.all_price/20  + "원 포인트 적립  " + my_point + "원래 포인트");
			point = Cart_view_controller.all_price/20;
			
			//id값을주고 장바구니 DB 삭제
			Main.gm_u.deleteAllCart(Login_controller.log_c.getCustom_id());
			Cart_view_controller.payFlag=true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("결제완료");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
	
	private void alertError(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("경고");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
