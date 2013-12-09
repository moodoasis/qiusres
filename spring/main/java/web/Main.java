package web;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.glassfish.gmbal.ParameterNames;

import domain.Word;
import service.XmlService;


@Path("/words")
public class Main extends javax.ws.rs.core.Application {
	private static Logger logger = Logger.getLogger(Class.class.getName());
	@Autowired XmlService xmlService;
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFullWords(@QueryParam("level") int level){
		List<Word> words = xmlService.getWordsByLevel(level);
		JSONObject json = new JSONObject();
		JSONArray list = new JSONArray();
		
		for(Word word : words){
			list.add(word.getFullWord());
		}
		
		json.put("contain", list);
		String jsonString = json.toJSONString();
		
		logger.info(jsonString);
		
		return jsonString;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		
		Main main = (Main)ctx.getBean("test");
		
		main.getFullWords(1);
		
	}

}
