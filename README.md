# SEEMobile
SEEMobile est une application à destination des étudiants de l'ÉTS pour consulter la liste des offres de stage à l'ÉTS et gérer ses postulations. Ce dépôt comporte la version Android de l'application. Elle est présentement en développement.

## Configuration
### Utilisation du VPN ÉTS
L'API permettant le fonctionnement de l'application se trouve sur le réseau interne de l'ÉTS, il est donc nécessaire de se connecter au VPN. Pour cela, suivez la procédure indiquée [ici](http://www.etsmtl.ca/services/sti/etudiants/Reseau-et-Communication/rpv).
### Google Play Services
Si vous ne voulez pas utiliser un téléphone physique pour tester l'application, vous devrez vous procurer une machine virtuelle Android. Pour ce projet, nous utilisons Genymotion qui va nous permettre de bénéficier d'applications externes comme Google Maps ou AnyConnect.

Nous allons devoir modifier notre machine virtuelle pour installer les Google Apps et avoir accès au Play Store :

1. Téléchargez [Genymotion-ARM-Translation_v1.1.zip](https://drive.google.com/file/d/0B0MZv7btHQ1IN01nVWpjbU9SN1U/view?usp=sharing)

2. Téléchargez les Google Apps pour votre version d'Android:

      - [Google Apps for Android 6.0](https://www.google.ca/search?q=benzo-gapps-m-20151011-signed-chroma-r3.zip)
      - [Google Apps for Android 5.1](https://www.google.ca/search?q=gapps-L-4-21-15.zip)
      - [Google Apps for Android 5.0](https://www.google.ca/search?q=gapps-lp-20141109-signed.zip)
      - [Google Apps for Android 4.4.4](https://www.google.ca/search?q=gapps-kk-20140606-signed.zip)
      - [Google Apps for Android 4.3](https://www.google.ca/search?q=gapps-jb-20130813-signed.zip)
      - [Google Apps for Android 4.2](https://www.google.ca/search?q=gapps-jb-20130812-signed.zip)
      - [Google Apps for Android 4.1](https://www.google.ca/search?q=gapps-jb-20121011-signed.zip)

3. Suivez la procédure indiquée [ici](https://gist.github.com/wbroek/9321145)
