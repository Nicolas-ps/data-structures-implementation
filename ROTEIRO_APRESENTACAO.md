# Roteiro de Apresentação — Grupo 1
## Sistema de Gerenciamento de Comentários em Fórum
### Estruturas de Dados | IFBA BSI 2026.1
### Duração estimada: 17–20 minutos

---

## ESTRUTURA GERAL

| Parte | Conteúdo | Tempo |
|---|---|---|
| 1 | Introdução e contexto | 2 min |
| 2 | Arquitetura e decisões de design | 3 min |
| 3 | Código — Modelo e nó da árvore | 3 min |
| 4 | Código — Lógica da árvore (ForumTree) | 4 min |
| 5 | Demonstração ao vivo (10 operações) | 6 min |
| 6 | Conclusão | 1 min |

---

## PARTE 1 — INTRODUÇÃO E CONTEXTO
**⏱ ~2 minutos | Tela: slides ou IDE fechada**

> "Bom dia/boa tarde. Somos o Grupo 1 da disciplina de Estruturas de Dados do
> IFBA, semestre 2026.1. Nosso trabalho consiste na implementação de um Sistema
> de Gerenciamento de Comentários em Fórum, utilizando a estrutura de dados
> Árvore."

**Apresentar o contexto do problema:**
> "Em fóruns e redes sociais, os comentários se organizam de forma hierárquica —
> um comentário pode ter várias respostas, e cada resposta pode ter suas
> próprias respostas. Isso forma naturalmente uma árvore."

**Mostrar o exemplo do enunciado:**
```
Comentário Principal
├── Resposta 1
│   ├── Resposta 1.1
│   └── Resposta 1.2
├── Resposta 2
│   └── Resposta 2.1
└── Resposta 3
```

> "Nosso sistema implementa exatamente essa estrutura, com 10 operações completas
> acessíveis por uma interface de linha de comando. Vamos detalhar a
> implementação agora."

---

## PARTE 2 — ARQUITETURA E DECISÕES DE DESIGN
**⏱ ~3 minutos | Tela: IDE com estrutura de pacotes aberta**

**Mostrar a estrutura de pacotes no IDE:**
```
src/nicolasps/com/github/
├── contracts/   → interfaces (Listable)
├── exceptions/  → exceções customizadas
├── lists/       → implementação de lista encadeada
├── trees/       → nós de árvore (BinaryNode, BST...)
└── forum/       → nosso sistema (4 arquivos)
```

> "O projeto segue uma organização por domínio. Nosso código está no pacote
> `forum`, composto por quatro classes com responsabilidades bem definidas."

**Explicar a separação de camadas:**
> "Temos a classe `Comment`, que é o modelo de dados — ela representa um
> comentário com autor, data e texto. `CommentNode` é o nó da árvore, que
> encapsula um `Comment` e mantém referências para o pai e para os filhos.
> `ForumTree` contém toda a lógica das 10 operações. E `ForumMain` é a
> interface com o usuário."

**Explicar a escolha do tipo de árvore:**
> "Usamos uma árvore genérica — também chamada de árvore n-ária — porque cada
> comentário pode ter um número ilimitado de respostas. Diferente de uma árvore
> binária, que limita a dois filhos, aqui cada nó mantém uma lista de filhos,
> implementada com `ArrayList` do Java Collections Framework, conforme exigido
> pelo enunciado."

---

## PARTE 3 — CÓDIGO: COMMENT E COMMENTNODE
**⏱ ~3 minutos | Tela: IDE aberta em Comment.java e CommentNode.java**

### Abrir: `forum/Comment.java`

> "A classe `Comment` representa o dado armazenado em cada nó da árvore."

**Destacar no código:**
- `private static int idCounter` → gera IDs únicos e sequenciais automaticamente
- `private final int id` e `private final LocalDateTime date` → imutáveis após criação (uso de `final`)
- `private String author` e `private String text` → mutáveis (suportam edição)
- `toShortString()` → versão compacta usada na visualização da árvore
- `toString()` com `@Override` → versão completa com data formatada

