class ActivityPool {
    private val activityList = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        for(a in activityList){
            if(levenshteinDistance(a.getName(), activity.getName()) < MAX_WORD_DISTANCE)
                TODO("is the same")
        }

    }


    fun levenshteinDistance(lhs: CharSequence, rhs: CharSequence): Int {
        val len0 = lhs.length + 1
        val len1 = rhs.length + 1

        // the array of distances
        var cost = IntArray(len0)
        var newCost = IntArray(len0)

        // initial cost of skipping prefix in String s0
        for (i in 0 until len0) cost[i] = i

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (j in 1 until len1) {
            // initial cost of skipping prefix in String s1
            newCost[0] = j

            // transformation cost for each letter in s0
            for (i in 1 until len0) {
                // matching current letters in both strings
                val match = if (lhs[i - 1] == rhs[j - 1]) 0 else 1

                // computing cost for each transformation
                val costReplace = cost[i - 1] + match
                val costInsert = cost[i] + 1
                val costDelete = newCost[i - 1] + 1

                // keep minimum cost
                newCost[i] = Math.min(Math.min(costInsert, costDelete), costReplace)
            }

            // swap cost/newcost arrays
            val swap = cost
            cost = newCost
            newCost = swap
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1]
    }


}