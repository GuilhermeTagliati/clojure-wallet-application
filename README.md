# API Clojure - Carteira de Investimentos

## Desenvolvedores
Nome: Guilherme Tagliati da Silva       RA: 17.00375-0
Nome: Breno Thomaz Savanini             RA: 17.00815-8
Nome: Lucas Martins Coelho 	            RA: 15.03863-7
Nome: Enricco Diniz 		            RA: 17.00165-0
Nome: Lucas Menezes                     RA: 16.00683-6

## Resumo
O projeto consiste em criar uma API para inserir, selecionar, atualizar e deletar informações de uma determinada carteira de investimentos. As carteiras de investimentos serão criadas com base no sistema de ARCA (Ativos Nacionais, *Real State*,  Caixa e Ativos Internacionais)


## Desenvolvimento
### Tecnologias

- Será utilizado o docker para provisão de recursos. 
- Postgres DB para armazenamento das informações
- Clojure como linguagem principal

### Informações lógicas do projeto
Serão consideradas essas as entidades principais.

**CARTEIRA:**
- name: string
- realstate: double
- international: double
- cash: double
- national: double
- operations: ListOperations

**USUARIO:**
- name: string
- wallet: Carteira

**OPERATION**
- stockOperation: 'string'
- value: double

**Diagrama Entidade Relacionamento**

![DER](https://raw.githubusercontent.com/GuilhermeTagliati/clojure-wallet-application/main/resources/images/clojure.png)

## Rodando o Projeto

O projeto está utilizando docker-compose para sua inicialização. Então assim que clonar o projeto, não esqueça de ter docker instalado na maquina e então rode os comandos abaixo:

Para construir e criar o uberjar do projeto em clojure:
```bash
docker-compose build
```

Para servir a aplicação e o banco de dados
```bash
docker-compose up
```

Com isso, todo o ambiente (banco de dados e api) serão criados e estarão disponíveis para teste em `localhost:3000` no caso da API e em `localhost:5432` no caso do banco de dados.

## Testes com o projeto

Dentro do projeto, no caminho `./test/rest/` existem diversos arquivos .rest que foram criados com o intuito de testar as chamadas HTTP. 