package com.kauan.paulino.farias.FuncoesFuncionariosSpring.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kauan.paulino.farias.FuncoesFuncionariosSpring.model.Dependente;
import com.kauan.paulino.farias.FuncoesFuncionariosSpring.model.Funcionario;

@Repository
public class FuncionarioDao {

	@Autowired
	private GenericDao gDao;
	
	public List<Dependente> funcDep() throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		List<Dependente> dependentes = new ArrayList<>();
		String sql = "Select * from fn_FuncDep()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Dependente d = new Dependente();
			d.setNome(rs.getString("nome_dependente"));
			d.setSalario(rs.getDouble("salario_dependente"));
			Funcionario f = new Funcionario();
			f.setNome(rs.getString("nome_funcionario"));
			f.setSalario(rs.getDouble("salario_funcinario"));
			d.setFuncionario(f);
			dependentes.add(d);
		}
		ps.close();
		rs.close();
		c.close();
		return dependentes;
	}
	
	public double somaSalario(Funcionario f) throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		double somaSalario = 0;
		String sql = "Select dbo.fn_SomaSalario(?) as Salario_Func_Dep";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			somaSalario = rs.getDouble("Salario_Func_Dep");
		}
		ps.close();
		rs.close();
		c.close();
		return somaSalario;
	}
}
