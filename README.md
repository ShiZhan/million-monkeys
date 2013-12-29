million-monkeys
===============
Random text generator: [The Shakespeare Million Monkeys](http://en.wikipedia.org/wiki/Infinite_monkey_theorem)

Also for generating a huge directory tree.

How to use:

1. deploy sbt
2. run `sbt assembly`
3. copy the **MillionMonkeys-assembly-1.0.jar** anywhere and run `java -jar MillionMonkeys-assembly-1.0.jar`

[download](http://goo.gl/if4oeq)

E.g.:

`java -jar MillionMonkeys-assembly-1.0.jar e: 10 10 10 1024`

Which means create a 'test' directory in **e:\**, then create 10 (level 1) sub-directories inside. 
For every level 1 sub-directory, create 10 (level 2) sub-sub-directories, 
each with 10 (level 3) files with random text, and every file is 1024 Bytes long.
