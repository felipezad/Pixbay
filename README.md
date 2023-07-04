# Pixbay

Pixbay is a simple image search app that uses the Pixbay API.
It was built using Android Clean Architecture and follows the MVVM pattern. 
The app is modularized and uses Dependency Injection (Hilt), Kotlin Coroutines, Jetpack Navigation, XML Layouts, and Unit Tests.

## Tradeoffs

Due to time constraints, some features were not implemented in this project. 
Espresso tests (UI tests) and Jetpack Compose were not included.
Standard XML layouts were used instead of Jetpack Compose because I have more experience with it and could deliver the project faster. 
The layout is basic but sufficient to demonstrate the project's architecture.

## Description

The project is structured into three modules: app, core, and feature. 
The app module is the main module where the UI is connected. 
The core module includes the database, model, network, storage, and navigation sub-modules. 
The feature module includes the search and detail sub-modules.

The app follows the Android Clean Architecture and MVVM pattern. The core module includes the database, model, network, storage, and navigation sub-modules. The feature module includes the search and detail sub-modules.

## How to run

To run the project, you need to create a Pixbay API key and add it to the `NetworkModule` file. Instead of providing a local.properties file, the project to use the `NetworkModule` for simplicity and left their own API Key there.

## Video

A sample run of the app is available for viewing.

video/device-2023-07-09-233106.mp4