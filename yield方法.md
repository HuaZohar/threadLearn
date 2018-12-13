# yield方法

`作用是放弃当前的CPU资源，将它让给其他的任务去占用CPU执行的时间，但是放弃的时间不确定，有可能刚刚放弃，马上获得CPU时间片。`

代码如下：

#### `自定义线程类1 MyThread1——不使用yield`

```java
package com.thread12;
public class MyThread extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("THREAD1 COST TIME ： " + (endTime - beginTime) + " MS");
    }
}
```

#### `自定义线程类2 MyThread2——使用yield`

```java
package com.thread12;
public class MyThread2 extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            Thread.yield(); //使用yield方法
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("THREAD2 COST TIME ： " + (endTime - beginTime) + " MS");
    }
}
```

#### `启动类`

```java
package com.thread12;

public class Run {
    public static void main(String[] args) {
        //不使用yield
        MyThread myThread = new MyThread();
        myThread.start();

        //使用yield
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
    }
}

```

#### `执行结果`

```
THREAD1 COST TIME ： 18 MS
THREAD2 COST TIME ： 20965 MS
```

