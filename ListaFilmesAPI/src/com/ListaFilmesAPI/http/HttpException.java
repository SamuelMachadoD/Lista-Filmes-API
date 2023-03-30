package com.ListaFilmesAPI.http;

public class HttpException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpException(String erro) {
		
		super(erro);
		
	}

}
