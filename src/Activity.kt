import java.time.Duration

class Activity(private val name: String, private val expectedDuration: Duration, private var likability: Int, private val parallelizable: Boolean) {


    private var avarageDuration = expectedDuration

    init {
        require(likability in MIN_LIKABILITY..MAX_LIKABILITY){ "Likability value out of range" }
    }

    fun getName() : String{
        return name
    }
}