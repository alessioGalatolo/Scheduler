import java.util.*

class Activity(private val name: String, private val expectedDuration: Int/*#of minutes*/,
               val deadline: Deadline /*Long represents the minutes from midnight. deadline can only be max 1 month away */,
               val duration: Int,
               val plesurable: Boolean,  val parallelizable: Boolean = false) {


    private var avarageDuration = expectedDuration

    init {
        //require(likability in 0..MAX_LIKABILITY){ "Likability value out of range" }
    }

    fun getName() : String{
        return name
    }

    override fun toString(): String {
        return name
    }

    inner class Deadline(private val dayOfMonth: Int, hour: Int, min: Int): Comparable<Deadline>{
        override fun compareTo(other: Deadline): Int {
            val currentDay = GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)
            val day1 = if(dayOfMonth >= currentDay) dayOfMonth
            else dayOfMonth + currentDay
            val day2 = if(other.dayOfMonth >= currentDay) other.dayOfMonth
            else other.dayOfMonth + currentDay

            return day1 - day2
        }

        fun getBlock(partsOfHour: Int): Int {
            return time / 60 / partsOfHour
        }

        val time = hour * 60 + min

        init{
            require(hour in 0 until 24)
            require(min in 0 until 60)
        }

    }
}