package managers

import identities.AttendanceRecord

class EventManager {
    private val attendanceList = mutableListOf<AttendanceRecord>()

    fun addAttendanceRecord(record: AttendanceRecord) {
        attendanceList.add(record)
    }

    fun listAttendanceRecords() {
        attendanceList.forEach {
            println("Student: ${it.student.name}, Institution: ${it.student.institution}, " +
                    "Event Location: ${it.event.location}, Date: ${it.event.date}, " +
                    "Time: ${it.event.time}, Seat: ${it.event.seatNumber}, Type: ${it.event.eventType}")
        }
    }
}
