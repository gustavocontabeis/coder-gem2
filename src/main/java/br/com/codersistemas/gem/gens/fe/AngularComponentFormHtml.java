package br.com.codersistemas.gem.gens.fe;

import java.io.IOException;
import java.util.Map;

import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Attribute;
import br.com.codersistemas.gem.model.Model;
import br.com.codersistemas.gem.util.TemplateHelper;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class AngularComponentFormHtml {
	
	private App app;
	private Model model;
	private Map<String, Object> input;

	public AngularComponentFormHtml(App app, Model model, Map<String, Object> input) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		this.app = app;
		this.model = model;
		this.input = input;
	}
	
	@Override
	public String toString() {
		try {
			return TemplateHelper.getInstance().execute(this.getClass(), input);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
