package com.eviro.assessment.grad001.muhluleridhumazi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;

import com.eviro.assessment.grad001.muhluleridhumazi.account.*;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController 
{
	@Autowired
	private Account account;
	
	@GetMapping("/importData")
	public String importData()
	{
		try 
		{
			account.importData();
			return "Upload Successful";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Error when uploading data";
		}
	}
	
	@GetMapping("/")
	public Collection<Profile> get()
	{
		return account.listAll();
	}
	
	@SuppressWarnings("unused")
	@GetMapping("{name}/{surname}")
	public FileSystemResource getHttpImageLink(@PathVariable String name , @PathVariable String surname)
	{
		FileSystemResource fs = new FileSystemResource(account.getFile(name));
		if (fs == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return fs;
	}
}
