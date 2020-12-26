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