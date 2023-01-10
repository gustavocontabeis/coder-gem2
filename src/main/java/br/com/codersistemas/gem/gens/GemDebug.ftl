Title: ${title}

Agora: ${hoje?string('dd.MM.yyyy HH:mm:ss')}

AppName:        ${app.appName}
Database:       ${app.database}
DefaultPackage: ${app.defaultPackage}
Schema:         ${app.schema}
<#list app.models as model>

  model.label:                        ${model.label}
  model.restURI:                      ${model.restURI}
  model.name:                         ${model.name}
  model.name.hifenCase:               ${model.name.hifenCase}
  model.name.name:                    ${model.name.name}
  model.name.underlineCase:           ${model.name.underlineCase}
  model.name.camelCase.capitalized:   ${model.name.camelCase.capitalized}
  model.name.camelCase.uncapitalized: ${model.name.camelCase.uncapitalized}
  
  <#list model.attributes as attribute>
    attribute.columnName:                    ${attribute.columnName!}
    attribute.label:                         ${attribute.label}
    attribute.size:                          ${attribute.size!}
    attribute.required:                      ${attribute.required?string('sim', 'não')}
    attribute.name.hifenCase:                ${attribute.name.hifenCase}
    attribute.name.name:                     ${attribute.name.name}
    attribute.name.underlineCase:            ${attribute.name.underlineCase}
    attribute.name.camelCase.capitalized:    ${attribute.name.camelCase.capitalized}
    attribute.name.camelCase.name:           ${attribute.name.camelCase.name}
    attribute.name.camelCase.uncapitalized:  ${attribute.name.camelCase.uncapitalized}
    attribute.type.name:                     ${attribute.type.name}
    attribute.type.className:                ${attribute.type.className}
    attribute.type.simpleClassName:          ${attribute.type.simpleClassName}
    attribute.type.subtype:                  ${attribute.type.subtype!}
    attribute.type.generic:                  ${attribute.type.generic?string('sim', 'não')}
	<#if attribute.type.generic>
        attribute.type.genericType.name.name:         ${(attribute.type.genericType.name.name)!}
    </#if>
    attribute.type.collection:               ${attribute.type.collection?string('sim', 'não')}
    attribute.type.primitive:                ${attribute.type.primitive?string('sim', 'não')}
    attribute.type.fk:                       ${attribute.fk?string('sim', 'não')}
    <#if attribute.fk>
        attribute.fkModel.className:         ${(attribute.fkModel.className)!}
        attribute.fkModel.pk.type.className: ${(attribute.fkModel.pk.type.className)!}
    </#if>
    attribute.type.enum:                     ${attribute.type.enum?string('sim', 'não')}
    <#if attribute.type.enum>
        <#list attribute.type.enumValues as enumValue>
            ${enumValue}
        </#list>
    </#if>
    
  </#list>
    
</#list>
