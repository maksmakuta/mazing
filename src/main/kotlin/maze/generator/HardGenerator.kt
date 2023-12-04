package maze.generator

import maze.app.Maze
import maze.core.IGenerator
import maze.core.IMaze
import kotlin.time.Duration

class HardGenerator(override var size: Pair<Int, Int>) : IGenerator {

    private var t = Duration.ZERO

    override fun generate(seed: Long): IMaze {
        val maze = Maze(size.first,size.second)

        return maze
    }

    override fun time(): Duration {
        return t
    }
}