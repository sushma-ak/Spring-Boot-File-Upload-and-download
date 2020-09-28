package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.example.demo.repository.DocRepository;

@Service
public class DocService {
	
	@Autowired
	private DocRepository docRepository;
	
	public Doc saveFile(MultipartFile file)
	{
		
		String docName=file.getOriginalFilename();
		
		try
		{
			Doc doc=new Doc(docName,file.getContentType(),file.getBytes());
			return docRepository.save(doc);
		}
		catch(Exception e)
		{
			
		}
		return null;
		
	}
	
	public Optional<Doc> getFile(Integer fileId)
	{
		return docRepository.findById(fileId);
		
	}

	
	public List<Doc> getAllfiles()
	{
		return docRepository.findAll();
		
	}
}
