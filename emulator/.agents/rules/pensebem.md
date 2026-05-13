---
trigger: always_on
---

# Documentação Técnica: Simulador Pense Bem (HTML/CSS/JS)

Este documento apresenta a análise detalhada da estrutura, estilização e lógica de programação do **Simulador Pense Bem**, com o objetivo de servir como base para futuras manutenções, refatorações e implementações de novas funcionalidades.

## 1. Visão Geral do Projeto

O projeto foi reestruturado e atualmente consiste em três arquivos distintos, separando adequadamente as responsabilidades das camadas do desenvolvimento web front-end:
- **`index.html`**: Estrutura e semântica dos elementos que compõem a interface do brinquedo (DOM).
- **`style.css`**: Estilização visual, contendo as regras de layout e cores da aplicação.
- **`script.js`**: Lógica de interação, manipulação de estado e controle de áudio e eventos.

---

## 2. Estrutura HTML (DOM)

A estrutura HTML busca replicar visualmente as seções físicas do brinquedo original, dividindo a interface em componentes lógicos.

### Principais Containers:
- `.console`: O wrapper principal que contém todo o simulador, centralizado na tela.
- `.top`: A parte superior do console.
  - `#display`: A tela do dispositivo, onde os números inseridos e as mensagens ("----", "OK", "ERRO") aparecem.
  - `.speaker`: Um elemento puramente visual (feito com gradiente linear repetido) que simula a saída de som.
- `#question`: Uma área de texto logo abaixo do painel superior, utilizada para exibir instruções ("Pressione ENTER para começar") e as perguntas ativas.
- `.keyboard`: O agrupamento de todos os botões do brinquedo, subdividido em seções utilizando o sistema de Grid do CSS.

### Divisão do Teclado (`.keyboard`):
1. **Teclas de Letras (`.row.top-letters`)**: Botões grandes com as opções A, B, C e D.
2. **Funções e Operadores (`.row.functions` e `.row.math`)**:
   - Funções de sistema: ENTER, LIVRO, DESL (Desligar), LIGA.
   - Operadores aritméticos: +, -, X, ÷.
3. **Teclado Numérico/Musical (`.row.numbers`)**: 10 botões numéricos (0 a 9). Cada botão contém dois elementos internos: o número em si e a nota musical associada (PAUSA, DÓ, RÉ, MI, etc.).
4. **Modos de Jogo (`.row.modes`)**: 10 botões amarelos menores na base, referentes aos diferentes modos de funcionamento do Pense Bem original (ADIÇÃO, SUBTRAÇÃO, SIGA-ME, etc.). *Atualmente não possuem lógica implementada.*

---

## 3. Estilização (CSS)

O CSS utiliza uma abordagem moderna com Flexbox e CSS Grid para o layout, focando na responsividade e alinhamento dos elementos simulando o hardware.

### Destaques Visuais:
- **Layout Flex e Grid**: O corpo usa `display: flex` para centralizar o console. O teclado usa intensivamente `display: grid` (`.row`, `.row.top-letters`, `.row.numbers`, etc.) para posicionar os botões de forma simétrica.
- **Estilo do Display**: O visor digital imita um display LED antigo através do fundo preto, cor de texto vermelha (`#ff2b2b`), fonte monoespaçada (`Courier New`) e um sombreamento interno (`box-shadow: inset 0 0 10px rgba(255,0,0,0.6)`).
- **Interação dos Botões**: Os botões `<button>` possuem estilização padrão que simula volume (bordas, background). O estado `:active` é muito importante, reduzindo a escala (`transform: scale(0.95)`) e adicionando sombra interna para dar a sensação tátil de clique.
- **Cores**: Há classes utilitárias para cores de fundo e texto, facilitando a aplicação do padrão visual do brinquedo (ex: `.red`, `.yellow-big`, `.blue`, `.green`, `.gray`).

---

## 4. Lógica de Programação (JavaScript)

O script atual implementa uma versão básica e funcional do loop de perguntas e respostas.

