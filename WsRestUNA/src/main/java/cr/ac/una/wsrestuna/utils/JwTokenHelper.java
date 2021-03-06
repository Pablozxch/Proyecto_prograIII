/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author cbcar
 */
public class JwTokenHelper {

    private static JwTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 10;
    private static final long EXPIRATION_RENEW_LIMIT = 30;
    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwTokenHelper()
    {
    }

    public static JwTokenHelper getInstance()
    {
        if(jwTokenHelper == null)
        {
            jwTokenHelper = new JwTokenHelper();
        }
        return jwTokenHelper;
    }

    public String generatePrivateKey(String username)
    {
        return AUTHENTICATION_SCHEME + Jwts
                  .builder()
                  .setSubject(username)
                  .setIssuedAt(new Date())
                  .setExpiration(getExpirationDate(false))
                  .claim("rnw" ,
                            (AUTHENTICATION_SCHEME + Jwts
                                      .builder()
                                      .setSubject(username)
                                      .setIssuedAt(new Date())
                                      .setExpiration(getExpirationDate(true))
                                      .signWith(key)
                                      .compact()))
                  .
                  signWith(key)
                  .compact();
    }

    public Claims claimKey(String privateKey) throws ExpiredJwtException , MalformedJwtException        //permite obtener el setsubject, setIssuedAt, setExpiration, etc
    {
        return Jwts
                  .parser() //descifra el token para descifrar si todavia est?? vigente y m??s cosas, si hay lago malo entonces este metodo devuelve si est?? expirado o erroneo
                  .setSigningKey(key)
                  .parseClaimsJws(privateKey)       //mediante la privateKey, si esta es correcta, entonces se obtendr?? todo el body
                  .getBody();
    }

    private Date getExpirationDate(boolean renewal)
    {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        if(renewal)
        {
            expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_RENEW_LIMIT);
        }
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
