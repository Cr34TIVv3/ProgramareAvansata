package project.reflection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Optional;


public class Main {
    public static void main(String[] args) throws Exception {
        //getting the file from path (CLI)
        String path = readLine();
        File classFile = new File(path);
        if (classFile.isFile() && getExtensionByStringHandling(classFile.getName()).get().equals("class")) {
            //loading file
            String parentPath = classFile.getParentFile().getPath();
            String packageName = getPackageName(classFile.getParentFile());
            path = "file:///" + path;
            System.out.println(path);
            URLClassLoader loader = URLClassLoader.newInstance(new URL[]{new URL(path)});
            Class clazz = loader.loadClass(packageName + "." + classFile.getName().substring(0,classFile.getName().length() - 6));

            System.out.println("Package: " + clazz.getPackageName());

            System.out.println("Class members: " + Arrays.toString(clazz.getDeclaredFields()));

            System.out.println("Class methods: " + Arrays.toString(clazz.getMethods()));

            Method[] methods= clazz.getMethods();

            Object object = clazz.getConstructor(String.class, Integer.class).newInstance("5", 2);

            for (var method : methods) {
                Annotation[] annotations = method.getAnnotations();

                for (var annotation : annotations) {
                    if (annotation instanceof org.testng.annotations.Test && method.getParameterCount() == 0 ) {
                        System.out.println("----");
                        method.invoke(object);
                    }
                }
            }
        }
        else {
            System.out.println("Wrong file");
        }
    }

    public static String getPackageName(File file) {
        return file.getParentFile().getName() + "." + file.getName();
    }

    public static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static Optional<String> getExtensionByStringHandling(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }
}
