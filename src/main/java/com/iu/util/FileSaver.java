package com.iu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	//매개변수의 경우 session에서 realpath꺼내도 상관없음
	
	public List<String> saver(MultipartFile [] file, String filePath) throws Exception{
		List<String> fileNames = new ArrayList<>();
		
		for(MultipartFile f : file){
			String fileName = this.saver(f, filePath);
			fileNames.add(fileName);
		}		
		return fileNames;
	}
	
	public String saver(MultipartFile file, String filePath) throws Exception{
		//1. 저장할 파일명 생성
		//iu.jpg
		String fileName = file.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName=UUID.randomUUID().toString()+fileName;
		
		//fileName=UUID.randomUUID().toString()+"_"+fileName;
		
		File f = new File(filePath, fileName);
		//FileCopyUtils.copy(f.getBytes(), file);
		file.transferTo(f);
		
		return fileName;
	}
}
