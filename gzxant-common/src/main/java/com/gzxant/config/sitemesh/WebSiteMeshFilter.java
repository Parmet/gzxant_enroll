package com.gzxant.config.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.stereotype.Component;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: WebSiteMeshFilter
 */
@Component
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    /** 需要装饰的访问路径 */
    private String contentPath = "/**,/404.html";
    
    /** 装饰器页面路径 */
    private String decoratorPath = "/layouts";

    /** 不需要装饰的访问路径,多个之间用英文逗号分隔 */
    private String excludedPaths = "*.js"
    		+ "," + "*.css"
    		+ "," + "/druid/*"
    		+ "," + "/swagger-ui"
    		+ "," + "/login"
    		+ "," + "/**/detail/*"
    		+ "," + "/**/insert"
    		+ "," + "/**/update/*"
    		+ "," + "/front/*"
            + "," + "/api/*";
    
//    private String excludeProject = "portals";
//    
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//    		throws IOException, ServletException {
//    	HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//    	String[] projects = excludeProject.split(",");
//    	boolean excludeFlag = false;
//    	for (int i = 0; i < projects.length; i++) {
//    		if (request.getRequestURL().toString().contains(projects[i].trim())) {
//    			excludeFlag = true;
//    			break;
//    		}
//		}
//    	
//    	if (excludeFlag) {
//    		filterChain.doFilter(request, response);
//    	}
//    	
//    	super.doFilter(servletRequest, servletResponse, filterChain);
//    }
//    
	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // 通过配置文件
        if (contentPath == null) {
            return;
        }
        String[] contentPaths = contentPath.split(",");
        for (String path : contentPaths) {
            builder.addDecoratorPath(path, decoratorPath);
        }

        if (excludedPaths == null) {
            return;
        }
        String[] paths = excludedPaths.split(",");
        for (String path : paths) {
            builder.addExcludedPath(path);
        }
        builder.addTagRuleBundle(new ScriptTagRuleBundle());
    }
}
