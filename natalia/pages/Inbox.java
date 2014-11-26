package ru.natalia.pages;
import java.io.Console;
import java.io.IOException;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import ru.natalia.pages.security.Secured;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Secured
public class Inbox {
	@Inject
	HttpServletRequest httpReq;
	
	@Inject
	Request request;
	
	@OnEvent(value="logout")
	Object awsdgsdfgdfg()
	{
		HttpSession se =httpReq.getSession();
		se.invalidate();
		return MyFirstPage.class;
	}
	

}
