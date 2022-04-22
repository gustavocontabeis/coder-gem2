package br.com.codersistemas.gem.model;

import br.com.codersistemas.gem.util.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Name {
	
	private String name;
	
	public String getUnderlineCase() {
		return StringHelper.toUnderlineCase(name).toLowerCase();		
	}
	public String getHifenCase() {
		return StringHelper.toHifenCase(name);
	}
	public String getPlural() {
		return StringHelper.plural(name);
	}
	public CamelCase getCamelCase() {
		return new CamelCase(name);
	}
}
