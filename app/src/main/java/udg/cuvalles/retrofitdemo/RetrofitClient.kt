package udg.cuvalles.retrofitdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente Retrofit configurado como Singleton.
 * Expone una única instancia de ApiService lista para usarse en toda la app.
 *
 * API utilizada: https://reqres.in/
 */
object RetrofitClient {

    // ✅ URL base de la API reqres.in (debe terminar con "/")
    private const val BASE_URL = "https://reqres.in/api/"

    /**
     * Interceptor de OkHttp que imprime en Logcat cada petición y respuesta.
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Cliente HTTP personalizado con el interceptor de logs.
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    /**
     * Instancia de Retrofit construida con la URL base, el cliente OkHttp
     * y el convertidor de Gson.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Servicio de la API listo para usarse. Al ser 'lazy', se crea solo
     * la primera vez que se accede a él.
     */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}