package br.com.itsm.domain;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {

	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String cpf, String senha) {
		super(id, nome, cpf, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}