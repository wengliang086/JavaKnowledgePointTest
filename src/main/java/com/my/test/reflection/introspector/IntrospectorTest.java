package com.my.test.reflection.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IntrospectorTest {

    public static void main(String[] args) {
        Map<String, String> request = new HashMap<>();
        request.put("id", "123");
        request.put("name", "Tom");
        request.put("sex", "true");
        request.put("height", "175.3");

        System.out.println(new Persion());
        System.out.println(Persion.class);

        try {
            Class<?> aClass = Class.forName("com.my.test.reflection.introspector.IntrospectorTest$Persion");
            System.out.println(new IntrospectorTest().toCustomChannelInfo(request, aClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object toCustomChannelInfo(Map<String, String> request, Class<?> clazz) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
        Object obj = clazz.newInstance();
        for (PropertyDescriptor propertyDescriptor : proDescriptors) {
            Method method = propertyDescriptor.getWriteMethod();
            if (method != null) {
                String param = request.get(propertyDescriptor.getName());
                if (param != null) {
                    param = param.trim();
                    try {
                        method.invoke(obj, this.convertObject(param, propertyDescriptor.getPropertyType()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

    private Object convertObject(String param, Class<?> clazz) {
        try {
            if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
                return Integer.valueOf(param);
            } else if (clazz.equals(long.class) || clazz.equals(Long.class)) {
                return Long.valueOf(param);
            } else if (clazz.equals(float.class) || clazz.equals(Float.class))
                return Float.valueOf(param);
            if (clazz.equals(double.class) || clazz.equals(Double.class))
                return Double.valueOf(param);
            if (clazz.equals(boolean.class) || clazz.equals(Boolean.class))
                return Boolean.valueOf(param);
            if (clazz.equals(short.class) || clazz.equals(Short.class))
                return Short.valueOf(param);
            if (clazz.equals(byte.class) || clazz.equals(Byte.class)) {
                return Byte.valueOf(param);
            }
            return param;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class Persion {
        private int id;
        private String name;
        private boolean sex;
        private float height;

        @Override
        public String toString() {
            return "Persion{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sex=" + sex +
                    ", height=" + height +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }
}
