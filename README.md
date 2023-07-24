# iTuneSearch

iTune Search is a simple project that represents for search music with iTune Search API.

The project app layers is present by MVVM pattern and use tech stack like Compose and Hilt.

## Demo
<div>
  <img src="misc/demo_1.gif" width="160" height="380" hspace="20">
  <img src="misc/demo_2.gif" width="160" height="380" hspace="20">
  <img src="misc/demo_3.gif" width="160" height="380" hspace="20">
</div>

## Description
* UI 
   * Compose for UI framework
   * Material design

* Tools
    * Kotlin
    * Coroutines for async operations
    * Hilt for dependency injection
    * Jetpack
        * Compose
        * Navigation for navigation between screen
        * ViewModel for stores, exposes and manages UI state
    * Retrofit for networking
    * Coil for image loading
* Architecture
  * Activity with Navigation Component
  * MVVM for presentation pattern
  * Adroid Architecture components like ViewModel and Navigation
  * Android KTX
 
## Presentation package
*  Data - package contain object related to API calling like response model, api and service.
*  Domain - package contain model that use for display and use case for API calling.
*  Presentation - package contain feature of the app and display screen, contain viewModel that contain state of each screen and common composable view. 

## Installation
* Install Android SDK - API level version 31
* Install Gradle JDK - java 11

## Contact
If have any concern feel free to contact me via swsa.piyada@gmail.com
