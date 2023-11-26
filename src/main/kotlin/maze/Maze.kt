package maze

import maze.core.IMaze
import maze.core.ISolvable
import maze.enums.Cell

class Maze(
    val w : Int,
    val h : Int
) : IMaze {

    var start = Pair(1,1)
    var finish = Pair(w-2,h-2)

    private val data = Array(w){x ->
        Array(h){y ->
            if((x == 0 || x == w-1)){
                Cell.WALL
            }else if((y == 0 || y == h-1)){
                Cell.WALL
            }else {
                when (Pair(x, y)) {
                    Pair(1, 1) -> Cell.START
                    Pair(w - 2, h - 2) -> Cell.END
                    else -> Cell.EMPTY
                }
            }
        }
    }

    override fun fill(cell: Cell) {
        for(i in 0..<w){
            for(j in 0..<h){
                set(i,j,cell)
            }
        }
    }

    override fun size() = Pair(w,h)

    override fun start(): Pair<Int, Int> {
        return start
    }

    override fun end(): Pair<Int, Int> {
        return finish
    }

    override operator fun set(x: Int, y: Int, cell: Cell) {
        if(x in 0 ..< w && y in 0 ..< h){
            data[x][y] = cell
        }
    }

    override operator fun get(x: Int, y: Int) : Cell {
        return data[x][y]
    }

    override fun solve(solver: ISolvable?) {
        solver?.solve(this)?.forEach { (x,y) ->
            if(Pair(x,y) !in listOf(start(),end()) )
                set(x,y,Cell.PATH)
        }
    }
}