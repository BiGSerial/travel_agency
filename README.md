### README - Trabalho de Programação em Estrutura de Dados

---

#### Português

**Título:** Trabalho de Programação em Estrutura de Dados - Árvores e Suas Variações

**Professor(a):** Renata Leite

**Descrição do Projeto:**

Este projeto tem como objetivo desenvolver um programa para uma agência de viagens utilizando conceitos de estrutura de dados com foco em árvores e suas variações. O trabalho é parte da disciplina ministrada pela professora Renata Leite e visa a aplicação prática dos métodos de manipulação de árvores.

**Funcionalidades:**

1. **Cadastro de Viagens:**
   - Inserção de novas viagens em uma árvore específica.
   - Listagem de todas as viagens cadastradas.

2. **Cadastro de Viajantes:**
   - Inserção de novos viajantes em uma árvore específica.
   - Listagem de todos os viajantes cadastrados.

3. **Relacionamento Viagens-Viajantes:**
   - Interligação de viajantes e viagens cadastrados.
   - Pesquisa de viajantes associados a uma determinada viagem.
   - Pesquisa de viagens associadas a um determinado viajante.

4. **Consultas e Listagens:**
   - Pesquisa de viagens por viajantes.
   - Pesquisa de viajantes por viagens.
   - Listagem completa de viajantes e viagens.

**Metodologia:**

O projeto será implementado utilizando métodos de manipulação de árvores, como inserção, busca, remoção e travessia. As árvores serão utilizadas para armazenar e organizar os dados de viagens e viajantes, proporcionando eficiência nas operações de busca e relacionamento entre eles.

**Estrutura do Repositório:**

