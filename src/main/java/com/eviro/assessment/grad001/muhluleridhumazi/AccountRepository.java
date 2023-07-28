package com.eviro.assessment.grad001.muhluleridhumazi;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.eviro.assessment.grad001.muhluleridhumazi.account.Profile;

@Repository
public class AccountRepository 
{
	private Profile profile;
	private Map<Integer, Profile> db = new HashMap<>();
	Path targetpath;
	public AccountRepository() throws URISyntaxException
	{
		targetpath = Paths.get(getClass().getResource("/").toURI()).getParent().getParent();
		profile = new Profile(targetpath + "\\1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
	}
	
	public URL converttohttpLink(URI imgLink)
	{
		URL tmp = null ;
		try {
			tmp = imgLink.toURL();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		return tmp;
	}
	
	public void importData() throws SQLException
	{
		String file = targetpath.toString().replace('\\', '/'); ;
		String url = "jdbc:h2:file:" + file + "/spring boot";
		Connection conn = DriverManager.getConnection(url,"","");
		Statement st = conn.createStatement();
		st.execute("DELETE FROM ACCOUNT");
		for (int i = 0; i < profile.getName().size(); i++)
		{
			URL tmp = converttohttpLink(profile.getImagelink().get(i));
			
		st.execute("INSERT INTO ACCOUNT " + 
				   "VALUES("+ (i +1) +" ,'"+profile.getName().get(i)+
				   "', '"+profile.getSurname().get(i)+"', '"+ tmp.toString()+"');");
		}
	}
	
	public Collection<Profile> get()
	{
		{{db.put(1, profile);}};
		
		return db.values();
	}
	
	public File getFile(String name)
	{		
		int i = profile.getName().indexOf(name);
		return profile.getImageFile().get(i);
	}
}
