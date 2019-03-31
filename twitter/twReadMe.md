![](https://jitpack.io/v/amitclickapps/twitterutil.svg?style=flat-square)
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
TwitterConfiguration.keys("", "")
                .isDebug(true)
                .config(this);
```

To check for active `session`

```kotlin
TwitterConnect.getSession()
```

`Login Sample`

```kotlin
if (TwitterConnect.getSession() == null) {
                    TwitterConnect.with()
                            .login(MainActivity.this)
                            .success(model -> {
                                Log.i(getLocalClassName(), "getUserName = " + model.getUserName());
                                Log.i(getLocalClassName(), "getUserId = " + model.getUserId());
                                return Unit.INSTANCE;
                            }).error(error -> {
                        return Unit.INSTANCE;
                    }).build();
                } 
```

`Profile Sample`

```kotlin
TwitterConnect.with()
              .profile()
              .success(s -> {

                                Log.i(getLocalClassName(), "Email = " + s.email);
                                Log.i(getLocalClassName(), "Name = " + s.name);
                                Log.i(getLocalClassName(), "profileImageUrl = " + s.profileImageUrl);
                                StringBuilder builder = new StringBuilder();
                                builder.append("Name = " + s.name + "\n");
                                builder.append("Email = " + s.email + "\n");
                                builder.append("Profile url = " + s.profileImageUrl + "\n");
                                builder.append("Follower Count = " + s.followersCount + "\n");
                                Log.i(getLocalClassName(), "builder = " + s.toString());
                                return Unit.INSTANCE;
                            })
                            .error(error -> {
                                return Unit.INSTANCE;
                            }).build();
```

`OnAcitivyResult`

```
if(requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE){
     TwitterConnect.get().onActivityResult(requestCode, resultCode, data);
 }
```

Download
--------
Add the JitPack repository to your root build.gradle:

![](https://jitpack.io/v/amitclickapps/twitterutil.svg?style=flat-square)


```groovy
	allprojects {
		repositories {
			maven { url "https://jitpack.io" }
		}
	}
```
App Gradle
```groovy
  repositories {
    jcenter()
}
```
Add the Gradle dependency:
```groovy
	dependencies {
		compile 'com.github.amitclickapps:twitterutil:latest'
	}
```