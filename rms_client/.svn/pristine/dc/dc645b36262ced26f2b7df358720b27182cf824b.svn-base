package kr.or.ddit.rms.admin.goods_mall.goods_view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.rms.user.goods_mall.goods_view.U_goods_view_controller;

public class A_goods_detail_view_controller implements Initializable {


	@FXML JFXButton Close_Btn;		//닫기 버튼
	
	@FXML Label Detail_Pname_Lbl;		//상품이름 라벨
	@FXML Label Detail_Price_Lbl;		//상품가격 라벨
	@FXML Label Detail_Point_Lbl;		//적립포인트 라벨
	
	@FXML JFXTextArea Detail_Content_Txt;	//상품 설명 텍스트

	@FXML ImageView Detail_Goods_imgv;		//상품 이미지 뷰
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		//이미지 세팅
		String[] prod_info = A_goods_view_controller.log_img.getAccessibleText().split("/");
		Image img = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+prod_info[2]);
//		Image img = new Image("image/prod/"+prod_info[2]);
		Detail_Goods_imgv.setImage(img);
		
		//상품명 세팅
		Detail_Pname_Lbl.setText(prod_info[0]);
		
		//상품가격 세팅
		Detail_Price_Lbl.setText(prod_info[1]);
		
		//상품정보 세팅
		Detail_Content_Txt.setText(prod_info[3]);
		
		//포인트정보 세팅
		int int_price = Integer.parseInt(prod_info[1]);
		Detail_Point_Lbl.setText(int_price / 20+"");
		
		//닫기버튼 클릭
		Close_Btn.setOnAction(e->{
			A_goods_view_controller.goods_view_dialog.close();
		});
	}
}
