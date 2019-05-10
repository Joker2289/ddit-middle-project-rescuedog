package kr.or.ddit.rms.admin.missing_animal_board;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MakeADAController implements Initializable{

	@FXML JFXButton Layout_logout_btn;
	@FXML JFXButton Layout_mypage_btn;
	@FXML JFXButton Layout_memCenter_btn;
	@FXML JFXButton Layout_select_btn;
	@FXML Label layout_name_label;
	@FXML JFXButton layout_logo_btn;
	@FXML ImageView Layout_logo_img;
	@FXML JFXButton Layout_rd_btn;
	@FXML JFXButton Layout_protect_btn;
	@FXML JFXButton Layout_adopt_btn;
	@FXML JFXButton Layout_shop_btn;
	@FXML JFXButton Layout_spon_btn;
	@FXML JFXButton Layout_vb_btn;
	@FXML JFXButton Layout_comu_btn;
	@FXML JFXButton LeftMenu_gotofind_btn;
	@FXML JFXButton LeftMenu_gotoAD_btn;
	@FXML JFXButton makeADimg_btn;
	@FXML JFXTextField makeADimg_tf;
	@FXML JFXTextField makeADTitle_tf;
	@FXML JFXTextField makeADdog_tf;
	@FXML JFXTextField makeADgender_tf;
	@FXML JFXTextArea makeADcontent_ta;
	@FXML JFXButton makeADok_btn;
	@FXML JFXButton makeADcancel_btn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeADok_btn.setOnAction(e->adCheck());
		makeADcancel_btn.setOnAction(e->gotoAdList());
	}
	private void gotoAdList() {
		if(makeADimg_tf.getText().isEmpty()) {
			
		}else if(makeADTitle_tf.getText().isEmpty()) {
			
		}else if(makeADgender_tf.getText().isEmpty()) {
			
		}else if(makeADdog_tf.getText().isEmpty()) {
			
		}else if(makeADcontent_ta.getText().isEmpty()) {
			
		}else {
			
		}
	}
	private void adCheck() {
		
	}
	
	private void makeAd() {
		Document doc = new Document(PageSize.A4, 20, 20, 30, 30);  // 페이지 사이즈와 좌우상하 여백

		try {
			PdfWriter.getInstance(doc, new FileOutputStream("my05.pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

		doc.open();

		try {
			doc.add(new Paragraph(" "));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Image jpg=null;
		try {
			jpg = Image.getInstance("D:/TEMP/sample.jpg");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doc.add(jpg);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		try {
			doc.add(new Paragraph(" "));
		} catch (DocumentException e) {
			e.printStackTrace();
		}



		String resPath = "test/itext/fonts/malgun.ttf";

		BaseFont bf = null;
		try {
			bf = BaseFont.createFont(resPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Font cellTitleFont = new Font(bf,10,Font.BOLD);

		Font cellNormalFont = new Font(bf,10, Font.NORMAL);


		PdfPTable table1 = new PdfPTable(4);

		table1.setHorizontalAlignment(Element.ALIGN_CENTER);

		table1.setTotalWidth(500f);

		        table1.setLockedWidth(true);

		        PdfPCell cell = null;

		        //row 1

		        cell = new PdfPCell(new Paragraph("강아지 이름(한글)", cellTitleFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph(" 홍길동", cellNormalFont));

		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("이름(영어)", cellTitleFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("Hong Gildong", cellNormalFont));

		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        // row 2

		        cell = new PdfPCell(new Paragraph("주민등록번호", cellTitleFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("123456-1234567", cellNormalFont));

		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        cell.setColspan(3);

		        table1.addCell(cell);

		        //row 3

		        cell = new PdfPCell(new Paragraph("전화번호", cellTitleFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("1111-1111", cellNormalFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("휴대폰", cellTitleFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        cell = new PdfPCell(new Paragraph("010-2222-2222", cellNormalFont));

		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		        cell.setFixedHeight(21);

		        table1.addCell(cell);

		        try {
					doc.add(table1);
					doc.add(new Paragraph(" "));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
		        doc.close();

	}

}
