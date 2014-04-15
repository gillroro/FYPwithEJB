package util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Appraisal;
import entity.Employee;
import entity.Job;
import entity.Project;
import entity.Skill;

/**
 * Session Bean implementation class FYPSessionBean
 */
@Stateless
public class SessionBean2 implements SessionBeanLocal1 {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean persist(Object object) {
		entityManager.persist(object);
		return true;
	}

	@Override
	public boolean merge(Object object) {
		entityManager.persist(object);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectByProjectName(String projectName) {
		List<Project> projects = new ArrayList<Project>();
		try{
			projects = (List<Project>) entityManager.createNamedQuery("Project.findByProjectName").setParameter("search_projectName","%"+ projectName + "%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return projects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectByDepartment(String department) {
		List<Project> projects = new ArrayList<Project>();
		try{
			projects = (List<Project>) entityManager.createNamedQuery("Project.findByDepartment").setParameter("search_department", "%" + department+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public Project findProjectById(int project_id) {
		Project project = (Project) entityManager.createNamedQuery("Project.findById").setParameter("project_id", project_id).getSingleResult();
		return project;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findJobByJobName(String jobName) {
		List<Job> jobs = new ArrayList<Job>();
		try{
			jobs = (List<Job>) entityManager.createNamedQuery("Job.findByJobName").setParameter("search_jobName", "%"+jobName+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jobs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findJobByDepartment(String department) {
		List<Job> jobs = new ArrayList<Job>();
		try{
			jobs = (List<Job>) entityManager.createNamedQuery("Job.findByDepartment").setParameter("search_department", "%"+department+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jobs;
	}

	@Override
	public Job findJobById(int job_id) {
		Job job = (Job) entityManager.createNamedQuery("Job.findById").setParameter("job_id", job_id).getSingleResult();
		return job;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> findSkillBySkillName(String skillName) {
		List<Skill> skills = new ArrayList<Skill>();
		try{
			skills= (List<Skill>) entityManager.createNamedQuery("Skill.findBySkillName").setParameter("search_skillName", "%"+skillName+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return skills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> findSkillByDescription(String description) {
		List<Skill> skills = new ArrayList<Skill>();
		try{
			skills= (List<Skill>) entityManager.createNamedQuery("Skill.findByDescription").setParameter("search_description", "%"+description+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return skills;
	}

	@Override
	public Skill findSkillById(int skill_id) {
		Skill skill = (Skill) entityManager.createNamedQuery("Skill.findById").setParameter("skill_id", skill_id).getSingleResult();
		return skill;
	}

	@Override
	public Appraisal findAppraisalById(int appraisal_id) {
		Appraisal appraisal = (Appraisal) entityManager.createNamedQuery("Appraisal.findById").setParameter("appraisal_id", appraisal_id).getSingleResult();
		return appraisal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appraisal> findAppraisalByEmployee(Employee employee) {
		List<Appraisal> appraisals = new ArrayList<Appraisal>();
		try{
			appraisals= (List<Appraisal>) entityManager.createNamedQuery("Appraisal.findByEmployee").setParameter("search_employee", "%"+employee+"%").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return appraisals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects() {
		List<Project> projects = new ArrayList<Project>();
		try{
			projects = (List<Project>) entityManager.createNamedQuery("Project.findAll").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return projects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobs() {
		List<Job> jobs = new ArrayList<Job>();
		try {
			jobs = (List<Job>) entityManager.createNamedQuery("Job.findAll").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jobs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getSkills() {
		List<Skill> skills = new ArrayList<Skill>();
		try{
			skills = (List<Skill>) entityManager.createNamedQuery("Skill.findAll").getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return skills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appraisal> getAppraisals() {
		List<Appraisal> appraisals = new ArrayList<Appraisal>();
		appraisals = (List<Appraisal>)entityManager.createNamedQuery("Appraisal.findAll").getResultList();
		return appraisals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees = (List<Employee>)entityManager.createNamedQuery("Employee.findAll").getResultList();
		return employees;
	}



}
