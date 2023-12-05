package maze.generator

import maze.core.Maze
import maze.core.IGenerator
import maze.core.Point
import maze.core.Size
import maze.enums.Cell
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

class RandomGenerator(override var size: Size) : IGenerator {

    private val m = Maze(size)
    private var t = Duration.ZERO

    override fun generate(seed : Long): Maze {
        val rnd = Random(seed)
        t = measureTime {
            val walls = mutableListOf<Int>()
            size.iterate{ i,j ->
                val s = rnd.nextInt(1, 100)
                m[i, j] = if ((i == 1 && j == 2) || (i == 2 && j == 1)) {
                    Cell.EMPTY
                }else if (s < 35 && walls.find { it == j - 1 } == null) {
                    walls.add(j)
                    Cell.WALL
                } else {
                    Cell.EMPTY
                }
                if (i == j)
                    walls.clear()
            }
        }
        m[m.start()] = Cell.START
        m[m.end()] = Cell.END
        return m
    }

    override fun setPoints(start: Point, end: Point) {
        m.setStart(start)
        m.setEnd(end)
    }

    override fun time(): Duration {
        return t
    }

}