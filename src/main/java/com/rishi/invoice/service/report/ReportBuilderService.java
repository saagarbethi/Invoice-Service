package com.rishi.invoice.service.report;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportBuilderService {
	
	@Autowired
	ReportResultSet resultSet;

	public byte[] generateReport(String path, String query) throws JRException, SQLException {
		// Load your JRXML template
		String templatePath = path;
//		JasperReport jasperReport = JasperCompileManager
//				.compileReport(JRLoader.loadObject(getClass().getResourceAsStream(templatePath)));
		JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);
		// Create a data source (e.g., a list of objects)
		// You'll need to replace this with your actual data source
		Map<String, Object> parameters = new HashMap<>();
		// parameters.put("parameterName", parameterValue); // Set parameters if needed
		
		JRDataSource dataSource = resultSet.createDataSourceFromQuery(query);

		// Compile the JasperPrint
		JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
