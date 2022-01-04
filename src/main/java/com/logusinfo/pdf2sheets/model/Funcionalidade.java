package com.logusinfo.pdf2sheets.model;

public class Funcionalidade {
		private Integer numero;
		private Integer USTEstimado;
		private Integer pesoFinal;
		private Integer USTImplantacao;
		private String casoDeUso;
		private String construcao;
 		public Funcionalidade() {
		}
		public Integer getUSTEstimado() {
			return USTEstimado;
		}
		public void setUSTEstimado(Integer uSTEstimado) {
			USTEstimado = uSTEstimado;
		}
		public Integer getUSTImplantacao() {
			return USTImplantacao;
		}
		public void setUSTImplantacao(Integer uSTImplantacao) {
			USTImplantacao = uSTImplantacao;
		}
		public String getCasoDeUso() {
			return casoDeUso;
		}
		public void setCasoDeUso(String casoDeUso) {
			this.casoDeUso = casoDeUso;
		}
		public Integer getNumero() {
			return numero;
		}
		public void setNumero(Integer numero) {
			this.numero = numero;
		}
		public void setConstrucao(String construcao) {
			this.construcao = construcao;
		}
		public String getConstrucao() {
			return construcao;
		}
		public Integer getPesoFinal() {
			return pesoFinal;
		}
		public void setPesoFinal(Integer pesoFinal) {
			this.pesoFinal = pesoFinal;
		}
}
