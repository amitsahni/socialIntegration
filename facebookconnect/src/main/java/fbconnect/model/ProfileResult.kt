package fbconnect.model

import com.google.common.base.Optional
import com.google.gson.annotations.SerializedName

/**
 * Created by amit on 4/2/17.
 */

class ProfileResult {

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
    private val id: String? = null

    @SerializedName("first_name")
    private val firstName: String? = null

    @SerializedName("timezone")
    private val timezone: String? = null

    @SerializedName("email")
    private val email: String? = null

    @SerializedName("verified")
    private val verified: String? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("locale")
    private val locale: String? = null

    @SerializedName("link")
    private val link: String? = null

    @SerializedName("last_name")
    private val lastName: String? = null

    @SerializedName("gender")
    private val gender: String? = null

    @SerializedName("updated_time")
    private val updatedTime: String? = null

    @SerializedName("picture")
    val picture: Picture? = null

    @SerializedName("cover")
    val cover: Cover? = null

    inner class Picture {
        @SerializedName("data")
        val data: Data? = null

        inner class Data {
            @SerializedName("url")
            private val profileImage: String? = null

            fun getProfileImage(): String {
                return Optional.fromNullable(profileImage).or("")
            }
        }
    }

    inner class Cover {
        @SerializedName("source")
        private val coverImage: String? = null

        fun getCoverImage(): String {
            return Optional.fromNullable(coverImage).or("")
        }
    }

    fun getId(): String {
        return Optional.fromNullable(id).or("")
    }

    fun getFirstName(): String {
        return Optional.fromNullable(firstName).or("")
    }

    fun getTimezone(): String {
        return Optional.fromNullable(timezone).or("")
    }

    fun getEmail(): String {
        return Optional.fromNullable(email).or("")
    }

    fun getVerified(): String {
        return Optional.fromNullable(verified).or("")
    }

    fun getName(): String {
        return Optional.fromNullable(name).or("")
    }

    fun getLocale(): String {
        return Optional.fromNullable(locale).or("")
    }

    fun getLink(): String {
        return Optional.fromNullable(link).or("")
    }

    fun getLastName(): String {
        return Optional.fromNullable(lastName).or("")
    }

    fun getGender(): String {
        return Optional.fromNullable(gender).or("")
    }

    fun getUpdatedTime(): String {
        return Optional.fromNullable(updatedTime).or("")
    }
}
