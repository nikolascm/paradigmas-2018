## Gerador de Certificados utilizado na VIII SAINF

<p align="justify"> <b>Descrição:</b> Afim de automatizar o processo de geração de certificados do evento, cuja responsabilidade assumi, descobri (por curiosidade) uma API denominada <a href="https://itextpdf.com/">iText</a>, cuja função é permitir a edição de arquivos .pdf utilizando a linguagem Java. Assim, os nomes dos participantes constam em um .txt, que o programa efetua a leitura e gera os certificados. 😀 </p>

## Executando aplicação a partir do NetBeans IDE

1. Abra o NetBeans IDE

2. Clique em Arquivo -> Abrir Projeto e procure por "CertificadosSAINF 2.0"

3. Selecione Executar > Limpar e Construir Projeto (Shift+F11), depois execute (f6)

## Atualizações

- Implementação de interface gráfica

- Adição da biblioteca <a href="https://pdfbox.apache.org/">PDFBox</a>

- Escolher arquivos de entrada e local para salvá-los

- Opção de "Copiar e colar" nomes ou digitá-los, além de importação de arquivo ".txt"

<b>Observação:</b> Adição de arquivos "Modelo.pdf" e "participantes.txt" para testes
