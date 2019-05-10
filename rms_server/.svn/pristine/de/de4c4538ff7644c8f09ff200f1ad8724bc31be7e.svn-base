package kr.or.ddit.rms.question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class QuestionServiceImpl extends UnicastRemoteObject implements QuestionService {
	private static final String URL = "https://gateway.watsonplatform.net/assistant/api";
	public static final String WORKSPACE_ID= "0aa6a19b-76ac-45ee-aa7f-720213cd0ab0";
	public static final String API_KEY = "MrZHx0XRwnqgCL3WL5tVW-UycU4U-aeuRox_jjByyWZX";
	public static final String VERSION = "2018-03-19";
	private Assistant assistant;
	public QuestionServiceImpl() throws RemoteException {
		
		IamOptions options = new IamOptions.Builder()
			    .apiKey(API_KEY)
			    .build();

		assistant = new Assistant(VERSION, options);

		assistant.setEndPoint(URL);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Watson-Learning-Opt-Out", "true");
		assistant.setDefaultHeaders(headers);
	}

	JsonParser parser = new JsonParser();
	@Override
	public ArrayList<String> getAnswer(String question) throws RemoteException{
		InputData input = new InputData.Builder(question).build();

		MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
		  .input(input)
		  .build();

		MessageResponse response = assistant.message(options).execute();
		
		JsonObject jsonObject=new JsonObject();
		JsonParser jsonParser=new JsonParser();
		
		Object obj = jsonParser.parse(response.toString());
		
		jsonObject = (JsonObject) obj;
		
		obj = jsonParser.parse(jsonObject.get("output").toString());
		
		jsonObject = (JsonObject) obj;
		
		JsonArray list = (JsonArray)jsonObject.get("text");
		
		Iterator<JsonElement> it = list.iterator();
		System.out.println("WATSON : ");
		ArrayList<String> texts = new ArrayList<>();
		while(it.hasNext()){
			texts.add(it.next().getAsString());
		}
		return texts;
	}

	
	
	private String findAnswer(String findS){
		JsonObject jsonO;
		try {
			jsonO = (JsonObject) parser.parse(new FileReader("src/kr/or/ddit/rms/question/rescuedog.json"));
			
			JsonArray str = jsonO.get("intents").getAsJsonArray();
			JsonArray node = jsonO.get("dialog_nodes").getAsJsonArray();
			for (int i = 0; i < node.size(); i++) {
				
				JsonObject temp = str.get(i).getAsJsonObject();
				JsonObject jsonElement = (JsonObject) node.get(i);
				if(jsonElement.get("title")!=null&&jsonElement.get("title").getAsString().equals(findS)) {
					String output = (String)jsonElement.get("output").getAsJsonObject().
							get("generic").getAsJsonArray().get(0).getAsJsonObject()
							.get("values").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
					if(output!=null) {
						return output;
					}
					else {
						return "등록되지 않은 질문입니다 ㅠ";
					}
				}
			}
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String keywordFind(String question) throws RemoteException {
		String[] FindS = question.split(" ");
		JsonObject jsonO;
		try {
			jsonO = (JsonObject) parser.parse(new FileReader("src/kr/or/ddit/rms/question/rescuedog.json"));
			for (int i = 0; i < FindS.length; i++) {
				JsonArray str = jsonO.get("intents").getAsJsonArray();
				for (int j = 0; j < str.size(); j++) {
					JsonObject temp = str.get(j).getAsJsonObject();
					boolean check = selData(temp.get("examples").getAsJsonArray(),FindS[i]);
					if(check) {
						System.out.println(temp.get("intent").getAsString());
						return temp.get("intent").getAsString();
					}
					
				}
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	private static boolean selData(JsonArray asJsonArray, String findS){
		for (int j = 0; j < asJsonArray.size(); j++) {
			String s = asJsonArray.get(j).getAsJsonObject().get("text").getAsString();
			Pattern pattern = Pattern.compile(s); 
			Matcher matcher = pattern.matcher(findS);
			while(matcher.find()) {
				System.out.println(s);
				return true;
			}
		}
		return false;
			
		
	}

}
