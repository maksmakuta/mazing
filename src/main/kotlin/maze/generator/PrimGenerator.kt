package maze.generator

import maze.core.Maze
import maze.core.IGenerator
import maze.core.Point
import maze.core.Size
import maze.enums.Cell
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class PrimGenerator(override var size: Size) : IGenerator {

    private var rand = Random()
    private val m = Maze(size)
    private var t = Duration.ZERO
    private val walls: MutableList<Wall> = mutableListOf()

    private data class Wall(val row : Int, val col : Int,val dir : Int)

    override fun generate(seed: Long): Maze {
        rand = Random(seed)
        m.setSeed(seed)
        m.fill(Cell.WALL)
        t = measureTime {
            generateMaze(m)
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

    private fun dir(i : Int,mult : Int) : Pair<Int,Int>{
        return when(i){
            1 -> Pair(-1*mult,0)
            2 -> Pair(1*mult,0)
            3 -> Pair(0,-1*mult)
            else -> Pair(0,1*mult)
        }
    }

    private fun Wall.inBox() : Boolean{
        var t = true
        for(i in 0 ..< 3) {
            val (x,y) = dir(dir,i)
            if (t) {
                t = (((row+x) in 1..size.w - 2) &&( (col+y) in 1..size.h - 2))
            }
        }
        return t
    }

    private fun generateMaze(maze: Maze) {
        addWallsToList(1,1)
        while (walls.isNotEmpty()) {
            val randomWallIndex = rand.nextInt(walls.size)
            val wall = walls[randomWallIndex]
            if(wall.inBox()) {
                val (x,y) = dir(wall.dir,2)
                if (maze[wall.row + x, wall.col + y] == Cell.WALL) {
                    for(j in 0.. 2) {
                        val (_x,_y) = dir(wall.dir,j)
                        maze[wall.row + _x, wall.col + _y] = Cell.EMPTY
                    }
                    addWallsToList(wall.row + x, wall.col + y)
                }
            }
            walls.removeAt(randomWallIndex)
        }
    }

    private fun addWallsToList(row: Int, col: Int) {
        if (row >= 1)
            walls.add(Wall(row, col,1))
        if (row <= size.w - 2)
            walls.add(Wall(row, col,2))
        if (col >= 1)
            walls.add(Wall(row, col,3))
        if (col <= size.h - 2)
            walls.add(Wall(row, col,4))
    }

}