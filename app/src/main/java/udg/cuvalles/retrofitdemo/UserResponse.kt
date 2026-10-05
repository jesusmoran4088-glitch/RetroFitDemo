package udg.cuvalles.retrofitdemo

import com.google.gson.annotations.SerializedName

/**
 * Data class que representa la respuesta completa de reqres.in:
 * https://reqres.in/api/users?page=1
 *
 * La respuesta está envuelta en metadatos de paginación y una lista de usuarios.
 */
data class UserResponse(
    @SerializedName("page")         val page: Int,
    @SerializedName("per_page")     val perPage: Int,
    @SerializedName("total")        val total: Int,
    @SerializedName("total_pages")  val totalPages: Int,
    @SerializedName("data")         val users: List<User>
)

/**
 * Data class que representa UN usuario individual de la API.
 */
data class User(
    @SerializedName("id")         val id: Int,
    @SerializedName("email")      val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name")  val lastName: String,
    @SerializedName("avatar")     val avatar: String
)