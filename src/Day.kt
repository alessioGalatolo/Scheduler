import java.time.DayOfWeek

class Day(name: DayOfWeek, partsOfHours: Int = 2) {
    private val hours = Array<Activity?>(HOURS_PER_DAY * partsOfHours, {_ -> null} ) //size

    fun resize(){
        TODO("resize the array hours to make space for shorter activity")
    }
}
