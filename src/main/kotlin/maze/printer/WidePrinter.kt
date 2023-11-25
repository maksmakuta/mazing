package maze.printer

import maze.core.IMaze
import maze.core.IPrinter
import maze.enums.Cell

class WidePrinter : IPrinter {

    override fun print(maze: IMaze) {
        val s = maze.size()
        for(i in 0 ..< s.first){
            for(j in 0 ..< s.second){
                print(getCell(maze[i,j]))
            }
            println()
        }
    }

    private fun getCell(cell: Cell) : String{
        return when(cell){
            Cell.WALL   -> "##"
            Cell.START  -> "SS"
            Cell.EMPTY  -> "  "
            Cell.END    -> "EE"
            Cell.PATH   -> "**"
        }
    }

}