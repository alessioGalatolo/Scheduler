import java.util.*

class Activity(val name: String,
               val deadline: Deadline,
               val duration: Int, //#number of minutes
               val plesurable: Boolean/*,  val parallelizable: Boolean = false*/) {


    private var avarageDuration = duration

    init {
        //require(likability in 0..MAX_LIKABILITY){ "Likability value out of range" }
    }


    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Activity)
            return false
        return name == other.name && deadline == other.deadline && duration == other.duration && plesurable == other.plesurable
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + deadline.hashCode()
        result = 31 * result + duration
        result = 31 * result + plesurable.hashCode()
        result = 31 * result + avarageDuration
        return result
    }

    inner class Deadline(private val dayOfMonth: Int, hour: Int, min: Int): Comparable<Deadline>{

        val time = hour * 60 + min

        init{
            require(hour in 0 until 24)
            require(min in 0 until 60)
        }

        fun getBlock(partsOfHour: Int): Int {
            return time / 60 / partsOfHour
        }

        override fun equals(other: Any?): Boolean {
            if(other == null || other !is Deadline)
                return false
            return dayOfMonth == other.dayOfMonth && time == other.time
        }

        override fun compareTo(other: Deadline): Int {
            val currentDay = GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)
            val day1 = if(dayOfMonth >= currentDay) dayOfMonth
            else dayOfMonth + currentDay
            val day2 = if(other.dayOfMonth >= currentDay) other.dayOfMonth
            else other.dayOfMonth + currentDay

            return day1 - day2
        }

        override fun hashCode(): Int {
            var result = dayOfMonth
            result = 31 * result + time
            return result
        }
    }
}