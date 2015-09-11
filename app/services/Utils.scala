package services

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.{ByteArrayOutputStream, FileInputStream}
import javax.imageio.ImageIO

/**
 * Some help toolkit for project.
 */
object Utils {

  def resizeImage(is: FileInputStream): Array[Byte] = {
    val os = new ByteArrayOutputStream()

    val sourceImage = ImageIO.read(is)
    val thumbnail = sourceImage.getScaledInstance(200, -1, Image.SCALE_SMOOTH)
    val bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
      thumbnail.getHeight(null),
      BufferedImage.TYPE_INT_RGB)
    bufferedThumbnail.getGraphics.drawImage(thumbnail, 0, 0, null)
    ImageIO.write(bufferedThumbnail, "jpeg", os)
    os.toByteArray
  }
}