> "O ID é gerado automaticamente por um contador estático, o que garante que
> cada comentário tenha um identificador único durante a execução do sistema."

### Abrir: `forum/CommentNode.java`

> "O `CommentNode` é o nó da nossa árvore. Ele encapsula um `Comment` e
> mantém as conexões estruturais."

**Destacar no código:**
- `private final List<CommentNode> children` — lista de filhos usando `java.util.ArrayList`
- `private CommentNode parent` — referência ao nó pai (permite subir na hierarquia)
- `addChild()` — ao adicionar um filho, automaticamente define o `parent` dele
- `isLeaf()` — retorna `true` quando `children` está vazio (folha da árvore)

> "A referência ao pai é fundamental para duas operações: exibir o caminho
> completo até um comentário e remover um nó da lista de filhos do seu pai."

---

## PARTE 4 — CÓDIGO: LOGICA DA ÁRVORE (FORUMTREE)
**⏱ ~4 minutos | Tela: IDE aberta em ForumTree.java**

> "A classe `ForumTree` é o coração do sistema. Ela implementa as 10
> operações exigidas pelo trabalho."

**Mostrar o campo `roots`:**
```java
private final List<CommentNode> roots;
```
> "A árvore é multiraiz — permite vários tópicos independentes em paralelo,
> como acontece num fórum real."

**Percorrer rapidamente cada operação no código:**

**Op 1 — insertComment:** simples, adiciona à lista de raízes.

**Op 2 — replyTo:** usa `findById` para localizar o pai e chama `addChild`.

**Op 3 — editComment:** localiza o nó e chama `setText` no `Comment`.

**Op 4 — removeComment:**
> "A remoção é em cascata — ao remover um nó, toda a subárvore abaixo dele
> é removida automaticamente, porque simplesmente desconectamos o nó do seu pai."

**Ops 5 e 10 — displayDiscussion / displayTree:**
> "Ambas usam recursão com uma técnica de prefixo para construir os
> caracteres `├──` e `└──`. A diferença é que a op 5 mostra o comentário
> completo com data, e a op 10 mostra uma prévia compacta."

**Mostrar o método `appendDetailed` ou `appendCompact`:**
```java
String connector = isLast ? "└── " : "├── ";
String childPrefix = prefix + (isLast ? "    " : "│   ");
```
> "Esse algoritmo decide qual conector usar com base em se o nó é o último
> filho ou não, e propaga o prefixo correto para os filhos."

**Op 6 — searchByAuthor:** busca recursiva insensível a maiúsculas/minúsculas.

**Op 7 — getPath:**
> "Sobe pela referência `parent` até a raiz, construindo o caminho de trás
> para frente com `path.add(0, ...)`, resultando em algo como:
> `[#1] João: Olá... > [#2] Maria: Que legal...`"

**Op 8 — getLeaves:** recursão que coleta apenas nós onde `isLeaf()` é `true`.

**Op 9 — countComments:** soma recursiva simples — `1 + soma dos filhos`.

---

## PARTE 5 — DEMONSTRAÇÃO AO VIVO (10 OPERAÇÕES)
**⏱ ~6 minutos | Tela: terminal com o sistema rodando**

**Comando para iniciar:**
```bash
java -cp out/production/data-structures-implementation nicolasps.com.github.forum.ForumMain
```

---

### DEMO — SEQUÊNCIA DE COMANDOS

#### Operação 1 — Inserir comentário principal
```
Comando: 1
Autor: Lucas
Texto: Alguém pode me ajudar com recursão em Java?
```
```
Comando: 1
Autor: Fernanda
Texto: Estou com dúvida sobre ArrayList vs LinkedList
```
> "Inserimos dois tópicos independentes — duas raízes da nossa árvore."

