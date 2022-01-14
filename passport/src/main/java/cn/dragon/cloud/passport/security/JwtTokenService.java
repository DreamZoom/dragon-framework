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

        Date expiresDate = new Date(nowMillis+1000*60*30);//30分钟

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

    public Token verify(Token token) throws Exception {
        try {
            UserDetails details = this.loadUserDetails(token);
            return new JwtToken(token.getToken(),details);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public UserDetails loadUserDetails(Token token) throws Exception {
        String username = null;
        try {
            Jws<Claims> jws =Jwts.parserBuilder().setSigningKey(keyService.getPublicKey()).build().parseClaimsJws(token.getToken());
            username = jws.getBody().getSubject();
        }catch (Exception exception){
            throw  new ApiException("token 验证失败");
        }
        if(!StringUtils.isEmpty(username)){
            return userDetailsService.loadUserByUsername(username);
        }
        return null;
    }
}
