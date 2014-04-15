package util;

import java.util.List;

import javax.ejb.Local;

import entity.Appraisal;
import entity.Employee;
import entity.Job;
import entity.Project;
import entity.Skill;

@Local
public interface SessionBeanLocal1 {
	
	public boolean persist(Object object);
	//Merge Object
	public boolean merge(Object object);

	//Project
	public List<Project> getProjects();
	public List<Project> findProjectByProjectName(String projectName);
	public List<Project> findProjectByDepartment(String department);
	public Project findProjectById(int project_id);
	//Job
	public List<Job> findJobByJobName(String jobName);
	public List<Job> findJobByDepartment(String department);
	public Job findJobById(int job_id);
	public List<Job> getJobs();
	
	//Skill
	public List<Skill> findSkillBySkillName(String jobName);
	public List<Skill> findSkillByDescription(String description);
	public Skill findSkillById(int skill_id);
	public List<Skill> getSkills();
	
	//Appraisal
	public List<Appraisal> findAppraisalByEmployee(Employee employee);
	public Appraisal findAppraisalById(int appraisal_id);
	public List<Appraisal> getAppraisals();
	
	//Employees
	public List<Employee> getEmployees();

}
