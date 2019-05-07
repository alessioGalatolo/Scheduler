import java.time.DayOfWeek
import kotlin.collections.ArrayList


class TimeTable {

    private val MAX_ACTIVITY_BUFFER = 10
    private val activityBuffer: ArrayList<Activity> = ArrayList()
    private val days : Array<Day> = Array(DayOfWeek.values().size){ i ->
        Day(DayOfWeek.values()[i])
    }//idea: initialized as monday to sunday, acts like circular array


    fun addActivity(activity: Activity){
        activityBuffer.add(activity)
        checkFlush()
    }

    fun addActivities(activities: List<Activity>){
        activityBuffer.addAll(activities)
        checkFlush()
    }

    fun flush(){
        activityBuffer.sortBy {it.deadline}
        for(activity in activityBuffer){
            val iterator = days[1].getBlockIterator()
            for(item in iterator){
                while(activity.deadline)
            }
        }

    }

    private fun checkFlush() {
        if(activityBuffer.size > MAX_ACTIVITY_BUFFER)
            flush()
    }

}