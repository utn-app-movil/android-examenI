package identities

data class Event(
    val location: String,
    val date: String,
    val time: String,
    val seatNumber: String,
    val eventType: EventType
)
