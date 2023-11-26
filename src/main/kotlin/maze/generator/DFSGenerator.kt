package maze.generator

import maze.Maze
import maze.core.AGenerator
import maze.core.IMaze
import maze.enums.Cell
import java.util.*
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

class DFSGenerator(override var size: Pair<Int, Int>) : AGenerator {

    private var t = Duration.ZERO
    private val visited: Array<Array<Boolean>> = Array(size.second) { Array(size.first) { false } }
    private val directions = arrayOf(
        Pair(-1,  0),
        Pair( 1,  0),
        Pair( 0, -1),
        Pair( 0,  1)
    )

    override fun generate(seed : Long): IMaze {
        val rand = Random(seed)
        val m = Maze(size.first,size.second)
        m.fill(Cell.WALL)
        t = measureTime {
            run(m, rand)
        }
        m[1,1] = Cell.START
        m[size.first-2,size.second-2] = Cell.END
        return m
    }

    override fun time(): Duration {
        return t
    }

    private fun run(maze: Maze,random: Random){
        val stack = Stack<Pair<Int, Int>>()
        val start = Pair(1, 1)

        stack.push(start)
        visited[start.first][start.second] = true

        while (stack.isNotEmpty()) {
            val current = stack.pop()
            val (row, col) = current
            maze[row,col] = Cell.EMPTY
            val neighbors = getUnvisitedNeighbors(row, col)
            if (neighbors.isNotEmpty()) {
                stack.push(current)
                val randomNeighbor = neighbors.random(random)
                val (nextRow, nextCol) = randomNeighbor
                maze[(row + nextRow) / 2,(col + nextCol) / 2] = Cell.EMPTY
                stack.push(randomNeighbor)
                visited[nextRow][nextCol] = true
            }
        }
    }

    private fun getUnvisitedNeighbors(row: Int, col: Int): List<Pair<Int, Int>> {
        val neighbors = mutableListOf<Pair<Int, Int>>()
        for ((dr, dc) in directions) {
            val newRow = row + 2 * dr
            val newCol = col + 2 * dc
            if (newRow in 1..<size.second-1 && newCol in 1..<size.first-1 && !visited[newRow][newCol]) {
                neighbors.add(Pair(newRow, newCol))
            }
        }
        return neighbors
    }

}