# 👁️ Focus Monitor - Inteligência Artificial para Produtividade



O **Focus Monitor** é uma ferramenta de monitoramento de atenção em tempo real desenvolvida em Java com OpenCV. O sistema utiliza a webcam para detectar se o usuário está focado na tela. Caso o usuário desvie o olhar (olhando para o celular, fechando os olhos ou saindo da frente da câmera), o sistema dispara automaticamente um vídeo de alerta com áudio para retomar o foco.

## 🚀 Funcionalidades

- **Detecção Facial em Tempo Real:** Utiliza o algoritmo Haar Cascade para identificar a presença do rosto.
- **Feedback Visual:** Desenha um retângulo dinâmico ("linha de foco") ao redor do rosto detectado.
- **Alerta de Distração Automático:** Dispara um vídeo (`.mp4`) via QuickTime Player (macOS) assim que a distração é detectada.
- **Auto-Close:** O vídeo de alerta é fechado automaticamente assim que o usuário volta a olhar para a tela.
- **Sensibilidade Ajustável:** Configuração de milissegundos para disparos mais rápidos ou lentos.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21+
- **Biblioteca de Visão Computacional:** [OpenCV](https://opencv.org/)
- **Gerenciador de Dependências:** Maven
- **Integração com SO:** AppleScript (osascript) para controle nativo do macOS.

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado:
- [JDK 17](https://www.oracle.com/java/technologies/downloads/) ou superior.
- [Maven](https://maven.apache.org/download.cgi).
- Arquivo `haarcascade_frontalface_default.xml` na raiz do projeto.
- Um vídeo chamado `alertateste.mp4` na pasta do projeto.

## 🔧 Configuração e Instalação

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/focus-monitor.git](https://github.com/arielesilvaa/focus-monitora.git)
Configurar o caminho do vídeo:
No arquivo FocusController.java, altere a variável CAMINHO_VIDEO para o caminho absoluto do seu arquivo no Mac:

Java
private final String CAMINHO_VIDEO = "/Users/seu-nome/Caminho/Para/alertateste.mp4";
Instalar dependências:
O projeto utiliza o openpnp para carregar o OpenCV facilmente via Maven:

XML
<dependency>
    <groupId>org.openpnp</groupId>
    <artifactId>opencv</artifactId>
    <version>4.5.1-2</version>
</dependency>
Executar:
Rode a classe Main.java.

🧠 Como funciona a Lógica?
O sistema opera em um loop de captura de frames:

A cada 30ms, a câmera captura uma imagem.

O FaceDetector processa a imagem buscando padrões faciais frontais.

O AttentionService calcula o tempo decorrido desde a última detecção positiva.

Se o tempo sem detecção ultrapassar o threshold (ex: 500ms), o ProcessBuilder envia um comando AppleScript para o sistema operacional abrir o vídeo.

🤝 Contribuições
Contribuições são sempre bem-vindas! Se você tiver alguma ideia para melhorar a detecção (como usar marcos faciais para detectar cansaço), sinta-se à vontade para abrir uma Issue ou enviar um Pull Request.

Desenvolvido com ❤️ por Ariele Silva


---
