package com.example.demo.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.DocService;
import com.example.demo.model.Doc;

@Controller
public class DocController {
	
	
	private DocService docService;
	
	
	@Autowired
	public DocController(DocService docService) {
		
		this.docService = docService;
	}


	@GetMapping("/app")
	public String get(Model model)
	{
		
		List<Doc> docs=docService.getAllfiles();
		model.addAttribute("docs",docs);
		System.out.println(docs);
		return "docview.html";
		
	}
	
	
	@PostMapping("/uploadFiles")
	public String uploadMultipartFiles(@RequestParam("files") MultipartFile[] files)
	{
		System.out.println("inside upload files");
		for(MultipartFile file:files)
		{
			docService.saveFile(file);
		}
		return "redirect:/app";
		
		
	}
	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId)
	{
		Doc doc=docService.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
		
	}
}
