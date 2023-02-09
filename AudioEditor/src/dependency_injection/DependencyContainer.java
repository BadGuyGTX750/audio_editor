package dependency_injection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DependencyContainer {
    private List<Dependency> dependencies = new ArrayList<>();
    public DependencyContainer() {

    }
    public void add(Class t, boolean isSingleton) {
        dependencies.add(new Dependency(t, isSingleton));
    }
    public Dependency get(Type t) {
        for (Dependency dep: dependencies)
            if (dep.getType() == t)
                return dep;
        return null;
    }
}
