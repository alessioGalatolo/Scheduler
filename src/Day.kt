import java.time.DayOfWeek
import java.util.*
import kotlin.collections.ArrayList

class Day(name: DayOfWeek, val partsOfHour: Int = 2) {

    private val hours = Array<Activity?>(HOURS_PER_DAY * partsOfHour) {null} //size

    fun resize(){
        TODO("resize the array hours to make space for shorter activity")
    }

    fun addActivity(activity: Activity, index: Int){
        hours[index] = activity
        //????????????????????
    }

    fun getFreeHours(): ArrayList<Int> {
        val freeHours = ArrayList<Int>()
        for(i in currentTimeRange()..(hours.size - 1)){
            if(hours[i] == null){
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


}
