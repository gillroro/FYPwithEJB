package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries( {
	@NamedQuery(name = "Employee.findAll", query = "select o from Employee o"),
	@NamedQuery(name = "Employee.findByUsername", query = "select o from Employee o where o.username=:username"),
	@NamedQuery(name = "Employee.findManager", query = "select o from Employee o where o.manager_id=null"),
})

@Entity
public class Employee {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String surname;
	private String address;
	private int salary;
	private String userType;
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private Employee manager;
	
	@OneToMany(mappedBy="manager")
    private Set<Employee> subordinates = new HashSet<Employee>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Employee_Project", 
                joinColumns={@JoinColumn(name="Employee_Id")}, 
                inverseJoinColumns={@JoinColumn(name="Project_Id")})
    private Set<Project> projects = new HashSet<Project>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Employee_Skill", 
                joinColumns={@JoinColumn(name="Employee_Id")}, 
                inverseJoinColumns={@JoinColumn(name="Skill_Id")})
    private Set<Skill> skills = new HashSet<Skill>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Employee_Job", 
                joinColumns={@JoinColumn(name="Employee_Id")}, 
                inverseJoinColumns={@JoinColumn(name="Job_Id")})
    private Set<Job> jobs = new HashSet<Job>();

	public Employee(){}

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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}


	


}
