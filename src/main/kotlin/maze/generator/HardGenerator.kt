package maze.generator

import maze.core.IGenerator
import maze.core.Maze
import maze.core.Point
import maze.core.Size
import kotlin.time.Duration

class HardGenerator(override var size: Size) : IGenerator {

    private var t = Duration.ZERO

    private val maze = Maze(size)

    override fun generate(seed: Long): Maze {
        maze.setSeed(seed)

        return maze
    }


    override fun setPoints(start: Point, end: Point) {
        maze.setStart(start)
        maze.setEnd(end)
    }

    override fun time(): Duration {
        return t
    }
}