package services;

import daos.User_TableBeansImpl;

public class RegistrationService {
	public String CreateNewEmployee(String firstname, String lastname, String username, String password) {
		User_TableBeansImpl impl = new User_TableBeansImpl();
		return impl.createNewEmployee(username, lastname, password, firstname);
	}

}
