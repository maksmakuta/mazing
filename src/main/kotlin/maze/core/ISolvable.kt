package maze.core

interface ISolvable {
    fun solve(maze : IMaze) : List<Pair<Int,Int>>
}