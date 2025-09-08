# Algoritmo Flood Fill - Trabalho Acadêmico

## Informações Institucionais

**Pontifícia Universidade Católica do Paraná**  
**Escola Politécnica - Ciência da Computação**  
**Disciplina:** Resolução de Problemas Estruturados em Computação  

### Equipe de Desenvolvimento

- **Fernando Alonso Piroga da Silva**
- **Jafte Carneiro Fagundes da Silva**  
- **Renato Pestana de Gouveia**

---

## Descrição do Projeto

Este projeto implementa o algoritmo **Flood Fill** (preenchimento por inundação) utilizando duas estruturas de dados lineares: **Pilha (Stack)** e **Fila (Queue)**. O algoritmo é amplamente utilizado em ferramentas de desenho como a função "balde de tinta" do Paint e em jogos como Go e Campo Minado para determinar áreas conectadas.

### Objetivos Principais

1. Implementar o algoritmo Flood Fill usando estruturas próprias de Pilha e Fila
2. Demonstrar as diferenças de comportamento entre as duas estruturas
3. Criar uma animação visual do processo de preenchimento
4. Aplicar conceitos de Programação Orientada a Objetos

---

## Estrutura do Projeto

```
src/
├── Main.java                     # Interface principal com menu
├── FileHandler.java              # Manipulação de imagens e renderização
├── floodfill/
│   ├── FloodFill.java           # Classe abstrata base
│   ├── FloodFillStack.java      # Implementação com Pilha
│   ├── FloodFillQueue.java      # Implementação com Fila
│   └── Point.java               # Representação de coordenadas
└── myutils/
    ├── Stack.java               # Implementação própria de Pilha
    ├── Queue.java               # Implementação própria de Fila
    ├── Node.java                # Nó para estruturas encadeadas
    └── ArrayList.java           # Lista dinâmica auxiliar
```

---

## Funcionalidades Implementadas

### 1. Algoritmo Flood Fill
- **Classe abstrata `FloodFill`**: Define a estrutura base comum
- **Herança**: `FloodFillStack` e `FloodFillQueue` especializam o comportamento
- **Encapsulamento**: Métodos privados para validações e utilitários
- **Polimorfismo**: Diferentes implementações do método `addNeighbors()`

### 2. Estruturas de Dados Próprias
- **Stack**: Implementação com encadeamento (LIFO - Last In, First Out)
- **Queue**: Implementação com encadeamento (FIFO - First In, First Out)
- **Node**: Classe genérica para encadeamento
- Todas seguem os princípios de estruturas lineares

### 3. Interface de Usuário
- **Menu interativo** para escolha da estrutura
- **Execução separada** de Stack e Queue
- **Feedback visual** com tempos de execução
- **Opção de renderização** independente

### 4. Renderização e Animação
- **Graphics2D**: Renderização de alta qualidade
- **Animação interativa**: Controles de play, pause e reset
- **Barra de progresso**: Visualização do andamento
- **Controle de velocidade**: Ajuste da velocidade da animação

---

## Como Executar

### Pré-requisitos
- Java 11 ou superior
- Imagem PNG de entrada (recomendado: cores sólidas ou fundo branco com divisões pretas)

### Estrutura de Diretórios
```
projeto/
├── src/                    # Código fonte
├── original_img/           # Pasta para imagem de entrada
│   └── mario.png          # Imagem de exemplo
└── output/                # Pasta para imagens geradas (criada automaticamente)
```

### Compilação e Execução
```bash
# Compilar o projeto
javac src/*.java src/floodfill/*.java src/myutils/*.java

# Executar o programa principal
java src.Main
```

### Menu de Opções
1. **Executar FloodFill com PILHA (Stack)** - Preenchimento em vermelho
2. **Executar FloodFill com FILA (Queue)** - Preenchimento em verde  
3. **Apenas renderizar animação** - Visualizar imagens já geradas
4. **Sair** - Encerrar programa

---

## Detalhes Técnicos

### Algoritmo Flood Fill

O algoritmo funciona da seguinte forma:

1. **Inicialização**: Define ponto inicial e cor de destino
2. **Validação**: Verifica se o pixel atual tem a cor original
3. **Preenchimento**: Altera a cor do pixel atual
4. **Expansão**: Adiciona vizinhos válidos na estrutura (Stack ou Queue)
5. **Iteração**: Repete até que não haja mais pixels para processar

