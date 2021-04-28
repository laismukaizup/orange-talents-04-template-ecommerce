package br.com.academy.lais.mercadolivre.Seguranca;

public class TokenRequest {

	private String token;
	private String tipo;

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public TokenRequest(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

}
