package fbconnect.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import fbconnect.FbConnect;
import fbconnect.Param;
import fbconnect.http.Builder;
import fbconnect.http.RetrofitManager;
import fbconnect.http.WebConnect;
import fbconnect.http.WebHandler;
import fbconnect.model.ProfileResult;
import retrofit2.Response;

/**
 * Created by amit on 4/2/17.
 */

public class ProfileController {

    public void profile(@NonNull final Param param) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(WebEndPoint.FIELD, "about,gender,first_name,birthday,last_name,name,link,email,cover,updated_time,timezone,work,picture.type(large)");
        map.put(WebEndPoint.ACCESS_TOKEN_KEY, FbConnect.get().getToken(param.getContext()));
        String userId = param.getMap().get(WebEndPoint.USER_ID_KEY);
        if (TextUtils.isEmpty(userId)) {
            userId = "me";
        }
        Builder client = WebConnect.with(param.getContext(), WebEndPoint.PROFILE)
                .callback(new WebHandler.OnWebCallback() {
                    @Override
                    public <T> void onSuccess(@Nullable T object, int taskId, Response response) {
                        Log.i(getClass().getSimpleName(), "profile = " + response.body().toString());
                        if (object instanceof ProfileResult
                                && param.getFbCallback() != null) {
                            param.getFbCallback().onSuccess(object);
                        }
                    }

                    @Override
                    public <T> void onError(@Nullable T object, String error, int taskId, Response response) {

                    }

                })
                .baseUrl(WebEndPoint.BASE)
                .successModel(ProfileResult.class)
                .showDialog(false)
                .taskId(WebEndPoint.PROFILE_TASK_ID)
                .requestParam(map);
        client.connect(HttpMethod.class).profile(userId, map)
                .enqueue(new RetrofitManager.CallBack<>(client.getWebParam()));

    }

}
