package udg.cuvalles.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que define los endpoints de la API reqres.in.
 * Retrofit genera automáticamente la implementación en tiempo de ejecución.
 *
 * URL final del endpoint: https://reqres.in/api/users?page=1
 */
interface ApiService {

    /**
     * Consulta el listado paginado de usuarios.
     *
     * @param page Número de página (por defecto 1).
     * @return Response<UserResponse> → Envuelve la respuesta paginada
     *         junto con el código HTTP (200, 404, 500, etc.).
     */
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1): Response<UserResponse>
}