[ ![Download](https://api.bintray.com/packages/amitsahni/Library/instagram/images/download.svg) ](https://bintray.com/amitsahni/Library/instagram/_latestVersion)

**How to Use Library**


#### Configuration

```kotlin
InstaConfiguration.clientId("clinetId", "redirectUrl")
                .isDebug(true)
                .config(getApplication());
```


To Check if alreadyLogin 

```
InstaConnect.isAlreadyLogin(context)
```

To check for active `token`

```
InstaConnect.accessToken(context)
```

`Login Sample`

```kotlin
if (!InstaConnect.isAlreadyLogin(context)) {
                InstaConnect.with()
                        .login(context)
                        .success(token -> {
                            Log.i(getLocalClassName(), "Token = " + token);
                            return Unit.INSTANCE;
                        }).error(error -> {
                    Log.i(getLocalClassName(), "Error = " + error);
                    return Unit.INSTANCE;
                }).build();
            }
```


`Profile Sample`

```kotlin
InstaConnect.with()
                        .profile(MainActivity.this)
                        .success(profile -> {
                            Log.i(getLocalClassName(), "Model = " + profile.toString());
                            return Unit.INSTANCE;
                        })
                        .build();
```


```
repositories {
    maven {
        url  "https://dl.bintray.com/amitsahni/Library" 
    }
}
```

```
implementation 'com.amitsahni:instagram:0.0.1-alpha01'
```