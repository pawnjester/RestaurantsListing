## Restaurants Listing
This app is a simple application that retrieves a list of restaurants from a json file located in the assets folder in the provider module.

## App Features
- Allow user to favorite a restaurant
- Sort the restaurant Listing by sort values
- Filter a restaurant by name.
- Favorite a restaurant.

## Built With 🛠

- Android Studio 4.1.1+
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Navigation Component
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.

- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.

## Architecture
- This app uses [MVVM (Model View View-Model)](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture, repository pattern and clean architecture.
- In line with the clean architechure, this project is divided into different modules:
    - App (This is the main entry of the application)
    - Cache (This module handles everything concerning the Room database)
    - Core ( This is an abstraction of the essentials of the app)
    - Data (This includes the domain layer. It implements the interface exposed by the domain layer and dispenses data to the app.
    - Domain (This contains the business Logic of the Application)
    - Provider (This provides an abstraction for the data source i.e. Json File)
    - Restaurants (This is main UI layer)

## How to make use of the app
- Clone this repo and run on an android device.
- This app requires a minimum sdk of 24

## How to run test
  - For unit Tests, you can either run a project wide test using `./gradlew test` or go to the `App`, `Cache` and `Restaurants` Modules to run them individually.
  - For Instrumentation/UI test, you can also run `./gradlew connectedAndroidTest` or locate the `RestuarantActivityTest.kt` file in the androidTest directory in the restaurant module

### Created by Okonji Emmanuel
