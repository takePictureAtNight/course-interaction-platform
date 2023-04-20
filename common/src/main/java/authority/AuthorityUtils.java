package authority;

import exception.RException;
import org.springframework.util.ObjectUtils;

import java.util.*;
public class AuthorityUtils {
    /**权限集合*/
    private static Map<String, Collection<String>> map = new HashMap<>();

    /**过滤权限集合*/
    private static Set<String> filterPermission = new HashSet<>();

    /**全局权限校验类*/
    private static Class c;

    /**
     * 是否开启 @PostMapping 全局校验 默认为false不开启
     */
    private static Boolean postAuthority = false;

    /**
     * 是否开启 全局校验 默认为false不开启
     */
    private static Boolean globalAuthority = false;

    /**
     * 设置是否开启 @PostMapping 全局校验
     * @param state
     * @param o
     */
    public static void setPostAuthority(Boolean state,Object o){
        if (o == null){
            throw new NullPointerException();
        }else if (!(o instanceof AuthorityVerify)){
            throw new ClassCastException(o.getClass()+ "类型不是 AuthorityVerify 实现类");
        }
        postAuthority = state;
        c = o.getClass();
    }

    /**
     * 获取 postAuthority 状态
     * @return
     */
    public static Boolean getPostAuthority(){
        return postAuthority;
    }

    /**
     * 重新初始化全局校验类Class
     */
    public static void cleanVerifyClass(){
        c = null;
    }

    /**
     * 开启全局权限校验类
     * @param state 开关
     * @param z 全局类
     */
    public static void setGlobalAuthority(Boolean state,Object z){

        if (z == null){
            throw new NullPointerException();
        }else if (!(z instanceof AuthorityVerify)){
            throw new ClassCastException(z.getClass()+ "类型不是 AuthorityVerify 实现类");
        }
        globalAuthority = state;
        c = z.getClass();
    }

    /**
     * 获取全局权限校验类
     * @return
     */
    public static Class getGlobalVerify(){
        return c;
    }



    /**
     * 添加权限
     * @param uId 用户id
     * @param authority 权限集合
     */
    public static void setAuthority(String uId,Collection<String> authority){
        map.put(uId,authority);
    }

    /**
     * 校验权限
     * @param uId
     * @param authority
     * @return
     */
    public static Boolean verify(String uId,String authority){
        if (isEmpty(uId)) {
            throw new RException("未初始化权限");
        }
        return map.get(uId).contains(authority);
    }

    /**
     * 排除权限
     * @param permissions
     */
    public static void exclude(String... permissions){
        filterPermission.addAll(Arrays.asList(permissions));
    }

    /**
     * 是否有过滤权限
     * @param permission
     * @return
     */
    public static Boolean filterPermission(String permission){
        return filterPermission.contains(permission);
    }

    /**
     * 判空
     * @param uId
     * @return
     */
    public static Boolean isEmpty(String uId){
        return ObjectUtils.isEmpty(map.get(uId));
    }
}
