package maze.solver

import maze.core.IMaze
import maze.core.ISolvable
import maze.enums.Cell
import kotlin.time.Duration
import kotlin.time.measureTime

class DFSSolver : ISolvable {

    private lateinit var visited : Array<BooleanArray>
    private val path = mutableListOf<Pair<Int, Int>>()
    private var time = Duration.ZERO

    override fun solve(maze: IMaze): List<Pair<Int, Int>> {
        visited = Array(maze.size().first) { BooleanArray(maze.size().second){ false } }
        time = measureTime {
            dfs(1, 1, maze)
        }
        return path
    }

    override fun time(): Duration {
        return time
    }

    private fun dfs(x : Int,y : Int,maze: IMaze) : Boolean{
        if (x < 0 || x >= maze.size().first || y < 0 || y >= maze.size().second || maze[x,y] == Cell.WALL || visited[x][y]) {
            return false
        }
        if (x == maze.end().first && y == maze.end().second) {
            path.add(x to y)
            return true
        }
        visited[x][y] = true
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        for ((dx, dy) in directions) {
            val newRow = x + dx
            val newCol = y + dy
            if (dfs(newRow, newCol,maze)) {
                path.add(x to y)
                return true
            }
        }
        return false
    }

}