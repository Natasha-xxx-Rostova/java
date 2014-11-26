package ru.natalia.pages.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.Session;

public class SecurityFilter implements RequestFilter {

	@Inject
	private ComponentEventLinkEncoder linkEncoder;
	
	@Inject
	private ComponentClassResolver classResolver;
	
	public boolean service(Request request, Response response,
			RequestHandler handler) throws IOException {
		
		String path = request.getPath().toLowerCase();
		if (path.startsWith("/assets/")
				|| path.endsWith(".css")
				|| path.endsWith(".js")
				|| path.endsWith(".png")
				|| path.endsWith(".jpg")
				|| path.endsWith(".gif")) {
			return handler.service(request, response);
		}
		
		Session session = request.getSession(false);
		PageRenderRequestParameters pageEvent = linkEncoder.decodePageRenderRequest(request);
		ComponentEventRequestParameters componentEvent = linkEncoder.decodeComponentEventRequest(request);

		System.out.println(path);
		
		Class<?> pageClass = null;
		String pageName = null!=componentEvent ? componentEvent.getActivePageName() : pageEvent.getLogicalPageName();
		System.out.println(pageName);
		
		try {
			pageClass = Class.forName(classResolver.resolvePageNameToClassName(pageName));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		Secured secured = pageClass.getAnnotation(Secured.class);
		if (secured != null && (session == null 
				|| session.getAttribute("USERID")== null)){
				System.out.println("redirect");
				response.sendRedirect("/MyFirstPage");
				return true;
			
		}
		return handler.service(request, response);
	}

}
