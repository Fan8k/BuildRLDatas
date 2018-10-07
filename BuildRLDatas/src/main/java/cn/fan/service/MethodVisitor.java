package main.java.cn.fan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter<HashMap<String, String>> {

	private String packageValue;
	private String classValue;

	@Override
	public void visit(MethodDeclaration n, HashMap<String, String> results) {
		// TODO Auto-generated method stub
		ArrayList<List<String>> allStates = new ArrayList<List<String>>();
		// 由于java中有方法重载，很多方法有可能名字一样，所以这一步加上方法的参数
		String declarationStr = n.getDeclarationAsString();
		String params = declarationStr.substring(declarationStr.indexOf("("));
		// package.classname.methodname(参数)
		String qualifiedMethodName = packageValue + "." + classValue + "." + n.getName() + params;
		System.out.println(qualifiedMethodName);
		Optional<BlockStmt> body = n.getBody();
		// 一个方法中所有的单个statements信息
		// NodeList<Statement> statements = body.get().getStatements();
		// StringBuffer statementStr = new StringBuffer();
		results.put(qualifiedMethodName, body.toString());
	}

	@Override
	public void visit(PackageDeclaration n, HashMap<String, String> arg) {
		// TODO Auto-generated method stub
		packageValue = n.getName().toString();
	}

	@Override
	public void visit(CompilationUnit n, HashMap<String, String> arg) {
		// TODO Auto-generated method stub
		// super.visit(n, arg);
		List<Node> childNodes = n.getChildNodes();
		for (Node node : childNodes) {
			if (node.getClass() == PackageDeclaration.class) {
				visit((PackageDeclaration) node, arg);
			} else if (node.getClass() == ClassOrInterfaceDeclaration.class) {
				visit((ClassOrInterfaceDeclaration) node, arg);
			} else if (node.getClass() == EnumDeclaration.class) {
				visit((EnumDeclaration) node, arg);
			}
		}
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, HashMap<String, String> arg) {
		// TODO Auto-generated method stub
		classValue = n.getName().toString();
		super.visit(n, arg); // 起到链接的作用
	}

	@Override
	public void visit(EnumDeclaration n, HashMap<String, String> arg) {
		// TODO Auto-generated method stub
		classValue = n.getName().toString();
		super.visit(n, arg);
	}

	/**
	 * 将原始的statements的\r\n 都用 @fan@替换 ,比如:int a = 10 @fan@ int b=10
	 * 
	 * @param primaryStatement
	 * @return
	 */
	private String dealPrimaryStatement(String primaryStatement) {
		String[] statements = primaryStatement.split("(\r\n)");
		StringBuffer resultStr = new StringBuffer();
		for (String statement : statements) {
			resultStr.append(statement.replaceAll("\r\n", "").trim());
			resultStr.append("@fan@");
		}
		return resultStr.toString();
	}

}
