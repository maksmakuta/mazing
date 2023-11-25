package maze.core

import maze.enums.Cell

interface IMaze {
    fun size() : Pair<Int,Int>
    fun start() : Pair<Int,Int>
    fun end() : Pair<Int,Int>
    operator fun set(x : Int,y : Int, cell : Cell)
    operator fun get(x : Int,y : Int) : Cell
    fun fill(cell: Cell)
    fun solve(solver: ISolvable? = null)
}