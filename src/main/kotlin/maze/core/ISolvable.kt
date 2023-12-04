package maze.core

interface ISolvable : ITimer{
    fun solve(maze : IMaze) : List<Pair<Int,Int>>
}