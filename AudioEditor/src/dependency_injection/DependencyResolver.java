package dependency_injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DependencyResolver {
    private DependencyContainer container;
    private List<Class> implToDelete = new ArrayList<>();
    public DependencyResolver(DependencyContainer container) {
        this.container = container;
    }
    public Object getService(Class t) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object obj = resolveDependencies(t);
        if (obj != null)
            return obj;
        for (Type tp: implToDelete) {
            Dependency impl = container.get(tp);
            if (impl != null) {
                impl.setImplementation(null);
                impl.setImplemented(false);
            }
        }
        implToDelete.clear();
        return null;
    }
    private Object resolveDependencies(Class t) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Dependency dep = container.get(t);
        if (dep == null)
            return null;
        Class depType = dep.getType();
        Constructor ctor = getAnnotatedConstructor(depType);
        Class[] ctorParametersTypes = ctor.getParameterTypes();
        if (ctorParametersTypes.length == 0) {
            if (dep.isSingleton() && dep.isImplemented())
                return dep.getImplementation();
            Object impl = ctor.newInstance();
            if (dep.isSingleton()) {
                dep.setImplementation(impl);
                dep.setImplemented(true);
            }
            return impl;
        }
        Object[] instParameters = new Object[ctorParametersTypes.length];
        int i = 0;
        for (Class parameterType: ctorParametersTypes) {
            Object instParamater = resolveDependencies(parameterType);
            this.implToDelete.add(parameterType);
            instParameters[i] = instParamater;
            i++;
        }
        try {
            Object impl = ctor.newInstance(instParameters);
            if (dep.isSingleton()) {
                dep.setImplementation(impl);
                dep.setImplemented(true);
            }
            return impl;
        } catch(Exception e) {
            return null;
        }
    }
    private Constructor getAnnotatedConstructor(Class depType) {
        Constructor[] ctors = depType.getConstructors();
        Constructor ctorPlanB = ctors[0];
        for (Constructor ctor: ctors) {
            if (ctor.isAnnotationPresent(Inject.class) || ctor.isAnnotationPresent(Injectable.class)) {
                return ctor;
            }
        }
        return ctorPlanB;
    }
}