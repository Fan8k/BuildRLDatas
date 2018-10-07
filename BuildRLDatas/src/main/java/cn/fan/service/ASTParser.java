package main.java.cn.fan.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import main.java.cn.fan.tools.ExtractJava;

public class ASTParser {

	private CompilationUnit compilationUnit;

	public ASTParser(String absPath, String filename) {
		compilationUnit = new SourceRoot(Paths.get(absPath)).parse("", filename);
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		List<String> javaFilePaths = new ArrayList<String>();
		try {
			ExtractJava.accessDirectory(new File("C:\\Users\\Thinkpad\\git\\poi\\src"), javaFilePaths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] pathSplit = ExtractJava.pathSplit(javaFilePaths.get(0));
		ASTParser astParser = new ASTParser(pathSplit[0], pathSplit[1]);
		MethodVisitor methodVisitor = new MethodVisitor();

		//
		HashMap<String, String> results = new HashMap<String, String>();
		methodVisitor.visit(astParser.getCompilationUnit(), results);
	}
}
