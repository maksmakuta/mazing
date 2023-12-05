package maze.core

class Size(val w : Int, val h : Int) {

    fun iterate(l : (Int,Int) -> Unit){
        for(x in 0 ..< w){
            for(y in 0 ..< h){
                l(x,y)
            }
        }
    }
}