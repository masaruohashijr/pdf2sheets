package com.logusinfo.pdf2sheets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.logusinfo.pdf2sheets.model.Construcao;
import com.logusinfo.pdf2sheets.model.Funcionalidade;
import com.logusinfo.pdf2sheets.model.Subsistema;

public class PdfParser {
	private String[] lines;

	public PdfParser(String[] lines) {
		this.lines = lines;
	}

	public List<Subsistema> parse() {

		List<Subsistema> subsistemas = new ArrayList<Subsistema>();

		Extrator extrator = new Extrator();
		Pattern patternSubsistema = Pattern.compile("^\\s*\\d{1}\\.\\d{1}\\.\\d{1}\\.\\d{1}\\.\\d{1}.*$");
		Pattern patternUCS = Pattern.compile("^\\s*\\d*\\s+UCS.*$");
		String pattern = "^(\\s*\\d*\\s+)(UCS\\s{0,1})(\\d{2,3}|X{2,3})([\\sa-zA-ZÀ-ú\\-\\–\\(\\)\\.\\,\\/\\d\\’]+)(Muito|Muito\\s{1}Complexo|Medio|Simples|(?<!Muito )Complexo)(\\s+\\d+)(\\s+\\d+)(\\s+\\d+)(.*)$";
		Pattern patternFunc = Pattern.compile(pattern);
		Subsistema subsistema = null;
		Funcionalidade funcionalidade = null;
		boolean repetir = false;
		boolean agrupar = false;
		String auxSubsistema = "";
		String nomeSubsistema = "";
		String line_anterior = "";
		String line = "";

		for (int i = 0; i< lines.length ; i++) {
			if(agrupar) {
				line = line_anterior + line + lines[i];
				line = line.replaceAll("\n", "");
				line_anterior  = "";
				System.err.println("SOLUÇÃO AGRUPAR:" + line);
			} else {
				if(!repetir) {
					line = lines[i];
				} else {
					System.err.println("SOLUÇÃO REPETIR:" + line);
				}
			}
			Matcher mSub = patternSubsistema.matcher(line);
			Matcher mUCS = patternUCS.matcher(line);
			Matcher mFuncionalidade = patternFunc.matcher(line);
			if (mUCS.matches()) {
				if (mFuncionalidade.find()) {
					if (null != subsistema) {
						funcionalidade = new Funcionalidade();
						String num = mFuncionalidade.group(1).trim();
						Integer numero = 0;
						if(!num.isBlank()) {
							numero = Integer.parseInt(num);
						}
						String casoDeUso = mFuncionalidade.group(2) + mFuncionalidade.group(3) + mFuncionalidade.group(4);
						String construcao = mFuncionalidade.group(5);
						Integer pesoFinal = Integer.parseInt(mFuncionalidade.group(6).trim());
						Integer USTEstimado = Integer.parseInt(mFuncionalidade.group(7).trim());
						Integer USTImplantacao = Integer.parseInt(mFuncionalidade.group(8).trim());
						funcionalidade.setNumero(numero);
						funcionalidade.setPesoFinal(pesoFinal);
						funcionalidade.setUSTEstimado(USTEstimado);
						funcionalidade.setUSTImplantacao(USTImplantacao);
						funcionalidade.setCasoDeUso(casoDeUso);
						funcionalidade.setConstrucao(construcao);
						subsistema.addFuncionalidade(funcionalidade);
						if(agrupar || repetir) {
							System.err.println("SUCESSO:"+line);
							agrupar = false;
							repetir = false;
						}
					}
				} else {
					System.err.println("ERRO FUNCIONALIDADE:" + line);
					agrupar = true;
					continue;
				}
			} else if (mSub.matches()
					&& !(nomeSubsistema = extrator.extraiNomeSubsistema(line)).equals(auxSubsistema)) {
				System.out.println("SUBSISTEMA: "+line);
				subsistema = new Subsistema();
				subsistema.setNome(nomeSubsistema);
				subsistemas.add(subsistema);
				auxSubsistema = nomeSubsistema;
			} else {
				if(line.contains("UCS")) {
					System.err.println("ERRO UCS:" + line);
					String retorno = limparHeading(line);
					if(retorno.isEmpty()) {
						agrupar = true;
					} else {
						line = retorno;
						repetir = true;
					}
					continue;
				} else {
					System.err.println("ERRO GERAL:" + line);
				}
			}
			line_anterior  = line;
		}
		return subsistemas;
	}

	private String limparHeading(String line) {
		String retorno = line;
		String pattern = "^(.*)(\\s+\\d+\\s+UCS\\s{1})(\\d{2,3}|X{2,3})([\\sa-zA-ZÀ-ú\\-\\–\\(\\)\\.\\,\\/\\d\\’]+)(Muito\\s{1}Complexo|Medio|Simples|(?<!Muito )Complexo)(\\s+\\d+)(\\s+\\d+)(\\s+\\d+)(.*)$";
		Pattern patternFunc = Pattern.compile(pattern);
		Matcher mFuncionalidade = patternFunc.matcher(line);
		if (mFuncionalidade.find()) {
			retorno = mFuncionalidade.group(2)+mFuncionalidade.group(3)+mFuncionalidade.group(4)+mFuncionalidade.group(5)+mFuncionalidade.group(6)+mFuncionalidade.group(7)+mFuncionalidade.group(8);
		}
		return (retorno.equals(line)?"":retorno);
	}
}
