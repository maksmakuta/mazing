package maze.app

class Size(val w : Int, val h : Int) {

    fun iterate(l : (Point) -> Unit){
        for(x in 0 ..< w){
            for(y in 0 ..< h){
                l(Point(x,y))
            }
        }
    }

}