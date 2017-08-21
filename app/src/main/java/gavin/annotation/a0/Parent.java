package gavin.annotation.a0;

@AInherited("Inherited")
@BNotInherited("没Inherited")
public class Parent {

    @AInherited("Inherited")
    @BNotInherited("没Inherited")
    public void testOverride() {

    }

    @AInherited("Inherited")
    @BNotInherited("没Inherited")
    public void testNotOverride() {
    }
}