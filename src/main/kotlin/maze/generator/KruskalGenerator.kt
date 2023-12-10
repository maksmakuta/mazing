package maze.generator

import maze.core.IGenerator
import maze.core.Maze
import maze.core.Point
import maze.core.Size
import maze.enums.Cell
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

class KruskalGenerator(override var size: Size) : IGenerator {

    private var t = Duration.ZERO
    private val maze = Maze(size)
    private lateinit var rand : Random
    private val walls = mutableListOf<Point>()
    private val sets = mutableListOf<MutableList<Point>>()

    override fun generate(seed: Long): Maze {
        maze.setSeed(seed)
        rand = Random(seed)
        setup()
        t = measureTime {
            run()
        }
        maze[maze.start()] = Cell.START
        maze[maze.end()] = Cell.END
        return maze
    }

    private fun setup() {
        maze.fill(Cell.WALL)
        for(x in 1..<maze.width-1 step 2){
            for(y in 1..<maze.height-1 step 2){
                maze[x,y] = Cell.EMPTY
                walls.add(Point(x,y))
                sets.add(mutableListOf(Point(x,y)))
            }
        }
    }

    private fun breakWall(point: Point){
        val dir = Point.DIRECTIONS.random(rand)
        val wall = point + dir
        val p2 = point + (dir * 2)
        if(p2 in maze){
            val set1 = sets.indexOfFirst{ it.contains(point)}
            val set2 = sets.indexOfFirst{ it.contains(p2)}
            if(set2 != set1){
                maze[wall] = Cell.EMPTY
                maze[p2] = Cell.EMPTY
                sets[set1].addAll(sets[set2])
                sets.removeAt(set2)
            }
        }else{
            breakWall(point)
        }
    }

    private fun run(){
        while (sets.size != 1) {
            walls.shuffled(rand).forEach { wall ->
                breakWall(wall)
            }
        }
    }

    override fun setPoints(start: Point, end: Point) {
        maze.setStart(start)
        maze.setEnd(end)
    }

    override fun time(): Duration {
        return t
    }
}