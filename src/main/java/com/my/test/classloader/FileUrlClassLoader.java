package com.my.test.classloader;

import java.io.File;
import java.net.*;

/**
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 如果继承URLClassLoader实现，那代码就更简洁了
 */
public class FileUrlClassLoader extends URLClassLoader {

    public FileUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public FileUrlClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String rootDir = "D:/Workspaces/ForIDEA/JavaKnowledgePointTest/src";
        String className = "com.my.test.classloader.DynamicLoadObj";
        File file = new File(rootDir);
        URI uri = file.toURI();
        URL url = uri.toURL();
        URL[] urls = new URL[]{url};
        FileUrlClassLoader fileUrlClassLoader = new FileUrlClassLoader(urls);
        Class<?> aClass = fileUrlClassLoader.loadClass(className);
        System.out.println(aClass.newInstance());
    }
}
