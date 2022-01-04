package com.logusinfo.pdf2sheets.model;

import java.util.ArrayList;
import java.util.List;

public class Subsistema {
	private String nome;
	private String descricao;
	private List<Funcionalidade> funcionalidades =  new ArrayList<Funcionalidade>();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidades.add(funcionalidade);
	}

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}
	
}
