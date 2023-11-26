package maze.solver

import maze.core.IMaze
import maze.core.ISolvable
import kotlin.time.Duration
import kotlin.time.measureTime

class StarSolver: ISolvable {

    private var t = Duration.ZERO

    override fun solve(maze: IMaze): List<Pair<Int, Int>> {
        val steps = mutableListOf<Pair<Int,Int>>()
        t = measureTime {

        }
        return steps
    }

    override fun time(): Duration {
        return t
    }


}