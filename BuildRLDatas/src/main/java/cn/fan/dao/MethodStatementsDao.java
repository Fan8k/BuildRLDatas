package main.java.cn.fan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.cn.fan.interfaces.MethodStatementsDaoI;
import main.java.cn.fan.model.Method;

public class MethodStatementsDao implements MethodStatementsDaoI {

	private Connection connection;

	public MethodStatementsDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean insertOne(Method method) {
		String sql = "insert into poi_method_statements(method_name,method_statement) values (?,?)";
		PreparedStatement prepareStatement = null;
		boolean flag = false;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, method.getMethod_name());
			prepareStatement.setString(2, method.getMethod_statement());
			prepareStatement.execute();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}
