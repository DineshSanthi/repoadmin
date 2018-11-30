package com.mycompany.client;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public abstract class ScriptManager {
    
	/*  public String scriptEval(String scriptEvent){
	  ScriptEngineManager manager = new ScriptEngineManager();
	  ScriptEngine engine = manager.getEngineByName("JavaScript");
	  Object value = "";

	  try {
			engine.eval(scriptEvent);
			System.out.println(engine.get("z"));
			
	  } catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	  return value.toString();*/
	
	 public static void main(String[] args) {
		    ScriptEngineManager manager = new ScriptEngineManager();
		    List<ScriptEngineFactory> factories = manager.getEngineFactories();
		    for (ScriptEngineFactory factory : factories) {
		      List<String> ext = factory.getExtensions();
		      for (int i = 0; i < ext.size(); i++) {
		        System.out.printf("Supported file extension: " + (String) ext.get(i) + "\n");
		      }
		    }
		  
	  
		
		
		/*public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("JavaScript");
	    Object value = "";

	    try {
			engine.eval("var x= 10; var y =20;var z = x+y;");
			System.out.println(engine.get("z"));
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
}
}
