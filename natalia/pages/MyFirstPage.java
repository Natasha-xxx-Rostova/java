package ru.natalia.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Reader;

import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import ru.natalia.services.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
public class MyFirstPage {
	@Component(id="loginForm")
	private Form form;
	
	@Inject
	private HttpServletRequest httpReq;
	
	@SessionState(create=true)
	private User user;
	
	@Property
	@Persist
	private String password;
	
	@Property
	@Persist
	private String email;
	
	Object onActivate()
	{
		System.out.println(user);
		HttpSession se =httpReq.getSession();
		if(se.getAttribute("USERID")!= null)
		{
			return Inbox.class;
		} else
		{

		return null;
		}
	}
	void onValidateFromLoginForm()
	{
		//user = new Users();
		user.readUserData("C:/tmp/"+email+".txt");
		if(!user.password.equals(password) || !user.email.equals(email))
			form.recordError("Error!");
		
	}
	Object onSuccess() throws IOException, ServletException
	{
			HttpSession se =httpReq.getSession(true);
			se.setAttribute("USERID", user);
			return Inbox.class;
	}
	
}
