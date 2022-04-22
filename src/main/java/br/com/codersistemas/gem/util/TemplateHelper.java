package br.com.codersistemas.gem.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class TemplateHelper {
	
	private static TemplateHelper INSTANCE;
	
    private static Configuration cfg;
    
    private TemplateHelper() {
    	
    }
    
    public static TemplateHelper getInstance() {
    	if( INSTANCE == null) {
    		INSTANCE = new TemplateHelper();
    	}
    	return INSTANCE;
    }
    
    public String execute(Class<?> classe, Map<String, Object> input) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    	return execute(classe, input, classe.getSimpleName()+".ftl");
    }
    
    public String execute(Class<?> classe, Map<String, Object> input, String templateName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    	
    	cfg = new Configuration();
    	cfg.setClassForTemplateLoading(classe, "");
    	
    	cfg.setIncompatibleImprovements(new Version(2, 3, 20));
    	cfg.setDefaultEncoding("UTF-8");
    	cfg.setLocale(new Locale("PT", "br"));
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = cfg.getTemplate(templateName);

        //Writer consoleWriter = new OutputStreamWriter(System.out);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
		Writer consoleWriter = new OutputStreamWriter(out);
        
        
        template.process(input, consoleWriter);
        String string = new String( out.toByteArray() );
        
        return string;
    }

}
