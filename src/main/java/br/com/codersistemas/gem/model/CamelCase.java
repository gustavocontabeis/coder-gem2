package br.com.codersistemas.gem.model;

import br.com.codersistemas.libs.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CamelCase {
	
	private String name;
	
	public String getCapitalized() {
		return StringUtil.capitalize(name);
	}

	public String getUncapitalized() {
		return StringUtil.uncapitalize(name);
	}

}
