package maze.printer

import maze.core.IPrinter
import maze.core.Maze
import maze.enums.Cell

class WidePrinter : IPrinter {

    override fun print(maze: Maze) {
        maze.size.iterate{ i,j ->
            print(getCell(maze[i,j]))
            if(j == maze.height - 1){
                println()
            }
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