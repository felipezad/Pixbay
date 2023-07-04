# Pixbay
Pixbay Api

General project structure

The project must be able to compile and run on Android 7.0 and higher
The app should follow the official Material Design guidelines
The main language must be Kotlin
The code must be:
Organized.
Efficient.
Readable.
Decoupled.
Follow best practices (e.g. DRY, SOLID)
Nice to have:
Configuration changes handling.
Unit and Instrumented tests.
CLEAN Architecture.
This is our tech stack (you're not required to use the same):
MVVM Architecture
Android Architecture Components
Jetpack Compose / Legacy View System with Databinding and ConstraintLayout
RxJava / Coroutines
Hilt
Coil
Retrofit / Ktor
Room
KMM (Multiplatform)
Prerequisites
You will need an API key for the Pixabay public web services. It can be retrieved from this page (you must be logged in to see it):
https://pixabay.com/api/docs/#api_search_images


Requirements
The user should be able to search for images, entering one or more words in a text field
Request the Pixabay API to show the images associated with the text provided by the user and parse the JSON response.
Display a list of results. Each entry should show:
A thumbnail of the image.
The Pixabay username.
A list of the image’s tags.
Cache the result for offline handling.
A click on the list item opens a dialog asking the user if he wants to see more details. In case of a positive answer, a new detail screen should be
opened.
The detail screen should contain:
A bigger version of the image.
The name of the user.
A list of the image’s tags.
The number of likes.
The number of downloads.
The number of comments.
When the app starts, it should trigger a search for the string “fruits”.
If you have any final comments about your result, please let us know, by creating a file called "README.md" inside the root directory of the proj
