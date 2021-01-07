# MyLibertyTest
Un piccolo test con il liberty server

Estratto da: https://openliberty.io/guides/rest-intro.html

## installare librerie aggiuntive

fare partire l'installer `install.sh`

```bash
./install.sh
```

Per windows invece, potreste scaricare a mano il Jar IOEasier e quindi installare con maven il jar:

```bash
mvn install:install-file -Dfile=IoEasier.jar  -DgroupId=psykeco -DartifactId=ioeasier -Dversion="$IoEasier_VERSION" -Dpackaging=jar -DgeneratePom=true -X
```

## config del server

il file di config del server previsto dal flusso si deve trovare nella cartella `liberty/config` e si deve chiamare `myapp_settings` ( può essere cambiato nelle varie classi del server)

Attualmente son stati previsti i seguenti campi:
```properties
# Connessioni db
DB_NAME=<NOME DEL DB>
DB_USER=<NOME UTENTE>
DB_PASSWORD=<PASSWORD>

# LOGGING
DEBUG_LOG=<PERCORSO DEL FILE DI LOG>
DEBUG_GLOBAL=<1 SE VUOI ATTIVARE IL DEBUG, 0 ALTRIMENTI>
```


## dev mode

`mvn liberty:dev`

## Collegarsi:

aprire una chrome windows con il comando da terminale (da **linux**, *per windows invece* personalizzare il percorso dell'eseguibile e il percorso della cartella indicando una qualunque cartella temporanea):  
`google-chrome-stable --disable-web-security --user-data-dir="/tmp"`  

Collegarsi a:  
`http://localhost:9080/MyApp`  

Premere un pulsante per testare una chiamata rest qualunque


## TODO

- implementare query remove e select
- implementare metodo per far tornare messaggi dalle query (messaggi di errore)
- implementare metodo per fare tornare numero di sequenza a insert
- implementare una Query Factory
- implementare Sessione Utente
- implementare crittografia su file setting (per campo password)
- .... prossimamente