package com.ListaFilmesAPI.main;

import com.ListaFilmesAPI.extratores.ExtratorDeConteudo;
import com.ListaFilmesAPI.extratores.ExtratorFilmes;
import com.ListaFilmesAPI.extratores.ExtratorNasa;

public enum API {
	TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json", new ExtratorFilmes()),
	NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=SK9plXLYpGPq1T6i3O6xfZM1wlOSP09ldLjb9Bvq", new ExtratorNasa());
	
	private String url;
	private ExtratorDeConteudo extrator;
	
	API(String url, ExtratorDeConteudo extrator){
		this.url = url;
		this.extrator = extrator;
		
	}

	public String getUrl() {
		return url;
	}
	
	public ExtratorDeConteudo getExtrator() {
		return extrator;
	}
}
