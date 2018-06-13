package com.xiaowei.design;

/**
 * 分布式锁实现：借助第三方系统，常见的实现方式：
 * 1.使用mysql主键唯一性特点，使用CAS的方式向数据库插入数据，能插入成功，说明获取锁成功，否则说明锁被占用，需要等待；
 * 2.使用缓存实现分布式锁，比如使用redis缓存服务器
 * 3.使用zookeeper来保证分布式一致性
 * Created by zhangwei on 2017/6/17.
 */
public class Distribute_lock {
    /**
     * mysql数据库是实现分布式锁
     */
    class mysql_lock {

    }

    /**
     * redis实现分布式锁
     */
    class redis_lock{

    }


    /**
     * zookeeper实现分布式锁
     */

}
