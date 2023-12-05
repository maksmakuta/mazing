package maze.core

import maze.enums.Cell

class Maze(
    val size : Size,
    val seed : Long,
    private var difficulty : Float
){

    private var data = Array(size.w){ Array(size.h) { Cell.EMPTY } }

    private var startPoint = Point(1,1)
    private var endPoint = Point(size.w-2,size.h-2)

    val width = size.w
    val height = size.h

    fun start() = startPoint
    fun end() = endPoint
    fun diff() = difficulty

    fun setStart(p : Point){
        startPoint = p
    }

    fun setEnd(p : Point){
        endPoint = p
    }

    operator fun set(x : Int,y : Int,value : Cell){
        val t = this[x,y]
        if(t in arrayOf(Cell.WALL,Cell.EMPTY,Cell.PATH))
            data[x][y] = value
    }

    operator fun set(p : Point,value : Cell){
        val t = this[p]
        if(t in arrayOf(Cell.WALL,Cell.EMPTY,Cell.PATH))
            data[p.x][p.y] = value
    }

    operator fun get(x: Int,y: Int) : Cell{
        return data[x][y]
    }

    operator fun get(p: Point) : Cell{
        return data[p.x][p.y]
    }

    operator fun contains(point : Point): Boolean {
        val w = point.x in 0 ..< width
        val h = point.y in 0 ..< height
        return w && h
    }

    fun fill(cell : Cell){
        fill(cell,0,0,size.w,size.h)
    }

    fun fill(cell : Cell,x : Int,y : Int,w : Int,h : Int){
        for(i in 0..< w){
            for(j in 0..< h){
                data[x+i][y+j] = cell
            }
        }
    }

    fun solve(solver : ISolver? = null) {
        if (solver != null) {
            val ans = solver.solve(this)
            for (point in ans){
                val c = this[point]
                if(c == Cell.EMPTY){
                    this[point] = Cell.PATH
                }else{
                    println("$point is a ${c.name}")
                }
            }
        }
    }

}