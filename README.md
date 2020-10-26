# SoccerCodingChallenge

Project 'SoccerCodingChallenge' is a coding challenge exercise which retrieves league details from API, parse data and display as shown in the below screenshots. 

<img src="https://github.com/abhim0504/SoccerCodingChallenge/blob/master/screenshots/Screenshot_20201025-193823.jpg" width="200" height="390">    <img src="https://github.com/abhim0504/SoccerCodingChallenge/blob/master/screenshots/Screenshot_20201025-193837.jpg?" width="200" height="390">

<img src="https://github.com/abhim0504/SoccerCodingChallenge/blob/master/screenshots/Screenshot_20201025-193859.jpg?" width="200" height="390">   <img src="https://github.com/abhim0504/SoccerCodingChallenge/blob/master/screenshots/Screenshot_20201025-193915.jpg?" width="200" height="390">

## Main Features: 
- Fetched and displayed results from below APIs (in TabView)
  - Fixtures: https://storage.googleapis.com/cdn-og-test-api/test-task/fixtures.json
  - Results: https://storage.googleapis.com/cdn-og-test-api/test-task/results.json
- Filtered results based on competition name
- Fixtures: 
  - Display match day of the month, match day of the week
  - If match was postponed, highlighted the item
- Results: 
  - Home and Away team scores were displayed
  - Winner team highlighted 


## Core Architectural components used: 
* MVVM Architecture with LiveData
* Kotlin/XML
* Retrofit
* Dagger2
* Data Binding
* Coroutine
