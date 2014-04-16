package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import session.SessionBeanFactory;
import session.SessionBeanLocal;
import session.WebSession;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import database.ConnectionCreation;
import database.EmployeeStrategy;
import database.UserContext;
import entity.Employee;

public class UserLoginAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private SessionBeanLocal ejbSessionBean;
	private Connection connection;
	private PreparedStatement checkDuplicate;
	private ResultSet results;
	private String username;
	private String firstName;
	private String surname;
	private String address;
	private int salary;
	private String password;
	private String comment;
	private String userType;
	private Employee employee = new Employee();
	
	@Override
	public void prepare() throws Exception {
		session = WebSession.getWebSessionInstance();
		ejbSessionBean = SessionBeanFactory.getSessionBeanInstance();
		
	}
	
	public String employeeLogin(){
		UserContext context = new UserContext(new EmployeeStrategy());
		if(context.login(username, password)){
			employee = (Employee) session.get("employee");
			return "success";
		}
		else{
			return "failure";
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() throws ClassNotFoundException, SQLException  {
		connection = ConnectionCreation.getConnection();
		checkDuplicate = connection.prepareStatement("SELECT username, user_type FROM employee WHERE username=? AND password =?");
		checkDuplicate.setString(1, getUsername());
		checkDuplicate.setString(2, getPassword());
		results = checkDuplicate.executeQuery();
		if (results.next()) {
			if(results.getString("user_type").equalsIgnoreCase("manager")){
				connection.close();
				checkDuplicate.close();
				WebSession.put("CurrentUser", employee);
				return "manager";
			}
			else{
			connection.close();
			checkDuplicate.close();
			WebSession.put("CurrentUser", employee);
			return "success";
			}
		}
		else {
			connection.close();
			checkDuplicate.close();
			return "failure";
		}
	}

	public String employeeRegister(){
		UserContext context = new UserContext(new EmployeeStrategy());
		context.register(username, password, firstName, surname, address, salary, userType);
		employee = (Employee) session.get("customer");
		return "success";
	}

	public String logout() {
		UserContext context = new UserContext(new EmployeeStrategy());
		context.logout();
		return "success";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
