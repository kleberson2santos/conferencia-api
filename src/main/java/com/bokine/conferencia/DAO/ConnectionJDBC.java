package com.bokine.conferencia.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.bokine.conferencia.domain.Conferencia;

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
	
	public List<String> todasConferencias() {
		String sql = "select nf.conferencia,nf.idnfe,idrecibo,nf.modelo,nf.data from nf "
				+ " where nf.filial=11 and nf.SERIE<>1"
				+ " and nf.modelo<>32 and nf.modelo=35 order by 1";

		String portNumber = "3050";
		String url = "jdbc:firebirdsql:" + this.host + "/" + portNumber + ":" + this.database;
		;
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
				Conferencia conferencia = new Conferencia();

				try {
					conferencia.setRomaneio(rs.getLong("romaneio"));
				} catch (Exception e) {
					conferencia.setRomaneio(0L);
					System.out.println("Erro ao capturar numero da conferencia");
				}
				
				//conferenciasList.add(conferencia);

			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar elemento: " + e);
		}
//		ArrayList<conferencia> list = new ArrayList<conferencia>(conferenciasList);
//		list.sort(new Comparador());
//
//		Map<String, conferencia> result1 = list.stream().sorted(new Comparador())
//				.collect(Collectors.toMap(conferencia::getconferencia, n -> n, // key = name, value = websites
//						(oldValue, newValue) -> oldValue, // if same key, take the old key
//						LinkedHashMap::new // returns a LinkedHashMap, keep order
//		));

		return Arrays.asList("000545","0897766","0974765","735459");
	}


}
