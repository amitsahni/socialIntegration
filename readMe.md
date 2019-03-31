**How to Use Library**

`FbConnect` is used to call every Method.
Currently this library support only following service.
- Login
- Logout
- Profile

----
`Model` used in above service



##### Login

  `LoginResult` - LoginResult

  `FBException` - FaceBook Exception

##### Logout

`Result` - N/A

`Error` - N/A

##### Profile

`ProfileResult` - Profile Information

`FBException` - FaceBook Exception


----
#### have to set in AndroidManifest
```xml
<!-- Facebook Integration -->
        <activity
            android:name="com.facebook.FacebookActivity"
            android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
            android:label="@string/app_name"
            android:screenOrientation="portrait" />

        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/fb_app_id" />
        <meta-data
            android:name="com.facebook.sdk.ApplicationName"
            android:value="@string/app_name" />
```
`Sample` Example

To get `KeyHash`

```
FbConnect.getKeyHash(this);
```

To check for active `token`

```
FbConnect.getToken(this)
```

`Login Sample`

```kotlin
if (TextUtils.isEmpty(FbConnect.get().getToken(this))) {
            FbConnect.with()
                     .login(this)
                     .permissions(emptyList())
                     .success {
            
                     }.error {
            
                     }.cancel {
            
                     }.build()
        }
```


`Profile Sample`

```kotlin
       FbConnect.with()
                .profile(this)
                .otherUserId("")
                .success { 
                    
                }.error { 
                    
                }.build()
```

`OnActivityResult`

```
if (resultCode == Activity.RESULT_OK) {
            if (FacebookSdk.isFacebookRequestCode(requestCode)) {
                //Facebook activity result
                //Do your stuff here
                //Further you can also check if it's login or Share etc by using
                //CallbackManagerImpl as explained by rajath's answer here
                if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
                    //login
                    Log.i(getLocalClassName(), "Login");
                    FbConnect.get().onActivityResult(requestCode, resultCode, data);
                }
            }
        }
```