### Diferenças entre Stack e Queue

| Aspecto | Stack (Pilha) | Queue (Fila) |
|---------|---------------|--------------|
| **Ordem** | LIFO (Last In, First Out) | FIFO (First In, First Out) |
| **Padrão** | Preenchimento em profundidade | Preenchimento em largura |
| **Comportamento** | Explora um caminho completamente antes de voltar | Explora todos os vizinhos no mesmo nível |
| **Visualização** | Padrão mais "orgânico" e irregular | Padrão mais "uniforme" e circular |

### Configurações do Algoritmo

- **Ponto inicial**: Centro da imagem (`width/2, height/2`)
- **Intervalo de salvamento**: 500 pixels (uma imagem salva a cada 500 pixels processados)
- **Vizinhança**: 4-conectada (cima, baixo, esquerda, direita)

---

## Arquivos Gerados

### Durante a Execução
- `floodfill_001.png`, `floodfill_002.png`, ... - Sequência de imagens do progresso
- `resultado_stack.png` - Resultado final da execução com Pilha
- `resultado_queue.png` - Resultado final da execução com Fila

### Animação
- Janela interativa com controles de reprodução
- Barra de progresso visual
- Informações de frame atual e total
- Controle de velocidade ajustável

---

## Princípios de POO Aplicados

### 1. **Encapsulamento**
- Atributos privados e protegidos
- Métodos públicos para interface
- Validações internas encapsuladas

### 2. **Herança**
- `FloodFill` como classe abstrata base
- `FloodFillStack` e `FloodFillQueue` como especializações
- Reutilização de código comum

### 3. **Polimorfismo**
- Método abstrato `executeFloodFill()`
- Implementações específicas em cada subclasse
- Comportamento distinto para cada estrutura

### 4. **Abstração**
- Interface simples para uso do algoritmo
- Detalhes de implementação ocultos
- Separação de responsabilidades

---

## Requisitos Atendidos

### Critérios da Rubrica (10 pontos)

- ✅ **Pilha própria (1,5 pontos)**: Implementação com encadeamento
- ✅ **Fila própria (1,5 pontos)**: Implementação com encadeamento  
- ✅ **Implementação correta (3,0 pontos)**: Estruturas funcionais e eficientes
- ✅ **Orientação a Objetos (2,0 pontos)**: Todos os princípios aplicados
- ✅ **Funcionamento completo (2,0 pontos)**: Execução sem bugs, interface completa

### Especificações Técnicas

- ✅ Uso de `BufferedImage` e `File` do Java
- ✅ Imagens PNG como entrada e saída
- ✅ Animação dos pixels sendo coloridos
- ✅ Lógica obrigatória de armazenamento em Pilha e Fila
- ✅ Estruturas próprias (não usar Collections do Java)
- ✅ Implementação sem recursividade (usando loops)

---

## Observações de Desenvolvimento

### Decisões de Design
- **Classe abstrata**: Permite reutilização de código comum entre Stack e Queue
- **Encadeamento**: Implementação eficiente em memória para as estruturas
- **Graphics2D**: Renderização de alta qualidade para a animação
- **Menu interativo**: Interface amigável para demonstração

### Melhorias Implementadas
- **Cópia de imagem**: Permite executar ambas estruturas na mesma sessão
- **Cores distintas**: Vermelho para Stack, Verde para Queue (facilita comparação)
- **Controles de animação**: Play, pause, reset e controle de velocidade
- **Feedback visual**: Barra de progresso e informações de frame

### Tratamento de Erros
- Validação de coordenadas (bounds checking)
- Verificação de cor (evita processamento desnecessário)
- Tratamento de exceções de I/O
- Verificação de existência de arquivos

---

## Conclusão

Este projeto demonstra com sucesso a implementação do algoritmo Flood Fill utilizando diferentes estruturas de dados, evidenciando como a escolha da estrutura afeta o comportamento do algoritmo. A implementação segue rigorosamente os princípios de Programação Orientada a Objetos e fornece uma interface visual interativa para melhor compreensão do funcionamento interno do algoritmo.

A diferença visual entre os padrões de preenchimento da Pilha e da Fila ilustra claramente os conceitos de estruturas LIFO e FIFO, contribuindo para o aprendizado prático dos conceitos teóricos da disciplina.
