package maze.core

interface ISolver : ITimer{
    fun solve(maze : Maze) : List<Point>
}