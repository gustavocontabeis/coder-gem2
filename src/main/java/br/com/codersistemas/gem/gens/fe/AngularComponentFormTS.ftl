import { Router, ActivatedRoute } from '@angular/router';
import { ${model.name.camelCase.capitalized}Service } from '../${model.name.camelCase.uncapitalized}.service';
import { Component, OnInit } from '@angular/core';
import { ${model.name.camelCase.capitalized} } from '../${model.name.camelCase.uncapitalized}';
import { MessageService, ConfirmationService, SelectItem } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { PessoaService } from 'src/app/pessoa/pessoa.service';

@Component({
  selector: 'app-${model.name.camelCase.uncapitalized}-add',
  templateUrl: './${model.name.camelCase.uncapitalized}-add.component.html',
  styleUrls: ['./${model.name.camelCase.uncapitalized}-add.component.css']
})
export class ${model.name.camelCase.capitalized}AddComponent implements OnInit {

  ${model.name.camelCase.uncapitalized}: ${model.name.camelCase.capitalized} = new ${model.name.camelCase.capitalized}();
  ${model.name.camelCase.uncapitalized}s!: ${model.name.camelCase.capitalized}[];
  exibirDialog!: boolean;
  novoRegistro!: boolean;

  <#list model.attributesFk as attribute>
  ${attribute.name.plural}: SelectItem[] = [];
  </#list>

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private ${model.name.camelCase.uncapitalized}Service: ${model.name.camelCase.capitalized}Service, 
  <#list model.attributesFkUnique as attribute>
    private ${attribute.type.name.camelCase.uncapitalized}Service: ${attribute.type.simpleClassName}Service,
  </#list>
  ) { }

  ngOnInit() {
    this.exibirDialog = false;
    this.novoRegistro = false;
    this.${model.name.camelCase.uncapitalized} = new ${model.name.camelCase.capitalized}();
    
  <#list model.attributesFk as attribute>
    this.${attribute.name.plural} = [];
  </#list>

  <#list model.attributesFkUnique as attribute>
    this.find${attribute.type.simpleClassName}();
  </#list>

    this.activatedRoute.params.subscribe(params => {
  <#list model.attributesFk as attribute>
      if (params.${attribute.columnName}) {
        const ${attribute.columnName} = params.${attribute.columnName} ? Number(params.${attribute.columnName}) : null;
        this.find${model.name.camelCase.capitalized}By${attribute.name.camelCase.capitalized}(Number(${attribute.columnName}));
      } else  
  </#list>
	 if (params.id) {
        const id = params.id ? Number(params.id) : null;
        this.buscar(Number(id));
      } else {
        this.consultar();
      }
    });

  }
  
  <#list model.attributesFkUnique as attribute>
  find${attribute.type.simpleClassName}ById(id: number){
    this.${attribute.name.camelCase.uncapitalized}Service.findById(id).subscribe((resposta: ${attribute.type.simpleClassName}) => {
      this.${attribute.name.plural}.push({label: resposta.nome, value: resposta});
      this.${model.name.camelCase.uncapitalized}.${attribute.name.camelCase.uncapitalized} = resposta;
      }, (error: any) => {
        console.log(error);
        alert(error.ok);
      }
    );
  }
  
  </#list>
  
  <#list model.attributesFkUnique as attribute>
  find${attribute.type.simpleClassName}(){
    this.${attribute.name.camelCase.uncapitalized}Service.consultar().subscribe((resposta: any) => {
      const itens = resposta as ${attribute.type.simpleClassName}[];
      itens.forEach(element => {
         this.${attribute.name.plural}.push({label: element.nome, value: element});
         let item = element as ${attribute.type.simpleClassName};
         if(this.${model.name.camelCase.uncapitalized}.${attribute.name.camelCase.uncapitalized} && this.${model.name.camelCase.uncapitalized}.${attribute.name.camelCase.uncapitalized}.id == item.id){
          this.${model.name.camelCase.uncapitalized}.${attribute.name.camelCase.uncapitalized} = item;
         }
      });
      }, (error: any) => {
        console.log(error);
        alert(error.ok);
      }
    );
  }
  </#list>
  
  novo() {
    const ${model.name.camelCase.uncapitalized} = new ${model.name.camelCase.capitalized}();
    this.exibirModal(${model.name.camelCase.uncapitalized});
  }

  exibirModal(${model.name.camelCase.uncapitalized}: ${model.name.camelCase.capitalized}) {
    this.novoRegistro = true;
    this.exibirDialog = true;
    this.${model.name.camelCase.uncapitalized} = ${model.name.camelCase.uncapitalized};
  }

  salvar() {
    console.log('salvar');
    this.${model.name.camelCase.uncapitalized}Service.adicionar(this.${model.name.camelCase.uncapitalized}).subscribe((resposta: any) => {
      this.consultar();
      this.exibirDialog = false;
      this.novoRegistro = false;
      this.messageService.add({severity: 'success', summary: 'OK', detail: 'Registro adicionado com sucesso.'});
      this.router.navigate(['/${model.name.camelCase.uncapitalized}/bloco/', this.${model.name.camelCase.uncapitalized}.bloco.id]);
      }, (error: any) => {
        console.log(error);
        alert(error.ok);
      }
    );
  }

  confirmarExcluir() {
    console.log('confirmarExcluir');
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir este registro?',
      accept: () => {
          console.log('confirmarExcluir - accept');
          this.excluir();
      },
      reject: () => {
          this.messageService.add({severity: 'success', summary: 'Cancelado', detail: 'Ok. Cancelado.'});
      }
    });
  }

  excluir() {
    console.log('excluir');
    this.${model.name.camelCase.uncapitalized}Service.excluir(this.${model.name.camelCase.uncapitalized}).subscribe((resposta: any) => {
      this.consultar();
      this.exibirDialog = false;
      this.novoRegistro = false;
      this.messageService.add({severity: 'success', summary: 'OK', detail: 'Registro excluído com sucesso.'});
      }, (error: any) => alert('erro ${model.name.camelCase.uncapitalized}s.')
    );
  }

  aoSelecionar(event: any) {
    this.novoRegistro = false;
  }
  
  onSubmit(${model.name.camelCase.uncapitalized}Form: any) {

  }

}

