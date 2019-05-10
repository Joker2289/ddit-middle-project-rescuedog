package kr.or.ddit.rms.question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Gsonlee {
	static JsonParser parser = new JsonParser();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String question = sc.nextLine();
		String s = findKey(question);
		if(s!=null) {
			String ans = findAnswer(s);
			System.out.println(ans);
		}
		else {
			System.out.println("등록되어 있지 않은 질문입니다 ㅠ"); 
		}
	}

	private static boolean selData(JsonArray asJsonArray, String findS) {
		for (int j = 0; j < asJsonArray.size(); j++) {
			String s = asJsonArray.get(j).getAsJsonObject().get("text").getAsString();
			Pattern pattern = Pattern.compile("^"+s+"*"); 
			Matcher matcher = pattern.matcher(findS);
			while(matcher.find()) {
				System.out.println(s);
				return true;
			}
		}
		return false;
			
		
	}

	private static String findKey(String s) {
		String[] FindS = s.split(" ");
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

	private static String findAnswer(String findS) {
		
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
					System.out.println(output);
					return output;
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
}
