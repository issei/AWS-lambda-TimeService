package br.com.issei.aws.api.lambda.vo;

public class ResponseClass {
	
	private String segundos;

	public ResponseClass(Integer segundos) {
		super();
		if(segundos!=null)
		this.segundos = segundos.toString();
	}

	public ResponseClass() {
		super();
	}

	public String getSegundos() {
		return segundos;
	}

	public void setSegundos(String segundos) {
		this.segundos = segundos;
	}

}