### Estado e Variáveis Globais:
- `currentAnswer` (String): Armazena os números digitados pelo usuário para a resposta atual.
- `started` (Booleano): Controla o estado de energia/jogo. Impede interações se estiver `false`.
- `maxDisplayChars` (Inteiro): Define o limite máximo de caracteres no display.
- `mode` (String): Identifica o modo atual de operação.
- `blinkInterval` e `blinkText`: Variáveis para controle da animação (piscar) do texto no display.
- `questions` (Array de Objetos): Base de dados hardcoded com as perguntas (`q`) e as respostas corretas (`a`). Exemplo: `{ q: "Quanto é 2 + 2?", a: "4" }`.
- `currentQuestion` (Objeto): Armazena a pergunta selecionada na rodada atual.

### Funções Principais:
1. `beep(freq = 600, duration = 100)`: 
   - Utiliza a API nativa do navegador (`AudioContext`) para gerar uma onda sonora do tipo quadrada (`square`).
   - Simula o som característico de bipe eletrônico a cada tecla pressionada.
2. `showRandomQuestion()`: 
   - Sorteia uma questão do array `questions`.
   - Atualiza a interface (`#question`), limpa o `#display` para "----" e zera a variável `currentAnswer`.
3. `animateDisplay(text)`: 
   - Recebe um texto (como "OK" ou "ERRO") e o exibe no display caractere por caractere usando `setInterval`, simulando uma animação de processamento.

### Lógica de Eventos (Event Listeners):
Um único laço `forEach` adiciona o evento de clique a todos os botões da interface. 
A identificação do botão clicado é feita extraindo o texto do botão: `const value = btn.innerText.split("\n")[0];`. Isso é necessário porque os botões numéricos possuem duas linhas (número e nota musical).

**Fluxo de Controle dos Botões**:
- Emite um som `beep()` independente do botão clicado.
- **DESL**: Define `started = false`, limpa a tela e exibe "Desligado". Retorna imediatamente (ignora o resto).
- **LIGA**: Altera a instrução para "Pressione ENTER para começar". Retorna.
- **ENTER**: 
  - Se o jogo não tiver começado, muda `started` para `true` e puxa a primeira pergunta.
  - Se já estiver jogando, valida a resposta digitada comparando `currentAnswer` com `currentQuestion.a`.
  - Dispara `animateDisplay()` mostrando acerto ou erro. Configura um `setTimeout` para puxar a próxima pergunta após 1.5 segundos. Retorna.
- **Botões Numéricos**: Se o jogo está iniciado (`started === true`) e o valor do botão for numérico (`!isNaN(value)`), ele concatena o valor em `currentAnswer` e atualiza o `#display`.

---

## 5. Pontos de Atenção e Oportunidades de Melhoria (Roadmap)

Se o objetivo for ampliar a fidelidade do simulador, as próximas alterações podem considerar:

1. **[CONCLUÍDO] Separação de Responsabilidades (Refatoração)**: O código foi segregado em `index.html`, `style.css` e `script.js` para melhor manutenção.
2. **Estruturas de Dados Avançadas**: Integrar a lógica existente no projeto Java (`PenseBem.java` de conversas anteriores) para consumir livros, modos e sessões de forma dinâmica via JSON, ao invés do array de perguntas fixo.
3. **Implementação de Modos de Jogo**: Os botões amarelos (ADIÇÃO, SIGA-ME, etc.) atualmente não têm ação. É necessário programar a alteração de estado para que cada modo mude o tipo de comportamento e o fluxo de perguntas.
4. **Sons Específicos para Teclas Musicais**: A função `beep()` pode ser alterada para receber as frequências exatas das notas musicais (Dó, Ré, Mi, etc.) quando os botões numéricos forem clicados no modo correspondente (ex: MEMÓRIA DE TONS).
5. **Correção do Bug de Animação**: A função `animateDisplay` ao mostrar "ERROAAAAAAAAA" não prevê o limite físico de caracteres que um display LED tem na vida real (que é fixo em poucos caracteres no Pense Bem), podendo causar distorções visuais dependendo do CSS. E a string de erro parece estar usando "A" de forma residual.
6. **Controle de Estado Mais Robusto**: Conforme mais modos forem criados, será útil usar um objeto de configuração ou padrão *State Machine* (Máquina de Estados) em vez de apenas uma variável booleana `started`.
