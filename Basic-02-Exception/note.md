## Java异常体系结构

`java.lang.Throwable`

编译时异常/受检异常(checked):
- IOException
  - EOFException
  - FileNotFoundExcetion
  - MalformedURLException
  - UnknownHostException
- ClassNotFoundException
- CloneNotSupportedException
- ...

运行时异常/非受检异常(RuntimeException/unchecked):
- ArithmeticException
- ClassCastException
- IllegalArgumentException`
- IndexOutOfBoundsException
- NoSuchElementException
- NullPointerException
- ...

