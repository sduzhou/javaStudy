package aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface UserAccess 
{
    boolean needLogin()      default true; // 是否需要登录
    int authType()           default  0; // 严正用户的方式  0-token 即先登陆在验证  1-单次验证     每条指令需带带密钥   10 11
    int ipIndex()            default 0; //访问的IP地址索引，0-不限制
    int accessRightNo()      default 0;  //访问所需要的权限 0-表示不需要权限 
    boolean  retry()         default false; // 是否需要指令重发检测
    boolean  isUpload()      default false;  //是否是上载文件
    boolean  isDownload()    default false;  //是否为下载文件
    boolean isCustomOutParam() default false; //是否是自定义参会参数
    boolean isRedirect()     default false; //重定向
}
