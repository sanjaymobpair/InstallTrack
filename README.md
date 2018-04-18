# Install Track
   It Will Help To Track Your Install Or Uninstall Of App.
## Getting Started
   This Library will provide the total install or uninstall of your app.
### Installing
   * Add it to build.gradle (Project level).
   
```
   allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

   * Add it to build.gradle (App Level).

```
   compile 'com.github.sanjay11MP:TestSdk:1.0.2'
   ```
### FCM Integration
   * Also You Should Implement FCM Integration 
   
* [Firebase Console](https://firebase.google.com/) - For Firebase Integration follow this link
* Add FCM Server Token in Your Project Application Class

Add Like : 

```
TrackLib.getInstance().serverKey(serverKey);
```
Make Sure Your Server Key Must be : 

![serverkey](https://github.com/sanjay11MP/TestSdk/blob/newsdk/app/src/main/res/drawable/server_key.png)
