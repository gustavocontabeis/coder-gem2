<br>
<p-panel header="Apartamentos" >
  <ng-template pTemplate="icons">
      <button pButton class="p-panel-header-icon p-link" title="Adicionar apartamentos a este bloco."
      [routerLink]="['/apartamento/apartamento-add/bloco/', apartamento.bloco.id]">
          <span class="pi pi-plus-circle"></span>
      </button>
  </ng-template>
<p-table [value]="apartamentos" [paginator]="true" [rows]="10" [rowsPerPageOptions]="[5,10,15,20,50,100]" [lazy]="true" (onLazyLoad)="consultarPaginado($event)" responsiveLayout="scroll" [totalRecords]="totalRecords" sortMode="multiple" [showCurrentPageReport]="true" currentPageReportTemplate="Exibindo {first} até {last} de {totalRecords} registros.">
    <ng-template pTemplate="header">
     <tr>
      <th pSortableColumn="id">Id
        <p-columnFilter type="text" field="id" display="menu"></p-columnFilter>
       <p-sortIcon field="id"></p-sortIcon></th>
      <th pSortableColumn="numero">Numero
        <p-columnFilter type="text" field="numero" display="menu"></p-columnFilter>
       <p-sortIcon field="numero"></p-sortIcon></th>
      <th pSortableColumn="bloco">Bloco
        <p-columnFilter type="text" field="bloco.id" display="menu"></p-columnFilter>
       <p-sortIcon field="bloco"></p-sortIcon></th>
      <th pSortableColumn="proprietario">Proprietario
        <p-columnFilter type="text" field="proprietario.id" display="menu"></p-columnFilter>
       <p-sortIcon field="proprietario"></p-sortIcon></th>
      <th pSortableColumn="titular">Titular
        <p-columnFilter type="text" field="titular.id" display="menu"></p-columnFilter>
       <p-sortIcon field="titular"></p-sortIcon></th>
      <th>&nbsp;</th>
     </tr>
    </ng-template>
    <ng-template pTemplate="body" let-apartamento>
     <tr>
      <td class="horizontal-compact id" style="text-align: right;">{{apartamento.id}}</td>
      <td class="horizontal-compact">{{apartamento.numero}}</td>
      <td class="horizontal-compact"><a [routerLink]="['/bloco/', apartamento.bloco.id]">{{apartamento.bloco.nome}}</a></td>
      <td class="horizontal-compact"><a [routerLink]="['/proprietario/proprietario-add/', apartamento.id]">{{apartamento.proprietario.nome}}</a></td>
      <td class="horizontal-compact"><a [routerLink]="['/titular/titular-add/', apartamento.titular.id]">{{apartamento.titular.nome}}</a></td>
      <td>
        <button pButton icon="pi pi-pencil" [routerLink]="['/apartamento/apartamento-add/', apartamento.id]" title="Selecionar Apartamento"></button>
        <button pButton icon="pi pi-list" [routerLink]="['/morador/apartamento/', apartamento.id]" title="Selecionar Moradores"></button></td>
     </tr>
    </ng-template>
   </p-table>
  </p-panel>
