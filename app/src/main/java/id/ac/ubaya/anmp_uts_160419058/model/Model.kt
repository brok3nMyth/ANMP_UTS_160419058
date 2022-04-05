package id.ac.ubaya.anmp_uts_160419058.model

import com.google.gson.annotations.SerializedName

data class Books(
    val title:String?,@SerializedName("book_name")
    val author:String?,
    val code:String?,
    val genre: String?,
    val image:String?, //@SerializedName("image_url")
)
data class News(
    val title: String?,
    val content: String?,@SerializedName("image_url")
    val date: String?,
)

data class Favorites(
    val title:String?,@SerializedName("book_name")
    val author:String?,
    val code:String?,
    val genre: String?,
    val image:String?, // probably combined with books
)

data class Loans(
    val code: String?,
    val title: String?,@SerializedName("book_name")
    val status : String?,
)
