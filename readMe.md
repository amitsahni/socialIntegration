
#### Do Facebook Confirguation in Firebase 
[Facebook Integration](https://firebase.google.com/docs/auth/android/facebook-login)

`Login`
```kotlin
FacebookConnect.with()
               .login(this)
               .success(model -> {
                    return Unit.INSTANCE;
                    })
               .error(e -> {
                     return Unit.INSTANCE;
               }).build();
```

```kotlin
FacebookConnect.with()
               .profile(this)
               .success(user -> {
                   Log.i(getLocalClassName(), user.getDisplayName() + " " + user.getEmail() + "" + user.getPhoneNumber());
                   return Unit.INSTANCE;
               }).build();
```

```kotlin
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (FacebookSdk.isFacebookRequestCode(requestCode)) {
                if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
                    FacebookConnect.onActivityResult(requestCode, resultCode, data);
                }
            } 
        }
    }
```


#### Do Google Confirguation in Firebase 
[Google Integration](https://firebase.google.com/docs/auth/android/google-signin)


```kotlin
GoogleConfiguration.clientId(webclientId)
                .build();
```

To check for current user

`Login`

```kotlin
 GoogleConnect.with()
              .login(activity, requestCode)
              .build();
```

`Profile Sample`

```kotlin
GoogleConnect.with()
             .profile()
             .build()
```

`OnAcitivyResult`

```kotlin
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 'requestCode') {
                GoogleConnect.onActivityResult(data, aBoolean -> {
                    if (aBoolean)
                        GoogleConnect.INSTANCE.with()
                                .profile()
                                .build();
                    return Unit.INSTANCE;
                });
            }
        }
    }
```

#### Do Google Confirguation in Firebase 
[Twitter Integration](https://firebase.google.com/docs/auth/android/google-login)


`Login`

```kotlin
 TwitterConnect.with()
               .login(this)
               .success(model -> {
                    return Unit.INSTANCE;
                  })
               .error(e -> {
                    return Unit.INSTANCE;
                  }).build();
```

`Profile Sample`

```kotlin
TwitterConnect.with()
              .profile(this)
              .success(info -> {
                Log.i(getLocalClassName(), info.getDisplayName() + " " + info.getEmail() + "" + info.getPhoneNumber());
                return Unit.INSTANCE;
                 })
              .build();
```

`OnAcitivyResult`

```kotlin
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TwitterConnect.onActivityResult(requestCode, resultCode, data);
    }
```

