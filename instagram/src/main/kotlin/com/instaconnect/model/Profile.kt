package com.instaconnect.model

import com.google.gson.annotations.SerializedName

data class Profile(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("meta")
        val meta: Meta
) {
    data class Data(
            @SerializedName("bio")
            val bio: String,
            @SerializedName("counts")
            val counts: Counts,
            @SerializedName("full_name")
            val fullName: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("is_business")
            val isBusiness: Boolean,
            @SerializedName("profile_picture")
            val profilePicture: String,
            @SerializedName("username")
            val username: String,
            @SerializedName("website")
            val website: String
    ) {
        data class Counts(
                @SerializedName("followed_by")
                val followedBy: Int,
                @SerializedName("follows")
                val follows: Int,
                @SerializedName("media")
                val media: Int
        )
    }

    data class Meta(
            @SerializedName("code")
            val code: Int
    )
}