package maze.core

interface IGenerator : ITimer{
    var size : Pair<Int,Int>
    fun generate(seed : Long) : IMaze
}