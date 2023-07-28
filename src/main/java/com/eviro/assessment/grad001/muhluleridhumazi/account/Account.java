package com.eviro.assessment.grad001.muhluleridhumazi.account;

import java.io.File;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eviro.assessment.grad001.muhluleridhumazi.AccountRepository;

@Component
public class Account 
{
	@Autowired 
	AccountRepository accountrepository;
	
	public void importData() throws SQLException
	{
		accountrepository.importData();
	}
	
	public Collection<Profile> listAll()
	{
		return accountrepository.get();
	}
	
	public File getFile(String name)
	{
		return accountrepository.getFile(name);
	}
	

	
	
	
}
