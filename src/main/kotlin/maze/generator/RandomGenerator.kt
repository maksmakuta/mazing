package maze.generator

import maze.Maze
import maze.core.AGenerator
import maze.core.IMaze
import maze.enums.Cell
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

class RandomGenerator(override var size: Pair<Int, Int>) : AGenerator {

    private var t = Duration.ZERO

    override fun generate(seed : Long): IMaze {
        val rnd = Random(seed)
        val m = Maze(size.first,size.second)
        t = measureTime {
            val walls = mutableListOf<Int>()
            for (i in 1..<size.first-1) {
                for (j in 1..<size.second-1) {
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
        }
        m[1,1] =Cell.START
        m[size.first-2,size.second-2] = Cell.END
        return m
    }

    override fun time(): Duration {
        return t
    }

}