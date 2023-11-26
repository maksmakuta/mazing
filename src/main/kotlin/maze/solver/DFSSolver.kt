package maze.solver

import maze.core.IMaze
import maze.core.ISolvable
import maze.enums.Cell

class DFSSolver : ISolvable {

    private lateinit var visited : Array<BooleanArray>
    private val path = mutableListOf<Pair<Int, Int>>()

    override fun solve(maze: IMaze): List<Pair<Int, Int>> {
        visited = Array(maze.size().first) { BooleanArray(maze.size().second){ false } }
        dfs(1,1,maze)
        return path
    }

    private fun dfs(x : Int,y : Int,maze: IMaze) : Boolean{
        // Check if the current position is out of bounds or is a wall
        if (x < 0 || x >= maze.size().first || y < 0 || y >= maze.size().second || maze[x,y] == Cell.WALL || visited[x][y]) {
            return false
        }

        // Check if we have reached the exit
        if (x == maze.end().first && y == maze.end().second) {
            path.add(x to y)
            return true
        }

        // Mark the current position as visited
        visited[x][y] = true

        // Explore adjacent positions in a specific order (e.g., up, down, left, right)
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        for ((dx, dy) in directions) {
            val newRow = x + dx
            val newCol = y + dy

            // Recursively explore the neighbor
            if (dfs(newRow, newCol,maze)) {
                path.add(x to y)
                return true
            }
        }
        return false
    }

}