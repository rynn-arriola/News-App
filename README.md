<h1 align="center" id="title">News Application</h1>

<p align="center"><img src="https://socialify.git.ci/rynn-arriola/Mobile-Project/image?description=1&amp;descriptionEditable=News%20App&amp;font=KoHo&amp;forks=1&amp;language=1&amp;name=1&amp;owner=1&amp;pattern=Circuit%20Board&amp;pulls=1&amp;stargazers=1&amp;theme=Dark" alt="project-image"></p>

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Top Headlines: Allows users to view the top headlines news articles.
*   Search News: enables users to search for specific news articles.
*   News by Country: Provides an option to filter news articles based on the selected country.
*   News by Language: Offers the ability to filter news articles based on the selected language.
*   Offline First Implementation: Implements a caching mechanism to store news articles locally. Allows users to access previously viewed news articles even when offline.
*   Paging Implementation: Implements pagination for efficient loading of large sets of news articles.
*   Scheduled News Fetching with WorkManager: Uses WorkManager to schedule a background job for fetching news articles Initiates the fetching of the latest news articles during the scheduled time.
*   Testing Implementation: Utilizes Mockito framework for writing unit tests. Utilizes JUnit Compose for UI testing within the Jetpack Compose framework.

<h2>🛠️ Installation Steps:</h2>

<p>1. Clone the Repository</p>

```
git clone https://github.com/rynn-arriola/News-App.git
cd NewsApp
```

<h2>Project Screenshots:</h2>

<div>
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/7b84dd37-0920-4bfa-980a-96c3a818ad9d">
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/4b209b5b-d20f-461d-a54a-e6a64246e555">
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/1b50a06c-5840-49f2-880f-8aa8ae77be94">
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/7e1dc89e-b00a-4855-a43a-5c1a36cbada9">
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/9f3bbf84-ed3a-4f30-82b2-f52c6322e7c0">
  <img width="170" src="https://github.com/rynn-arriola/News-App/assets/29761759/81d1f54f-6686-4510-92d5-3042554b22c6">
</div>

  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Jetpack Compose: Modern Android UI toolkit for building native UIs.
*   Compose Navigation: Navigation library for Jetpack Compose.
*   MVVM (Model-View-ViewModel) Architecture: Design pattern for separating concerns in the app architecture.
*   Coil: Image loading library for Android.
*   Dagger Hilt: Dependency injection library for Android.
*   Paging Compose: Jetpack Compose library for handling paginated data.
*   Mockito: Mocking framework for writing unit tests in Java and Kotlin.
*   JUnit: Testing framework for writing unit tests in Java and Kotlin.
*   Room Database: Jetpack library for SQLite database interactions.
*   Work Manager: Jetpack library for managing background tasks.
*   Kotlin Flow: Asynchronous programming using Kotlin's Flow API.

<h2>📁 Project Structure</h2>

```
 └───newsapp
                │   📄 MainActivity.kt
                │   📄 NewsApplication.kt
                │
                ├───data
                │   ├───api
                │   │       📄 ApiKeyInterceptor.kt
                │   │       📄 NetworkService.kt
                │   │
                │   ├───local
                │   │   │   📄 DataBaseService.kt
                │   │   │   📄 NewsDataBase.kt
                │   │   │   📄 NewsDataBaseService.kt
                │   │   │
                │   │   ├───dao
                │   │   │       📄 ArticleDao.kt
                │   │   │
                │   │   └───entity
                │   │           📄 Article.kt
                │   │           📄 Source.kt
                │   │
                │   ├───model
                │   │       📄 ApiArticle.kt
                │   │       📄 ApiSource.kt
                │   │       📄 Country.kt
                │   │       📄 Language.kt
                │   │       📄 LanguageNewsResponse.kt
                │   │       📄 LanguageSource.kt
                │   │       📄 TopHeadlinesResponse.kt
                │   │
                │   └───repository
                │           📄 NewsRepo.kt
                │           📄 OfflineArticleRepository.kt
                │           📄 PagingRepository.kt
                │           📄 TopHeadLinesPagingSource.kt
                │
                ├───di
                │   ├───modules
                │   │       📄 ActivityModule.kt
                │   │       📄 ApplicationModule.kt
                │   │       📄 FragmentModule.kt
                │   │
                │   └───qualifiers
                │           📄 Qualifiers.kt
                │
                ├───ui
                │   ├───base
                │   │       📄 BaseViewModel.kt
                │   │       📄 CommonUi.kt
                │   │       📄 ComposeNavigation.kt
                │   │       📄 Screen.kt
                │   │
                │   ├───countries
                │   │       📄 CountriesNewsScreen.kt
                │   │       📄 CountriesNewsViewModel.kt
                │   │       📄 CountriesScreen.kt
                │   │       📄 CountriesViewModel.kt
                │   │
                │   ├───dashboard
                │   │       📄 DashBoardScreen.kt
                │   │
                │   ├───languages
                │   │       📄 LanguageNewsScreen.kt
                │   │       📄 LanguageNewsViewModel.kt
                │   │       📄 LanguageScreen.kt
                │   │       📄 LanguageViewModel.kt
                │   │
                │   ├───newssource
                │   │       📄 DirectSourceScreen.kt
                │   │       📄 NewsSourcesScreen.kt
                │   │       📄 SourceViewModel.kt
                │   │
                │   ├───offline
                │   │       📄 OfflineArticleViewModel.kt
                │   │       📄 OfflineScreen.kt
                │   │
                │   ├───pagination
                │   │       📄 PagingScreen.kt
                │   │       📄 PagingViewModel.kt
                │   │
                │   ├───search
                │   │       📄 SearchScreen.kt
                │   │       📄 SearchViewModel.kt
                │   │
                │   └───topheadline
                │           📄 TopHeadLineScreen.kt
                │           📄 TopHeadLinesViewModel.kt
                │
                ├───util
                │       📄 Constants.kt
                │       📄 DispatcherProvider.kt
                │       📄 Extension.kt
                │       📄 NetworkHelper.kt
                │       📄 UiState.kt
                │
                └───worker
                        📄 NewsWorker.kt
                        📄 WorkStatusObserver.kt
```


<h2>🚀Explore other branches</h2>

Check out additional branches to explore variations of this project that leverage different technologies and implementations. Each branch may showcase unique features and technology stacks. Here are some notable branches:

- **Dagger-XML-Implementation:**
  - Implements dependency injection using Dagger with XML layouts.

- **Hilt-XML-Implementation:**
  - Utilizes Hilt for dependency injection with XML layouts.
