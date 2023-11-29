import java.io.Serializable

data class Landmark(val name: String, val address: String, val latitude: Double, val longitude: Double):
    Serializable