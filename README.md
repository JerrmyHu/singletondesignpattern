#单例各种写法的优缺点及破坏单例的方式

<h1>饿汉式</h1><br>
 &nbsp这种模式是当类加载初始化的时候就将实例创建出来，所以具有执行效率高，没有加载任何锁，而且这会被jvm加载一次，所以具有线程安全的优点，但是由于是一开始就加载初始化，所以导致它不管有没有用都会创建，浪费内存的缺点

<h1>懒汉式</h1><br>
 &nbsp这种模式是只有当被外部类调用的时候才会被加载初始化一个实例，我们都知道饿汉式是空间换时间，那么懒汉式就是时间换空间，所以它具有节省内存的有点。
由于懒汉式只有在被调用的时候才初始化，在线程存在不同步加载，这就导致它有线程不安全的缺点，比如在两个线程都调用getInstance方法，这就可能导致并发问题的缺点，这个缺点可以通过加上synchronized锁、双重检验锁单例写法，内部类单例写法实现线程安全问题

<h1>注册式单例</h1><br>
 &nbsp注册式单例又叫做登记式单例，它将我们实例化出来的每一个实例登记在一个容器或者某个地方，要使用的时候通过唯一标识获取到这个实例，这种单例写法有两种：枚举登记登记式、容器缓存登记式<br>
    &nbsp&nbsp枚举登记式<br>
        &nbsp&nbsp&nbsp这种单例是Effective Java 这本书中非常推荐的一种写法，这种单例是线程安全，而且代码非常简洁就可以完成懒汉式单例中双重检查锁写法的单例。而且枚举式单例可以很好的避免通过反射破坏，序列化破坏我们初始化出来的单例。枚举单例实现是类在进行编译的时候枚举单例在静态代码块中就给INSTANCE进行了赋值，实际上枚举式单例就是饿汉式单例的实现，这是在编译时，进行字节码重组时加上去的<br>
       &nbsp&nbsp&nbsp 枚举式单例可以避免序列化破坏的原因：因为枚举类型是通过类名和Class对象类找到唯一标识的枚举对象，所以枚举对象不可以被类加载器加载多次，就算通过序列化破坏，通过类加载器的时候，也始终只有一个实例。可以通过查看ObjectInputStream中readObject()方法，在readObject（）方法中调用readEum()校验<br>
        &nbsp&nbsp&nbsp枚举式单例可以避免反射破坏的原因:当通过反射的nerInstance()方法时候，会报一个没有找到无参构造方法的异常，java.lang.NoSuchMethodException，通过暴力反射的时候会报一个java.lang.IllegalArgumentException:Cannot reflectively create enum objects,通过查看jdk的Constructor中的newInstance（）方法可以看出，在newInstance方法中做了强制的判断，如果修饰符是Modifier.ENUM枚举类型会直接抛出异常<br>
    &nbsp&nbsp容器缓存式单例<br>
        &nbsp&nbsp&nbsp这种写法是用于创建线程非常多的时候使用，它是非线程安全的，但这种写法有利于对实例的管理，spring中就是使用这种方式注册单例<br>
           
<h1>序列化式单例</h1>
&nbsp这种写法是利用反序列化后的对象后重新分配内存（重新创建对象），再重写 readResolve()方法，返回INSTANCE，就可以实现，这种写法非常简洁，但是从到ObjectInputStream-->readOrdinaryObject()-->readResolve()-->invokeReadResolve()中可以看出反序列化式会在invokeReadResolve（）方法中反射调用readResolveMehtod(),在反序列化中会实例化两次，由于重写了readResolve(),新创建的对象没有返回。所以说创建对象的频率越大，内存分配开销就越大，消耗内存<br>

<h1>ThreadLocal线程单例</h1>
&nbsp这种写法只能保证在单个线程中是唯一的，不能保证它在全局中是唯一的，这种写法是线程安全的，这种写法是将全部对象都放入ThreadLocalMap中，为每个线程提供一个对象，实际上就是空间时间实现线程隔离<br>

<h1>反射破坏单例</h1>
&nbsp这种通过反射clazz.getDeclaredConstructor() 这个方法拿到私有构造方法，从而new一个instance，可以通过在构造方法中抛出异常的做法避免这种破坏<br>

<h1>序列化破坏单例</h1>
&nbsp序列化单例中已经说明

<h1>懒汉式 内部类写法的执行逻辑</h1>
&nbsp当LazyInnerClassSingleton在加载的时候，jvm会优先加载内部类LazyHolder，LazyHolder会先加载，然后在加载LazyInnerClassSingleton，调用LazyInnerClassSingleton中的getInstance（）方法时，内部类LzyHolder中的逻辑才会被执行
