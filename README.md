# Requisitos para compilar e executar a aplicação

# Requisitos:
  
  1 - Ter instalado e configurado o JDK 1.8 ou superior.
  
  2 - Ter instalado e configurado do o maven.
  
#  Compilação, execução da aplicação e execução de testes unitários.
   Estando com as ferramentas instaladas e configuradas adequadamente e, tendo clonado o codigo fonte, basta seguir os passos descritos seguir:

   Entrar em terminal (Linux/Mac) ou no CMD do do Windows e acessar o diretório onde o código fonte foi clonado, entrar no diretorio "desafio" e executar os comandos para cada ação desejada.
  
    
  1 - Para compilar a aplicação, execute o comando:
    
    mvn clean install
    
  2 - Tendo compilado o projeto, para executá-lo utilize o comando:
  
    java -jar target/desafio-0.0.1.jar  
  
  3 - Para executar os teste unitários, entre o comando:

    mvn test
  
    
  
  

# Documentação de especificação da API.
  Para visualizar especificação da API, desenvolvida com a ferramenta Swagger. 
  Com a aplicação executando acesse: {URL base}/swagger-ui.html  
  
  Ex.:
   
    http://localhost:8080/swagger-ui.html
