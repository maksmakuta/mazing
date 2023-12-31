package maze.generator

import maze.core.Maze
import maze.core.IGenerator
import maze.core.Point
import maze.core.Size
import maze.enums.Cell
import java.util.*
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.measureTime

class DFSGenerator(override var size: Size) : IGenerator {

    private var t = Duration.ZERO
    private val maze = Maze(size)
    private val visited: Array<Array<Boolean>> = Array(size.w) { Array(size.h) { false } }

    override fun time(): Duration {
        return t
    }

    override fun generate(seed : Long): Maze {
        val rand = Random(seed)
        maze.fill(Cell.WALL)
        t = measureTime {
            run(maze, rand)
        }
        maze[maze.start()] = Cell.START
        maze[maze.end()] = Cell.END
        return maze
    }

    override fun setPoints(start: Point, end: Point) {
        maze.setStart(start)
        maze.setEnd(end)
    }

    private fun run(maze: Maze, random: Random){
        val stack = Stack<Point>()
        stack.push(maze.start())
        visited[maze.start().x][maze.start().y] = true
        while (stack.isNotEmpty()) {
            val current = stack.pop()
            val (row, col) = current
            maze[row,col] = Cell.EMPTY
            val neighbors = getUnvisitedNeighbors(current,maze)
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

    private fun getUnvisitedNeighbors(point: Point,maze: Maze): List<Point> {
        val neighbors = mutableListOf<Point>()
        for (dir in Point.DIRECTIONS) {
            val newPoint = point + (dir * 2)
            if(newPoint in maze && !visited[newPoint.x][newPoint.y]){
                neighbors.add(newPoint)
            }
        }
        return neighbors
    }

}