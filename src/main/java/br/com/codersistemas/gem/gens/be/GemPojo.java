package br.com.codersistemas.gem.gens.be;

import java.util.Map;

import br.com.codersistemas.gem.exceptions.GemException;
import br.com.codersistemas.gem.exceptions.GemRuntimeException;
import br.com.codersistemas.gem.gens.BaseGem;
import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Model;
import br.com.codersistemas.libs.utils.JPAUtil;

public class GemPojo extends BaseGem {
	
	public GemPojo(App app, Model model, Map<String, Object> input) {
		super(app, model, input);
	}
	
	@Override
	public String print() throws GemException {
		try {
			StringBuilder sb = new StringBuilder();
			Class<?> className = Class.forName(model.getClassName());
			sb.append(JPAUtil.gerarClasse(className));
			sb.append("\n");
			sb.append(JPAUtil.gerarAtributos(className));
			sb.append("\n");
			sb.append("}\n");
			sb.append("//Ajuste os tamanhos dos campos, obrigatoriedade, etc.\n");
			return sb.toString();
		} catch (ClassNotFoundException e) {
			throw new GemException("Classe "+model.getClassName()+" nao existe.");
		}
	}
	
	@Override
	public String toString() {
		try {
			return print();
		} catch (GemException e) {
			throw new GemRuntimeException(e);
		}
	}

}
