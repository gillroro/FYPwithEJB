package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import entity.Job;

public class JobBoardAction extends ActionSupport implements Preparable {


	private static final long serialVersionUID = 1L;
	private List<Job> jobs = new ArrayList<Job>();;
	private String job_name;
	private String description;
	private String department;
	private Employee employee;
	private Job job;
	private Date closing_date;
	private Connection connection;
	private PreparedStatement addJob;
	private PreparedStatement getJobs;
	private PreparedStatement getJobByDepartment;
	private ResultSet results;
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
	private Map<String, Object> session;
	private SessionBeanLocal ejbSessionBean;
	private String jobId;
	
	@Override
	public void prepare() throws Exception {
		session = WebSession.getWebSessionInstance();
		ejbSessionBean = SessionBeanFactory.getSessionBeanInstance();
		
	}

	public String forward(){
		return NONE;
	}

	public String execute() throws ClassNotFoundException, SQLException{
		
//		connection = ConnectionCreation.getConnection();
//		addJob = connection.prepareStatement("INSERT INTO Job(job_name, description, department, closing_date) VALUES(?,?,?,?)");
//		addJob.setString(1, getJob_name());
//		addJob.setString(2, getDescription());
//		addJob.setString(3, getDepartment());
//		addJob.setDate(4, (java.sql.Date)closing_date);
//		addJob.executeUpdate();
//		WebSession.put("Job", job);
//		//		job.setDepartment(department);
//		//		job.setJobDesc(description);
//		//		job.setJobName(jobName);
//		//		jobs.add(job);
//		
		Job job;
		boolean merge = false;
		if(!jobId.equals("")){
			int id = Integer.parseInt(jobId);
			job = ejbSessionBean.findJobById(id);
			merge = true;
		}
		else {
			job = new Job();
		}
		job.setJobName(job_name);
		job.setJobDesc(description);
		job.setDepartment(department);
		job.setClosing_date(closing_date);
		if(merge){
			ejbSessionBean.merge(job);
		}
		else {
			ejbSessionBean.persist(job);
		}
		return SUCCESS;
	}

	

	public Date getClosing_date() throws ParseException {
		return (Date) format2.parse(format2.format(closing_date));
		
	}

	public void setClosing_date(Date closing_date) {
		this.closing_date = closing_date;
	}

	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getAllJobs(){
		jobs = ejbSessionBean.getJobs();
		return SUCCESS;
	}

	public String displayList(){
		getAllJobs();
		if(jobs != null){
			for(int i=0; i< jobs.size(); i++){
				System.out.println(jobs.get(i));
			}
			return SUCCESS;
		}
		else{
			return "failure";
		}
	}

	public String getJobByDepartment(){
		jobs = ejbSessionBean.findJobByDepartment(department);
		return "success";
	}
	
	public String displayDepartmentList(){
		getJobByDepartment();
		if(jobs != null){
			for(int i=0; i< jobs.size(); i++){
				System.out.println(jobs.get(i).getJobName());
			}
			return SUCCESS;
		}
		else{
			return "failure";
		}
		
	}
	
	public String displayApplications(){
		return NONE;
	}
	
	public String jobApplication(){
		System.out.println();
		return NONE;
	}

}
