package session;

import javax.naming.Context;
import javax.naming.InitialContext;

import session.SessionBeanLocal;

public class SessionBeanFactory {
	
	private static Context context;
	private static SessionBeanLocal sessionBean;
	
	private SessionBeanFactory(){
		
	}
	
	public static SessionBeanLocal getSessionBeanInstance(){
		try{
			context = new InitialContext();
			sessionBean = (SessionBeanLocal) context.lookup("SessionBean/local");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return sessionBean;
	}
	
	

}
