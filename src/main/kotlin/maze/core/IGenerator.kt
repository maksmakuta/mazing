package maze.core

interface IGenerator : SpeedTimer{
    var size : Pair<Int,Int>
    fun generate(seed : Long) : IMaze
}