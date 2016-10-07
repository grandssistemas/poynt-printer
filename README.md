plugin cordova PoyntPrinter

Para usa-lo coloque os arquivos e pastas do projeto dentro da pasta plugins/cordova-plugin-poyntprinter/</br>
Assim será gerado uma variável PoyntPrinter.</br>
Para chamar o pagamento use PoyntPrinter.print(idImpressao, imagemImpressao, callbackSucesso, callbackFalha);</br>
O idImpressao deve ser informado um valor para informar qual a origem da solicitação.</br>
A imagemImpressao é imagem da impressão que deve ser mandada.</br>
O callbackSucesso retorna um JSON com as informações do pagamento.</br>
O callbackFalha retorna um JSON com a falha.
