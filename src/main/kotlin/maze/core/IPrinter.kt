package maze.core

interface IPrinter {
    fun print(maze: Maze)

    companion object{
        const val CELL_SIZE = 10
    }
}