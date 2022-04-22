package br.com.codersistemas.gem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attribute {
	private Name name;
	private String label, columnName;
	private boolean pk, fk, required, unique;
	private Model model;
	private Model fkModel;
	private Type type;
	private Integer size;
}
