package maze.generator

import maze.Maze
import maze.core.AGenerator
import maze.core.IMaze
import kotlin.time.Duration
import kotlin.time.measureTime

class PrimGenerator(size: Pair<Int, Int>) : AGenerator(size) {

    private var t = Duration.ZERO

    override fun generate(seed: Long): IMaze {
        val m = Maze(size.first,size.second)
        t = measureTime {

        }
        return m
    }

    override fun time(): Duration {
        return t
    }


}