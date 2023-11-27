package maze.printer

import maze.core.IMaze
import maze.core.IPrinter
import maze.enums.Cell
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class ImagePrinter : IPrinter {

    override fun print(maze: IMaze) {
        val CELL_SIZE = 10
        val width = maze.size().first * CELL_SIZE
        val height = maze.size().second * CELL_SIZE
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g2d: Graphics2D = image.createGraphics()
        g2d.color = Color.WHITE
        g2d.fillRect(0, 0, width, height)
        for(i in 0 ..< maze.size().first){
            for(j in 0 ..< maze.size().second){
                val cell = maze[i,j]
                val color = when(cell){
                    Cell.PATH   -> Color.RED
                    Cell.WALL   -> Color.BLACK
                    Cell.EMPTY  -> Color.WHITE
                    else -> Color.GRAY
                }
                g2d.color = color
                g2d.fillRect(i* CELL_SIZE,j* CELL_SIZE, CELL_SIZE, CELL_SIZE)
            }
        }
        g2d.dispose()
        try {
            val outputfile = File("${maze.seed}.png")
            ImageIO.write(image, "png", outputfile)
            println("Image saved to: ${outputfile.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}