package com.ad.service;

import java.io.File;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.ad.constant.XpathEnum;
import com.ad.entity.ElementDataValue;
import com.ad.entity.ScreeningFieldMaster;
import com.ad.repository.ElementDataValueRepository;
import com.ad.repository.ScreeningFieldMasterRepository;
import com.ad.utility.Utility;

import net.sf.saxon.lib.NamespaceConstant;

@Service
public class XpathService {

	@Autowired
	private ElementDataValueRepository elementDataValueRepository;
	
	@Autowired
	private ScreeningFieldMasterRepository screeningFieldMasterRepository;

	public void execute(File xmlFile) throws Exception {

		// The following initialization code is specific to Saxon HE
		System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON,
				"net.sf.saxon.xpath.XPathFactoryImpl");

		XPathFactory xpFactory = XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
		XPath xpExpression = xpFactory.newXPath();
		System.err.println("Loaded XPath Provider " + xpExpression.getClass().getName());

		// Build the source document.
		InputSource inputSrc = new InputSource(xmlFile.toURI().toString());

		
		// Iterating Screening fields from DB
		List<String> screeningFieldsByVersion = screeningFieldMasterRepository.getScreeningFieldsByVersion(FilenameUtils.removeExtension(xmlFile.getName()));
		screeningFieldsByVersion.forEach(sf -> {
			XPathExpression findSerialNos;
			String elementValue = null;
			try {
				findSerialNos = xpExpression.compile(Utility.ignoreNameSpace(sf));
				elementValue = (String) findSerialNos.evaluate(inputSrc, XPathConstants.STRING);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			System.out.println(sf + ":" + elementValue);
			
			//Data Save operation
			ElementDataValue dataValue = new ElementDataValue();
			dataValue.setElement(sf);
			dataValue.setPainversion(FilenameUtils.removeExtension(xmlFile.getName()));
			dataValue.setValue(elementValue);
			elementDataValueRepository.save(dataValue);
		});
		
		// Iterating ENUM screening fields
//		for (XpathEnum xpathEnum : XpathEnum.values()) {
//			XPathExpression findSerialNos = xpExpression.compile(Utility.ignoreNameSpace(xpathEnum.getValue()));
//			String elementValue = (String) findSerialNos.evaluate(inputSrc, XPathConstants.STRING);
//			System.out.println(xpathEnum.getValue() + ":" + elementValue);
//			
//			//Data Save operation
//			ElementDataValue dataValue = new ElementDataValue();
//			dataValue.setElement(xpathEnum.getValue());
//			dataValue.setPainversion(FilenameUtils.getName(xmlFile.getName()));
//			dataValue.setValue(elementValue);
//			elementDataValueRepository.save(dataValue);
//		}

	}

}
