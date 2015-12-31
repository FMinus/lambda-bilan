package com.lambda.bilan.helpers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileHelper {
	private String DATA_DIR= PropretiesHelper.getText("general.data.dir");
	
	public String setRandomName(String filename){
		String extension = filename.substring(filename.lastIndexOf("."));
		return  RandomGenerator.randomNumber() + extension;
	}
		
	public String saveDocument(MultipartFile multipart) throws IOException,LambdaException {
		//generate names 
		String documentFileName =multipart.getOriginalFilename();
		String generatedName = setRandomName(documentFileName);
		try {
            byte[] bytes = multipart.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(DATA_DIR+"/"+generatedName)));
            stream.write(bytes);
            stream.close();
            return generatedName;
        } catch (Exception e) {
        	throw new LambdaException(PropretiesHelper.getText("general.upload.fail"));
        }
		
	}
	
	
}
