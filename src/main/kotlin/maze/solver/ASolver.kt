package maze.solver

import maze.core.ISolver
import maze.core.Maze
import maze.core.Point
import maze.enums.Cell
import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.time.Duration
import kotlin.time.measureTime

class ASolver : ISolver {

    private var time = Duration.ZERO
    private val q = PriorityQueue<Item>()
    private val closed = mutableListOf<Point>()

    data class Item(
        val point: Point,
        val priority : Float,
        var parent : Item? = null
    ) : Comparable<Item>{

        override fun compareTo(other: Item): Int {
            return -this.priority.compareTo(other.priority)
        }

    }

    private fun h(point: Point,goal : Point) : Int{
        return abs(point.x - goal.x) + abs(point.y - goal.y)
    }

    override fun solve(maze: Maze): List<Point> {
        val list = mutableListOf<Point>()
        var ans : Item?
        time = measureTime {
            q.add(Item(maze.start(),0F))
            ans = run(maze)
        }
        if(ans != null) {
            while (ans!!.parent != null) {
                maze[ans!!.point] = Cell.PATH
                ans = ans!!.parent
            }
        }
        return list
    }

    private fun run(maze: Maze) : Item?{
        while(q.isNotEmpty()) {
            val item = q.poll()
            if (item.point !in closed) {
                closed.add(item.point)
                for (dir in Point.DIRECTIONS) {
                    val p = item.point + dir
                    if (p in maze && maze[p] != Cell.WALL) {
                        val f = item.priority + h(p, maze.end())
                        if (p == maze.end()) {
                            println("Found end")
                            return Item(p, f, item)
                        }
                        q.add(Item(p, f, item))
                    }
                }
            }
        }
        println("no data")
        return null
    }

    override fun time(): Duration {
        return time
    }
}