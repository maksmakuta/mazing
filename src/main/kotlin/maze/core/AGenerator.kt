package maze.core

import kotlin.time.Duration

abstract class AGenerator(var size : Pair<Int,Int>) {
    abstract fun generate(seed : Long) : IMaze
    abstract fun time() : Duration
}