- **src/**: Contém o código fonte do projeto.
- **docs/**: Documentação do projeto.
- **tests/**: Testes unitários e de integração.
- **README.md**: Este arquivo.

**Como Executar o Projeto:**

1. Clone o repositório.
2. Instale as dependências necessárias.
3. Execute o arquivo principal para iniciar o programa.

---

#### English

**Title:** Data Structures Programming Project - Trees and Their Variations

**Professor:** Mrs Leite, Renata

**Project Description:**

This project aims to develop a program for a travel agency using data structure concepts focusing on trees and their variations. The work is part of the course taught by Professor Renata Leite and aims at the practical application of tree manipulation methods.

**Features:**

1. **Travel Registration:**
   - Insertion of new trips into a specific tree.
   - Listing all registered trips.

2. **Traveler Registration:**
   - Insertion of new travelers into a specific tree.
   - Listing all registered travelers.

3. **Travel-Travelers Relationship:**
   - Interlinking registered travelers and trips.
   - Search for travelers associated with a specific trip.
   - Search for trips associated with a specific traveler.

4. **Queries and Listings:**
   - Search for trips by travelers.
   - Search for travelers by trips.
   - Complete listing of travelers and trips.

**Methodology:**

The project will be implemented using tree manipulation methods such as insertion, search, deletion, and traversal. Trees will be used to store and organize travel and traveler data, providing efficiency in search operations and relationships between them.

**Repository Structure:**

- **src/**: Contains the project's source code.
- **docs/**: Project documentation.
- **tests/**: Unit and integration tests.
- **README.md**: This file.

**How to Run the Project:**

1. Clone the repository.
2. Install the necessary dependencies.
3. Run the main file to start the program.


# Travel Management System

Este é um sistema de gerenciamento de viagens que permite gerenciar viajantes e viagens, incluindo a adição, remoção e busca de dados. A interface é baseada em menus de texto.

## Estrutura de Diretórios

src/
│
├── main/
│ ├── java/
│ │ ├── data/
│ │ │ ├── ItemTraveler.java
│ │ │ └── ItemTrip.java
│ │ ├── models/
│ │ │ ├── TravelerNode.java
│ │ │ └── TripNode.java
│ │ ├── tools/
│ │ │ ├── AppConstants.java
│ │ │ ├── IDGenerator.java
│ │ │ ├── ScreenTools.java
│ │ ├── trees/
│ │ │ ├── TravelerTree.java
│ │ │ └── TripTree.java
│ │ ├── view/
│ │ │ ├── MainMenu.java
│ │ │ ├── TravelerMenu.java
│ │ │ └── TripMenu.java
│ │ └── Main.java
│ └── resources/



## Manual de Uso

### Introdução

Este projeto é um sistema de gerenciamento de viagens que permite gerenciar viajantes e viagens, incluindo a adição, remoção e busca de dados. A interface é baseada em menus de texto.

### Estrutura do Projeto

- **data**: Contém as classes de dados `ItemTraveler` e `ItemTrip`.
- **models**: Contém as classes `TravelerNode` e `TripNode` que representam os nós das árvores de viajantes e viagens.
- **tools**: Contém utilitários e constantes usadas no aplicativo.
  - `AppConstants`: Contém constantes usadas em todo o aplicativo.
  - `IDGenerator`: Gera IDs únicos para viajantes e viagens.
  - `ScreenTools`: Utilitários para limpar a tela e exibir títulos.
  - `SampleDataLoader`: Carrega dados de amostra para o aplicativo.
- **trees**: Contém as classes `TravelerTree` e `TripTree` que implementam as árvores binárias de viajantes e viagens.
- **view**: Contém os menus do aplicativo.
  - `MainMenu`: Menu principal do aplicativo.
  - `TravelerMenu`: Menu de gerenciamento de viajantes.
  - `TripMenu`: Menu de gerenciamento de viagens.

### Como Usar

1. **Inicialização do Programa**

   Para iniciar o programa, compile e execute a classe `Main` que abrirá o menu principal.

   ```bash
   `javac -d bin src/main/java/**/*.java`
   `java -cp bin main.java.Main`

Menu Principal

1. Manage Travelers: Gerencia os viajantes.
2. Manage Trips: Gerencia as viagens.
3. Load Sample Data: Carrega dados de amostra.
0. Exit: Sai do programa.
Gerenciamento de Viajantes

No menu TravelerMenu você pode:

1. Register Traveler: Registrar um novo viajante.
2. Search Traveler: Buscar viajantes.
List All Travelers: Lista todos os viajantes.
Search Traveler by Name: Busca viajantes por nome usando curingas (%).
Select Traveler: Seleciona um viajante para gerenciar.
3. List All Travelers (Pre-Order): Lista todos os viajantes em pré-ordem.
4. List All Travelers (Post-Order): Lista todos os viajantes em pós-ordem.
5. List Trips of a Traveler: Lista as viagens de um viajante.
0. Back to Main Menu: Retorna ao menu principal.
Gerenciamento de Viagens

No menu TripMenu você pode:

1. Register Trip: Registrar uma nova viagem.
2. Search Trip: Buscar viagens.
List All Trips: Lista todas as viagens.
Select Trip: Seleciona uma viagem para gerenciar.
0. Back to Main Menu: Retorna ao menu principal.
Gerenciamento de Relações

Ao selecionar um viajante ou uma viagem, você pode:

Edit Traveler: Editar os dados do viajante.
Remove Traveler: Remover o viajante.
Add Trip to Traveler: Adicionar uma viagem ao viajante.
Remove Trip from Traveler: Remover uma viagem do viajante.
Edit Trip: Editar os dados da viagem.
Remove Trip: Remover a viagem.
Add Traveler to Trip: Adicionar um viajante à viagem.
Remove Traveler from Trip: Remover um viajante da viagem.
List Travelers of Trip: Listar os viajantes de uma viagem.
Carregar Dados de Amostra

Ao selecionar a opção "Load Sample Data" no menu principal, 15 viagens e 30 viajantes serão gerados aleatoriamente sem duplicatas.

Dicas
Use o wildcard % para buscar viajantes ou viagens que contenham parte do nome ou destino.
Navegue pelos menus usando os números correspondentes às opções disponíveis.
Com este manual, você deve estar apto a gerenciar viajantes e viagens usando este sistema.