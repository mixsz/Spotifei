# Spotifei – Plataforma de Informações de Áudios Digitais

## Objetivo do Projeto
O projeto **Spotifei** tem como objetivo desenvolver uma plataforma que permite aos usuários visualizar informações detalhadas e interagir com todos os tipos de música, organizando e explorando conteúdos de formas simples e intuitiva.
Inspirado no Spotify, o projeto foca no compartilhamento e organização de informações musicais, sem incluir a reprodução dos áudios.

## Tecnologias Utilizadas
- **Java** com **Swing** (interface gráfica)
- **PostgreSQL** (banco de dados relacional)
- **JDBC** (conexão Java ↔ banco)
- Arquitetura **MVC** (Model-View-Controller)

## 📐 Arquitetura e Estrutura do Sistema

O projeto foi organizado seguindo o padrão MVC:

```plaintext
Projeto/src/
├── model/       # Contém classes de entidades do sistema (Exemplo: usuario, pessoa, artista e etc)
├── view/        # Interfaces gráficas feita pelo Swing(JFrames)
├── controller/  # Controladores responsáveis por fazer a ação da interface e criar regras de negócio
├── dao/         # Responsável pela comunicaçao com o banco de dados
└── projeto/     # Classe principal que inicializa o programa
```

## Funcionalidades do Sistema

O Spotifei oferece diversas funcionalidades, que permitem o usuário interagir com músicas de forma simples e eficaz, além de possuir um sistema de contas. 
Abaixo está uma descrição detalhada das principais funcionalidades do projeto:

### 1. Cadastro de Usuário
Permite que o usuário crie uma conta, fornecendo informações básicas como nome, sobrenome, username, sexo e senha. O sistema verifica os dados, e se estiverem corretos, a conta é armazenada no banco de dados com um ID único (o ID é gerado pela própria tabela DB).
Obs: O username é único, caso um usuário tente criar uma conta com um username já utilizado, o sistema informa que o username já está em uso.

### 2. Login de Usuário
O usuário, após criar sua conta, é capaz de acessar o sistema efetuando o login. É necessário o **username** e **senha**, após digitar, o sistema valida os dados digitados, e se estiver correto, o usuário é autenticado e direcionado para a home, permitindo acesso às funcionalidades privadas. 
**Importante:** Após o login, o sistema trabalha com o ID e username do usuário. Isso ocorre para manipular qualquer informação do usuário logado durante o uso da plataforma; Exemplo: criar uma playlist ou curtir uma música são vinculados com o usuário através do ID.

### 3. Busca de Músicas
O usuário consegue buscar música(s) utilizando filtros como o nome da música, nome do artista ou gênero da música. Não é necessário preencher todos os campos para procurar uma música (isso é informado ao usuário).
Além disso, o banco de dados salva as primeiras 10 músicas por cada busca, caso seja uma busca com tamanho menor que 10, é mantido as primeiras músicas que já estavam no histórico e são jogadas para o final da lista, sempre mantendo 10 músicas. Isso ocorre porque o usuário pode visualizar suas últimas 10 músicas buscadas.

### 4. Listagem de informações das músicas buscadas
A busca direciona o usuário para uma nova tela e é exibido todas as músicas encontradas conforme a exigência. Essas músicas são separadas por bloco, onde cada uma contém as informações: artista, gênero, ano de criação e álbum. Além disso cada bloco de música possui 2 botões que permite o usuário curtir ou descurtir a música.
Obs: Se nenhuma música for encontrada, o usuário não é direcionado para nenhuma tela, e é apenas exibido um pop-up informando que nenhuma música foi encontrada.

### 5. Curtir e Descurtir Músicas
O usuário pode expressar suas preferências curtindo ou descurtindo músicas através dos dois botões mencionados anteriormente. Essa interação é salva no banco de dados e pode ser posteriormente visualizada na parte do histórico.
Obs: Se o usuário tentar curtir a mesma música duas vezes, é mostrado um pop-up dizendo que a música selecionada já foi curtida (isso serve para descurtir também).

### 6. Gerenciamento de Playlists
O sistema permite ao usuário criar, editar e excluir playlists personalizadas.
O usuário pode escolher o que deseja fazer clicando em um dos 3 botões com as ações específicas, após o click ele é redirecionado para uma das telas específicas que serão explicadas a seguir:

