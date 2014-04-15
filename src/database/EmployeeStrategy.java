package database;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import entity.Employee;
import session.SessionBeanFactory;
import session.SessionBeanLocal;
import session.WebSession;

public class EmployeeStrategy implements UserStrategy {
	
	private Map<String, Object> session = WebSession.getWebSessionInstance();
	private SessionBeanLocal ejbSessionBean = SessionBeanFactory.getSessionBeanInstance();

	@Override
	public boolean login(String username, String password) {
		
		session.put("Employee", "employee");
		return true;
	}

	@Override
	public void logout() {
		session = ActionContext.getContext().getSession();
		session.remove("customer");
		session.clear();

	}

	@Override
	public void register(String username, String password, String firstName,
			String surname, String address, int salary, String userType) {
		

	}

}
