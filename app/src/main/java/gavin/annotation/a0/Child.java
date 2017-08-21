package gavin.annotation.a0;

/**
 * Child继承了Parent的AInherited注解
 * BNotInherited因为没有@Inherited声明，不能被继承
 */
public class Child extends Parent {

    /**
     * 重写的testOverride不继承任何注解
     * 因为Inherited不作用在方法上
     */
    @Override
    public void testOverride() {

    }

    /**
     * testNotOverride没有被重写
     * 所以注解AInherited和BNotInherited依然生效。
     */
}