package maze.solver

import maze.core.ISolver
import maze.core.Maze
import maze.core.Point
import maze.enums.Cell
import kotlin.time.Duration
import kotlin.time.measureTime

class DFSSolver : ISolver {

    private lateinit var visited : Array<BooleanArray>
    private val path = mutableListOf<Point>()
    private var time = Duration.ZERO

    override fun solve(maze: Maze): List<Point> {
        path.clear()
        visited = Array(maze.width) { BooleanArray(maze.height){ false } }
        time = measureTime {
            dfs(maze.start(), maze)
        }
        println("path :")
        path.forEach {
            println(it)
        }
        return path
    }

    override fun time(): Duration {
        return time
    }

    private fun dfs(point: Point,maze: Maze) : Boolean{
        if (point !in maze || maze[point] == Cell.WALL || visited[point.x][point.y]) {
            return false
        }
        if (point == maze.end()) {
            path.add(point)
            return true
        }
        visited[point.x][point.y] = true
        for (dir in Point.DIRECTIONS) {
            val newPoint = point + dir
            if (dfs(newPoint,maze)) {
                path.add(newPoint)
                return true
            }
        }
        return false
    }

}