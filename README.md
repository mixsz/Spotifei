# Spotifei – Plataforma de Informações de Áudios Digitais

## Autor e link para o vídeo:
* Danilo (mixsz) - 24.124.069-6
  
  Link do vídeo: https://www.youtube.com/watch?v=YorS6QUNqPE

## Objetivo do Projeto
O projeto **Spotifei** tem como objetivo desenvolver uma plataforma que permite aos usuários visualizar informações detalhadas e interagir com diferentes tipos de músicas, organizando e explorando conteúdos de formas simples e intuitiva.

Inspirado no Spotify, o projeto foca no compartilhamento e organização de informações musicais, sem incluir a reprodução dos áudios.

## Tecnologias Utilizadas
- **Java** com **Swing** (interface gráfica)
- **PostgreSQL** (banco de dados relacional)
- **JDBC** (conexão Java - banco)
- Arquitetura **MVC** (Model-View-Controller)

## Arquitetura e Estrutura do Sistema

O projeto foi organizado seguindo o padrão MVC:

```plaintext
Projeto/src/
├── model/       # Contém classes de entidades do sistema (Exemplo: usuario, pessoa, artista e etc)
├── view/        # Interfaces gráficas feitas com Swing(JFrames)
├── controller/  # Controladores responsáveis por fazer a interaçãoa da interface e criar regras de negócio
├── dao/         # Responsável pela comunicação com o banco de dados
└── projeto/     # Classe principal que inicializa o programa
```

## Funcionalidades do Sistema

O Spotifei oferece diversas funcionalidades, que permitem ao usuário interagir com músicas de forma simples e eficaz, além de possuir um sistema de login e cadastro de usuários. 

Abaixo está uma descrição detalhada das principais funcionalidades do projeto:

### 1. Cadastro de Usuário
Permite que o usuário crie uma conta, fornecendo informações básicas como nome, sobrenome, username, sexo e senha. O sistema verifica os dados e se estiverem corretos, a conta é armazenada no banco de dados com um ID único (o ID é gerado pela própria tabela "usuario" do DB).

Observação: O username é único, caso um usuário tente criar uma conta com um username já utilizado, o sistema informa que o username já está em uso.

### 2. Login de Usuário
O usuário, após criar sua conta, é capaz de acessar o sistema efetuando o login. São necessários o **username** e **senha**, após digitar, o sistema valida os dados digitados, e se estiver correto, o usuário é autenticado e redirecionado para a home, permitindo acesso às funcionalidades privadas. 

**Importante:** Após o login, o sistema trabalha com o ID e username do usuário. Isso ocorre para manipular qualquer informação relacionada ao usuário logado durante o uso da plataforma, exemplo: criar uma playlist ou curtir uma música são vinculados com o usuário através do ID.

### 3. Busca de Músicas
O usuário consegue buscar música(s) utilizando filtros como o nome da música, nome do artista ou gênero da música. Não é necessário preencher todos os campos para procurar uma música (isso é informado ao usuário).
Além disso, o banco de dados salva automaticamente as primeiras 10 músicas por cada busca. Caso seja uma busca com tamanho menor que 10, é mantido as primeiras músicas que já estavam no histórico e são movidas para o final da lista, sempre mantendo 10 músicas. Isso ocorre porque o usuário pode visualizar suas últimas 10 músicas buscadas (explicado posteriormente).

### 4. Listagem de Informações das Músicas Buscadas
A busca redireciona o usuário para uma nova tela e são exibidas todas as músicas encontradas conforme a exigência. Essas músicas são separadas por blocos, onde cada uma contém as informações: artista, gênero, ano de criação e álbum. Além disso cada bloco de música possui 2 botões que permite o usuário curtir ou descurtir a música.

Observação: Se nenhuma música for encontrada, o usuário não é direcionado para nenhuma tela, e é apenas exibido um pop-up informando que nenhuma música foi encontrada.

### 5. Curtir e Descurtir Músicas
O usuário pode expressar suas preferências curtindo ou descurtindo músicas através dos dois botões mencionados anteriormente. Essa interação é salva no banco de dados e pode ser posteriormente visualizada na parte do histórico.

Observação: Se o usuário tentar curtir a mesma música duas vezes, é exibido um pop-up dizendo que a música selecionada já foi curtida anteriormente (isso serve para descurtir também).

### 6. Gerenciamento de Playlists
O sistema permite ao usuário criar, editar e excluir playlists personalizadas.
O usuário pode escolher o que deseja fazer, clicando em um dos 3 botões com as ações específicas, após clicar, ele é redirecionado para uma das telas específicas que serão explicadas a seguir:

* ### 6.1 Criar Playlist 
  O usuário pode criar uma nova playlist digitando um nome, essa playlist é vinculada ao usuário logado (pelo id) e salva no banco de dados. Não é possível o mesmo usuário criar duas ou mais playlists com o mesmo nome.

