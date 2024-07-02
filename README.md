Investing Buddy App

Investing Buddy is an Android application designed to assist users in managing their investments in stocks and cryptocurrencies efficiently. It provides real-time crypto prices, investment news, note-taking capabilities, investment portfolio visualization, fear and greed index tracking, and various financial calculators.

Features
Real-time Crypto Prices: Fetch current prices of various cryptocurrencies.

Investment News: Stay updated with the latest news related to stocks and cryptocurrencies.

Note Taking: Create and manage notes related to your investment strategies using Firebase Realtime Database.

Portfolio Visualization: Generate pie charts to visualize your investment allocation stored locally using SQLite.

Fear and Greed Index: Monitor market sentiment through the fear and greed index.

Financial Calculators:

Lumpsum Calculator: Calculate returns for lump sum investments.
SIP (Systematic Investment Plan) Calculator: Estimate returns for regular investments over time.



Technologies Used

Programming Languages: Kotlin
Database:
SQLite (for local storage of pie chart data)

Firebase Realtime Database (for note-taking and authentication)

Cloud Services: Firebase (for real-time updates and authentication)

Networking: Retrofit and OkHttp (for API communication)

XML: Layouts and UI design




API Integration
CoinMarketCap API
For fetching real-time crypto prices and market data, the app integrates with the CoinMarketCap API. Developers can obtain their API key from CoinMarketCap and configure it in the app's networking code.

News API by newsapi.org
To provide users with the latest news related to cryptocurrencies, the app fetches data from the News API by newsapi.org. Developers need to obtain an API key from newsapi.org and integrate it into the app using Retrofit and OkHttp libraries.




Version Control: GitHub (for source code management)
Installation
To use Investing Buddy, follow these steps:

Clone the repository from GitHub:

bash
Copy code
git clone https://github.com/kanavnayyer/investing-buddy.git
Open the project in Android Studio.

Set up Firebase:

Create a Firebase project at Firebase Console.
Add your Android app to the Firebase project (package name com.example.investingbuddy).
Download google-services.json and place it in the app/ directory of your project.

Obtain your API keys from CoinMarketCap and newsapi.org and configure them in your app's networking code.



paste here in util package


class AllApi {
    companion object{
         val apikCrypto=" " // crypto

        lateinit var auth: FirebaseAuth
        val clientid:String=" "  // firebase client id
        const val  Api_key=" "   // new apiKey
        const val  base_url="https://newsapi.org/"

    }
}


Build and run the app on an Android device or emulator.











ScreenShots

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/b0401480-69a2-4d22-9c5f-ba830139d19c" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/6afc47a8-4f06-4eec-87c0-bb7cddc04de8" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/6549bc80-e807-4d53-a139-c91e53f2a012" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/e6c2e04a-583c-4a8b-a8dc-204c3411c8e3" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/339a3feb-38c6-471f-9e3a-67309e4bdfcb" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/ce2183e6-3a1d-405c-80e6-3d5731d4944e" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/b260aa6f-dda5-43c8-af7d-1bdb5e891cab" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/5cfde229-9173-4784-ad15-c9de50f9fbee" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/5566ea66-4cc9-4815-9148-dc15db13320a" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/fd753f3b-7ad9-4649-aacd-7d2b5e0fabdc" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/c09634dd-2b3a-4bf7-9410-9d789f533480" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/926cb6c1-9985-4052-a150-5ecd13a8e821" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/f6ed846f-ee2f-4a47-b708-17993a63bf72" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/a55cf1f0-120a-4fe1-bfd1-80d010c9ca41" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/bc3167b4-42c0-46a6-8809-b9924e919153" width="300" height="600">

<img src="https://github.com/kanavnayyer/investing-buddy/assets/78265974/6b802f5d-9555-4b41-aec9-61a5ef7a1694" width="300" height="600">



https://github.com/kanavnayyer/investing-buddy/assets/78265974/fb8bf6f8-db2d-47d4-915b-9d15411918cd



https://github.com/kanavnayyer/investing-buddy/assets/78265974/e9e69e59-e7a6-421a-99f2-5df7fabf0543



https://github.com/kanavnayyer/investing-buddy/assets/78265974/b056a42e-9a98-477a-a893-2b355504d2f5



https://github.com/kanavnayyer/investing-buddy/assets/78265974/eee23862-4eb3-45ac-aa78-50927e7199ef

