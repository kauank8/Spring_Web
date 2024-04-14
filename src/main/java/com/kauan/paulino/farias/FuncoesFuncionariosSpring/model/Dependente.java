package com.kauan.paulino.farias.FuncoesFuncionariosSpring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dependente {
	private int codigo;
	private String nome;
	private double salario;
	private Funcionario funcionario;
}
