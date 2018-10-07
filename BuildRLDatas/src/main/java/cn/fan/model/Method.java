package main.java.cn.fan.model;

public class Method {
	private int id;
	private String method_name;
	private String method_statement;

	public Method() {
		// TODO Auto-generated constructor stub
	}

	public Method(int id, String method_name, String method_statement) {
		super();
		this.id = id;
		this.method_name = method_name;
		this.method_statement = method_statement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	public String getMethod_statement() {
		return method_statement;
	}

	public void setMethod_statement(String method_statement) {
		this.method_statement = method_statement;
	}

	@Override
	public String toString() {
		return "Method [id=" + id + ", method_name=" + method_name + ", method_statement=" + method_statement + "]";
	}

}
