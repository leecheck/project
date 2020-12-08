package com.gytech.Configuration.token;

/**
 * 拦截器接口 HandlerInterceptor
 *
 * @author Li Lu
 * @date 2019-06-21
 */
public interface HandlerInterceptor {

    /**
     * 预处理回调方法,实现处理器的预处理，第三个参数为响应的处理器,
     * 自定义Controller,返回值为true表示继续流程（如调用下一个拦截器或处理器）
     * 或者接着执行postHandle()和afterCompletion()；
     * false表示流程中断，不会继续调用其他的拦截器或处理器，中断执行
     *
     * @return boolean
     */
    public boolean preHandle();

    /**
     * 后处理回调方法，实现处理器的后处理（DispatcherServlet进行视图返回渲染之前进行调用），
     * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，
     * modelAndView也可能为null
     */
    public void postHandle();

    /**
     * 整个请求处理完毕回调方法,该方法也是需要当前对应的Interceptor的preHandle()的返回值为true时才会执行，
     * 也就是在DispatcherServlet渲染了对应的视图之后执行。用于进行资源清理。
     * 整个请求处理完毕回调方法。如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    public void afterCompletion();
}
