package maze.core

interface ISolvable : SpeedTimer{
    fun solve(maze : IMaze) : List<Pair<Int,Int>>
}