package com.ad.schedular;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ad.constant.Constant;
import com.ad.service.XpathService;
import com.ad.utility.Utility;

/**
 * 
 * @author Aniket Deshkar
 * 
 *         Schedular class is executing every minute with given cron = "0 0/1 *
 *         1/1 * ?"
 *
 */
@Component
public class Schedular {

	@Autowired
	XpathService xpathService;

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void cronJobSch() throws Exception {
		System.out.println("Java cron job expression:: " + LocalDateTime.now());

		List<String> listAllFiles = Utility.listAllFiles(Constant.FOLDERPATH.getValue());
		listAllFiles.forEach(q -> {
			File xmlFile = new File(Constant.FOLDERPATH.getValue().concat(q));
			try {
				// Condition : folder should contains XML and XSD with same name
				if (FilenameUtils.getExtension(xmlFile.getName()).equals(Constant.XML.getValue())
						&& listAllFiles.contains(FilenameUtils.removeExtension(xmlFile.getName()).concat(".")
								.concat(Constant.XSD.getValue()))) {

					// validate schema XML with XSD
					if (Utility.validateXMLSchema(
							Constant.FOLDERPATH.getValue()
									.concat(FilenameUtils.removeExtension(xmlFile.getName()).concat(".")
											.concat(Constant.XSD.getValue())),
							Constant.FOLDERPATH.getValue().concat(q))) {
						// XML elements reading execution
						xpathService.execute(xmlFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}
}
