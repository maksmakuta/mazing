package maze.solver

import maze.core.IMaze
import maze.core.ISolvable
import maze.enums.Cell
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class WalkerSolver : ISolvable {

    private var t = Duration.ZERO
    private var data = arrayOf<BooleanArray>()
    private val dirs = listOf(
        Pair( 0, 1),
        Pair( 0,-1),
        Pair( 1, 0),
        Pair(-1, 0),
    )

    override fun solve(maze: IMaze) : List<Pair<Int,Int>> {
        val steps = Stack<Pair<Int,Int>>()
        t = measureTime {

        }
        return steps
    }

    override fun time(): Duration {
        return t
    }

}