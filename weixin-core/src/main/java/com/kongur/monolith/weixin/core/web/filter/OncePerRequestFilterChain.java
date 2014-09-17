package com.kongur.monolith.weixin.core.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 执行多个filter，只执行一次
 * 
 * @author zhengwei
 */
public class OncePerRequestFilterChain extends OncePerRequestFilter {

    /**
     * 过滤器
     */
    private Filter[] filters = new Filter[0];

    public void setFilters(List<Filter> fs) {
        if (fs != null && !fs.isEmpty()) {
            filters = fs.toArray(new Filter[fs.size()]);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                                                                                                      throws ServletException,
                                                                                                                      IOException {
        InternalFilterChain internalChain = new InternalFilterChain(this.filters, filterChain);
        internalChain.doFilter(request, response);
    }

    private static final class InternalFilterChain implements FilterChain {

        private Filter[]    fs;

        private FilterChain chain;

        private int         currentIndex = -1;

        private int         filtersSize;

        public InternalFilterChain(Filter[] fs, FilterChain chain) {
            super();
            this.fs = fs;
            this.chain = chain;
            this.filtersSize = fs.length;
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
            currentIndex++;
            if (currentIndex == filtersSize) {
                chain.doFilter(request, response);
            } else {
                fs[currentIndex].doFilter(request, response, this);
            }
        }
    }

}
