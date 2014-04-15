package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries( {
	@NamedQuery(name = "Skill.findAll", query = "select o from Skill o"),
	@NamedQuery(name = "Skill.findBySkillName", query = "select o from Skill o where o.name=:name"),
	@NamedQuery(name = "Skill.findByDescription", query = "select o from Skill o where o.description=:description"),
})

@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	@ManyToMany(mappedBy="skills")
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Skill(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	

}