* ### 6.2 Editar Playlist 
   Nessa tela, o usuário é capaz de ver todas as suas playlists separadas por bloco, além disso, são exibidas internamente em cada playlist, as 7 primeiras músicas (se a playlist tiver mais de 7 músicas, é exibido um "mais..." informando que têm mais músicas), e 3 botões que direciona o usuário para uma nova tela específica, abaixo será explicado a funcionalidade de cada botão:

  Observação: Se o usuário não possuir nenhuma playlist, mesmo assim ele é redirecionado para essa tela, porém é exibido a seguinte mensagem: "Ops! Nenhuma playlist por aqui!" e ele só é capaz de voltar para a tela anterior.
  
    * ### Renomear Playlist    
        O usuário consegue ver todas as músicas adicionadas dentro da playlist selecionada, além disso é exibida a opção para o usuário alterar o nome da playlist (sem perder as músicas vinculadas). Após clicar em "confirmar", é exibida uma caixa de confirmação com 
 as opções "sim" e "não", se sim, o banco de dados faz um UPDATE no nome da playlist, aparece uma nova mensagem de sucesso e o usuário é redirecionado para a tela anterior, se não, não acontece nada.
        
        Observação: Não é possível colocar um nome que já está sendo utilizado por outra playlist do MESMO usuário.

        Observação 2: Junto com o botão de "confirmar", existe o botão "cancelar", que ao clicar, volta na tela anterior e nenhuma ação é realizada.
      
    * ### Adicionar Música  
       O usuário pode buscar música do mesmo jeito do **Item 3 (Busca de músicas)**, a diferença será na listagem de músicas buscadas: será, igualmente, dividido por blocos e mostrado as informações das músicas, porém terá apenas um botão "adicionar", que após clicar, o banco de dados adiciona essa música na playlist + se não for encontrado nenhuma música, será exibido, no mesmo panel que exibe as músicas, uma mensagem "Ops! Nenhuma Música Encontrada! :C".
      
      Observação: Se a música já estiver na playlist, é informado através de um pop-up, e não é feito a adição/atualização no codigo SQL.
      
    * ### Remover Música 
       O usuário é capaz de ver todas as músicas que estão dentro da playlist selecionada, cada uma é dividida por blocos e dentro de cada bloco é mostrado as infos da música + um botão para excluir música representado por um 'X' vermelho. Após clicar, é exibida uma mensagem se o usuário realmente deseja excluir a música, se sim, uma mensagem de sucesso aparece e a música é imediatamente removida da tela de exibição, se não, não acontece nada.
* ### 6.3 Excluir Playlist 
   Para essa tela, são exibidas todas as playlists com as primeiras 7 músicas (do mesmo jeito do item 6.2 - Editar Playlist), porém é exibido apenas um botão de exclusão representado por um 'X' vermelho. Após clicar, é exibida uma mensagem se o usuário realmente deseja excluir essa playlist, se sim, uma mensagem de sucesso aparece e a playlist é imediatamente removida da tela de exibição e no banco de dados, se não, não acontece nada.
  
   Observação: As músicas vinculadas nessa playlist são removidas automaticamente pelo banco de dados, isso acontece porque foi utilizado na table de musicas_da_playlist a cláusula "ON DELETE CASCADE" que remove automaticamente todos os elementos vinculados pelo id playlist (isso é explicado via javadoc nos métodos que possuem essa função).

### 7. Visualização de Histórico
O sistema salva automaticamente as interações do usuário, permitindo que ele visualize seu histórico nessa parte. O histórico é capaz de mostrar 3 informações, sendo elas: 

* **Últimas 10 Músicas Buscadas**  
  Exibe as 10 últimas músicas buscadas, é ordenado pela mais recente até a mais antiga, simbolizado pela numeração de 1 a 10.
  As músicas são separadas por blocos, possuindo internamente as informações vistas anteriormente.

* **Músicas Curtidas**  
   Exibe todas as músicas curtidas pelo usuário, é ordenado por ordem alfabética.
   São separadas por blocos, contém as informações musicas, porém não possuem numeração.

* **Músicas Descurtidas**  
   Exibe todas as músicas descurtidas pelo usuário, é ordenado por ordem alfabética.
   São separadas assim como as músicas curtidas.

### 8. Desconectar
Na home, é possível que o usuário se desconecte e volte para a tela inicial do sistema. Essa ação faz com que o sistema remova os dados salvos do usuário logado.

### 9. Sair
Já na tela inicial, é possível sair, ou seja, encerrar a sessão. É exibido uma caixa de confirmação após clicar, se sim, o programa é encerrado, se não, nada acontece.

