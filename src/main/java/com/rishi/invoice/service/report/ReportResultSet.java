package com.rishi.invoice.service.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

@Service
public class ReportResultSet {

	private final DataSource dataSource;

	public ReportResultSet(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JRDataSource createDataSourceFromQuery(String query) throws SQLException {

		JRResultSetDataSource jrDataSource = null;
		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			// Create a JRDataSource from the ResultSet
			jrDataSource = new JRResultSetDataSource(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jrDataSource;

	}

}
