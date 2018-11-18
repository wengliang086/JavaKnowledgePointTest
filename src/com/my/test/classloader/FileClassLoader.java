package com.my.test.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 参考：https://blog.csdn.net/javazejian/article/details/73413292
 */
public class FileClassLoader extends ClassLoader {

    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("--- FileClassLoader --> findClass");
        // 获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            //直接生成class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 编写获取class文件并转换为字节码流的逻辑
     *
     * @param name 全类名
     * @return 字节码
     */
    private byte[] getClassData(String name) {
        // 读取类文件的字节
        String path = classNameToPath(name);
        try {
            FileInputStream ins = new FileInputStream(path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesNumRead = 0;
            // 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesNumRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 类文件的完全路径
     */
    private String classNameToPath(String className) {
        return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }

    public static void main(String[] args) {
        String rootDir = "D:/Workspaces/ForIDEA/JavaKnowledgePointTest/src";
        FileClassLoader fileClassLoader = new FileClassLoader(rootDir);
        String className = "com.my.test.classloader.DynamicLoadObj";
        try {
            Class<?> aClass = fileClassLoader.loadClass(className);
            System.out.println(aClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
