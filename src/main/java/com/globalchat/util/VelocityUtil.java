package com.globalchat.util;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	private static VelocityEngine engine = null;
	
	public static void writeFileToResponse(HttpServletResponse response, String fileName) {
		writeFileToResponse(response, fileName, new VelocityContext());
	}
	
	public static void writeFileToResponse(
			HttpServletResponse response, 
			String fileName, 
			VelocityContext context) 
	{
		try {
			String resourceName = "/templates/"+fileName;
			VelocityEngine ve = VelocityUtil.getVelocityEngine();
			Template t = ve.getTemplate(resourceName);	
			
			t.merge(context, response.getWriter());

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/html");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
	
	protected static VelocityEngine getVelocityEngine() {
		if (engine == null) {
			engine = new VelocityEngine();			
			engine.init(getProperties());
		}
		
		return engine;
	}
	
	private static Properties getProperties() {
		Properties prop = new Properties();
		
		prop.put("resource.loader", "class");
		prop.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		return prop;
	}
}
