package maze.core

interface IGenerator : ITimer{
    var size : Size
    fun generate(seed : Long) : Maze
}