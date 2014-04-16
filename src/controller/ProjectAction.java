package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import session.SessionBeanFactory;
import session.SessionBeanLocal;
import session.WebSession;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import database.ConnectionCreation;
import entity.Employee;
import entity.Project;

public class ProjectAction extends ActionSupport implements Preparable {

	
	private static final long serialVersionUID = 1L;
	
	private String projectName;
	private String employeeName;
	private Date startDate;
	private Date endDate;
	private String department;
	private Connection connection;
	private PreparedStatement addProject;
	private PreparedStatement getProjects;
	private PreparedStatement deleteProject;
	private PreparedStatement getEmployees;
	private ResultSet results;
	private List<Project> projects = new ArrayList<Project>();
	private List<Employee> members = new ArrayList<Employee>();
	private Map<String, Object> session;
	private SessionBeanLocal ejbSessionBean;
	private String projectId;
	private Employee employee;
	
	@Override
	public void prepare() throws Exception {
		session = WebSession.getWebSessionInstance();
		ejbSessionBean = SessionBeanFactory.getSessionBeanInstance();
		employee = (Employee) session.get("employee");
	}
	
	public String createProject(){
		Project project;
		boolean merge = false;
		if(!projectId.equals("")){
			int id = Integer.parseInt(projectId);
			project = ejbSessionBean.findProjectById(id);
			merge = true;
		}
		else{
			project = new Project();
		}
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setDepartment(department);
		if(merge){
			ejbSessionBean.merge(project);
		}
		else{
			ejbSessionBean.persist(project);
		}
//		try {
//			connection = ConnectionCreation.getConnection();
//			addProject = connection.prepareStatement("INSERT INTO project(projectName, startDate, endDate, department) VALUES (?,?,?,?)");
//			addProject.setString(1, getProjectName());
//			addProject.setDate(2, getStartDate());
//			addProject.setDate(3, getEndDate());
//			addProject.setString(4, getDepartment());
//			addProject.executeUpdate();
//			connection.close();
//			addProject.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return SUCCESS;
	}
	
	public String deleteProject() throws SQLException{
		getAllProjects();
		for(int i=0; i< projects.size(); i++){
			if(projects.get(i).getProjectName().equalsIgnoreCase(projectName)){
				connection = ConnectionCreation.getConnection();
				deleteProject = connection.prepareStatement("DELETE FROM PROJECT WHERE projectName= ?");
				deleteProject.setString(1, projectName);
				deleteProject.executeUpdate();
			}
		}
		connection.close();
		deleteProject.close();
		
		return SUCCESS;
		
		
	}
	
	public String assignEmployeeToProject(){
		getAllEmployees();
		getAllProjects();
		return SUCCESS;
	}
	
	public String getAllProjects(){
		projects = ejbSessionBean.getProjects();
		return SUCCESS;
	}
	
	public String getAllEmployees(){
		members = ejbSessionBean.getEmployeesByUserType("employee");
		return SUCCESS;
	}
	
	public String displayAllProjects(){
		getAllProjects();
		if(projects != null){
			return SUCCESS;
		}
		else{ 
			return "failure";
		}
	}
	
	public String forward(){
		return NONE;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Employee> getMembers() {
		return members;
	}

	public void setMembers(List<Employee> members) {
		this.members = members;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
