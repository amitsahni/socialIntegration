package fbconnect.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/2/17.
 */

public class ProfileResult {

    /*{
   "id": "10211600460784783",
   "name": "Amit Sahni",
   "first_name": "Amit",
   "last_name": "Sahni",
   "gender": "male",
   "email": "amit_sahni1987\u0040yahoo.co.in",
   "picture": {
      "data": {
         "is_silhouette": false,
         "url": "https://fb-s-a-a.akamaihd.net/h-ak-xpf1/v/t1.0-1/p200x200/16426096_10211562057304720_5970492147583730155_n.jpg?oh=0165a23d3562d3b1b3b89abaeb7fcc0d&oe=59080EB4&__gda__=1493928063_63a6873a68bc62b4b378cd6586a96233"
      }
   },
   "cover": {
      "id": "10209343861811219",
      "offset_y": 58,
      "source": "https://scontent.xx.fbcdn.net/v/t1.0-9/q81/p720x720/13417524_10209343861811219_667651129504691913_n.jpg?oh=5f78a748c6ae26ed88f85db066f7d2ca&oe=59039433"
   }
}*/
    @SerializedName("id")
    private String id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("email")
    private String email;

    @SerializedName("verified")
    private String verified;

    @SerializedName("name")
    private String name;

    @SerializedName("locale")
    private String locale;

    @SerializedName("link")
    private String link;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("updated_time")
    private String updatedTime;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("cover")
    private Cover cover;

    public class Picture {
        @SerializedName("data")
        private Data data;

        public class Data {
            @SerializedName("url")
            private String profileImage;

            public
            @NonNull
            String getProfileImage() {
                return Optional.fromNullable(profileImage).or("");
            }
        }

        public
        @Nullable
        Data getData() {
            return data;
        }
    }

    public class Cover {
        @SerializedName("source")
        private String coverImage;

        public
        @NonNull
        String getCoverImage() {
            return Optional.fromNullable(coverImage).or("");
        }
    }

    public
    @NonNull
    String getId() {
        return Optional.fromNullable(id).or("");
    }

    public
    @NonNull
    String getFirstName() {
        return Optional.fromNullable(firstName).or("");
    }

    public
    @NonNull
    String getTimezone() {
        return Optional.fromNullable(timezone).or("");
    }

    public
    @NonNull
    String getEmail() {
        return Optional.fromNullable(email).or("");
    }

    public
    @NonNull
    String getVerified() {
        return Optional.fromNullable(verified).or("");
    }

    public
    @NonNull
    String getName() {
        return Optional.fromNullable(name).or("");
    }

    public
    @NonNull
    String getLocale() {
        return Optional.fromNullable(locale).or("");
    }

    public
    @NonNull
    String getLink() {
        return Optional.fromNullable(link).or("");
    }

    public
    @NonNull
    String getLastName() {
        return Optional.fromNullable(lastName).or("");
    }

    public
    @NonNull
    String getGender() {
        return Optional.fromNullable(gender).or("");
    }

    public
    @NonNull
    String getUpdatedTime() {
        return Optional.fromNullable(updatedTime).or("");
    }

    public
    @Nullable
    Picture getPicture() {
        return picture;
    }

    public
    @Nullable
    Cover getCover() {
        return cover;
    }
}
