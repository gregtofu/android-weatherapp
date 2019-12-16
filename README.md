# MétESIEA

Projet Android de **Théodore BLANC-TALON**

### Description
Cette application me permet d'obtenir les conditions météo à l'ESIEA (site d'Ivry) en temps réel.

Je m'en sers déjà pour savoir à quel point je vais être détrempé en arrivant en cours (à vélo) le matin.

### Prérequis
 - Tutoriel Android de Google
 - Utilisation d'Android Studio
 - Utilisation de Picasso
 - Utilisation de Retrofit
 - Notions sur les fragments

### Consignes respectées:

 - Appels vers API REST (OpenWeatherMap)
 - Fragments (accessibles via une navbar)
 - Images récupérées dynamiquement depuis un site web via Picasso
 - Design mélangé entre Material Design et design personnel

### Fonctionnalités:
Le fragment "Home" de mon application donne la météo sur Ivry en temps réel.
Pour ce faire, j'utilise Retrofit pour effectuer un appel vers l'API OpenWeatherMap, en précisant l'ID unique d'Ivry-sur-Seine dans l'URL.
Ensuite, je mets l'image correspondant au temps actuel en cache avec Picasse, et je l'injecte dans mon ImageView.

![Fragment principal](https://cdn.discordapp.com/attachments/486235276167872533/656201421116538943/Screenshot_20191216-192925_MtESIEA.jpg)

Le fragment "APP INFO" de mon application est un fragment contenant des données statiques, déjà présentes sur mon téléphone.

![Fragment contenant les informations sur l'application](https://cdn.discordapp.com/attachments/486235276167872533/656201463575478284/Screenshot_20191216-192932_MtESIEA.jpg)

