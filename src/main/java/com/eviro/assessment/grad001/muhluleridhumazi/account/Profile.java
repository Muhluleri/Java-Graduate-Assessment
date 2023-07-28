package com.eviro.assessment.grad001.muhluleridhumazi.account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import org.apache.commons.io.FileUtils;

public class Profile implements FileParser
{
	private ArrayList<String[]> profile = new ArrayList<String[]>();
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> surname = new ArrayList<String>();
	private ArrayList<String> format = new ArrayList<String>();
	private ArrayList<String> csvData = new ArrayList<String>();
	private ArrayList<File> ImageData = new ArrayList<File>();
	private ArrayList<URI> ImageLink = new ArrayList<URI>();
	private File file;
	
	private String tmpName, tmpSurname ,tmpFormat; //For image file naming
	private String[] tmp;
	
	public Profile(String file)
	{
		this.file = new File(file);
		parseCSV(this.file);
	}
	
	public File getFile()
	{
		return file;
	}
	
	@Override
	public void parseCSV(File csvFile) 
	{
		try 
		{
			Scanner sc = new Scanner(csvFile);
			while (sc.hasNextLine())
			{
				String[] i = sc.nextLine().split(",");
				profile.add(i);				
			}
			
			for (String[] temp : profile)
			{
				name.add(temp[0]);
				surname.add(temp[1]);
				format.add(temp[2]);
				csvData.add(temp[3]);	
			}
			sc.close();
			
			name.remove(0);
			surname.remove(0);
			format.remove(0);
			csvData.remove(0);
			
			for (int i = 0; i < name.size(); i++)
			{
				tmpName =name.get(i);
				tmpSurname = surname.get(i);
				tmp = format.get(i).split("/",2);
				tmpFormat = tmp[1];
				ImageData.add(convertCSVDataToimage(csvData.get(i)));
				ImageLink.add(createImageLink(ImageData.get(i)));
			}
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}
	
	@Override
	public File convertCSVDataToimage(String base64ImageData) 
	{
		File image = new File(tmpName + " " + tmpSurname + "." + tmpFormat);
		byte[] imageData = Base64.getDecoder().decode(base64ImageData);
		try 
		{
			FileUtils.writeByteArrayToFile(image, imageData);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return image;
	}
	@Override
	public URI createImageLink(File fileImage) 
	{
		URI uri = null;
		try
		{
			uri = fileImage.toURI();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return uri;
	}
	
	public ArrayList<File> getImageFile() 
	{
		return ImageData;
	}
	
	public ArrayList<String> getName()
	{
		return name;
	}
	
	public ArrayList<String> getSurname()
	{
		return surname;
	}
	
	public ArrayList<String> getFormat()
	{
		return format;
	}
	
	public ArrayList<URI> getImagelink()
	{
		return ImageLink;
	}
}
