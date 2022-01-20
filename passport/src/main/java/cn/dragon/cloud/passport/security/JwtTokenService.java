package cn.dragon.cloud.passport.security;

import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService implements TokenService {
    @Resource
    private JwtKeyService keyService;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Token generate(UserDetails userDetails) {
        long nowMillis = System.currentTimeMillis();
        Date currentDate = new Date(nowMillis);

        Date expiresDate = new Date(nowMillis+1000*60*60*12);//12*60分钟

        ObjectMapper mapper =new ObjectMapper();
        Map<String,Object> claims = mapper.convertValue(userDetails, HashMap.class);

        String jws = Jwts.builder().compressWith(CompressionCodecs.DEFLATE)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expiresDate)
                .addClaims(claims)
                .signWith(keyService.getPrivateKey()).compact();
        return new JwtToken(jws, userDetails);
    }

    @Override
    public Token loadToken(String token) throws Exception {

        if(StringUtils.isEmpty(token)){
            throw  new ApiException("token 无效");
        }
        String username = null;
        try {
            Jws<Claims> jws =Jwts.parserBuilder().setSigningKey(keyService.getPublicKey()).build().parseClaimsJws(token);
            username = jws.getBody().getSubject();
        }catch (Exception exception){
            throw  new ApiException("token 验证失败");
        }
        if(StringUtils.isEmpty(username)){
            throw  new ApiException("token 无效");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null){
            throw  new ApiException("授权账户不存在");
        }
        return new JwtToken(token, userDetails);
    }
}
