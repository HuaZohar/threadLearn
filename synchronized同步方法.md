# synchronized同步方法

## 方法内的变量是线程安全的

### 实验1：

#### 共享变量类

```java
package com.chapter02.thread1;

public class HasSelfPrivateNum {
    public void addI(String username) {
        int num = 0;
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### 自定义线程A

```java
package com.chapter02.thread1;
public class ThreadA extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");
    }
}
```

#### 自定义线程B

```java
package com.chapter02.thread1;
public class ThreadB extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;
    public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }
    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("b");
    }
}
```

#### 启动类

```java
package com.chapter02.thread1;
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        threadA.start();
        ThreadB threadB = new ThreadB(hasSelfPrivateNum);
        threadB.start();
    }
}
```

#### 运行结果

```
a set over!
b set over!
b num = 200
a num = 100
```

#### 结论

方法中的变量不存在非线程安全问题，永远都是线程安全的。

## 实例变量是非线程安全的

### 实验2：

#### 共享变量类

```java
package com.chapter02.thread1;

public class HasSelfPrivateNum {
    //todo 注意此处 synchronized 和 变量
    private int num = 0;
    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### 自定义线程A

```java
package com.chapter02.thread1;
public class ThreadA extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");
    }
}
```

#### 自定义线程B

```java
package com.chapter02.thread1;
public class ThreadB extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("b");
    }
}
```

#### 启动类

```java
package com.chapter02.thread1;

public class Run {
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        threadA.start();

        ThreadB threadB = new ThreadB(hasSelfPrivateNum);
        threadB.start();

    }
}

```

#### 运行结果

```
//如果不添加synchronized
a set over!
b set over!
b num = 200
a num = 200

//如果添加synchronized
a set over!
a num = 100
b set over!
b num = 200
```

#### 结论

在两个线程访问同一个对象中的同步方法时一定是线程安全的。

## 多个对象对个锁

#### 修改实验2代码，修改启动类

```java
package com.chapter02.thread1;

public class Run {
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNumA = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNumA);
        threadA.start();
	    
        HasSelfPrivateNum hasSelfPrivateNumB = new HasSelfPrivateNum();
        ThreadB threadB = new ThreadB(hasSelfPrivateNumB);
        threadB.start();

    }
}
```

#### 输出结果

```
a set over!
b set over!
b num = 200
a num = 100
```

#### 结论

上面的示例 创建了2个HasSelfPrivateNum类的对象，所以产生了2个锁。