* **Criar Playlist**  
  O usuário pode criar uma nova playlist digitando um nome, essa playlist é vinculada ao usuário logado (pelo id) e salva no banco de dados. Não é possível o mesmo usuário criar duas ou mais playlists com o mesmo nome.

* **Editar Playlist**  
   Nessa tela, o usuário é capaz de ver todas as suas playlists separadas por bloco, além disso, é exibido internamente por playlist, as 7 primeiras músicas (se a playlist tiver mais de 7 músicas, é exibido um "mais..." informando que têm mais músicas), e 3 botões que direciona o usuário para uma nova tela específica, abaixo será explicado a funcionalidade de cada botão:
  Obs: Se o usuário não possuir nenhuma playlist, mesmo assim ele é redirecionado para essa tela, porém é exibido a seguinte mensagem: "Ops! Nenhuma playlist por aqui!" e ele só é capaz de voltar para a tela anterior.
    * **Renomear Playlist**
      O usuário consegue ver todas as músicas adicionadas dentro da playlist selecionada.
      Permite alterar o nome da playlist sem perder as músicas vinculadas.
      Após o click, é exibido uma confirmação com "sim" e "não", se sim, aparece uma nova mensagem de sucesso e o usuário é redirecionado para a tela anterior, se não, não acontece nada.
      Obs: Não é possível colocar um nome que já está sendo utilizado por outra playlist do MESMO usuário.
    * **Adicionar Música**  
       O usuário pode buscar música do mesmo jeito do **Item 3 (Busca de músicas)**, a única diferença será na listagem de músicas buscadas: Será, igualmente, divido por blocos e mostrado as informações das músicas, porém terá apenas um botão "adicionar", que após o click, o banco de dados adiciona essa música na playlist.
      Obs: Se a música já estiver na playlist, é informado através de um pop-up, e não é feito a adição/atualização no codigo SQL.
    * **Remover Música**  
       O usuário é capaz de ver todas as músicas que estão dentro da playlist selecionada, cada uma é dividida por blocos e dentro de cada bloco é mostrado as infos da música + um botão para excluir música representado por um 'X' vermelho. Após o click, é exibido uma mensagem se o usuário realmente deseja excluir a música, se sim, aparece uma mensagem de sucesso e a música é imediatamente removida da tela de exibição, se não, não acontece nada.
* **Excluir Playlist**  
   Para essa tela, é exibido todas as playlists com as primeiras 7 músicas (do mesmo jeito do item 6 - Editar Playlist), porém é exibido apenas um botão de exclusão representado por um 'X' vermelho. Após clicar, é exibido uma mensagem se o usuário realmente deseja excluir essa playlist, se sim, aparece uma mensagem de sucesso e a playlist é imediatamente removida da tela de exibição, se não, não acontece nada.
   Obs: As músicas vinculadas nessa playlist são removidas automaticamente pelo banco de dados, isso acontece porque foi utilizado na table de musicas_da_playlist "ON DELETE CASCADE" que remove automaticamente todos os elementos vinculados pelo id playlist (isso é explicado via javadoc nos métodos que possuem essa função).

### 7. Visualização de Histórico
O sistema salva automaticamente as interações do usuário, permitindo que ele visualize seu histórico nessa parte. O histórico é capaz de mostrar 3 informações, sendo elas: 

* **Últimas 10 Músicas Buscadas**  
  Exibe as 10 últimas músicas buscadas, é ordenado pela mais recente até a mais antiga, simbolizado pela numeração de 1 a 10.
  As músicas são separadas por blocos, possuindo internamente as informações.

* **Músicas Curtidas**  
   Exibe todas as músicas curtidas pelo usuário, é ordenado por ordem alfabética.
   São separadas por blocos, porém não possuem numeração.

* **Músicas Descurtidas**  
   Exibe todas as músicas curtidas pelo usuário, é ordenado por ordem alfabética.
   São separadas por blocos assim como as músicas curtidas.

### 8. Desconectar
Na home, é possível que usuário se desconecte e volte para a tela inicial do sistema. Essa ação faz com que o sistema remova os dados salvos do usuário logado.

### 9. Sair
Já na tela inicial, é possível sair, ou seja, encerrar a sessao. É exibido uma confirmação após o click, se sim, o programa é encerrado, se não, nada acontece.
