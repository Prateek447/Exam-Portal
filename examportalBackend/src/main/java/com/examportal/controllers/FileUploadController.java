package com.examportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.examportal.helper.FileUploadHelper;



@RestController
@CrossOrigin("*")
public class FileUploadController {
	
//	@Autowired
//	private FileUploadHelper fileuploadHelper;
//	
//	@PostMapping("/upload-file")
//	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
//		
////		System.out.println(file.getContentType());
////		System.out.println(file.getName());
////		System.out.println(file.getSize());
//		
//		try {
//			
//			if(file.isEmpty()) {
//				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
//			}
//			
//			//uploading file here
//			boolean f =  fileuploadHelper.uploadFileHelper(file);
//	
//			if(f) {
//				//return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully upload");
//				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
//			}
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Failure | Try Again");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
//		
//	}
	
	
	

}
