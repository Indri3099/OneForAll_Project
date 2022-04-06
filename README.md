# OneForAll_Project
> Designed & Developed by [Enrico Pallotta](https://github.com/Indri3099)

Project work for the "Advanced method for programming" course of my Bachelor's degree in Computer Science (A.Y. 19/20). <br>
## RELAZIONE
Per leggere la relazione relativa al progetto clicca [qui](./doc/RELAZIONE.md) <br>
Per ulteriore documentazione [javadoc](./doc/javadoc/index.html) <br>
## RUN
Per eseguire l'applicazione puoi compilare il sorgente e fare una run della classe **main/Runner.java** oppure lanciare direttamente il file **OneForAll_Project.jar**
> Il progetto è stato sviluppato usando IntelliJ e Netbeans su O.S. Ubuntu 20.04 LTS, non dovrebbero esserci problemi di compatibilità dell'app su windows/mac.<br>
> **Attenzione** : compilando ed eseguendo il progetto su Netbeans non vengono riprodotti i file audio (sul mio pc), su IntelliJ e tramite Jar invece non ho riscontrato alcun problema.

L'esecuzione del Jar potrebbe generare un errore, questo è dovuto al fatto che il codice è stato compilato con una versione più recente della JRE (versione 55.0), su windows ho risolto inserendo una nuova variabile d'ambiente con il path della JDK 11 (con la quale è stato sviluppato il progetto) e spostandola sopra la variabile d'ambiente che indicava il percorso di default per la JRE, così facendo il Jar viene eseguito con la JDK 11 che supporta la versione 55.0 e ne consente la normale esecuzione. (su Ubuntu non ho avuto questi problemi)
