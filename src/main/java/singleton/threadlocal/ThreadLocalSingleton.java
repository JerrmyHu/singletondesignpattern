package singleton.threadlocal;

public class ThreadLocalSingleton {

    private  ThreadLocalSingleton(){

    }

    public static ThreadLocalSingleton getInstance(){
        return threadLocalSingletonThreadLocal.get();
    }


    private static final ThreadLocal<ThreadLocalSingleton> threadLocalSingletonThreadLocal=
        new ThreadLocal<ThreadLocalSingleton>(){

            @Override
            protected ThreadLocalSingleton initialValue() {
                return new ThreadLocalSingleton();
            }
        };
}
