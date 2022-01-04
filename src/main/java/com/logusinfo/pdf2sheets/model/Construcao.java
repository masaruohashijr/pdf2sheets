package com.logusinfo.pdf2sheets.model;

public enum Construcao {
	MUITO_COMPLEXO("Muito Complexo", 30),
	COMPLEXO("Complexo", 30),
	MEDIO("Muito Complexo", 13),
	SIMPLES("Muito Complexo", 3)
	;
	
	private String descricao;
	private Integer pesoFinal;
	
	Construcao(String descricao, Integer pesoFinal){
		this.descricao = descricao;
		this.pesoFinal = pesoFinal;
	}
}
