package org.nec.services;

import org.nec.dao.AdminDao;
import org.nec.dao.AdminImplementation;

public class LoginSuccess {
	public static AdminDao getAdminDao() {
		AdminDao adao = new AdminImplementation();
		return adao;
		
	}

}
