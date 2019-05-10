package kr.or.ddit.rms.user.goods_mall.goods_view;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.user.goods_mall.order.Order_view_controller;
import kr.or.ddit.rms.vo.CartVO;

public class U_goods_detail_view_controller implements Initializable {

	@FXML JFXButton Detail_Buy_Btn;		//구매 버튼
	@FXML JFXButton Detail_Cart_Btn;	//카트 담기 버튼
	@FXML JFXButton Close_Btn;			//닫 버튼
	@FXML JFXButton Detail_Minus_Btn;	//수량 플러스버튼
	@FXML JFXButton Detail_Plus_Btn;	//수량 마이너스버튼
	
	@FXML Label Detail_Pname_Lbl;		//상품이름 라벨
	@FXML Label Detail_Price_Lbl;		//상품가격 라벨
	@FXML Label Detail_Point_Lbl;		//적립포인트 라벨
	@FXML Label Detail_APrice_Lbl;		//총 구매 가격 라벨
	
	@FXML JFXTextArea Detail_Content_Txt;	//상품 설명 텍스트
	@FXML JFXTextField Detail_Amount_Txt;	//상품 수량 텍스트

	@FXML ImageView Detail_Goods_imgv;		//상품 이미지 뷰
	
	public static Stage goods_detail_view_dialog;
	
	int tmp_Amount = 1;
	
	public static String name = null;		
	public static String price = null;
	public static String point = null;
	public static String qaun = null;
	
	public static String[] prod_info = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//이미지 세팅
		prod_info = U_goods_view_controller.log_img.getAccessibleText().split("/");
		Image img = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+prod_info[2]);
		Detail_Goods_imgv.setImage(img);
		
		//상품명 세팅
		Detail_Pname_Lbl.setText(prod_info[0]);
		
		//상품가격 세팅
		Detail_Price_Lbl.setText(prod_info[1]);
		Detail_APrice_Lbl.setText(prod_info[1]);
		
		//상품정보 세팅
		Detail_Content_Txt.setText(prod_info[3]);
		
		//포인트정보 세팅
		int int_price = Integer.parseInt(prod_info[1]);
		Detail_Point_Lbl.setText(int_price / 20+"");
		
		//닫기 버튼
		Close_Btn.setOnAction(e->{
			U_goods_view_controller.goods_view_dialog.close();
		});
		
		//구매 버튼 클릭
		Detail_Buy_Btn.setOnAction(e->{
			name = Detail_Pname_Lbl.getText();
			price = Detail_APrice_Lbl.getText();
			point = Detail_Point_Lbl.getText();
			qaun = Detail_Amount_Txt.getText();
			goto_order();
		});
		
		//수량 증가 버튼
		Detail_Plus_Btn.setOnAction(e->{
			
			tmp_Amount++;
			Detail_Amount_Txt.setText(tmp_Amount+"");
			
			if(tmp_Amount >= 10) {
				Detail_Plus_Btn.setVisible(false);
			}else if(tmp_Amount >= 1) {
				Detail_Minus_Btn.setVisible(true);
			}
			
			int All_price = int_price * tmp_Amount;
			Detail_APrice_Lbl.setText(All_price+"");
			
			int point2 = All_price / 20; 
			Detail_Point_Lbl.setText(point2 + "");
			
		});
		
		//수량 감소 버튼
		Detail_Minus_Btn.setOnAction(e->{
			
			tmp_Amount--;
			Detail_Amount_Txt.setText(tmp_Amount+"");
			if(tmp_Amount <= 1) {
				Detail_Minus_Btn.setVisible(false);
			}else if(tmp_Amount < 10) {
				Detail_Plus_Btn.setVisible(true);
			}
			
			int All_price = int_price * tmp_Amount;
			Detail_APrice_Lbl.setText(All_price+"");
			
			int point2 = All_price / 20; 
			Detail_Point_Lbl.setText(point2 + "");
		});
		
		
		//CART 버튼 클릭시 장바구니 추가
		Detail_Cart_Btn.setOnAction(e->{
			CartVO cvo = new CartVO();
			
			//먼저 custom_id 와 prod_num 이 있는지 확인한다 
			
			try {
				
				//먼저 custom_id 와 prod_num 이 있는지 확인한다 
				cvo.setCustom_id(Login_controller.log_c.getCustom_id());
				cvo.setProd_num(prod_info[4]);
				List<CartVO> search_result = Main.gm_u.getSearchCart(cvo);
				
				//검색 결과가 있다면 상품 수량 증가(update문)
				if(search_result.size() != 0) {
					//검색결과로 수량을 가져옴
					int cnt = Integer.parseInt(search_result.get(0).getCart_cnt());
					 
					//아이디와 상품번호가 같은 컬럼의 수량에 +1
					cvo.setCustom_id(Login_controller.log_c.getCustom_id());
					cvo.setProd_num(prod_info[4]);
					cvo.setCart_cnt((cnt+Integer.parseInt(Detail_Amount_Txt.getText()))+"");
					 
					 //업데이트
					 Main.gm_u.updateCart(cvo);
					 
					 U_goods_view_controller.goods_view_dialog.close();
					 
					 alertTest("'"+ prod_info[0] + "'  상품 수량 증가");
				 }
				 
				//검색결과가 없다면 상품 추가
				if(search_result.size() == 0) {
					//상품결과가 없다면 장바구니 추가(insert문)
					cvo.setProd_num(prod_info[4]);
					cvo.setCustom_id(Login_controller.log_c.getCustom_id());
					cvo.setCart_cnt(Detail_Amount_Txt.getText());
					int cnt = Main.gm_u.insertCart(cvo);
					
					U_goods_view_controller.goods_view_dialog.close();
					 
					 alertTest("'"+ prod_info[0] + "' 상품 장바구니 추가완료");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
	}
	
	
	
	private void goto_order() {
		goods_detail_view_dialog = new Stage(StageStyle.UTILITY);
		
		goods_detail_view_dialog.initModality(Modality.APPLICATION_MODAL);
		//dialog.initOwner();
		goods_detail_view_dialog.setTitle("주문 / 결제 창");
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("detail_order_view.fxml"));
			
			Scene scene = new Scene(parent);
			
			goods_detail_view_dialog.setScene(scene);
			goods_detail_view_dialog.setResizable(false);//크기고정
			goods_detail_view_dialog.show();
					
		} catch(IOException ee) {
			ee.printStackTrace();
		}
		
	}



	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("추가 완료");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
