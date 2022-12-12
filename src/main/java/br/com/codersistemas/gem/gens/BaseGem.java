package br.com.codersistemas.gem.gens;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.codersistemas.gem.exceptions.GemException;
import br.com.codersistemas.gem.exceptions.TemplateGemException;
import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Model;
import br.com.codersistemas.gem.util.TemplateHelper;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class BaseGem {
	
	protected App app;
	protected Model model;
	protected Map<String, Object> input;

	public BaseGem(App app, Model model) {
		this.app = app;
		this.model = model;
		this.input = new HashMap<>();
	}

	public BaseGem(App app, Model model, Map<String, Object> input) {
		this.app = app;
		this.model = model;
		this.input = input;
	}

	public String print() throws GemException {
		try {
			String execute = TemplateHelper.getInstance().execute(this.getClass(), input);
			return execute;
		} catch (TemplateNotFoundException e) {
			throw new TemplateGemException(e);
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
