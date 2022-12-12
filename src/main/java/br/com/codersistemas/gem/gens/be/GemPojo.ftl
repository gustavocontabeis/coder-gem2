package ${app.defaultPackage}.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.entity.Condominio;

public interface ${model.name.camelCase.capitalized}Repository extends JpaRepository<${model.name.camelCase.capitalized}, ${model.pk.type.simpleClassName!}>, JpaSpecificationExecutor<${model.name.camelCase.capitalized}> {

}

