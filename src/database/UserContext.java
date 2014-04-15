package database;

public class UserContext {
	
	private UserStrategy strategy;
	
	public UserContext(UserStrategy strategy){
		this.strategy = strategy;
	}

	public boolean login(String email, String password){
		return strategy.login(email, password);
	}

	public void register(String username, String password, String firstName, String surname, String address, int salary, String userType){
		strategy.register(username, password, firstName, surname, address, salary, userType);
	}

	public void logout(){
		strategy.logout();
	}

}
