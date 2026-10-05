package udg.cuvalles.retrofitdemo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel que gestiona la carga de usuarios desde reqres.in.
 * Mantiene el estado de la UI y sobrevive a cambios de configuración.
 */
class UserViewModel : ViewModel() {

    // ✅ Estado: Lista de usuarios cargados desde la API
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    // ✅ Estado: ¿Estamos cargando datos?
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // ✅ Estado: Mensaje de error (null cuando no hay error)
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    // Al crear el ViewModel, disparamos la carga automáticamente
    init {
        fetchUsers()
    }

    /**
     * Ejecuta la petición GET a la API dentro de viewModelScope.
     */
    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.apiService.getUsers(page = 1)
                if (response.isSuccessful) {
                    _users.value = response.body()?.users ?: emptyList()
                } else {
                    _error.value = "Error del servidor: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}