## Banco de Dados
O banco de dados foi desenvolvido para armazenar todos os dados do programa, mesmo se for fechado de uma maneira proposital ou com alguma queda de conexão. A estrutura utiliza PostgreSQL e possui 7 tabelas que se relacionam para garantir organização de dados, a seguir será descrito cada tabela:

### 1. usuario
Armazena todos os dados do usuário, incluindo nome, sobrenome, username (único), idade, sexo e senha. Além disso, é criado um id SERIAL que é armazenado diretamente no banco de dados, com nenhum input necessário.

PK: id

### 2. artista
Contém informações de artistas de diferentes gêneros musicais, seus atributos são compostos por nome artístico, nome, sobrenome, idade, sexo, nacionalidade, status e gênero musical. Também é criado um id SERIAL para cada artista.

PK: id

### 3. musica
Registra todas as músicas disponíveis na plataforma, associadas a um artista, tendo atributos como nome da música, gênero, ano de lançamento, álbum e id SERIAL.

PK: id --- FK: artista_id (artista(id))

### 4. playlist
Armazena todas as playlists criadas por usuários. É **importante** saber que essa tabela só armazena o **id da playlist** e o **id do usuário proprietário**, as músicas da playlist é armazenada na próxima tabela.

PK: id --- FK: id_usuario (usuario(id))

### 5. musicas_da_playlist
Tabela de associação que relaciona músicas às playlists, possibilitando playlists com várias músicas. A tabela possui apenas o id da música e o id da playlist.

**Importante**: ao excluir uma playlist, todas as músicas associadas a ela são removidas automaticamente por causa do uso de `ON DELETE CASCADE` na chave `id_playlist`.

PK: id_playlist e id_musica --- FK: id_playlist (referência de playlist(id)) + id_musica (referência de musica(id))

### 6. historico
Registra as últimas 10 músicas buscadas por cada usuário. Possui um id SERIAL como chave primária, que é importante para saber a ordem da música mais recente pesquisada até a mais antiga (o ID sempre é crescente), além disso possui o id da música e o id do usuário. Sempre que é procurado mais músicas pelo usuário, o sistema faz um `UPDATE` nessa tabela e substitui as músicas que já estavam pelas mais recentes.

PK: id --- FK: id_usuario (usuario(id)) + id_musica (musica(id))

### 7. interacao
Tabela que registra a interação do usuário com as músicas. A tabela possui o atributo `status` que tem duas opções de valores: curtida ou descurtida, além disso não possui um id próprio pois não é necessário mostrar em uma determinada ordem no histórico.

PK: usuario_id e musica_id --- FK: usuario_id (usuario(id)) + musica_id (musica(id))

### Relacionamentos Principais
Cada música está associada a um único artista.
Cada playlist pertence a um único usuário.
Uma playlist pode conter N músicas (via `musicas_da_playlist`).
Usuário pode interagir com N músicas, e as interações são armazenadas na tabela `interacao`.

## Como Usar a Plataforma
É recomendado utilizar a IDE **NetBeans** e o gerenciador de banco de dados **pgAdmin** para facilitar o uso e configurações do projeto.

### 1. Clonar o repositório
Faça download do projeto pelo Git ou manualmente.

### 2. Adicionar o .jar ao projeto
No NetBeans, vá em `Projeto -> Libraries -> Add JAR/Folder` e selecione o arquivo `postgresql-42.7.5.jar` localizado em `Projeto/jar/`.

### 3. Restaurar o banco de dados
No pgAdmin, faça restore do arquivo `.sql` que está localizado em `Projeto/banco-de-dados`.

### 4. Configurar a conexão com banco de dados
No arquivo `Projeto/src/DAO/Conexao.java`, atualize os dados do usuário, senha, porta e nome do banco conforme a configuração local.

### 5. Executar o projeto
Após todos os passos, basta compilar e executar normalmente pela IDE.

## Conclusão
O desenvolvimento do Spotifei me proporcionou diversos aprendizados, como a aplicação da estrutura MVC, que deixou o código mais limpo e organizado, e a implementação da conexão entre Java e o banco de dados PostgreSQL que permitiu uma forma mais eficiente, fácil e legível de armazenar os dados da plataforma (diferente dos semestres anteriores com txt).

Outro ponto importante foi o uso do Java Swing para criação de interfaces gráficas (diferente dos semestres anteriores, que era apenas pelo terminal. Essa etapa apresentou muitos desafios, porém um resultado visual muito melhor, aprimorado junto com os ensinamentos da matéria de UX/UI desse semestre, que tornou as interfaces mais amigáveis para o usuário.

Apesar do projeto não ter o resultado igual de uma aplicação profissional, acredito que os conhecimentos e habilidades adquiridas através desse trabalho serão muitos importantes para o desenvolvimento das disciplinas futuras.
