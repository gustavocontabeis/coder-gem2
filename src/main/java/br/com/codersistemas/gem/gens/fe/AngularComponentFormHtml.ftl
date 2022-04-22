<form #${model.name.camelCase.uncapitalized}Form="ngForm" (ngSubmit)="onSubmit(${model.name.camelCase.uncapitalized}Form)">
  <div class="p-fluid" *ngIf="${model.name.camelCase.uncapitalized}">
  <#list model.attributesNotCollection as attribute>
    <#if attribute.type.subtype = 'BOOLEAN'>
    	BOOLEAN: ${attribute.name.name}
    </#if>
    <#if attribute.type.subtype = 'NUMBER'>
     <div class="p-field">
      <label id="label-id" for="${attribute.name.name}">${attribute.label}</label>
      <input id="${attribute.name.name}" name="${attribute.name.name}" #${attribute.name.name}="ngModel" pInputText pKeyFilter="int" placeholder="${attribute.label}" [(ngModel)]="${model.name.camelCase.uncapitalized}.${attribute.name.name}" [required]="${attribute.required?string('true', 'false')}" pattern="^\d+$">
     </div>
    </#if>
    <#if attribute.type.subtype = 'TXT'>
     <div class="p-field">
      <label id="label-${attribute.name.name}" for="${attribute.name.name}">${attribute.label}</label>
      <input id="${attribute.name.name}" name="${attribute.name.name}" #${attribute.name.name}="ngModel" pInputText placeholder="${attribute.label}" [(ngModel)]="${model.name.camelCase.uncapitalized}.${attribute.name.name}" [required]="${attribute.required?string('true', 'false')}">
     </div>
    </#if>
    <#if attribute.type.subtype = 'DATE'>
    	DATE: ${attribute.name.name}
    </#if>
    <#if attribute.type.subtype = 'FK'>
     <div class="p-field">
      <label id="label-${attribute.name.name}" for="${attribute.name.name}">${attribute.label}</label>
      <p-dropdown id="${attribute.name.name}" name="${attribute.name.name}" #${attribute.name.name}="ngModel" [options]="${attribute.name.plural}" [(ngModel)]="${model.name.camelCase.uncapitalized}.${attribute.name.name}" [required]="${attribute.required?string('true', 'false')}"></p-dropdown>
     </div>
    </#if>
    <#if attribute.type.enum>
     <div class="p-field">
      <label id="label-${attribute.name.name}" for="${attribute.name.name}">${attribute.label}</label>
      <p-dropdown id="${attribute.name.name}" name="${attribute.name.name}" #${attribute.name.name}="ngModel" [options]="${attribute.name.plural}" [(ngModel)]="${model.name.camelCase.uncapitalized}.${attribute.name.name}" [required]="${attribute.required?string('true', 'false')}"></p-dropdown>
     </div>
    </#if>
  </#list>
  </div>
</form>
<div class="ui-dialog-buttonpane ui-helper-clearfix">
  <button pButton icon="pi pi-check" (click)="salvar()" label="Salvar" style="margin-right: 2px;" [disabled]="!${model.name.camelCase.uncapitalized}Form.valid"></button>
  <button pButton icon="pi pi-times" (click)="confirmarExcluir()" label="Excluir" class="ui-button-secondary" style="margin-right: 2px;" *ngIf="${model.name.camelCase.uncapitalized}.id"></button>
</div>
<p-confirmDialog #confirmacaoDialog header="Confirmação" icon="pi pi-exclamation-triangle">
  <p-footer>
    <button type="button" pButton icon="pi pi-times" label="Não" (click)="confirmacaoDialog.reject()"></button>
    <button type="button" pButton icon="pi pi-check" label="Sim" (click)="confirmacaoDialog.accept()"></button>
  </p-footer>
</p-confirmDialog>