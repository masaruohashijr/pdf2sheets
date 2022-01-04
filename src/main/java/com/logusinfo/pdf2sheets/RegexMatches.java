package com.logusinfo.pdf2sheets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

	public static void main(String args[]) {
		// String to be scanned to find the pattern.
		String[] lines = { 
/*				"  46 UCS 41 – Associar Objetivo Setorial/Regional Simples 3 60 6  ",
				"  78 UCS 86 - Imprimir Liberação Orçamento Itens NA Simples  3 60 6  ",
				"1 UCS 01 – Associar Região Simples 3 60 6 ",
				"        16  UCS 16 - Emitir Relatório de Desdobramento da Receita Simples 3 60 6  ",
				"  e revisão do Plano Plurianual (PPA). Permitindo que todas as  2 UCS 02 – Associar Região Localidade Simples 3 60 6  ",
				" 9 UCS 042 - Solicitar Acesso Sigef - Manter Complexo 30 600 60 ",
				" 40 UCS XXX - Alterar Senha Usuário Simples 3 60 6  ",
				" 4 UCS 04 - Detalhar Conceito (Programa) Simples 3 60 6    dos seus respectivos relatórios legais e gerenciais.",
				"5  UCS 05 - Emitir Relatório Consolidação das Fontes de               Financiamento dos Investimentos Simples 3 60 6             ",
				" 41 UCS 36 – Manter Indicador Simples 3 60 6  ",
				"      UCS 08 – Emitir Relatório de Demonstrativo da Aplicação da Receita      elaboração da proposta  orçamentária  serão  8         com Impostos na Manutenção e Desenvolvimento Medio 13 260 26  ",
				*/
				"30   UCS308–RemanejarProgramaçãoFinanceira Medio  13 260 26 "
				};
		String pattern = "^(.*)(\\s*\\d*\\s+)(UCS\\s{0,1})(\\d{2,3}|X{2,3})([\\sa-zA-ZÀ-ú\\-\\–\\(\\)\\.\\,\\/\\d\\’]+)(Muito\\s{1}Complexo|Medio|Simples|(?<!Muito )Complexo)(\\s+\\d+)(\\s+\\d+)(\\s+\\d+)(.*)$";
		Pattern patternUCS = Pattern.compile("^\\s*\\d*\\s*UCS.*$");
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);
		for (String line : lines) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				System.out.println("Found 0: " + m.group(0));
				System.out.println("Found 1: " + m.group(1));
				System.out.println("Found 2: " + m.group(2));
				System.out.println("Found 3..5: " + m.group(3) + m.group(4) + m.group(5));
				System.out.println("Found 6: " + m.group(6));
				System.out.println("Found 7: " + m.group(7));
				System.out.println("Found 8: " + m.group(8));
				System.out.println("Found 9: " + m.group(9));
			} else {
				System.out.println("NO MATCH");
			}
			Matcher mUCS = patternUCS.matcher(line);
			System.out.println(mUCS.matches());
			System.out.println("++++++++++++++++++++++++++++");
		}
		// Now create matcher object.
	}
}