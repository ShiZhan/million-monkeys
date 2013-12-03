/**
 * Copyright 2013 Zhan Shi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object MillionMonkeys {

  import java.io.{ File, PrintWriter }
  import scala.util.Random

  def randomStr(length: Int) = {
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ Array('\n', ' ')
    (0 to length) map { c => chars(Random.nextInt(chars.length)) } mkString
  }

  def createFile(name: String) = {
    val p = new PrintWriter(new File(name))
    p.print(randomStr(1024))
    p.close
  }

  def createDir(name: String) = new File(name).mkdir

  class Names[T](nList: Seq[String]) {
    def in(parent: String) = nList map { parent + '/' + _ }
    def mkdir = nList foreach createDir
    def create = nList foreach createFile
  }

  implicit def names[T](nList: Seq[String]) = new Names(nList)

  def names(n: Int) = (1 to n) map { i => "%08x".format(i) }

  def main(args: Array[String]) = {
    if (args.length < 2)
      println("usage: MillionMonkeys <target directory> <level 1>[...<level n>]")
    else {
      val root = Seq(args(0) + "/test")
      val levels = args.drop(1).map(_.toInt).toList

      (root /: levels) { (r, l) => r.mkdir; r flatMap { names(l) in _ } }.create

      levels.zipWithIndex.foreach {
        case (l, i) =>
          val levelTotal = (1 /: levels.take(i + 1)) { (r, c) => r * c }
          println("Level " + i + ':' + levelTotal)
      }
    }
  }

}
