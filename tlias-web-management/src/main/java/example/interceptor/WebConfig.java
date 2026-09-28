package example.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 配置类，交给Spring容器管理，这样拦截器注册才会生效
public class WebConfig implements WebMvcConfigurer {

  /*
   * //自定义的拦截器对象
   * 
   * @Autowired
   * private DemoInterceptor demoInterceptor;
   * 
   * 
   * @Override
   * public void addInterceptors(InterceptorRegistry registry) {
   * //注册自定义拦截器对象
   * registry.addInterceptor(demoInterceptor).addPathPatterns("/**");//
   * 设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
   * }
   */
  @Autowired
  private TokenInterceptor tokenInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/login"); // 登录接口要放行
  }

}
