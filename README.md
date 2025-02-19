# Contexto

Projeto de LP2 2022/23 (deisi jungle)

# Erro

GameManager:311 (procurar por // palves)

Essa linha está a mais


# Guião

- Começa-se com o issue do cliente. 
  - Ler o jogo2.txt
  - Jogar o pássaro (avançou 5)
  - Jogar o elefante (avançou 2)
  - Jogar o pássaro (avançou 5)
  - Jogar o elefante (avançou 2)
  - Jogar o pássaro (avançou 5)
  - Jogar o elefante (avançou 2)
  - Jogar o pássaro (avançou 5) - iria apanhar a carne. Mas não avançou pois ficou sem energia
  - Jogar o elefante (avançou 2)
  - Jogar o pássaro (avançou 5) - agora já consegue apanhar a carne (ERRO)

# Metodologia (errada) de debug

- Lançamos o visualizador e lemos o ficheiro. Temos que fazer o pássaro chegar à carne mas é demasiado complicado
pois as jogadas são aleatórias. Como é que vamos reproduzir o erro?
- Testes unitários - reproduzimos os steps do cliente num teste.
  - Verificamos que de facto se verifica aquilo que o cliente diz
- Corremos em debug e acabamos por perceber que o pássaro tem 60 de energia, logo tem energia suficiente. Mas como?
- Metemos informação nos testes para ver a evolução da energia do pássaro
- Descobrimos que, após o NO_ENERGY, o pássaro passa para 60 de energia (antes tinha 10)
- Ou seja, o problema está no movimento anterior - fazemos debug nesse movimento
- Usamos um watch sobre a energia do pássaro e descobrimos que o pássaro ganha energia na chamada
ao energyAndPositiondDontMove(...)
- Entrando lá dentro, vemos que aumenta a energia. Podíamos alterar esse método, mas qual o seu objetivo? Quem é que o chama?
  - Percebemos que é chamado quando o movimento é zero (fica no mesmo sítio) ou quando não tem energia nenhuma.
  - Vamos à procura no enunciado e encontramos no fim da página 7 que, ao descansar recupera energia.