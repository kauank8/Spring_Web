<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/style1.css"/>'>
<title>Funçoes</title>
</head>
<body>
	<div align="center">
		<H1><b>Exemplo Funções com Spring Web</b></H1>
	</div>
	
	<div align="center" class="container">
		<form action="funcionario" method="post">
			<p class="title">
				<b>Funcionarios e Dependentes</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="id_input_data"  id="codigo" name="codigo" placeholder="Codigo-Funcionario"
						 type="text" pattern="[0-9]*" title="Por favor, digite apenas números."  
						value='<c:out value="${funcionario.codigo }"></c:out>'>
					</td>
				</tr>
				<tr>	
					<td>
						<input type="submit" id="botao" name="botao" value="Listar Funcionario e Dependentes">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Consultar Soma Salario">
					</td>
				</tr>	
			</table>
		</form>
	</div>	
		
	<div align="center">
		<c:if test="${not empty erro }">
			<h2><b> <c:out value="${erro }" /> </b></h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<h3><b> <c:out value="${saida }" /> </b></h3>	
		</c:if>
	</div>
	<br />
	<br />
	<br />
	<div align="center" >
		<c:if test="${not empty dependentes }">
			<table class="table_round">
				<thead>
					<tr>
						<th class="lista">Nome Funcionario</th>
						<th class="lista">Salario Funcionario</th>
						<th class="lista">Nome Dependente</th>
						<th class="lista_ultimoelemento">Salario Dependente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="d" items="${dependentes }">
						<tr>
							<td class="lista"><c:out value="${d.nome } " /></td>
							<td class="lista"><c:out value="${d.salario } " /></td>
							<td class="lista"><c:out value="${d.funcionario.nome } " /></td>
							<td class="lista_ultimoelemento"><c:out value="${d.funcionario.salario } " /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty salario }">
			<h3><b> A soma do salario desse funcionario com seus dependentes é <c:out value="${salario }" /> </b></h3>	
		</c:if>
	</div>					
</body>
</html>