package com.eviro.assessment.grad001.muhluleridhumazi.account;

import java.io.File;
import java.net.URI;

public interface FileParser 
{
	void parseCSV(File csvFile);
	File convertCSVDataToimage(String base64ImageData);
	URI createImageLink(File fileImage);
}
