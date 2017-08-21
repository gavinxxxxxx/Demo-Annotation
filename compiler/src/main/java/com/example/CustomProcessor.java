package com.example;

import com.google.auto.common.SuperficialValidation;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * 自定义注解处理器 - 编译时注解
 *
 * @author gavin.xiong 2017/8/17
 */
@AutoService(Processor.class) // 谷歌提供的自动注册注解，为你生成注册Processor所需要的格式文件（com.google.auto相关包）。
public class CustomProcessor extends AbstractProcessor {

    private Elements mElementUtils;
    private Types mTypeUtils;
    private Filer mFiler;
    private Messager mMessages;

    /**
     * 注解处理器的初始化
     * 一般在这里获取我们需要的工具类
     *
     * @param env 提供工具类Elements, Types和Filer
     */
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        //Element代表程序的元素，例如包、类、方法。
        mElementUtils = env.getElementUtils();

        //处理TypeMirror的工具类，用于取类信息
        mTypeUtils = env.getTypeUtils();

        //Filer可以创建文件
        mFiler = env.getFiler();

        //错误处理工具
        mMessages = env.getMessager();
    }

    /**
     * 处理器实际处理逻辑入口。
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        for (Element element : env.getElementsAnnotatedWith(Bind.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                // TODO: 2017/8/18
            } catch (Exception e) {
                mMessages.printMessage(Diagnostic.Kind.ERROR, e.toString(), element);
            }
        }


        return false;
    }

    /**
     * 指定java版本 - 一般返回最新版本即可
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 指定注解处理器是注册给哪个注解的，返回指定支持的注解类集合。
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new LinkedHashSet<>();
        /*
         * 大部分 class getName、getCanonicalNam 这两个方法没有什么不同的。
         * 但是对于array或内部类等就不一样了。
         * getName返回的是[[Ljava.lang.String之类的表现形式，
         * getCanonicalName返回的就是跟我们声明类似的形式。
         */
        sets.add(Bind.class.getCanonicalName());
        return sets;
    }

}
