# WORKSHOP SPRINGBOOT 3 + JPA

Este repositório contém um projeto em Java com **Spring Boot 3.5.5**, desenvolvido como parte do curso de java do Nélio Alves, mais especificamente no módulo "Projeto web services com Spring boot + JPA/Hibernate" 

Porém, decidi ir além do projeto inicial, então implementei muitas features e refatorações como método de estudo e melhoria, adicionando robustez, padrões mais próximos do mercado e aprimoramento da lógica de negócio.

O projeto demonstra uma compreensão sólida do padrão de arquitetura em camadas e dos conceitos básicos de Spring Boot, JPA/Hibernate e bancos de dados relacionais.

---

### Principais Habilidades e Conhecimentos

Este projeto me ajudou a aprofundar minha experiência em:

* **Arquitetura em Camadas**: Organização clara entre .entities, .repositories, .services e .resources.
* **Lógica de Negócio**: Implementação de operações **CRUD** e regras de negócio para garantir a integridade dos dados, como o tratamento de associações em `ProductService`.
* **APIs RESTful**: Criação de endpoints claros e aderentes aos padrões de web services.
* **Inglês Técnico**: Prática e melhoria do inglês técnico na documentação e na sintáxe do código.

---

### Diferenciais

Para ir além do curso, implementei as seguintes melhorias:

✅ **Finalização** completa das implementações nas classes de `repositories` e `resources`.

✅ **Aprimoramento** da lógica de negócio no método updateData da classe ProductService, garantindo a integridade dos dados e o tratamento de associações (como a relação entre Product e Category).

✅ **Aprimoramento** da lógica de negócio na classe Order, garantindo que sempre que um Payment for associado a um Order, esse Order terá seu OrderStatus automaticamente alterado para PAID.

✅ **Validações** de dados de entrada para email e número de telefone na entidade User utilizando regex.

✅ **Tratamento** de exceções mais aprofundado, garantindo que a API retorne o código HTTP correspondente a situação.

✅ **Testes** unitários para validação e regras de negócio

✅ **Implementação** de padrão DTO para melhor saída e entrada de dados na API.

✅ **Documentação** automática da API utilizando Swagger/openAPI.

✅ **Implementação** de banco de dados relacional MySQL.


---

Este projeto serve tanto como um portfólio prático do meu padrão de código e atenção a detalhes, quanto como uma referência de estudo, utilizei o projeto do curso como base para criar algo mais robusto e próximo dos padrões de mercado reais.

Fique à vontade para explorar o código e me contatar caso tenha dúvidas ou feedback. Adoraria ouvir de você!

***

# WORKSHOP SPRINGBOOT 3 + JPA

This repository contains a Java + **Spring Boot 3.5.5** project, developed as part of Nélio Alves' Java course, specifically in the "Projeto web services com Spring boot + JPA/Hibernate" module.

However, I decided to go beyond the initial project, then I implemented many features and refactoring as a study and self improvement method, Adding confiability, closer market patterns, and business rules improvement.

The project demonstrates a solid understanding of the layered architecture pattern and the basic concepts of Spring Boot, JPA/Hibernate, and relational databases.

---

### Main Skills and Knowledge

This project helped me to deepen my experience in:

* **Layered Architecture**: Clear organization between `.entities`, `.repositories`, `.services`, and `.resources`.
* **Business Logic**: Implementation of business rules and **CRUD** operations to ensure data integrity, as well as handling associations in the `ProductService`.
* **RESTful APIs**: Creation of clear endpoints that follow good web service patterns.
* **Technical English**: Practicing and improving my technical English in documentation and code comments.

---

### Key Enhancements

To go beyond the course, I implemented the following **enhancements**:

✅ **Complete** finalization of the class implementations in `repositories` and `resources`.

✅ **Improved** business logic in the `updateData` method of the `ProductService` class, ensuring data integrity and correct handling of associations (like the relationship between `Product` and `Category`).

✅ **Improved** business logic in Order, ensuring that every time a Payment is associated with an Order, its OrderStatus is automatically updated to PAID.

✅ **Input** data validation to email and phone number in User using regex.

✅ **Better** exception handling, ensuring that the API return the right HTTP code for each situation.

✅ **Unit** tests for validation and business logic.

✅ **DTO** pattern implementation for better data input and output on the API.

✅ **Automatic** documentation of the API using Swagger/openAPI.

✅ **MySQL** relational database implementation.


---

This project serves both as a practical portfolio of my coding pattern and attention to details, and as a study reference, I used the course project as a base to build something more solid and closer to real market patterns.

Feel free to explore the code and contact me if you have any doubts or feedback. I would love to hear from you!
