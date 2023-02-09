package dependency_injection;

import java.lang.reflect.Type;

public class Dependency {
    private Object implementation;
    private Class dpType;
    private boolean isImplemented;
    private boolean isSingleton;
    public Dependency(Class dpType, boolean isSingleton) {
        this.dpType = dpType;
        this.isSingleton = isSingleton;
    }
    public Object getImplementation() {
        return implementation;
    }
    public void setImplementation(Object implementation) {
        this.implementation = implementation;
    }
    public Class getType() {
        return dpType;
    }
    public boolean isImplemented() {
        return isImplemented;
    }
    public void setImplemented(boolean implemented) {
        this.isImplemented = implemented;
    }
    public boolean isSingleton() {
        return isSingleton;
    }
}
