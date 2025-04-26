Conversor de Moeda

Este é um projeto de um Conversor de Moedas criado em Java 17 utilizando Maven.

 A interface gráfica foi desenvolvida com Swing, e as taxas de câmbio são obtidas em tempo real a partir da API Awesomeapi.

🛠️ Funcionalidades

Interface gráfica com layout simples para facilitar a interação do usuário.

Seleção de moedas de origem e destino através de dropdowns.

Conversão de moedas utilizando dados em tempo real obtidos via API.

Tratamento de erros para entradas inválidas e conexões com a API.

🧩 Arquitetura do Projeto
Camada de Apresentação:

Desenvolvida com Java Swing.

Fornece uma interface intuitiva para o usuário selecionar as moedas e inserir os valores.

Camada de Negócio:

Lógica para consumir a API Awesomeapi.

Realiza cálculos de conversão com base nas taxas obtidas.

Camada de Dados:

Gerenciamento de requisições HTTP e manipulação dos dados recebidos em formato JSON.

🖥️ Interface Gráfica

Dropdowns para selecionar a moeda de origem e destino.

Campo de entrada para inserir o valor a ser convertido.

Botão para iniciar a conversão.

Exibição do resultado da conversão em tempo real.

🌐 Integração com a API

Esta aplicação utiliza a API Awesomeapi para obter as taxas de câmbio mais recentes. 

🛡️ Tratamento de Erros

Validação das Moedas: Garante que as moedas de origem e destino são válidas.

Erros na API: Mensagens de erro amigáveis exibidas para o usuário caso a conexão falhe ou os dados sejam inválidos.
____________________________________________________________________________________________

![Image](https://github.com/user-attachments/assets/17d2609f-1863-4726-b23c-cc4a5814cd51)

