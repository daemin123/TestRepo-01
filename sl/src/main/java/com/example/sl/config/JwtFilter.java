//package com.example.sl.config;
//
//import com.example.sl.domain.service.UserService;
//import com.example.sl.utils.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Slf4j
//public class JwtFilter extends OncePerRequestFilter {
//
//        @Autowired
//        private UserService userService;
//        @Autowired
//        private String secretKey;
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//        log.info("authorization : {}",authorization);
//
//        // token이 안보내지면 차단
//        if(authorization == null || !authorization.startsWith("Bearer ")) {
//
//            log.error("authentication을 잘못 보냈습니다.");
//            filterChain.doFilter(request, response);
//            return ;
//        }
//        // Token꺼내기
//        String token = authorization.split(" ")[1];
//
//        // Token EXpired되었는지 여부
//        if(JwtUtil.isExpired(token,secretKey)) {
//            log.error("Token이 만료되었습니다.");
//            filterChain.doFilter(request,response);
//            return ;
//        }
//
//        // UserName Token에서 꺼내기
//        String userName = JwtUtil.getUserName(token,secretKey);
//        log.info("userName : " + userName);
//
//        // 권한 부여
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
//
//        // Detail을 넣어줌
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request,response);
//    }
//}
