package kr.or.ddit.rms.question;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface QuestionService extends Remote{
	public ArrayList<String> getAnswer(String keyword) throws RemoteException;
	String keywordFind(String question) throws RemoteException;
}
