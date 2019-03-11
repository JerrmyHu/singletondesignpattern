package singleton.lazy;

/**
 * 懒汉式 内部类
 */
public class LazyInnerClassSingleton {


    private static class LazyHolder{
        private static final  LazyInnerClassSingleton lazyInnerClassSingleton
                = new LazyInnerClassSingleton();
    }

    private LazyInnerClassSingleton(){
        if(LazyHolder.lazyInnerClassSingleton != null){
            throw new RuntimeException("不可以创建多实例");
        }

    }

    public static final  LazyInnerClassSingleton getInstance(){
        return LazyHolder.lazyInnerClassSingleton;
    }

}
