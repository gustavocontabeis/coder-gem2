package ${app.defaultPackage}.dto;

import java.io.Serializable;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ${model.name.camelCase.capitalized}DTO implements Serializable {

  <#list model.attributes as attribute>
  	private ${attribute.type.simpleClassName} ${attribute.name.name};
  </#list>
}
