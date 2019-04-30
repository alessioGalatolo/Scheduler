import java.time.Duration

class Activity(private val name: String, private val expectedDuration: Duration, val urgency: Int, var likability: Int,  val parallelizable: Boolean = false) {

    private var avarageDuration = expectedDuration

    init {
        require(likability in 0..MAX_LIKABILITY){ "Likability value out of range" }
        require(urgency in 0..MAX_URGENCY){"urgency value out of range"}
    }

    fun getName() : String{
        return name
    }
}