package fbconnect.controllerT

/**
 * Created by amit on 4/2/17.
 */

object WebEndPoint {
    val USER_ID_KEY = "user_id"
    val ALBUM_ID_KEY = "album_id"
    var ACCESS_TOKEN_KEY = "access_token"
    var FIELD = "fields"
    val ID = "id"
    var BASE = "https://graph.facebook.com/v1.0/"
    val PROFILE = "{$ID}"
    val FRIEND = "{$ID}/friends"
    val ALBUM = "{$ID}/albums"
    val PHOTO = "{$ID}/photos"
}
