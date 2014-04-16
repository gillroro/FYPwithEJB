package controller;

import java.sql.Connection;
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
import entity.Skill;

public class SkillsAction extends ActionSupport implements Preparable {
	private Map<String, Object> session;
	private SessionBeanLocal ejbSessionBean;
	private static final long serialVersionUID = 1L;
	private String skillName;
	private String description;
	private List<Skill> skills = new ArrayList<Skill>();
	private Connection connection;
	private PreparedStatement addSkills;
	private PreparedStatement getSkills;
	private PreparedStatement addProof;
	private PreparedStatement addSkillToEmployee;
	private ResultSet results;
	private String proof;
	private Employee employee;
	private String skillId;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	
	public String getAllSkills() {
		skills = ejbSessionBean.getSkills();
		return SUCCESS;
	}
	
	public String display(){
		getAllSkills();
		if(skills!=null){
			return SUCCESS;
		}
		else {
			return "failure";
		}
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public String execute(){
		Skill skill;
		boolean merge = false;
		if(!skillId.equals("")){
			int id = Integer.parseInt(skillId);
			skill = ejbSessionBean.findSkillById(id);
			merge = true;
		}
		else {
			skill = new Skill();
		}
		skill.setName(skillName);
		skill.setDescription(description);
		if(merge){
			ejbSessionBean.merge(skill);
		}
		else {
			ejbSessionBean.persist(skill);
		}
		return SUCCESS;
		
	}
	
	public String addProof(){
		try {
		connection = ConnectionCreation.getConnection();
		addProof = connection.prepareStatement("INSERT INTO skill_proof(employee_id, proof) VALUES(?,?)");
		addProof.setInt(1, 1);
		addProof.setString(2,getProof());
		addProof.executeUpdate();
		addSkillToEmployee = connection.prepareStatement("INSERT INTO employee_skill(SkillId,EmployeeId) VALUES(?,?)");
		addSkillToEmployee.setInt(1,1);
		addSkillToEmployee.setInt(2, 1);
		addSkillToEmployee.executeUpdate();
		connection.close();
		addProof.close();
		addSkillToEmployee.close();
		return SUCCESS;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return "failure";
		}
		
		
		

	}
	
	
	public String forward(){
		return NONE;
	}
	@Override
	public void prepare() throws Exception {
		session = WebSession.getWebSessionInstance();
		ejbSessionBean = SessionBeanFactory.getSessionBeanInstance();
		employee = (Employee) session.get("employee");
		
	}




}
