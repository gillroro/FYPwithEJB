package database;

public interface UserStrategy {
	
	public boolean login(String username, String password);
	public void logout();
	public void register(String username, String password, String firstName, String surname, String address, int salary, String userType);

}
