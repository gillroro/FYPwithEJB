package interceptors;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import entity.Employee;

public class EmployeeLoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -8728529572631782065L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
		Employee employee = (Employee) sessionAttributes.get("employee");
		if(employee == null){
			return Action.LOGIN;
		}
		else{
			return invocation.invoke();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}
}