package main.java.cn.fan.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import main.java.cn.fan.dao.MethodStatementsDao;
import main.java.cn.fan.model.Method;
import main.java.cn.fan.tools.ExtractJava;
import main.java.cn.fan.tools.MySqlConnectFactory;

public class Main {
	public static void main(String[] args) {
		// 存储所有的java文件路径
		List<String> javaFilePaths = new ArrayList<String>();
		try {
			ExtractJava.accessDirectory(new File("C:\\Users\\Thinkpad\\git\\poi\\src"), javaFilePaths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 进行ast树的method提取
		MethodVisitor methodVisitor = new MethodVisitor();
		MethodStatementsDao methodStatementsDao = new MethodStatementsDao(MySqlConnectFactory.getConnection());
		Method method = new Method();
		for (String javaFilePath : javaFilePaths) {
			System.out.println(javaFilePath);
			// 一个java文件 就是一个compilationunit
			String[] pathSplit = ExtractJava.pathSplit(javaFilePath);
			ASTParser astParser = new ASTParser(pathSplit[0], pathSplit[1]);
			HashMap<String, String> results = new HashMap<String, String>();
			methodVisitor.visit(astParser.getCompilationUnit(), results);
			for (Entry<String, String> result : results.entrySet()) {
				// 插入数据库的操作
				method.setMethod_name(result.getKey());
				method.setMethod_statement(result.getValue());
				methodStatementsDao.insertOne(method);
			}
		}
	}
}
