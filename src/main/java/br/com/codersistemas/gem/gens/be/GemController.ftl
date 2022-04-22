package br.com.codersistemas.condominiosadm.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codersistemas.condominiosadm.domain.${model.name.camelCase.capitalized};
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.${model.name.camelCase.capitalized}Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${model.restURI}")
public class ${model.name.camelCase.capitalized}Controller extends BaseController<${model.name.camelCase.capitalized}> {
	
	@Autowired
	private ${model.name.camelCase.capitalized}Service ${model.name.camelCase.uncapitalized}Service;
	
	@GetMapping
	public List<${model.name.camelCase.capitalized}> list() {
		return ${model.name.camelCase.uncapitalized}Service.findAll(Sort.by(Order.asc("${model.pk.name.name}")));
	}
	
	@PostMapping("/page")
	public Page<${model.name.camelCase.capitalized}> list(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<${model.name.camelCase.capitalized}> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return ${model.name.camelCase.uncapitalized}Service.findAll(specification, pageRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<${model.name.camelCase.capitalized}> find(@PathVariable Long id) {
		Optional<${model.name.camelCase.capitalized}> findById = ${model.name.camelCase.uncapitalized}Service.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ${model.name.camelCase.capitalized} save(@Valid @RequestBody ${model.name.camelCase.capitalized} entity) {
		return ${model.name.camelCase.uncapitalized}Service.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<${model.name.camelCase.capitalized}> delete(@PathVariable ${model.pk.type.simpleClassName} id) {
		Optional<${model.name.camelCase.capitalized}> findById = ${model.name.camelCase.uncapitalized}Service.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		${model.name.camelCase.uncapitalized}Service.delete(findById.get());
		return new ResponseEntity<${model.name.camelCase.capitalized}>(HttpStatus.NO_CONTENT);
	}
	
	//FKs
	<#list model.attributesFk as attribute>
	@GetMapping("/${attribute.name.camelCase.uncapitalized}/{id}")
	public ResponseEntity<List<${model.name.camelCase.capitalized}>> findBy${attribute.name.camelCase.capitalized}(@PathVariable("id") ${model.pk.type.simpleClassName} id) {
		Optional<List<${model.name.camelCase.capitalized}>> findById = ${model.name.camelCase.uncapitalized}Service.findBy${attribute.name.camelCase.capitalized}Id(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}
		return ResponseEntity.ok(findById.get());
	}
	
    </#list>
}

