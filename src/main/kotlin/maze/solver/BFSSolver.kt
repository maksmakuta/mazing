package maze.solver

import maze.core.ISolver
import maze.core.Maze
import maze.core.Point
import maze.enums.Cell
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class BFSSolver : ISolver {

    private var t = Duration.ZERO

    override fun solve(maze: Maze): List<Point> {
        val steps = mutableListOf<Point>()
        t = measureTime {
            steps.addAll(bfs(maze))
        }
        return steps
    }

    private data class BPoint(val p : Point,var parent : BPoint? = null)

    private fun bfs(maze: Maze) : List<Point>{
        val visited = Array(maze.width){
            BooleanArray(maze.height){
                false
            }
        }
        val queue: Queue<BPoint> = LinkedList()
        queue.add(BPoint(maze.start()))
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if(current.p == maze.end()){
                return toSteps(current)
            }
            for (neighbor in neighbors(current.p,maze)) {
                val (x,y) = neighbor
                if (!visited[x][y]) {
                    visited[x][y] = true
                    queue.add(BPoint(neighbor,current))
                }
            }
        }
        return listOf()
    }

    private fun toSteps(p : BPoint) : List<Point>{
        val list = mutableListOf<Point>()
        list.add(p.p)
        var c = p.parent
        while (c?.parent != null){
            list.add(c.p)
            c = c.parent
        }
        return list
    }

    private fun neighbors(point : Point,maze: Maze) : List<Point>{
        val list = mutableListOf<Point>()
        for(i in Point.DIRECTIONS){
            val newPoint = point + i
            if(newPoint in maze && maze[newPoint] != Cell.WALL){
                list.add(newPoint)
            }
        }
        return list
    }

    override fun time(): Duration {
        return t
    }
}