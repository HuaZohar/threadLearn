# 能停止的线程_使用return停止线程

实现代码如下：

#### `自定义线程类`

```java
package com.thread8;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();

        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了！！");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }
}

```

#### `启动类`

```java
package com.fiberhome.thread8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Run {
    public static void main(String[] args) {

        File f = new File("out.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);

        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!!");
    }
}

```

#### `执行结果`

```text
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698257
timer = 1541148698258
timer = 1541148698258
timer = 1541148698258
timer = 1541148698258
timer = 1541148698258
timer = 1541148698258
end!!
停止了！！
```

#### `结论`

`如上，将interrupt()和return结合使用也能实现停止线程的效果`

###### `不过还是建议使用“抛异常”法来实现线程的停止，因为在catch块中可以对异常的信息进行相关的处理，而且使用异常流能更好、更方便的控制程序的运行流程，不至于代码中出现多个return;造成污染。`

