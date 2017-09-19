package fbconnect.controller;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by amit on 4/2/17.
 */

public interface HttpMethod {

    @GET(WebEndPoint.PROFILE)
    Call<Object> profile(@Path(WebEndPoint.ID) String id, @QueryMap Map<String, String> map);

    @GET(WebEndPoint.FRIEND)
    Call<Object> friends(@Path(WebEndPoint.ID) String id, @QueryMap Map<String, String> map);

    @GET(WebEndPoint.ALBUM)
    Call<Object> albums(@Path(WebEndPoint.ID) String id, @QueryMap Map<String, String> map);

    @GET(WebEndPoint.PHOTO)
    Call<Object> photos(@Path(WebEndPoint.ID) String id, @QueryMap Map<String, String> map);
}
