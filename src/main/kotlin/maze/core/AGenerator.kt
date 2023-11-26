package maze.core

interface AGenerator : SpeedTimer{
    var size : Pair<Int,Int>
    fun generate(seed : Long) : IMaze
}