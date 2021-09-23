package com.examportal.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;


import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
@RequestMapping("/files")
public class FileUploadHelper {

	 public final String DIRECTORY = new ClassPathResource("static/images").getFile().getAbsolutePath();

	 public FileUploadHelper() throws IOException{}
	 
	 @PostMapping("upload")
	 public ResponseEntity<String> uploadFileHelper(@RequestParam("files") MultipartFile file) {
		 try {
		 Files.copy(file.getInputStream(),Paths.get(DIRECTORY+File.separator+file.getOriginalFilename()),
				 StandardCopyOption.REPLACE_EXISTING);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return ResponseEntity.ok().body(file.getOriginalFilename()+"is successfully saved");
	 }


	 // define a location
//	    public static final String DIRECTORY = System.getProperty("*");
//
//	    // Define a method to upload files
//	    @PostMapping("/upload")
//	    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
//	        List<String> filenames = new ArrayList<>();
//	        for(MultipartFile file : multipartFiles) {
//	        	
//	            String filename = StringUtils.cleanPath(file.getOriginalFilename());
//	            
//	            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
//	            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//	            filenames.add(filename);
//	        }
//	        return ResponseEntity.ok().body(filenames);
//	    }

	    // Define a method to download files
	    @GetMapping("download/{filename}")
	    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
	    	
	        Path filePath = Paths.get(DIRECTORY+File.separator+filename);
	        if(!Files.exists(filePath)) {
	            throw new FileNotFoundException(filename + " was not found on the server");
	        }
	        Resource resource = new UrlResource(filePath.toUri());
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.add("File-Name", filename);
	        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
	        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
	                .headers(httpHeaders).body(resource);
	    }



}
