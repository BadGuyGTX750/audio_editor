import builder_service.AppBuilder;

import static builder_service.AppBuilder.getAppBuilder;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = getAppBuilder();
        appBuilder.mainBuild();
    }
}