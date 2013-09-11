package pl.edu.icm.ceon.scala_commons

import java.io.File

/**
 * @author Mateusz Fedoryszak (m.fedoryszak@icm.edu.pl)
 */
object files {
  def retrieveFilesByExtension(dir: File, extension: String): Iterable[File] = {
    def toFiles(f: File): Iterable[File] = {
      if (f.isDirectory)
        retrieveFilesByExtension(f, extension)
      else if (f.getName.endsWith("." + extension))
        Some(f)
      else
        None
    }
    for {
      d <- dir.listFiles()
      f <- toFiles(d)
    } yield f
  }
}
