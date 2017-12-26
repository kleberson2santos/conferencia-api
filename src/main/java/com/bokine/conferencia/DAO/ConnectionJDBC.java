package com.bokine.conferencia.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.bokine.conferencia.domain.Conferencia;
import com.bokine.conferencia.domain.Produto;

public class ConnectionJDBC {

	public Connection c;
	
	private String host;
	private String user;
	private String pass;
	private String database;
	
	public ConnectionJDBC(String host, String database, String user, String pass) {
		this.pass = pass;
		this.user = user;
		this.host = host;
		this.database = database;
	}
	
	/**
	 * @param query
	 *            String contendo a query que se deseja executar
	 * @return ResultSet em caso de estar tudo Ok, null em caso de erro.
	 */
	private ResultSet executar(String query) {
		Statement st;
		ResultSet rs;

		try {
			st = this.c.createStatement();
			rs = st.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Produto> todasConferencias() {
		Conferencia conferencia = new Conferencia(1L);
		List<Produto> produtos = new ArrayList<Produto>();
		
	
		String sql = "select " + 
				"p.DESCRICAO1, p.COD_PRODUTO, cp.COR, tp.TAMANHO from PEDIDO_VENDA pv " + 
				"left join PRODUTO_PEDIDOV ppv on pv.PEDIDOV = ppv.PEDIDOV " + 
				"left join PRODUTOS p on p.PRODUTO = ppv.PRODUTO " + 
				"left join CLIENTES c on c.CLIENTE = pv.CLIENTE " + 
				"left join COR_PROD cp on cp.PRODUTO =  p.PRODUTO " + 
				"left join TAM_PROD tp on tp.PRODUTO = p.PRODUTO " + 
				"where " + 
				"ppv.SAIDA is null and " + 
				"pv.DATA_ENTREGA = '29.11.2017' " + 
				"and pv.LISTA_CASAMENTO='F' " + 
				"and pv.CLIENTE<>31;";

		String portNumber = "3050";
		String url = "jdbc:firebirdsql:" + this.host + "/" + portNumber + ":" + this.database+"?charSet=ISO-8859-1";
		String userName = this.user;
		String passName = this.pass;

		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
			this.c = DriverManager.getConnection(url, userName, passName);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		ResultSet rs = executar(sql);

		try {
			while (rs.next()) {
				Produto p = new Produto();
				String cor = "";
				String codigo = "";
				String tamanho = "";
				try {
					p.setNome(rs.getString("DESCRICAO1"));
				} catch (Exception e) {
					System.out.println("Erro ao capturar nome da base firebird:\n"+ e);
				}
				try {
					codigo = rs.getString("COD_PRODUTO");
				} catch (Exception e) {
					System.out.println("Erro ao capturar codigo da base firebird:\n"+ e);
				}
				try {
					cor = ajustaCor(rs.getString("COR"));
				} catch (Exception e) {
					System.out.println("Erro ao capturar cor da base firebird:\n"+ e);
				}
				try {
					tamanho = rs.getString("TAMANHO");
				} catch (Exception e) {
					System.out.println("Erro ao capturar tamanho da base firebird:\n"+ e);
				}
				p.setCodigoDeBarras(codigo+cor+tamanho);
				produtos.add(p);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar produto da base firebird: " + e);
		}
		conferencia.setProdutos(produtos);
		return conferencia.getProdutos();
	}

	private String ajustaCor(String cor) {
		String corAux = StringUtils.trimAllWhitespace(cor);
		
		if(corAux.length()==1)
		return new StringBuilder()
		        .append("").append(0).append(0).append(corAux).toString();
		else
			if(corAux.length()==2)
				return new StringBuilder()
				        .append("").append(0).append(corAux).toString();
			else
				if(corAux.length()>3)
					return "2000000000";
		return cor;
	}


}
