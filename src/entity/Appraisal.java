package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "Appraisal.findAll", query = "select o from Appraisal o"),
	@NamedQuery(name = "Appraisal.findByEmployee", query = "select o from Appraisal o where o.employee=:employee"),
})

@Entity
public class Appraisal {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String accomplishments;
	private String performance;
	private String barriers;
	private String improvements;
	private String attendanceRecord;
	private String respectRecord;
	private Employee employee;
	
	
	public String getAccomplishments() {
		return accomplishments;
	}
	public void setAccomplishments(String accomplishments) {
		this.accomplishments = accomplishments;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getBarriers() {
		return barriers;
	}
	public void setBarriers(String barriers) {
		this.barriers = barriers;
	}
	public String getImprovements() {
		return improvements;
	}
	public void setImprovements(String improvements) {
		this.improvements = improvements;
	}
	public String getAttendanceRecord() {
		return attendanceRecord;
	}
	public void setAttendanceRecord(String attendanceRecord) {
		this.attendanceRecord = attendanceRecord;
	}
	public String getRespectRecord() {
		return respectRecord;
	}
	public void setRespectRecord(String respectRecord) {
		this.respectRecord = respectRecord;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
