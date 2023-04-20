package authority;

import utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UnifyAuthorityVerify implements AuthorityVerify{
    @Override
    public Boolean authorityVerify(HttpServletRequest request,String... permissions) {

        System.out.println("指定的:"+ Arrays.toString(permissions));
        // 具体权限校验代码
        String uId = JwtUtils.getMemberIdByJwtToken(request);
        for (String permission : permissions) {
            if (!AuthorityUtils.verify(uId,permission)) {
                return false;
            }
        }
        return true;
    }
}
