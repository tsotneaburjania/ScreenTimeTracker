# Simple Screen Time Tracker

This app measures screen time of a smartphone. Besides that functionality, it also is able to show the user a random word along its explanation in varios forms and usages.

For measuring screen time this app makes use of Android's own ```BroadcastReceiver``` and events such as ```ACTION_SCREEN_ON``` and ```ACTION_SCREEN_OFF```. The interval in time between these events is the screen time.

This application takes a random word from [Random Word API](http:///random-word-api.herokuapp.com/home), then passes that word to another api, [DictionaryAPI](https://dictionaryapi.dev) where the definitions and forms/usages/examples are found.

For storing screen time history, the app uses Room.

On every screen shut off, a notification comes on with info about the most recent screen time interval.

![Screen One](https://raw.githubusercontent.com/tsotneaburjania/ScreenTimeTracker/master/app/src/main/res/drawable/scr1.png)
![Screen Two](https://raw.githubusercontent.com/tsotneaburjania/ScreenTimeTracker/master/app/src/main/res/drawable/scr2.png)
