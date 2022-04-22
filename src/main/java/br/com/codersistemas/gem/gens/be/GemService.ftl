package ${app.defaultPackage}.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.codersistemas.condominiosadm.domain.${model.name.camelCase.capitalized};
import br.com.codersistemas.condominiosadm.repository.${model.name.camelCase.capitalized}Repository;
import br.com.codersistemas.condominiosadm.repository.BlocoRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class ${model.name.camelCase.capitalized}Service {

	@Autowired
	private ${model.name.camelCase.capitalized}Repository ${model.name.camelCase.uncapitalized}Repository;

	<#list model.attributesFk as attribute>
	@Autowired
	private ${attribute.name.camelCase.capitalized}Repository ${attribute.name.camelCase.uncapitalized}Repository;
	
    </#list>
	
	@Transactional(readOnly = true)
	public List<${model.name.camelCase.capitalized}> findAll(Sort by) {
		return ${model.name.camelCase.uncapitalized}Repository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<${model.name.camelCase.capitalized}> findAll(Specification<${model.name.camelCase.capitalized}> specification, PageRequest pageRequest) {
		return ${model.name.camelCase.uncapitalized}Repository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<${model.name.camelCase.capitalized}> findById(${model.pk.type.simpleClassName} id) {
		return ${model.name.camelCase.uncapitalized}Repository.findById(id);
	}

	@Transactional(readOnly = false)
	public ${model.name.camelCase.capitalized} save(@Valid ${model.name.camelCase.capitalized} entity) {
		return ${model.name.camelCase.uncapitalized}Repository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(${model.name.camelCase.capitalized} ${model.name.camelCase.uncapitalized}) {
		${model.name.camelCase.uncapitalized}Repository.delete(${model.name.camelCase.uncapitalized});		
	}

	<#list model.attributesFk as attribute>
	@Transactional(readOnly = true)
	public Optional<List<${model.name.camelCase.capitalized}>> findBy${attribute.name.camelCase.capitalized}Id(${attribute.fkModel.pk.type.simpleClassName} id){
		return ${model.name.camelCase.uncapitalized}Repository.findBy${attribute.name.camelCase.capitalized}Id(id);
	}
	
    </#list>
}

