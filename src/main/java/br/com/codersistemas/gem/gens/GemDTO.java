package br.com.codersistemas.gem.gens;

import java.util.Map;

import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Model;

public class GemDTO extends BaseGem {
	
	public GemDTO(App app, Model model, Map<String, Object> input) {
		super(app, model, input);
	}

}
