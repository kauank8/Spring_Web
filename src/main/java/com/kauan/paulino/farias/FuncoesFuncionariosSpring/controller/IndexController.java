package com.kauan.paulino.farias.FuncoesFuncionariosSpring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kauan.paulino.farias.FuncoesFuncionariosSpring.model.Dependente;
import com.kauan.paulino.farias.FuncoesFuncionariosSpring.model.Funcionario;
import com.kauan.paulino.farias.FuncoesFuncionariosSpring.persistence.FuncionarioDao;
import com.kauan.paulino.farias.FuncoesFuncionariosSpring.persistence.GenericDao;

@Controller
public class IndexController {
	@Autowired
	GenericDao gDao;
	
	@Autowired
	FuncionarioDao fDao;
	
	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.GET)
	public ModelAndView indexGet(ModelMap model) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.POST)
	public ModelAndView indexPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("botao");
		String codigo = allRequestParam.get("codigo");
		
		List<Dependente> dependentes = new ArrayList<>() ;
		Funcionario funcionario = new Funcionario();
		Double somaSalario = 0.0;
		String erro = "";
		String saida = "";
		
		if(cmd.contains("Consultar Soma Salario") && !codigo.trim().isEmpty()) {
			funcionario.setCodigo(Integer.parseInt(codigo));
		}
		
		try {
			if(cmd.contains("Consultar Soma Salario")) {
				somaSalario = fDao.somaSalario(funcionario);
				if (somaSalario == 0) {
					saida = "Esse funcionario não existe";
					somaSalario = null;
				}
			}
			if(cmd.contains("Listar Funcionario e Dependentes")) {
				dependentes = fDao.funcDep();
				somaSalario = null;
			}
		} catch (SQLException | ClassNotFoundException e){
			erro = e.getMessage();
		}finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("salario", somaSalario);
			model.addAttribute("dependentes", dependentes);
		}
		
		return new ModelAndView("index");
	}
}
