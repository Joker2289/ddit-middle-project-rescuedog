package kr.or.ddit.rms.mainpage.sign_up.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.image.Image;

public class CaptChaApiProcess implements Serializable {
	
	public HashMap<String, Object> CaptChaApiCall(String _strCode) {
		HashMap<String, Object> retMap = new HashMap();
		String strNKey = getCaptchaNkey(_strCode);
		String strImageFileName = "";
		
		if( strNKey.length() > 0 ) {
			Random rd = new Random();
			String strRandom = "";
			for( int i = 1; i < 5; i++ ) {
				strRandom += String.format("%d", rd.nextInt(1000));
			}
					
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
			strImageFileName = "cap" + sdf.format(date);
			getCaptchaImageSave(strNKey, strImageFileName);
			
			String path = "\\\\Sem-pc\\공유폴더\\Rescuedog\\captcha_imgs\\" + strImageFileName + ".jpg";		 
			// 2. 스트림 준비
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			// 3. 파일 읽어오기
			Image img = new Image(bis);
		
			retMap.put("IMG", img);
			retMap.put("RETKEY", strNKey);			
		}
		
		return retMap;
	}
	
	public String getCaptchaNkey(String _strCode) {
		String strNKey = "";
		
		String clientId = "Fw848DJ_5BoGv53Ogemw";//애플리케이션 클라이언트 아이디값";		
        String clientSecret = "Yv5aT_4klf";//애플리케이션 클라이언트 시크릿값";
        try {
            String code = _strCode; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            // Never에서 OpenAPI를 통해 받은 CaptchaNkey를 화면에 전달하기 위해 Key만 따로 분리하는 작업
            String[] strTemp = null;
            String strTmp = "";
            strTemp = response.toString().split(":"); 
            for( int i = 0; i < strTemp.length; i++ ) {
            	strTmp = strTemp[i].replaceAll("\"", "");
            	if(strTmp.indexOf("key") <= -1 ) {
            		strNKey = strTmp.replaceAll("}", "");
            	}
            }
            
            System.out.println(response.toString());
            return strNKey;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return strNKey;
	}

	public void getCaptchaImageSave(String _strKey, String _strImageName) {
		String clientId = "Fw848DJ_5BoGv53Ogemw";//애플리케이션 클라이언트 아이디값";		
        String clientSecret = "Yv5aT_4klf";//애플리케이션 클라이언트 시크릿값";
        try {
            String key = _strKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
            String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                File f = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\captcha_imgs\\" + _strImageName + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.flush();
                outputStream.close();
                is.close();
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
            
            con.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	public HashMap APIExamCaptchaNkeyResult(String _strNKey, String _strCaptchaValue) {
		HashMap mapRet = new HashMap();
		
		String clientId = "Fw848DJ_5BoGv53Ogemw";//애플리케이션 클라이언트 아이디값";		
        String clientSecret = "Yv5aT_4klf";//애플리케이션 클라이언트 시크릿값";
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String key = _strNKey; // 캡차 키 발급시 받은 키값
            String value = _strCaptchaValue; // 사용자가 입력한 캡차 이미지 글자값
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            
            while ((inputLine = br.readLine()) != null) {
            	
            	 String[] strStep1 = null;
                 String strTmp = "";
                 strStep1 = inputLine.toString().split(",");
                 
                 String strKey = "";
                 String strValue = "";
            	String[] strStep2 = strStep1[0].split(":");
        		strKey = "result";
        		strValue = strStep2[1];
        		mapRet.put(strKey, strValue);                	
            }
            br.close();
            return mapRet;
        } catch (Exception e) {
            System.out.println(e);
        }    
        
        return mapRet;
	}
}
