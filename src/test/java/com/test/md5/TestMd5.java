package com.test.md5;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 15:42:00
 * 描    述：md5测试类
 */
public class TestMd5 {

    /**
     * 带盐加密
     */
    @Test
    public void ss (){
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        /**
         * md5.encodePassword("代价密密码", "盐");
         */
        String md5Password = md5.encodePassword("admin", "yangliao");
        System.out.println(md5Password);

    }

    /**
     * 带盐判断是否输入正确
     */
    @Test
    public void sss (){
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        /**
         * //判断密码是否输入正确
         *
         *  md5.isPasswordValid("加密后的字符", "未加密的密码", "盐")
         */
        boolean b = md5.isPasswordValid("ea5ee9e27a2cf00e9ee7b2e534b21d03", "admin", "yangliao");
        System.out.println(b);

    }

    ////////////////////////////////////////////////分割线/////////////////////////////////////////////////////

    /**
     * 不带盐加密
     */
    @Test
    public void aa (){
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        /**
         * md5.encodePassword("代价密密码", "盐");
         */
        String md5Password = md5.encodePassword("admin", null);
        System.out.println(md5Password);

    }

    /**
     * 不带盐判断是否输入正确
     */
    @Test
    public void aaa (){
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        /**
         * //判断密码是否输入正确
         *
         *  md5.isPasswordValid("加密后的字符", "未加密的密码", "盐")
         */
        boolean b = md5.isPasswordValid("21232f297a57a5a743894a0e4a801fc3", "admin", null);
        System.out.println(b);

    }


    //////////////////////////////////////////////////////
    public static String saltMD5Encoder(String rawStr){
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        //带盐值的MD5加密
        return encoder.encodePassword(rawStr, "secret");
    }

    public static String standPwdEncoder(String rawStr){
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("my-secret-key");//秘钥值
//        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder.encode(rawStr);
    }

    public static void main(String[] args) {
        System.out.println(standPwdEncoder("admin"));
    }

}
