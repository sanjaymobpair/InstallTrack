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

* After Integration of FCM Add this line in your **MyFirebaseInstanceIDService** Class 
```
String refreshedToken = FirebaseInstanceId.getInstance().getToken();
TrackLib.getInstance().updateFCMToken(refreshedToken);
```
* Add FCM Server Token in Your Project Application Class
Add Like : 

```
TrackLib.getInstance().serverKey(serverKey);
```
Make Sure Your Server Key Must be : 

![serverkey](https://github.com/sanjay11MP/TestSdk/blob/newsdk/app/src/main/res/drawable/server_key.png)

* After this you should read our makeaff document 

* You should add *ApiKey* and *Domain Point* From  our makeaff panel

```
TrackLib.getInstance().apiKey("63498e5007e70c082121eda21c696b6b_5ad49ed585c80f14511897eb");
TrackLib.getInstance().domainEndPoint("technology.makeaff.com");
```
below screenshot show your *apikey* and *Domain Point* 
