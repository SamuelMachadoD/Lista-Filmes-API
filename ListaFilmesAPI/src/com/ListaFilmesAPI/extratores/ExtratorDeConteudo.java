package com.ListaFilmesAPI.extratores;

import java.util.List;

import com.ListaFilmesAPI.main.Conteudo;

public interface ExtratorDeConteudo {
	List<Conteudo> extrai(String json);
}
