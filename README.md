<h1 align="center">Pokemon showcase Android App</h1>

<p align="center">
Le but de ce projet est de démontrer les connaissances en développement Android en Kotlin, en utilisant une clean architecture avec MVVM, le UI étant créer en Jetpack Compose. le projet utilise l'api publique https://pokeapi.co/
</p> 


## Clean Architecture avec MVVM

<p>La clean architecture permet la séparation entre les différentes préoccupations de la codebase
ainsi permettant une résilience face aux changements (maintenance) et une flexibilité améliorée</p>
<p>Le code de l'application est séparé principalement en 3 dossiers data - domain - presentation.</p>
<p>La couche presentation à accès à domain alors que l'inverse n'est pas vrai</p>
<p>La couche data à accès à domain alors que l'inverse n'est pas vrai</p>
<p>La couche presentation n'a aucune dépendance avec la couche data et vice-versa</p>

## Compose et paging 3.0

Jetpack Compose nous permet de créer du UI d'une façon déclarative, il apporte aussi beaucoup d'amélioration de performance grâce notamment à son système de recomposition intelligent et efficace.

la librairie Paging nous permet de mettre en place un systeme de pagination pour charger nos items de l'api, elle gére les requetes et les erreurs qui peuvent survenir au cours de chargement de nouveau data.

## Tests unitaires et UI
- Des tests unitaires sont ajoutés pour tester les uses cases et le paging source ainsi que des tests unitaires du mapping entre les entités et les models
- Des tests UI (instrementalisés) sont ajoutés pour tester les composables.

Jetpack Compose nous permet de créer du UI d'une façon déclarative, il apporte aussi beaucoup d'amélioration de performance grâce notamment à son système de recomposition intelligent et efficace.

la librairie Paging nous permet de mettre en place un systeme de pagination pour charger nos items de l'api, elle gére les requetes et les erreurs qui peuvent survenir au cours de chargement de nouveau data.

## Theming

Dark mode et light mode selon le thème du device.
Design system est utilisé, et qui a été généré via [Material theme builder][13]

## Outils d'analyse de code

[Detekt][14] est utilisé pour analyser statiquement le code.
Le plugin Detekt de [twitter][15] est utilisé en plus des règles par défaut pour renforcer l'analyse des composables

## Librairies

* [Compose][0] Toolkit pour créer déclarativement des interfaces graphiques natives.
* [Compose Navigation][1] Pour la navigation dans Jetpack Compose.
* [Material3][2] Material 3 offre du theming amélioré et de nouveaux composants.
* [Coroutines][3] Permet une programmation asynchrone et non bloqué.
* [Retrofit][4] Client REST et type safe pour effectuer des requêtes avec un web service.
* [OkHttp3][5] Client http permettant de faire des requêtes http.
* [Dagger Hilt][6] Librairie facilitant l'injection de dépendance.
* [Landscapist-Coil][7] Image downloading and caching library supported by Jetpack Compose.
* [Truth][8] Librairie pour écrire plus clairement les assertions de tests.


[0]:  https://developer.android.com/jetpack

[1]:  https://developer.android.com/jetpack/compose/navigation

[2]:  https://developer.android.com/jetpack/androidx/releases/compose-material3

[3]:  https://github.com/Kotlin/kotlinx.coroutines

[4]:  https://github.com/square/retrofit

[4]:  https://square.github.io/okhttp/

[6]:  https://dagger.dev/hilt/

[7]: https://github.com/skydoves/landscapist

[13]: https://m3.material.io/theme-builder

[14]: https://detekt.dev/

[15]: https://twitter.github.io/compose-rules/detekt/
