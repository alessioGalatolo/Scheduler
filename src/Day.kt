import java.time.DayOfWeek
import java.util.*
import kotlin.collections.ArrayList

class Day(name: DayOfWeek, private var partsOfHour: Int = 2) {

    private var hours = Array<ArrayList<Activity>>(HOURS_PER_DAY * partsOfHour) {ArrayList()} //size

    fun reduceMinActivityTime(newPartsOfHour: Int){
        if(newPartsOfHour > partsOfHour) {
            partsOfHour = newPartsOfHour
            val old = hours
            hours = Array(HOURS_PER_DAY * partsOfHour){i ->
                TODO()
            }
        }
        TODO("resize the array hours to make space for shorter activity")
    }

    fun addActivity(activity: Activity, index: Int){
        if(hours[index].isEmpty())
            hours[index].add(activity)
        else if(activity.parallelizable && hours[index][0].parallelizable) //need to check only first element for induction
            hours[index].add(activity)
        else
            throw ActivitiesNotCompatibleException("The hour you're trying to access is occupied and one of the activities is not serializable")
    }

    fun addActivities(activities: List<Activity>, indexes: List<Int>){
        require(activities.size == indexes.size){ "every activity must have a index to be put to" }
        for(i in 0 until activities.size){
            addActivity(activities[i], indexes[i])
        }
    }

    fun getBlockIterator(): TimeBlockIterator{
        return TimeBlockIterator(this)
    }

    fun getFreeHours(): ArrayList<Int> {
        val freeHours = ArrayList<Int>()
        for(i in currentTimeRange() until hours.size){
            if(hours[i].isEmpty()){
                freeHours.add(i)
            }
        }
        return freeHours
    }

    //returns the current time range. ex: 17:20 -> 35 if partsOfHours == 2. should be the index of the array of hours
    private fun currentTimeRange(): Int{
        val currentTime = GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY) * partsOfHour
        return currentTime + ( GregorianCalendar().get(GregorianCalendar.MINUTE).toDouble() / 60 * partsOfHour ).toInt() + 1
    }


    inner class TimeBlockIterator(private val day: Day, initCursor: Int = -1): Iterator<Pair<Int, Int>> {

        private var cursor: Int = if(initCursor != -1) initCursor
        else day.currentTimeRange()

        override fun hasNext(): Boolean {
            for(i in cursor until day.hours.size){
                if(day.hours[i].isEmpty())
                    return true
            }
            return false
        }

        //returns the range of free block such as both ends are included [start, end]
        override fun next(): Pair<Int, Int> {
            for(i in cursor until day.hours.size){
                if(day.hours[i].isEmpty()) {
                    val start = i
                    var end = i
                    while (day.hours[end].isEmpty()) {
                        end++
                    }
                    cursor = end
                    return Pair(start, end - 1)
                }
            }
            throw NoSuchElementException()
        }
    }


}
