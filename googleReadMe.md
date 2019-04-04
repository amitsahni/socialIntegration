[ ![Download](https://api.bintray.com/packages/amitsahni/Library/google/images/download.svg) ](https://bintray.com/amitsahni/Library/google/_latestVersion)

**How to Use Library**


`TwitterConnect` is used to call every Method.
Currently this library support only following service.
- Login
- Logout
- Profile

----
`Model` used in above service



##### Login

  `Result` - TwitterSession

  `Error` - TwitterException

##### Logout

`Result` - N/A

`Error` - N/A

##### Profile

`Result` - User

`Error` - TwitterException


----

`Sample` Example

To set `Configuration` regarding Twitter Key , Twitter Secret

```kotlin
GoogleConfiguration.clientId(webclientId)
                .build();
```

To check for current user

```kotlin
GoogleConfiguration.getAuth().getCurrentUser();
```

`Login Sample`

```kotlin
FirebaseUser user = GoogleConfiguration.getAuth().getCurrentUser();
            if (user == null) {
                GoogleConnect.INSTANCE.with()
                        .login(activity, requestCode)
                        .build();
            } else {
                Log.i(getLocalClassName(), user.getDisplayName() + " " + user.getEmail() + "" + user.getPhoneNumber());
            }
```

`Profile Sample`

```kotlin
GoogleConnect.with()
             .profile()
             .build()
```

`OnAcitivyResult`

```kotlin
GoogleConfiguration.onActivityResult(data, aBoolean -> {
                    if (aBoolean)
                        GoogleConnect.INSTANCE.with()
                                .profile()
                                .build();
                    return Unit.INSTANCE;
                });
```

Download
--------

[ ![Download](https://api.bintray.com/packages/amitsahni/Library/google/images/download.svg) ](https://bintray.com/amitsahni/Library/google/_latestVersion)


```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/amitsahni/Library" 
    }
}
```

```groovy
implementation 'com.amitsahni:google:0.0.1-alpha01'
```