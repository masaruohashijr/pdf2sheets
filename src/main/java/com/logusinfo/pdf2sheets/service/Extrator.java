package com.logusinfo.pdf2sheets.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extrator {
	
	public String extraiNomeSubsistema(String linha){
		String nomeSubsistema = "";
		if(null!=linha) {
			int posHifen = linha.indexOf("-");
			int posTravessao = linha.indexOf("–");
			posHifen = (posHifen==-1)?posTravessao:posHifen;
			nomeSubsistema =linha.substring(posHifen+2).trim(); 
		}
		return nomeSubsistema;
	}

	public Integer extraiNumero(String line) {
		System.out.println(line);
		return 0;
	}

	public Integer extraiUSTEstimado(String line) {
		return 0;
	}

	public Integer extraiUSTImplantacao(String line) {
		return 0;
	}

	public String extraiCasoDeUso(String line) {
		return line;
	}

	public String extraiConstrucao(String line) {
		return line;
	}
}
