# Simple Screen Time Tracker
ეს აპლიკაცია ითვლის სმარტფონის სქრინის ჩართულობის ზანგრძლოვობას. გარდა ამ ფუნქციისა, აპლიკაცია ასევე გთავაზობთ შემთხვევითობის
პრინციპით არჩეული ინგლისური სიტყვების განმართებებსა და მათი გამოყენების მაგალითებს (სხვადასხვა ფორმაში).

სქრინის ჩართულობის დროის გასაზომად აპლიკაცია იყენებს Android-ის ```BroadcastReceiver```-სა და ორ event-ს:
```ACTION_SCREEN_ON``` და ```ACTION_SCREEN_OFF```. ამ ორ event-ს შორის არსებული შუალედი სწორედაც რომ წარმოადგენს
სქრინის ჩართულობის დროს. 

შემთხვევითი სიტყვის წამოსაღებად აპლიკაცია იყენებს [Random Word API](http:///random-word-api.herokuapp.com/home)-ს, ხოლო ამ სიტყვის შესაბამისი 
განმარტებებისათვის - [DictionaryAPI](https://dictionaryapi.dev)-ს. 

იმისათვის, რომ აპლიკაციამ შეინახოს სქრინის ჩართულობის ინტერვალები, იყენებს Room-ს, ანუ SQLite-ს. 

სქრინის ყოველ გათიშვაზე აპლიკაცია გამოგიგზავნით შეტყობინებას, რომელიც ჩართულობის დროს 
გაგაცნობთ.

![Screen One](https://raw.githubusercontent.com/tsotneaburjania/ScreenTimeTracker/master/app/src/main/res/drawable/scr1.png)
![Screen Two](https://raw.githubusercontent.com/tsotneaburjania/ScreenTimeTracker/master/app/src/main/res/drawable/scr2.png)
