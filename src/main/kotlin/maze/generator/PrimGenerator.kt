package maze.generator

import maze.Maze
import maze.core.AGenerator
import maze.core.IMaze
import maze.enums.Cell
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class PrimGenerator(size: Pair<Int, Int>) : AGenerator(size) {

    private var rand = Random()
    private var t = Duration.ZERO
    private val walls: MutableList<Wall> = mutableListOf()

    private data class Wall(val row : Int, val col : Int,val dir : Int)

    override fun generate(seed: Long): IMaze {
        rand = Random(seed)
        val m = Maze(size.first, size.second)
        m.fill(Cell.WALL)
        t = measureTime {
            generateMaze(m)
        }
        m[1,1] = Cell.START
        m[size.first-2, size.second-2] = Cell.END
        return m
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
                t = (((row+x) in 1..size.first - 2) &&( (col+y) in 1..size.second - 2))
            }
        }
        return t
    }

    private fun generateMaze(maze: IMaze) {
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
        if (row <= size.first - 2)
            walls.add(Wall(row, col,2))
        if (col >= 1)
            walls.add(Wall(row, col,3))
        if (col <= size.second - 2)
            walls.add(Wall(row, col,4))
    }

}