---

#### Operação 2 — Responder a um comentário
```
Comando: 2
ID do comentário para responder: 1
Autor: Pedro
Texto: Claro! Recursão é quando um método chama a si mesmo.
```
```
Comando: 2
ID do comentário para responder: 1
Autor: Ana
Texto: Recomendo começar com fatorial e Fibonacci como exemplos!
```
```
Comando: 2
ID do comentário para responder: 3
Autor: Lucas
Texto: Obrigado Pedro! Mas como definimos o caso base?
```
> "Agora temos um comentário respondido com dois níveis de profundidade —
> Lucas respondeu a Pedro, que já era resposta de Lucas."

---

#### Operação 10 — Exibir árvore (compacta)
```
Comando: 10
```
> "Veja a estrutura hierárquica com os conectores `├──` e `└──`, exatamente
> como no exemplo do enunciado. A identação mostra a profundidade de cada
> nó."

---

#### Operação 5 — Exibir discussão completa
```
Comando: 5
```
> "A versão detalhada exibe autor, data e hora de cada comentário além do texto."

---

#### Operação 3 — Editar comentário
```
Comando: 3
ID do comentário a editar: 3
Novo texto: Claro! Recursão é quando um método chama a si mesmo. Precisa sempre de um caso base!
```
```
Comando: 10
```
> "Confirmamos que o texto foi atualizado na estrutura."

---

#### Operação 6 — Buscar por autor
```
Comando: 6
Nome do autor (ou parte): lucas
```
> "A busca é insensível a maiúsculas e aceita parte do nome. Retornou todos
> os comentários do Lucas."

---

#### Operação 7 — Caminho completo
```
Comando: 7
ID do comentário: 5
```
> "O sistema percorre a árvore de baixo para cima usando a referência `parent`
> e exibe o caminho completo desde a raiz até o comentário #5."

---

#### Operação 8 — Folhas (sem respostas)
```
Comando: 8
```
> "São listados apenas os comentários que não possuem respostas — as folhas
> da árvore."

---

#### Operação 9 — Contar total
```
Comando: 9
```
> "A contagem é feita recursivamente: cada nó soma 1 mais a contagem de
> todos os seus filhos."

---

#### Operação 4 — Remover comentário
```
Comando: 4
ID do comentário a remover: 1
Confirmar (s/n): s
```
```
Comando: 9
```
> "Removemos o comentário #1 — e junto com ele, todas as respostas
> aninhadas foram removidas automaticamente. O total diminuiu de 5 para 1."

---

#### Encerrar
```
Comando: 0
```

---

## PARTE 6 — CONCLUSÃO
**⏱ ~1 minuto | Tela: IDE ou terminal**

> "Com isso, demonstramos as 10 operações exigidas pelo trabalho, todas
> implementadas com árvore n-ária, Java Collections Framework e interface
> por linha de comando."

> "A estrutura de árvore se mostrou ideal para representar discussões
> hierárquicas: a inserção é eficiente, a remoção em cascata é natural pela
> estrutura do grafo, e a exibição recursiva gera a visualização com
> conectores automaticamente."

> "Agradecemos a atenção. Professor [nome], ficamos à disposição para
> dúvidas."

---

## DICAS PARA A GRAVAÇÃO

- **Fonte do terminal:** aumentar para pelo menos 16pt antes de gravar
- **Resolução:** gravar em 1920×1080 para garantir legibilidade no YouTube
- **Não esquecer:** deixar o vídeo como **não listado** ou **público** (nunca privado)
- **Pratiquem a demo antes:** executar a sequência de comandos acima uma vez antes de gravar para não travar
- **Dividam as partes:** cada membro fala as partes que implementou — garante que todos apareçam no vídeo conforme exigido
- **Tempo de fala:** com 2 membros, ~8-10 min cada; com 3, ~6 min cada; com 4, ~4-5 min cada
