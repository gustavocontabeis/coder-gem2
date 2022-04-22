package br.com.codersistemas.gem.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.codersistemas.gem.gens.GemDebug;
import br.com.codersistemas.gem.gens.be.GemController;
import br.com.codersistemas.gem.gens.be.GemRepository;
import br.com.codersistemas.gem.gens.be.GemService;
import br.com.codersistemas.gem.gens.fe.AngularComponentFormHtml;
import br.com.codersistemas.gem.gens.fe.AngularComponentFormTS;
import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Model;

public class Main {
	public static void main(String[] args) throws Exception {

		App app = App.builder()
				.appName("condominioadm-adm")
				.defaultPackage("br.com.codersistemas.condominioadm")
				.schema("schema")
				.database("database")
				.models(new ArrayList<Model>())
				.build();

		app.addClasses(
				br.com.codersistemas.condominiosadm.domain.Apartamento.class, 
				br.com.codersistemas.condominiosadm.domain.Banco.class, 
				br.com.codersistemas.condominiosadm.domain.Banco2.class, 
				br.com.codersistemas.condominiosadm.domain.BancoLancamento.class,
				br.com.codersistemas.condominiosadm.domain.Bloco.class, 
				br.com.codersistemas.condominiosadm.domain.Boleto.class, 
				br.com.codersistemas.condominiosadm.domain.Caixa.class, 
				br.com.codersistemas.condominiosadm.domain.CentroDeCusto.class,
				br.com.codersistemas.condominiosadm.domain.Condominio.class, 
				br.com.codersistemas.condominiosadm.domain.Faturamento.class, 
				br.com.codersistemas.condominiosadm.domain.Morador.class, 
				br.com.codersistemas.condominiosadm.domain.Pessoa.class, 
				br.com.codersistemas.condominiosadm.domain.Sindico.class);
		
		java.util.Map<String, Object> input = new HashMap<String, Object>();

		Model model = app.getModels().get(0);
		
		input.put("title", "Exemplo Gem");
		input.put("hoje", new Date());
		input.put("app", app);
		input.put("models", app.getModels());
		input.put("model", model);
		
		System.out.println(model.getPk().getType().getClassName());
		System.out.println("=========================================================================");
		System.out.println(new GemDebug(app, model, input));
		System.out.println("=========================================================================");
		System.out.println(new GemRepository(app, model, input));
		System.out.println("=========================================================================");
		System.out.println(new GemService(app, model, input));
		System.out.println("=========================================================================");
		System.out.println(new GemController(app, model, input));
		System.out.println("=========================================================================");
		System.out.println(new AngularComponentFormHtml(app, model, input));
		System.out.println("=========================================================================");
		System.out.println(new AngularComponentFormTS(app, model, input));
		System.out.println("=========================================================================");
		
		

	}
}
