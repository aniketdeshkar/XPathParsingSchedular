package com.ad.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import com.ad.constant.Constant;

/**
 * 
 * @author Aniket Class contains utility for XML parsing
 *
 */
public class Utility {

	/**
	 * 
	 * Method to validate XML file with XSD Schema
	 * 
	 * @param xsdPath
	 * @param xmlPath
	 * @return
	 */
	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param folderPath
	 * @return
	 */
	public static List<String> listAllFiles(String folderPath) {
		List<String> fileList = new ArrayList<String>();
		try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						String fileExtension = FilenameUtils.getExtension(filePath.getFileName().toString());
						if (fileExtension.equalsIgnoreCase(Constant.XML.getValue())
								|| fileExtension.equalsIgnoreCase(Constant.XSD.getValue())) {
							fileList.add(filePath.getFileName().toString());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileList;
	}
	
	/**
	 * 
	 * @param expression
	 * @return
	 */
	public static String ignoreNameSpace(String expression) {
		StringBuffer modExpression = new StringBuffer();
		Stream.of(expression.split("/")).map(elem -> new String(elem)).collect(Collectors.toList()).forEach(q -> modExpression.append("/*[name()='"+q+"']"));
		return modExpression.toString();
	}
	
}