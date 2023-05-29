package demo.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LoggedInvocation
public class MethodLogger {
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        System.out.println("Called method: " + ctx.getMethod().getName());
        Object result = ctx.proceed();
        return result;
    }
}
