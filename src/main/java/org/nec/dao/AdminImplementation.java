package org.nec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.nec.dto.AdminDto;
import org.nec.services.SingletonClass;

public class AdminImplementation implements AdminDao{
	private SingletonClass object = SingletonClass.getObject();
    private Connection connnect = object.getDataBaseConnection();
    AdminDao adao = null;
	@Override
	public void login(int id, String password) {
		// TODO Auto-generated method stub
		
		String query = "select id,password from teja19.admin";
		try {
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				
				System.out.println("Id:"+rs.getInt("id")+"password"+rs.getString("password"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
