package com.eviro.assessment.grad001.muhluleridhumazi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import java.io.IOException;
import java.sql.*;

@SpringBootApplication
@PropertySource("application.properties")
public class AccountApplication 
{
	public static void main(String[] args) throws IOException, SQLException 
	{
		SpringApplication.run(AccountApplication.class, args);
		
	}
}
