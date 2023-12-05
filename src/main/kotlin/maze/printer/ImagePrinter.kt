package maze.printer

import maze.core.IPrinter
import maze.core.Maze
import maze.enums.Cell
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class ImagePrinter : IPrinter {

    companion object{
        val CELL_SIZE = 10
    }

    override fun print(maze: Maze) {
        val width = maze.width * CELL_SIZE
        val height = maze.height * CELL_SIZE
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g2d: Graphics2D = image.createGraphics()
        g2d.color = Color.WHITE
        g2d.fillRect(0, 0, width, height)
        maze.size.iterate{ i,j ->
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
        g2d.dispose()
        try {
            val outputfile = File("${maze.seed()}.png")
            ImageIO.write(image, "png", outputfile)
            println("Image saved to: ${outputfile.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}