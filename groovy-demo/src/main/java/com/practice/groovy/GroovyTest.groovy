package com.practice.groovy

import org.junit.Test

class GroovyTest {
/**
 * 范围是一系列的值。例如 0..4 表明包含整数 0、1、2、3、4。
 * Groovy 还支持排除范围，0..<4 表示 0、1、2、3。
 * 还可以创建字符范围：'a'..'e' 相当于 a、b、c、d、e。
 *
 */

    @Test
    void test() {
        // 0 1 2 3 4
        for (i in 0..4) {
            print "$i\t"
        }
        // a b c d e
        for (i in 'a'..'e') {
            print "$i\t"
        }
    }

    @Test
    void test1() {
        // 0 1 2 3
        for (i in 0..<4) {
            print "$i\t"
        }
        // a b c d
        for (i in 'a'..<'e') {
            print "$i\t"
        }
    }

    /**
     * Groovy 支持默认参数值，可以在函数或方法的正式定义中指定参数的默认值。调用函数的程序可以选择省略参数，使用默认值。
     * @param val
     * @param repeat
     */

    def repeat(val, repeat = 5) {
        for (i in 0..<repeat) {
            println val
        }
    }

    @Test
    void test3() {
        repeat('hello test3')
    }

    @Test
    void test4() {
        repeat('hello test4', 6)
    }

    /**
     * Groovy 可以直接在语言内使用集合。在 Groovy 中，不需要导入专门的类，也不需要初始化对象。
     * 集合是语言本身的本地成员。Groovy 也使集合（或者列表）的操作变得非常容易，为增加和删除项提供了直观的帮助。
     *
     * groovy中Range是集合
     * groovy中的list默认为ArrayList, 可以使用as运算符指定集合类型
     * groovy 支持用 << 符号向集合中添加数据
     */
    @Test
    void test5() {
        // 把范围当集合
        def range = 0..4
        println range.class     // class groovy.lang.IntRange
        assert range instanceof List   // 证明了range是集合
        // groovy定义集合
        def coll = ["Groovy", "Java", "Ruby"]
        // groovy中的list默认为ArrayList
        assert coll instanceof ArrayList
        assert coll instanceof Collection
        // 可以使用as运算符选择自己想要的类型
        def coll1 = ["Groovy", "Java", "Ruby"] as LinkedList
        assert coll1 instanceof LinkedList
        // 使用 << 符号向集合中添加数据
        coll << "Cotlin"
        println coll.toString()  // [Groovy, Java, Ruby, Cotlin]
        // 可以通过索引直接添加项。
        coll[5] = "Python"
        println coll.toString()  // [Groovy, Java, Ruby, Cotlin, null, Python]

        // Groovy 还允许在集合中增加或去掉集合：
        def numbers = [1, 2, 3, 4]
        assert numbers + 5 == [1, 2, 3, 4, 5]
        assert numbers - [2, 3] == [1, 4]
        //
        assert numbers.join(",") == "1,2,3,4"
        assert [1, 2, 3, 4, 3].count(3) == 2

    }

    /**
     * 声明数组的方式和列表一样，只不过需要显示指定数组类型。
     * 数组的使用方法也和列表类似，只不过由于数组是不可变的，所以不能像数组末尾添加元素。
     */
    @Test
    void test6() {
        Integer[] array = [0, 1, 2, 3, 4]
        assert array instanceof Integer[]
        def array2 = [0, 1, 2, 3, 4] as int[]
        assert array2 instanceof int[]
    }


    /**
     * groovy 中没有字符字面量
     * 可以通过以下方式定义char：
     *      1. 'a' as char
     *      2.  (char) 'a'
     */
    @Test
    void test7() {
        def a = 'a'
        assert a instanceof String
        def b = 'b' as char
        assert b instanceof Character
        def c = (char) 'c'
        assert c.class == Character
    }

    /**
     * 如果使用def关键字声明数字类型，那么这些数字会自动选择可以容纳它们的类型。
     */
    @Test
    void test8() {
        def a = 1
        assert a instanceof Integer

        // Integer.MAX_VALUE
        def b = 2147483647
        assert b instanceof Integer

        // Integer.MAX_VALUE + 1
        def c = 2147483648
        assert c instanceof Long

        // Long.MAX_VALUE
        def d = 9223372036854775807
        assert d instanceof Long

        // Long.MAX_VALUE + 1
        def e = 9223372036854775808
        assert e instanceof BigInteger
    }

    /**
     * map 的定义和list的定义是类似的，只是需要同时定义key 和 value
     * 获取value 可以用 map['key'] 或者 map.key
     */
    @Test
    void test9() {
        def colors = [red: '#FF0000', green: '00FF00', blue: '0000FF']
        assert colors['red'] == '#FF0000'
        assert colors.red == '#FF0000'

        /**
         * 关于Map有一点需要注意。如果将一个变量直接作为Map的key的话，其实Groovy会用该变量的名称作为key，而不是实际的值。
         * 如果需要将变量的值作为key的话，需要在变量上添加小括号。
         */
        def key = 'name'
        def person = [key: 'Guillaume']      //键是key而不是name

        assert !person.containsKey('name')
        assert person.containsKey('key')

        //这次才正确的将key变量的值作为Map的键
        person = [(key): 'Guillaume']

        assert person.containsKey('name')
        assert !person.containsKey('key')
    }

    /**
     * 在Groovy中默认情况下使用点运算符.会引用属性的Getter或Setter。
     * 如果希望直接访问字段，需要使用.@运算符。
     */
    class User {
        public final String name
        User(String name) { this.name = name}
        String getName() { "Name: $name" }
    }
    @Test
    void test10() {
        def user = new User('Bob')
        // .name 调用的是getName()方法
        assert user.name == 'Name: Bob'
        // .@name 是直接访问字段
        assert user.@name == 'Bob'
    }
}
