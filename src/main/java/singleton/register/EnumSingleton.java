package singleton.register;

public enum EnumSingleton {

    INSTANCE;

    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    private static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
