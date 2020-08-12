package com.mecitdeniz.petsAppRest.Controllers;

import org.springframework.http.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadFile {

	
	@PostMapping("/upload")
	public ResponseEntity<Integer> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
		
		//System.out.print("Gellllldiii");
		try {
			saveImage(imageFile);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/download/{resimAd}",method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile(@PathVariable("resimAd") String resimAd) throws IOException{
		
		String path = "C:\\Users\\mecit\\Desktop\\Dev\\petsAppRest\\resimke\\";
		String fileName = path+resimAd;
		
		//String fileName = "D:/resimke/profil.jpg";
		File file = new File(fileName);
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition",String.format("attachment; fileName=\"%s",file.getName()));
		headers.add("Cache-Control","no-chache,no-storage,must-revalidate");
		headers.add("Pragma","no-chache");
		headers.add("Expires", "0");
		
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}

	
	
	public void saveImage(MultipartFile imageFile) throws Exception {
		byte[] bytes = imageFile.getBytes();
	
		Path path = Paths.get("C:\\Users\\mecit\\Desktop\\Dev\\petsAppRest\\resimke\\"+imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}
	
}
