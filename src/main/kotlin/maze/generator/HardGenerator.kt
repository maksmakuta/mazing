package maze.generator

import maze.core.IGenerator
import maze.core.Maze
import maze.core.Size
import kotlin.time.Duration

class HardGenerator(override var size: Size) : IGenerator {

    private var t = Duration.ZERO

    override fun generate(seed: Long): Maze {
        val m = Maze(size,seed,0F)

        return m
    }

    override fun time(): Duration {
        return t
    }
}