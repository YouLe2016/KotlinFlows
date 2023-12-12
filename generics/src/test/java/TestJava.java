import com.wyl.generics.part1.Point;

import org.junit.Test;

public class TestJava {
    @Test
    public void test() {
        Point<?> point;
        point = new Point<Integer>(3, 3);
        point = new Point<Float>(4.3f, 4.3f);
        point = new Point<Double>(4.3d, 4.90d);
        point = new Point<Long>(12l, 23l);
        point = new Point<String>("", "");
        point = new Point<Object>(new Object(), new Object());
        System.out.println(point);
    }

    public void test2() {
        Point<?> point;
        point = new Point<Integer>(1,1);
        // 以下两行写法报错
        // point = new Point<?>();
        // ? point
    }

    public void test3() {
        Point<? extends Number> point;
        // 这行是可以的，也就是说包括自身
        point = new Point<Number>(3, 3);
        point = new Point<Integer>(3, 3);
        point = new Point<Float>(4.3f, 4.3f);
        point = new Point<Double>(4.3d, 4.90d);
        point = new Point<Long>(12l, 23l);
        // 以下两行报错
        // point = new Point<String>("","");
        // point = new Point<Object>(new Object(),new Object());

        Number x = point.getX();
        Long a = (Long) x;
        // 报错。只可取其中的值，不可修改
        // point.setX(Integer.valueOf(3));
    }
}
